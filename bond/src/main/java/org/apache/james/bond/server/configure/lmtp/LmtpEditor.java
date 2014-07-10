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

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.james.bond.server.configure.BaseProtocol;
import org.apache.james.bond.server.configure.BaseProtocolEditor;
import org.apache.james.bond.shared.BondConst;
import org.eclipse.jetty.xml.XmlConfiguration;

/**
 * It access to the LMTP configuration file and saves the LMTP protocol
 * properties using {@link XmlConfiguration}
 */
public class LmtpEditor extends BaseProtocolEditor {
  private static final String lmtpXmlName = BondConst.NAME_TEMPL_LMTP;
  private static final String LMTP_SERVER = "lmtpserver";

  private final String MAX_MESSAGE_SIZE;
  private final String SMTP_GREETING;

  /**
   * Creates a new {@link LmtpEditor} to read and update the LMTP configuration
   */
  public LmtpEditor() {
    super(LMTP_SERVER, lmtpXmlName);

    MAX_MESSAGE_SIZE = LMTP_SERVER + ".maxmessagesize";
    SMTP_GREETING = LMTP_SERVER + ".smtpGreeting";
  }

  /**
   * It reads the LMTP protocol information of the xml configuration file and
   * retrieves it
   * 
   * @return the LMTP protocol information
   * @throws Exception
   */
  public Lmtp readProtocol() throws Exception {
    return (Lmtp) this.readProtocol(new Lmtp());
  }

  @Override
  protected void readSpecificProperties(XMLConfiguration config,
      BaseProtocol protocol) {
    Lmtp lmtp = (Lmtp) protocol;

    lmtp.setMaximumMessageSize(config.getString(MAX_MESSAGE_SIZE, "0"));
    lmtp.setGreeting(config.getString(SMTP_GREETING));
  }

  /**
   * It saves the LMTP protocol, both the general and the specific properties,
   * on the xml configuration file
   * 
   * @param lmtp
   *          the lmtp with all the information to be saved
   * @throws ConfigurationException
   */
  public void writeProtocol(Lmtp lmtp) throws ConfigurationException {
    super.writeProtocol(lmtp);
  }

  @Override
  protected void addSpecificProperties(XMLConfiguration config,
      BaseProtocol protocol) {
    Lmtp lmtp = (Lmtp) protocol;

    config.setProperty(MAX_MESSAGE_SIZE, lmtp.getMaximumMessageSize());
    config.setProperty(SMTP_GREETING, lmtp.getGreeting());
  }
}
