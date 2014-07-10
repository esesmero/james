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
package org.apache.james.bond.server.configure.pop3;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.james.bond.server.configure.BaseProtocol;
import org.apache.james.bond.server.configure.BaseProtocolEditor;
import org.apache.james.bond.shared.BondConst;
import org.eclipse.jetty.xml.XmlConfiguration;

/**
 * It access to the Pop3 configuration file and saves the Pop3 protocol
 * properties using {@link XmlConfiguration}
 */
public class Pop3Editor extends BaseProtocolEditor {

  private static final String pop3XmlName = BondConst.NAME_TEMPL_POP3;
  private static final String POP3SERVER = "pop3server(0)";

  /**
   * Creates a new {@link Pop3Editor} to read and update the POP3 configuration
   */
  public Pop3Editor() {
    super(POP3SERVER, pop3XmlName);
  }

  /**
   * It reads the Pop3 protocol information of the xml configuration file and
   * retrieves it
   * 
   * @return the Pop3 protocol information
   * @throws Exception
   */
  public Pop3 readProtocol() throws Exception {
    return (Pop3) this.readProtocol(new Pop3());
  }

  /**
   * It saves the Pop3 protocol, both the general and the specific properties,
   * on the xml configuration file
   * 
   * @param pop3
   *          the pop3 with all the information to be saved
   * @throws ConfigurationException
   */
  public void writeProtocol(Pop3 pop3) throws ConfigurationException {
    super.writeProtocol(pop3);
  }

  @Override
  protected void readSpecificProperties(XMLConfiguration config,
      BaseProtocol protocol) {
  }

  @Override
  protected void addSpecificProperties(XMLConfiguration config,
      BaseProtocol protocol) {
  }
}
