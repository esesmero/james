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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.apache.james.bond.server.JamesConnector;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/**
 * This keeps a cache of the domains from the James server
 */
public class DomainCache {

  private static LoadingCache<String, Domain> domainsCache;
  final static int MAXIMUM_SIZE = 1000;

  static {
    domainsCache = CacheBuilder.newBuilder().maximumSize(MAXIMUM_SIZE)
        .build(new CacheLoader<String, Domain>() {

          @Override
          public Map<String, Domain> loadAll(Iterable<? extends String> keys)
              throws Exception {
            return JamesConnector.mapDomains();
          }

          @Override
          public Domain load(String key) throws Exception {
            Map<String, Domain> map = this.loadAll(null);
            return map.get(key);
          }
        });
    try {
      List<Domain> domainsList = JamesConnector.listDomains();
      for (Domain domain : domainsList)
        domainsCache.put(domain.getId(), domain);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * It returns the Domain with the id.
   * 
   * @param id
   * @return
   * @throws ExecutionException
   */
  public Domain get(String id) throws ExecutionException {
    return domainsCache.getIfPresent(id);
  }

  /**
   * It retrieves all the domains from the cache
   * 
   * @return
   * @throws ExecutionException
   */
  public List<Domain> listDomains() throws ExecutionException {
    List<Domain> domainsList = new ArrayList<Domain>(domainsCache.asMap()
        .values());
    return domainsList;
  }

  /**
   * It adds the new domain in both the cache and the server
   * 
   * @param domain
   * @throws Exception
   */
  public void addDomain(Domain domain) throws Exception {
    try {
      domainsCache.put(domain.getId(), domain);
      JamesConnector.addDomain(domain);
    } catch (Exception e) {
      e.printStackTrace();
      domainsCache.invalidate(domain.getId());
      throw e;
    }
  }

  /**
   * It removes the domain from both the cache and the server
   * 
   * @param domain
   * @throws Exception
   */
  public void removeDomain(Domain domain) throws Exception {
    try {
      JamesConnector.removeDomain(domain);
      domainsCache.invalidate(domain.getId());
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
  }
}
