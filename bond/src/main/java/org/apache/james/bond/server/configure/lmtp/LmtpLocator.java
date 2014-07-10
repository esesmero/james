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
package org.apache.james.bond.server.configure.lmtp;

import com.google.web.bindery.requestfactory.shared.Locator;

public class LmtpLocator extends Locator<Lmtp, Void> {

  @Override
  public Lmtp create(Class<? extends Lmtp> clazz) {
    return new Lmtp();
  }

  @Override
  public Lmtp find(Class<? extends Lmtp> clazz, Void id) {
    return null;
  }

  @Override
  public Class<Lmtp> getDomainType() {
    return Lmtp.class;
  }

  @Override
  public Void getId(Lmtp domainObject) {
    return null;
  }

  @Override
  public Class<Void> getIdType() {
    return null;
  }

  @Override
  public Object getVersion(Lmtp domainObject) {
    return domainObject.getVersion();
  }
}