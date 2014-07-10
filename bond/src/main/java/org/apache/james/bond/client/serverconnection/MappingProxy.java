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
package org.apache.james.bond.client.serverconnection;

import java.util.Set;

import org.apache.james.bond.server.manage.mappings.Mapping;
import org.apache.james.bond.server.manage.mappings.MappingLocator;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(value = Mapping.class, locator = MappingLocator.class)
public interface MappingProxy extends EntityProxy {

  public Integer getVersion();

  public void setId(String id);

  public void setVersion(Integer version);

  public String getUserAndDomain();

  public void setUserAndDomain(String userAndDomain);

  public Set<String> getMappings();

  public void setMappings(Set<String> mappings);
}
