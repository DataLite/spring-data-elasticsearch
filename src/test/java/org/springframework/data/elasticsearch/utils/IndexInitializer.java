/*
 * Copyright 2019-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.data.elasticsearch.utils;

import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.IndexOperations;

/**
 * Utility to initialize indexes.
 *
 * @author Peter-Josef Meisch
 */
public class IndexInitializer {

	private IndexInitializer() {}

	/**
	 * Initialize a fresh index with mappings for {@link Class}. Drops the index if it exists before creation.
	 *
	 * @param operations
	 * @param clazz
	 * @deprecated since 4.0, use {@link IndexInitializer#init(IndexOperations)}
	 */
	@Deprecated
	public static void init(ElasticsearchOperations operations, Class<?> clazz) {
		init(operations.indexOps(clazz));
	}

	/**
	 * Initialize a fresh index with mappings for {@link Class}. Drops the index if it exists before creation.
	 *
	 * @param indexOperations
	 */
	public static void init(IndexOperations indexOperations) {
		indexOperations.delete();
		indexOperations.create();
		indexOperations.putMapping(indexOperations.createMapping());
		indexOperations.refresh();
	}
}
