/****************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one   *
 * or more contributor license agreements.  See the NOTICE file *
 * distributed with this work for additional information        *
 * regarding copyright ownership.  The ASF licenses this file   *
 * to you under the Apache License, Version 2.0 (the            *
 * "License"); you may not use this file except in compliance   *
 * with the License.  You may obtain a copy of the License at   *
 *                                                              *
 *   http://www.apache.org/licenses/LICENSE-2.0                 *
 *                                                              *
 * Unless required by applicable law or agreed to in writing,   *
 * software distributed under the License is distributed on an  *
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY       *
 * KIND, either express or implied.  See the License for the    *
 * specific language governing permissions and limitations      *
 * under the License.                                           *
 ****************************************************************/
package org.apache.james.bond.server.manage.domain;

import java.util.concurrent.ExecutionException;

import com.google.web.bindery.requestfactory.shared.Locator;

public class DomainLocator extends Locator<Domain, String> {
	private DomainCache domainsCache = new DomainCache();

	@Override
	public Class<Domain> getDomainType() {
		return Domain.class;
	}

	@Override
	public Class<String> getIdType() {
		return String.class;
	}

	@Override
	public Domain create(Class<? extends Domain> clazz) {
		return new Domain();
	}

	@Override
	public Domain find(Class<? extends Domain> clazz, String id) {
		try {
			return domainsCache.get(id);
		} catch (ExecutionException e) {
			return null;
		}
	}

	@Override
	public String getId(Domain domainObject) {
		return domainObject.getId();
	}

	@Override
	public Object getVersion(Domain domainObject) {
		return domainObject.getVersion();
	}
}
