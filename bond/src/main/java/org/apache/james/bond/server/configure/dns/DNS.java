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
package org.apache.james.bond.server.configure.dns;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Min;

/**
 * Class that describes all the properties to configure a DNS protocol
 */
public class DNS {
  private Integer version;
  private List<String> serversIp = new ArrayList<String>();
  private boolean autodiscover;
  private boolean authoritative;
  private boolean singleIPperMX;
  @Min(value = 0, message = "The maximum cache size has to be a positive integer")
  private String maxCacheSize;

  public DNS() {
  }

  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  public List<String> getServersIp() {
    return serversIp;
  }

  public void setServersIp(List<String> serversIp) {
    this.serversIp = serversIp;
  }

  public void setServerIp(String serverIp) {
    this.serversIp.add(serverIp);
  }

  public boolean isAutodiscover() {
    return autodiscover;
  }

  public void setAutodiscover(boolean autodiscover) {
    this.autodiscover = autodiscover;
  }

  public boolean isAuthoritative() {
    return authoritative;
  }

  public void setAuthoritative(boolean authoritative) {
    this.authoritative = authoritative;
  }

  public boolean isSingleIPperMX() {
    return singleIPperMX;
  }

  public void setSingleIPperMX(boolean singleIPperMX) {
    this.singleIPperMX = singleIPperMX;
  }

  public String getMaxCacheSize() {
    return maxCacheSize;
  }

  public void setMaxCacheSize(String maxCacheSize) {
    this.maxCacheSize = maxCacheSize;
  }
}
