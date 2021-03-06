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
package ttf.model.property;

import java.util.Map;

/**
 * Groups model properties.
 * 
 * @author Mihai Paraschiv
 * 
 * @param <K>
 *            the type of key managed by the store
 * @param <V>
 *            the type of value managed by the store
 */
public interface PropertyGroup<K, V> extends Map<K, V> {
}
