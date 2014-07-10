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

import static org.apache.james.bond.shared.BondConst.JAMES_CONF;
import static org.apache.james.bond.shared.BondConst.NAME_TEMPL_DNS;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.james.bond.server.Util;
import org.eclipse.jetty.xml.XmlConfiguration;

/**
 * It access the configuration file and saves the properties for the DNS
 * protocol using {@link XmlConfiguration}
 */
public class DNSEditor {

  private static final String dnsXmlPath = JAMES_CONF + "/" + NAME_TEMPL_DNS
      + ".conf";
  private static final String SERVERS = "servers";
  private static final String SERVER = "servers.server";
  private static final String AUTODISCOVER = "autodiscover";
  private static final String AUTHORITATIVE = "authoritative";
  private static final String SINGLE_IP_PER_MX = "singleIPperMX";
  private static final String MAX_CACHE_SIZE = "maxcachesize";

  /**
   * It retrieves all the elements and properties of the DNS protocol
   * 
   * @return the DNS information
   * @throws Exception
   */
  public static DNS readProtocol() throws Exception {
    DNS dns = new DNS();

    File xmlFile = new File(dnsXmlPath);

    if (!xmlFile.exists()) {
      Util.copyFile(NAME_TEMPL_DNS + "-template.conf", dnsXmlPath);
    }

    XMLConfiguration config = new XMLConfiguration(dnsXmlPath);

    @SuppressWarnings("unchecked")
    List<String> servers = config.getList(SERVER);
    dns.setServersIp(servers);
    dns.setAutodiscover(config.getBoolean(AUTODISCOVER, true));
    dns.setAuthoritative(config.getBoolean(AUTHORITATIVE, false));
    dns.setSingleIPperMX(config.getBoolean(SINGLE_IP_PER_MX, false));
    dns.setMaxCacheSize(config.getString(MAX_CACHE_SIZE));

    return dns;
  }

  /**
   * It saves the DNS configutation properties on the xml configuration file
   * 
   * @param dns
   *          the dns protocol with all the information to be saved
   * @throws ConfigurationException
   */
  public static void writeProtocol(DNS dns) throws ConfigurationException,
      FileNotFoundException {
    XMLConfiguration config2 = new XMLConfiguration(dnsXmlPath);
    XMLConfiguration config = new XMLConfiguration(config2);
    config.setFileName(dnsXmlPath);

    // clear servers parent before saving all server nodes to avoid duplicates.
    config.clearTree(SERVERS);
    config.clearProperty(SERVERS);

    for (String server : dns.getServersIp()) {
      config.addProperty(SERVER, server);
    }

    config.setProperty(AUTODISCOVER, dns.isAutodiscover());
    config.setProperty(AUTHORITATIVE, dns.isAuthoritative());
    config.setProperty(SINGLE_IP_PER_MX, dns.isSingleIPperMX());
    config.setProperty(MAX_CACHE_SIZE, dns.getMaxCacheSize());

    config.save();

    addHeader();
  }

  /**
   * It adds a comment on the top of the configuration file indicating that this
   * file has been edited with the Apache James Bond tool
   */
  private static void addHeader() {
    try {
      Util.copyFileWithHeader(dnsXmlPath);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}