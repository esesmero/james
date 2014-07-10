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
package org.apache.james.bond.server.configure.imap;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.james.bond.server.configure.BaseProtocol;
import org.apache.james.bond.server.configure.BaseProtocolEditor;
import org.apache.james.bond.shared.BondConst;
import org.eclipse.jetty.xml.XmlConfiguration;

/**
 * It access to the IMAP configuration file and saves the IMAP protocol
 * properties using {@link XmlConfiguration}
 */
public class ImapEditor extends BaseProtocolEditor {
  private static final String imapXmlName = BondConst.NAME_TEMPL_IMAP;
  private static final String IMAP_SERVER = "imapserver";

  private final String COMPRESS;
  private final String MAX_LINE_LENGTH;
  private final String IN_MEMORY_SIZE_LIMIT;

  /**
   * Creates a new {@link ImapEditor} to read and update the IMAP configuration
   */
  public ImapEditor() {
    super(IMAP_SERVER, imapXmlName, false, true);

    COMPRESS = IMAP_SERVER + ".compress";
    MAX_LINE_LENGTH = IMAP_SERVER + ".maxLineLength";
    IN_MEMORY_SIZE_LIMIT = IMAP_SERVER + ".inMemorySizeLimit";
  }

  /**
   * It reads the IMAP protocol information of the xml configuration file and
   * retrieves it
   * 
   * @return the IMAP protocol information
   * @throws Exception
   */
  public Imap readProtocol() throws Exception {
    return (Imap) this.readProtocol(new Imap());
  }

  @Override
  protected void readSpecificProperties(XMLConfiguration config,
      BaseProtocol protocol) {
    Imap imap4 = (Imap) protocol;

    imap4.setCompress(config.getBoolean(COMPRESS, false));
    imap4.setMaxLinelength(config.getString(MAX_LINE_LENGTH, "0"));
    imap4.setInMemSizeLimit(config.getString(IN_MEMORY_SIZE_LIMIT, "0"));
  }

  /**
   * It saves the IMAP protocol, both the general and the specific properties,
   * on the xml configuration file
   * 
   * @param imap
   *          the imap with all the information to be saved
   * @throws ConfigurationException
   */
  public void writeProtocol(Imap imap) throws ConfigurationException {
    super.writeProtocol(imap);
  }

  @Override
  protected void addSpecificProperties(XMLConfiguration config,
      BaseProtocol protocol) {
    Imap imap4 = (Imap) protocol;

    config.setProperty(COMPRESS, imap4.isCompress());
    config.setProperty(MAX_LINE_LENGTH, imap4.getMaxLinelength());
    config.setProperty(IN_MEMORY_SIZE_LIMIT, imap4.getInMemSizeLimit());
  }
}