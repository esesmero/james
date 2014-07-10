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

/**
 * It defines the services that the RequestFactory offers to access the LMTP
 * configuration
 */
public class LmtpService {
  private static LmtpEditor lmtpEditor = new LmtpEditor();

  /**
   * It retrieves the Lmtp Configuration data
   * 
   * @return
   * @throws Exception
   */
  public static Lmtp getAllLmtpData() throws Exception {
    return lmtpEditor.readProtocol();
  }

  /**
   * It saves the LMTP configuration
   * 
   * @param lmtp
   * @throws ConfigurationException
   */
  public void persist(Lmtp lmtp) throws ConfigurationException {
    lmtpEditor.writeProtocol(lmtp);
  }
}
