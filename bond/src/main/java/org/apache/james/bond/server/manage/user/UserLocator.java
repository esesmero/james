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
package org.apache.james.bond.server.manage.user;

import java.util.List;

import org.apache.james.bond.server.JamesConnector;

import com.google.web.bindery.requestfactory.shared.Locator;

public class UserLocator extends Locator<User, String> {

  @Override
  public Class<User> getDomainType() {
    return User.class;
  }

  @Override
  public Class<String> getIdType() {
    return String.class;
  }

  @Override
  public User create(Class<? extends User> clazz) {
    return new User();
  }

  @Override
  public User find(Class<? extends User> clazz, String id) {
    List<User> users;
    try {
      users = JamesConnector.listUsers();
      int index;
      if (users != null && (index = users.indexOf(clazz)) > -1)
        return users.get(index);
    } catch (Exception e) {
    }
    return null;
  }

  @Override
  public String getId(User domainObject) {
    return domainObject.getId();
  }

  @Override
  public Object getVersion(User domainObject) {
    return domainObject.getVersion();
  }
}
