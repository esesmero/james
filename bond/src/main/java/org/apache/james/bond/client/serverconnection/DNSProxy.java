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

import java.util.List;

import org.apache.james.bond.server.configure.dns.DNS;
import org.apache.james.bond.server.configure.dns.DNSLocator;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(value = DNS.class, locator = DNSLocator.class)
public interface DNSProxy extends EntityProxy {
  public List<String> getServersIp();

  public void setServersIp(List<String> serversIp);

  public boolean isAutodiscover();

  public void setAutodiscover(boolean autodiscover);

  public boolean isAuthoritative();

  public void setAuthoritative(boolean authoritative);

  public boolean isSingleIPperMX();

  public void setSingleIPperMX(boolean singleIPperMX);

  public String getMaxCacheSize();

  public void setMaxCacheSize(String maxCacheSize);
}