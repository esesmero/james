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
package org.apache.james.bond.server.configure;

import static org.apache.james.bond.shared.BondConst.JAMES_CONF;

import java.io.File;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.james.bond.server.Util;
import org.eclipse.jetty.xml.XmlConfiguration;

/**
 * It access the configuration file and saves the protocol both its common
 * properties and its particular properties using {@link XmlConfiguration}
 */
public abstract class BaseProtocolEditor {

  private final String serviceName;
  private final String xmlPath;
  private final String SERVER;
  private final String ENABLED;
  private final String BIND;
  private final String CONNECTION_BACKLOG;
  private final String CONNECTION_TIMEOUT;
  private final String CONNECTION_LIMIT;
  private final String CONNECTION_LIMIT_PER_IP;
  private final String HELLO_NAME;
  private final String AUTODETECT;

  private final String SOCKET_TLS;
  private final String START_TLS;
  private final String KEYSTORE;
  private final String SECRET;
  private final String PROVIDER;
  private final boolean hasTimeout;

  /**
   * Creates a new {@link BaseProtocolEditor} with the received properties
   * 
   * @param serverElement
   *          the name of the server element in the xml inside the root element
   * @param xmlFile
   *          name of the xmlFile without the extension or the path
   */
  public BaseProtocolEditor(String serverElement, String xmlFile) {
    this(serverElement, xmlFile, true, false);
  }

  /**
   * Creates a new {@link BaseProtocolEditor} with the received properties
   * 
   * @param serverElement
   *          the name of the server element in the xml inside the root element
   * @param xmlFile
   *          name of the xmlFile without the extension or the path
   * @param hasTimeout
   *          true if the server element contains the connectiontimeout
   *          property, false otherwise
   * @param connectionLimAndHelloNameInHandler
   *          true if connectionLimit, connectionLimitPerIP and helloName are
   *          declared inside the handler element in the xml
   */
  public BaseProtocolEditor(String serverElement, String xmlFile,
      boolean hasTimeout, boolean connectionLimAndHelloNameInHandler) {
    this.SERVER = serverElement;
    this.serviceName = xmlFile;
    xmlPath = JAMES_CONF + "/" + xmlFile + ".conf";

    this.hasTimeout = hasTimeout;

    ENABLED = SERVER + "[@enabled]";
    BIND = SERVER + ".bind";
    CONNECTION_BACKLOG = SERVER + ".connectionBacklog";
    CONNECTION_TIMEOUT = SERVER + ".connectiontimeout";

    SOCKET_TLS = SERVER + ".tls[@socketTLS]";
    START_TLS = SERVER + ".tls[@startTLS]";
    KEYSTORE = SERVER + ".tls.keystore";
    SECRET = SERVER + ".tls.secret";
    PROVIDER = SERVER + ".tls.provider";

    if (connectionLimAndHelloNameInHandler) {
      CONNECTION_LIMIT = SERVER + ".handler.connectionLimit";
      CONNECTION_LIMIT_PER_IP = SERVER + ".handler.connectionLimitPerIP";
      HELLO_NAME = SERVER + ".handler.helloName";
      AUTODETECT = SERVER + ".handler.helloName[@autodetect]";
    } else {
      CONNECTION_LIMIT = SERVER + ".connectionLimit";
      CONNECTION_LIMIT_PER_IP = SERVER + ".connectionLimitPerIP";
      HELLO_NAME = SERVER + ".helloName";
      AUTODETECT = SERVER + ".helloName[@autodetect]";
    }
  }

  /**
   * It retrieves all the elements and properties of the protocol, both common
   * and specific
   * 
   * @param protocol
   *          the object to modify with the retrieved information
   * @return the modified received protocol
   * @throws Exception
   */
  protected BaseProtocol readProtocol(BaseProtocol protocol) throws Exception {
    File xmlFile = new File(xmlPath);
    if (!xmlFile.exists()) {
      Util.copyFile(serviceName + "-template.conf", xmlPath);
    }

    XMLConfiguration config = new XMLConfiguration(xmlPath);

    protocol.setEnabled(config.getBoolean(ENABLED, false));
    protocol.setBind(config.getString(BIND));
    protocol.setConnectionBacklog(config.getString(CONNECTION_BACKLOG));
    if (hasTimeout)
      protocol.setConnectionTimeout(config.getString(CONNECTION_TIMEOUT));
    protocol.setConnectionLimit(config.getString(CONNECTION_LIMIT));
    protocol.setConnectionLimitPerIp(config.getString(CONNECTION_LIMIT_PER_IP));

    protocol.setHelloNameAutodetect(config.getBoolean(AUTODETECT, true));

    if (!protocol.isHelloNameAutodetect()) {
      protocol.setHelloNameValue(config.getString(HELLO_NAME));
    }

    protocol.setTlsSocket(config.getBoolean(SOCKET_TLS, false));
    protocol.setTlsStart(config.getBoolean(START_TLS, false));
    protocol.setTlsKeystore(config.getString(KEYSTORE));
    protocol.setTlsSecret(config.getString(SECRET));
    protocol.setTlsProvider(config.getString(PROVIDER));

    readSpecificProperties(config, protocol);

    return protocol;
  }

  /**
   * It retrieves the specific elements and properties of the protocol received
   * 
   * @param config
   *          the {@link XMLConfiguration} to access the xml configuration file
   * @param protocol
   *          the object to modify with the retrieved information
   * @return the modified received protocol
   * @throws Exception
   */
  protected abstract void readSpecificProperties(XMLConfiguration config,
      BaseProtocol protocol);

  /**
   * It saves the protocol, both the general and the specific properties, on the
   * xml configuration file
   * 
   * @param protocol
   *          the protocol with all the information to be saved
   * @throws ConfigurationException
   */
  protected void writeProtocol(BaseProtocol protocol)
      throws ConfigurationException {
    XMLConfiguration config2 = new XMLConfiguration(xmlPath);
    XMLConfiguration config = new XMLConfiguration(config2);
    config.setFileName(xmlPath);

    config.setProperty(ENABLED, protocol.isEnabled());
    config.setProperty(BIND, protocol.getBind());
    config.setProperty(CONNECTION_BACKLOG, protocol.getConnectionBacklog());
    if (hasTimeout)
      config.setProperty(CONNECTION_TIMEOUT, protocol.getConnectionTimeout());
    config.setProperty(CONNECTION_LIMIT, protocol.getConnectionLimit());
    config.setProperty(CONNECTION_LIMIT_PER_IP,
        protocol.getConnectionLimitPerIp());
    config.setProperty(AUTODETECT, protocol.isHelloNameAutodetect());

    if (!protocol.isHelloNameAutodetect()) {
      config.setProperty(HELLO_NAME, protocol.getHelloNameValue());
    }

    config.setProperty(SOCKET_TLS, protocol.isTlsSocket());
    config.setProperty(START_TLS, protocol.isTlsStart());
    config.setProperty(KEYSTORE, protocol.getTlsKeystore());
    config.setProperty(SECRET, protocol.getTlsSecret());
    config.setProperty(PROVIDER, protocol.getTlsProvider());

    addSpecificProperties(config, protocol);

    config.save();

    addHeader();
  }

  /**
   * It saves the specific properties of the protocol on the xml configuration
   * file
   * 
   * @param config
   *          {@link XMLConfiguration} to be able to save the protocol
   *          properties
   * @param protocol
   *          the protocol with all the information to be saved
   * @throws ConfigurationException
   */
  protected abstract void addSpecificProperties(XMLConfiguration config,
      BaseProtocol protocol);

  private void addHeader() {
    try {
      Util.copyFileWithHeader(xmlPath);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
