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

/**
 * It represents a domain of the server
 */
public class Domain {
  private Integer version;
  private String domain;

  public Domain() {
  }

  public Domain(String domain) {
    this.domain = domain;
  }

  public String getId() {
    return domain;
  }

  public Integer getVersion() {
    if (version == null)
      version = 0;
    return version;
  }

  public void setId(String id) {
    this.domain = id;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  public String getDomain() {
    return domain;
  }

  public void setDomain(String domain) {
    this.domain = domain;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null)
      return false;
    if (obj == this)
      return true;
    if (!(obj instanceof Domain))
      return false;
    Domain domainObj = (Domain) obj;
    return this.getId().equals(domainObj.getId());
  }
}