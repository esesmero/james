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
package org.apache.james.bond.server.manage.mappings;

import java.util.List;

import org.apache.james.bond.server.JamesConnector;

/**
 * It defines the services that the RequestFactory offers to access the Mappings
 */
public class MappingService {
  /**
   * It retrieves all the mappings
   * 
   * @return
   * @throws Exception
   */
  public static List<Mapping> listMappings() throws Exception {
    return JamesConnector.listMappings();
  }

  /**
   * It adds a new address mapping to the user and domain
   * 
   * @param user
   * @param domain
   * @param address
   * @throws Exception
   */
  public static void addAddressMapping(String user, String domain,
      String address) throws Exception {
    JamesConnector.addAddressMapping(user, domain, address);
  }

  /**
   * It adds a new regular expression mapping to the user and domain
   * 
   * @param user
   * @param domain
   * @param regex
   * @throws Exception
   */
  public static void addRegexMapping(String user, String domain, String regex)
      throws Exception {
    JamesConnector.addRegexMapping(user, domain, regex);
  }

  /**
   * It removes the address mapping for the user and domain
   * 
   * @param user
   * @param domain
   * @param address
   * @throws Exception
   */
  public static void removeAddressMapping(String user, String domain,
      String address) throws Exception {
    JamesConnector.removeAddressMapping(user, domain, address);
  }

  /**
   * It removes the regular expression mapping for the user and domain
   * 
   * @param user
   * @param domain
   * @param regex
   * @throws Exception
   */
  public static void removeRegexMapping(String user, String domain, String regex)
      throws Exception {
    JamesConnector.removeRegexMapping(user, domain, regex);
  }
}