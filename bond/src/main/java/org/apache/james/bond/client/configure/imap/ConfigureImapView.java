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
package org.apache.james.bond.client.configure.imap;

import org.apache.james.bond.client.configure.ConfigureProtocolView;
import org.apache.james.bond.client.serverconnection.ImapProxy;

/**
 * It defines the methods to show, edit and save a {@link ImapProxy}
 */
public interface ConfigureImapView extends ConfigureProtocolView {
  /**
   * It sets the {@link Presenter} that manages the view
   * 
   * @param listener
   */
  void setPresenter(Presenter listener);

  /**
   * It fills the view with the information of the {@link ImapProxy}
   * 
   * @param imap
   */
  void fillData(ImapProxy imap);

  /**
   * It defines the behavior of the presenter that manages the
   * {@link ConfigureImapView}
   */
  public interface Presenter {
    /**
     * Retrieves the DNS information from the server and fills the view with it
     */
    public void getAllImapData();

    /**
     * It saves the changes made in the IMAP information
     * 
     * @param imap
     */
    public void saveImap(ImapProxy imap);
  }
}
