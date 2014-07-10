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
package org.apache.james.bond.server.configure.smtp;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.james.bond.server.configure.BaseProtocol;
import org.apache.james.bond.server.configure.BaseProtocolEditor;
import org.apache.james.bond.shared.BondConst;
import org.eclipse.jetty.xml.XmlConfiguration;

/**
 * It access to the Smtp configuration file and saves the Smtp protocol
 * properties using {@link XmlConfiguration}
 */
public class SmtpEditor extends BaseProtocolEditor {
  private static final String smtpXmlName = BondConst.NAME_TEMPL_SMTP;
  private static final String SMTP_SERVER = "smtpserver(0)";

  private final String AUTH_REQUIRED;
  private final String VERIFY_IDENTITY;
  private final String MAX_MESSAGE_SIZE;;
  private final String HELO_EHLO_ENFORCEMENT;
  private final String ADDR_BRACKETS_ENFORCEMENT;
  private final String SMTP_GREETING;
  private final String AUTHORIZED_ADDRESSES;
  private final String TLS_ALGORITHM;

  /**
   * Creates a new {@link SmtpEditor} to read and update the SMTP configuration
   */
  public SmtpEditor() {
    super(SMTP_SERVER, smtpXmlName);

    AUTH_REQUIRED = SMTP_SERVER + ".authRequired";
    VERIFY_IDENTITY = SMTP_SERVER + ".verifyIdentity";
    MAX_MESSAGE_SIZE = SMTP_SERVER + ".maxmessagesize";
    HELO_EHLO_ENFORCEMENT = SMTP_SERVER + ".heloEhloEnforcement";
    ADDR_BRACKETS_ENFORCEMENT = SMTP_SERVER + ".addressBracketsEnforcement";
    SMTP_GREETING = SMTP_SERVER + ".smtpGreeting";
    AUTHORIZED_ADDRESSES = SMTP_SERVER + ".authorizedAddresses";
    TLS_ALGORITHM = SMTP_SERVER + ".tls.algorithm";
  }

  /**
   * It reads the Smtp protocol information of the xml configuration file and
   * retrieves it
   * 
   * @return the Smtp protocol information
   * @throws Exception
   */
  public Smtp readProtocol() throws Exception {
    return (Smtp) this.readProtocol(new Smtp());
  }

  @Override
  protected void readSpecificProperties(XMLConfiguration config,
      BaseProtocol protocol) {
    Smtp smtp = (Smtp) protocol;

    smtp.setAuthRequired(config.getString(AUTH_REQUIRED, "false"));
    smtp.setVerifyIdentity(config.getBoolean(VERIFY_IDENTITY, true));
    smtp.setMaximumMessageSize(config.getString(MAX_MESSAGE_SIZE, "0"));
    smtp.setHeloEhloEnforcement(config.getBoolean(HELO_EHLO_ENFORCEMENT, true));
    smtp.setAddrBracketsEnforcement(config.getBoolean(
        ADDR_BRACKETS_ENFORCEMENT, true));
    smtp.setGreeting(config.getString(SMTP_GREETING));
    smtp.setAuthorizedAddresses(config.getString(AUTHORIZED_ADDRESSES));
    smtp.setTlsAlgorithm(config.getString(TLS_ALGORITHM));
  }

  /**
   * It saves the Smtp protocol, both the general and the specific properties,
   * on the xml configuration file
   * 
   * @param smtp
   *          the smtp with all the information to be saved
   * @throws ConfigurationException
   */
  public void writeProtocol(Smtp smtp) throws ConfigurationException {
    super.writeProtocol(smtp);
  }

  @Override
  protected void addSpecificProperties(XMLConfiguration config,
      BaseProtocol protocol) {
    Smtp smtp = (Smtp) protocol;

    config.setProperty(AUTH_REQUIRED, smtp.getAuthRequired());
    config.setProperty(VERIFY_IDENTITY, smtp.isVerifyIdentity());
    config.setProperty(MAX_MESSAGE_SIZE, smtp.getMaximumMessageSize());
    config.setProperty(HELO_EHLO_ENFORCEMENT, smtp.isHeloEhloEnforcement());
    config.setProperty(ADDR_BRACKETS_ENFORCEMENT,
        smtp.isAddrBracketsEnforcement());
    config.setProperty(SMTP_GREETING, smtp.getGreeting());
    config.setProperty(AUTHORIZED_ADDRESSES, smtp.getAuthorizedAddresses());
    config.setProperty(TLS_ALGORITHM, smtp.getTlsAlgorithm());
  }
}
