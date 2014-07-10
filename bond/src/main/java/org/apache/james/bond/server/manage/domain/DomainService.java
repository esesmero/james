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

import java.util.List;

/**
 * It defines the services that the RequestFactory offers to access the Domains
 */
public class DomainService {
  private static DomainCache domainCache = new DomainCache();

  /**
   * It retrieves all the domains from the cache
   * 
   * @return
   * @throws Exception
   */
  public static List<Domain> listDomains() throws Exception {
    return domainCache.listDomains();
  }

  /**
   * It saves a new domain
   * 
   * @param domain
   * @throws Exception
   */
  public void persist(Domain domain) throws Exception {
    domainCache.addDomain(domain);
  }

  /**
   * It removes the domain
   * 
   * @param domain
   * @throws Exception
   */
  public void remove(Domain domain) throws Exception {
    domainCache.removeDomain(domain);
  }
}