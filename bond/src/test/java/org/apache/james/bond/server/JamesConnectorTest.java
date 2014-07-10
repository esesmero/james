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
package org.apache.james.bond.server;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.james.bond.server.manage.domain.Domain;
import org.apache.james.bond.server.manage.user.User;
import org.junit.Test;

public class JamesConnectorTest {

  @Test
  public void testAddDeleteUser() {
    try {
      List<User> users = JamesConnector.listUsers();
      User user1234 = new User("user1234@yo.es", "1234");

      if (users.contains(user1234))
        JamesConnector.removeUser(user1234);

      JamesConnector.addUser(user1234);
      users = JamesConnector.listUsers();
      assertTrue(users.contains(user1234));

      JamesConnector.removeUser(user1234);
      users = JamesConnector.listUsers();
      assertFalse(users.contains(user1234));
    } catch (Exception e) {
      System.err.println("James server not reachable");
    }
  }

  @Test
  public void testAddDeleteDomain() {
    try {
      List<Domain> domains = JamesConnector.listDomains();
      Domain domain = new Domain("domain.es");

      if (domains.contains(domain))
        JamesConnector.removeDomain(domain);

      JamesConnector.addDomain(domain);
      domains = JamesConnector.listDomains();
      assertTrue(domains.contains(domain));

      JamesConnector.removeDomain(domain);
      domains = JamesConnector.listDomains();
      assertFalse(domains.contains(domain));
    } catch (Exception e) {
      System.err.println("James server not reachable");
    }
  }
}
