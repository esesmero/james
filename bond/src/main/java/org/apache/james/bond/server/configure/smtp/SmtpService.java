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

/**
 * It defines the services that the RequestFactory offers to access the SMTP
 * configuration
 */
public class SmtpService {
  private static SmtpEditor smtpEditor = new SmtpEditor();

  /**
   * It retrieves the Smtp Configuration data
   * 
   * @return
   * @throws Exception
   */
  public static Smtp getAllSmtpData() throws Exception {
    return smtpEditor.readProtocol();
  }

  /**
   * It saves the Smtp configuration
   * 
   * @param smtp
   * @throws ConfigurationException
   */
  public void persist(Smtp smtp) throws ConfigurationException {
    smtpEditor.writeProtocol(smtp);
  }
}
