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

import static org.apache.james.bond.shared.BondConst.JAMES_ADDRESS;
import static org.apache.james.bond.shared.BondConst.JAMES_PORT;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.james.bond.server.manage.domain.Domain;
import org.apache.james.bond.server.manage.mappings.Mapping;
import org.apache.james.bond.server.manage.user.User;
import org.apache.james.cli.probe.ServerProbe;
import org.apache.james.cli.probe.impl.JmxServerProbe;

/**
 * It connects with james-cli to manage users and domains
 */
public abstract class JamesConnector {
  private static ServerProbe serverProbe;

  public static ServerProbe getServerProbe() throws Exception {
    if (serverProbe == null) {
      try {
        serverProbe = new JmxServerProbe(JAMES_ADDRESS, JAMES_PORT);
      } catch (Exception e) {
        throw (e);
      }
    }
    // TODO test that the connection has not been closed.
    return serverProbe;
  }

  /**
   * It retrieves all the users from the server
   *
   * @return
   * @throws Exception
   *           if there is any problem
   */
  public static List<User> listUsers() throws Exception {
    List<User> users = new ArrayList<User>();

    String[] usersArray = getServerProbe().listUsers();
    if (usersArray != null)
      for (String username : usersArray)
        users.add(new User(username));

    return users;
  }
  
  /**
   * It adds a new user
   *
   * @param user
   * @throws Exception
   *           if there is a problem in the connection or the user cannot be
   *           added
   */
  public static void addUser(User user) throws Exception {
    getServerProbe().addUser(user.getUsername(), user.getPassword());
  }

  /**
   * It removes the user from the server
   *
   * @param user
   * @throws Exception
   *           if there is a problem in the connection or the user cannot be
   *           removed
   */
  public static void removeUser(User user) throws Exception {
    getServerProbe().removeUser(user.getUsername());
  }

  /**
   * It substitute the user password for the new one
   *
   * @param user
   * @throws Exception
   *           if there is a problem
   */
  public static void changePassword(User user) throws Exception {
    getServerProbe().setPassword(user.getUsername(), user.getPassword());
  }

  /**
   * It retrieves all the domains from the server
   *
   * @return
   * @throws Exception
   *           if there is any problem
   */
  public static List<Domain> listDomains() throws Exception {
    List<Domain> domains = new ArrayList<Domain>();

    String[] domainsArray = getServerProbe().listDomains();
    if (domainsArray != null)
      for (String domain : domainsArray)
        domains.add(new Domain(domain));

    return domains;
  }

  /**
   * It retrieves all the domain in a {@link Map} from the server
   *
   * @return
   * @throws Exception
   *           if there is any problem
   */
  public static Map<String, Domain> mapDomains() throws Exception {
    Map<String, Domain> domains = new HashMap<String, Domain>();

    String[] domainsArray = getServerProbe().listDomains();
    if (domainsArray != null)
      for (String domain : domainsArray)
        domains.put(domain, new Domain(domain));

    return domains;
  }

  /**
   * It adds a new domain
   *
   * @param domain
   * @throws Exception
   *           if there is a problem in the connection or the domain cannot be
   *           added
   */
  public static void addDomain(Domain domain) throws Exception {
    getServerProbe().addDomain(domain.getDomain());
  }

  /**
   * It removes the domain from the server
   *
   * @param domain
   * @throws Exception
   *           if there is a problem in the connection or the domain cannot be
   *           removed
   */
  public static void removeDomain(Domain domain) throws Exception {
    getServerProbe().removeDomain(domain.getDomain());
  }

  /**
   * It retrieves a Map that contains all mappings. The key is the user@domain
   * and the value is a Collection which holds all mappings for that key
   *
   * @return
   * @throws Exception
   */
  public static Map<String, Collection<String>> getMappings() throws Exception {
    return getServerProbe().listMappings();
  }

  /**
   * It retrieves a Map that contains all mappings. The key is the user@domain
   * and the value is a Collection which holds all mappings for that key
   *
   * @return
   * @throws Exception
   */
  public static List<Mapping> listMappings() throws Exception {
    List<Mapping> list = new ArrayList<Mapping>();
    Map<String, Collection<String>> map = getMappings();

    Collection<String> mappingsCollection;
    Set<String> mappingsSet;
    if (map != null)
      for (String userAndDomain : map.keySet()) {
        mappingsCollection = map.get(userAndDomain);
  
        if (mappingsCollection instanceof Set) {
          mappingsSet = (Set<String>) mappingsCollection;
        } else {
          mappingsSet = new HashSet<String>(mappingsCollection);
        }
  
        list.add(new Mapping(userAndDomain, mappingsSet));
      }

    return list;
  }

  /**
   * It adds an address mapping
   *
   * @param user
   *          username, or null if no username should be used
   * @param domain
   *          domain, or null if no domain should be used
   * @param toAddress
   *          address
   * @throws Exception
   */
  public static void addAddressMapping(String user, String domain,
      String toAddress) throws Exception {
    getServerProbe().addAddressMapping(user, domain, toAddress);
  }

  /**
   * Remove address mapping
   *
   * @param user
   *          username, or null if no username should be used
   * @param domain
   *          domain, or null if no domain should be used
   * @param fromAddress
   *          address
   * @throws Exception
   */
  public static void removeAddressMapping(String user, String domain,
      String fromAddress) throws Exception {
    getServerProbe().removeAddressMapping(user, domain, fromAddress);
  }

  /**
   * Return the explicit mapping stored for the given user and domain or null,
   * if no mapping was found
   *
   * @param user
   *          the username
   * @param domain
   *          the domain
   * @return the collection which holds the mappings, or null if no mapping is
   *         found.
   * @throws Exception
   */
  public static Collection<String> listUserDomainMappings(String user,
      String domain) throws Exception {
    return getServerProbe().listUserDomainMappings(user, domain);
  }

  /**
   * It adds regex mapping
   *
   * @param user
   *          username, or null if no username should be used
   * @param domain
   *          domain, or null if no domain should be used
   * @param regex
   *          the regex
   * @throws Exception
   */
  public static void addRegexMapping(String user, String domain, String regex)
      throws Exception {
    getServerProbe().addRegexMapping(user, domain, regex);
  }

  /**
   * Remove regex mapping for the given user and domain
   *
   * @param user
   *          username, or null if no username should be used
   * @param domain
   *          domain, or null if no domain should be used
   * @param regex
   *          the regex
   * @throws Exception
   */
  public static void removeRegexMapping(String user, String domain, String regex)
      throws Exception {
    getServerProbe().removeRegexMapping(user, domain, regex);
  }
}