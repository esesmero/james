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
package org.apache.james.bond.client.configure.pop3;

import org.apache.james.bond.client.configure.ConfigureProtocolView;
import org.apache.james.bond.client.serverconnection.Pop3Proxy;

/**
 * It defines the methods to show, edit and save a {@link Pop3Proxy}
 */
public interface ConfigurePop3View extends ConfigureProtocolView {
  /**
   * It sets the {@link Presenter} that manages the view
   * 
   * @param listener
   */
  void setPresenter(Presenter listener);

  /**
   * It fills the view with the information of the {@link Pop3Proxy}
   * 
   * @param pop3
   */
  void fillData(Pop3Proxy pop3);

  /**
   * It defines the behavior of the presenter that manages the
   * {@link ConfigurePop3View}
   */
  public interface Presenter {
    /**
     * Retrieves the POP3 information from the server and fills the view with it
     */
    public void getAllPop3Data();

    /**
     * It saves the changes made in the POP3 information
     * 
     * @param pop3
     */
    public void savePop3(Pop3Proxy pop3);
  }
}