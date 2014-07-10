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

/**
 * It defines the services that the RequestFactory offers to access the Users
 */
public class UserService {
  /**
   * It retrieves all the users from the server
   * 
   * @return
   * @throws Exception
   */
  public static List<User> listUsers() throws Exception {
    return JamesConnector.listUsers();
  }

  /**
   * It adds a new user to the server
   * 
   * @param user
   * @throws Exception
   */
  public void persist(User user) throws Exception {
    JamesConnector.addUser(user);
  }

  /**
   * It removes the user from the server
   * 
   * @param user
   * @throws Exception
   */
  public void remove(User user) throws Exception {
    JamesConnector.removeUser(user);
  }

  /**
   * It changes the password to the user
   * 
   * @param user
   * @throws Exception
   */
  public void changePassword(User user) throws Exception {
    JamesConnector.changePassword(user);
  }
}