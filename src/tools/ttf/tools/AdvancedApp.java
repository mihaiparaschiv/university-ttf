/*
 * Copyright 2010 Mihai Paraschiv
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ttf.tools;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.chain.Command;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ttf.analysis.AnalysisController;
import ttf.analysis.command.EntityDetectionCommand;
import ttf.analysis.command.ModelPersistenceCommand;
import ttf.analysis.command.TopicLoadingCommand;
import ttf.analysis.command.TopicSelectionCommand;
import ttf.analysis.command.TopicUpdateCommand;
import ttf.analysis.context.ContextFactory;
import ttf.analysis.input.InternalProvider;
import ttf.analysis.processor.ChainProcessor;
import ttf.incoming.BasicTransformer;
import ttf.incoming.FeedInfo;
import ttf.incoming.IncomingArticle;
import ttf.incoming.IncomingArticleListRSH;
import ttf.incoming.Transformer;
import ttf.model.article.Article;
import ttf.test.TestUtil;
import ttf.util.AppContext;

import com.sun.syndication.io.FeedException;

public class AdvancedApp {
    private static final String INCOMINGARTICLES = "IncomingArticles";
    private static final String SOURCES = "sources";

    private final Log log = LogFactory.getLog(BasicApp.class);
    private final Configuration configuration;
    private final ContextFactory contextFactory;
    private final Transformer transformer;
    private final DataSource dataSource;

    public AdvancedApp(Configuration configuration, ContextFactory contextFactory, DataSource dataSource,
            Transformer transformer) {
        this.configuration = configuration;
        this.contextFactory = contextFactory;
        this.transformer = transformer;
        this.dataSource = dataSource;
    }

    public static void main(String[] args) throws IllegalArgumentException, FeedException, IOException,
            ConfigurationException, SQLException {
        Configuration config = TestUtil.getDefaultConfiguration();
        AppContext appContext = AppContext.build(config);

        Transformer transformer = new BasicTransformer(appContext.getArticleFactory());

        AdvancedApp app = new AdvancedApp(config, appContext.getContextFactory(), appContext.getDataSource(),
                transformer);
        app.run();
    }

    public void run() throws IllegalArgumentException, FeedException, IOException, SQLException {
        // setup parameters
        double minSimilarity = configuration.getDouble("analysis.minSimilarity");

        // setup commands
        List<Command> commands = new LinkedList<Command>();
        commands.add(new EntityDetectionCommand());
        commands.add(new TopicLoadingCommand());
        commands.add(new TopicSelectionCommand(minSimilarity));
        commands.add(new TopicUpdateCommand());
        commands.add(new ModelPersistenceCommand());

        QueryRunner run = new QueryRunner(dataSource);
        String queryInterval = "SELECT min(retrievalInterval) FROM " + SOURCES;
        String queryArticles = "SELECT title, author, publishedAt, discoveredAt, address, content FROM "
                + INCOMINGARTICLES + " WHERE processed = 0";
        String updateArticle = "UPDATE " + INCOMINGARTICLES + " SET processed = 1 WHERE address = ?";

        // Read update interval
        long updateInterval = 0;
        Integer interval = (Integer)run.query(queryInterval, new ScalarHandler());
        if (interval != null)
            updateInterval = ((Integer)interval) * FeedInfo.MINUTE;
        
        while (updateInterval > 0) {
            // Get unprocessed incoming articles
            InternalProvider provider = new InternalProvider();
            List<IncomingArticle> incomingArticles = run.query(queryArticles, new IncomingArticleListRSH());
            
            for (IncomingArticle incomingArticle : incomingArticles) {
                Article article = transformer.transform(incomingArticle);
                provider.add(article);            
            }

            process(commands, provider);
            
            // Mark processed
            for (IncomingArticle incomingArticle : incomingArticles) {
                run.update(updateArticle, incomingArticle.getAddress());
            }
                        
            try {
                Thread.sleep(updateInterval);
            } catch (InterruptedException e) {
            }
        }
    }

    private void process(List<Command> commands, InternalProvider provider) {
        ChainProcessor processor = new ChainProcessor(contextFactory, commands);
        AnalysisController controller = new AnalysisController(provider, processor);
        controller.run();
    }
}