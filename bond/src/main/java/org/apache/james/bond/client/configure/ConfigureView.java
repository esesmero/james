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
package org.apache.james.bond.client.configure;

import org.apache.james.bond.client.CommonTabsView;
import org.apache.james.bond.client.configure.dns.ConfigureDNSView;
import org.apache.james.bond.client.configure.imap.ConfigureImapView;
import org.apache.james.bond.client.configure.lmtp.ConfigureLmtpView;
import org.apache.james.bond.client.configure.pop3.ConfigurePop3View;
import org.apache.james.bond.client.configure.smtp.ConfigureSmtpView;

/**
 * It defines the methods necessary to add all the necessary tabs to configure
 * the server
 */
public interface ConfigureView extends CommonTabsView {
  /**
   * It adds the {@link ConfigureDNSView}
   * 
   * @param configureDNSView
   */
  public void setDnsView(ConfigureDNSView configureDNSView);

  /**
   * It adds the {@link ConfigurePop3View}
   * 
   * @param configurePop3View
   */
  public void setPop3View(ConfigurePop3View configurePop3View);

  /**
   * It adds the {@link ConfigureSmtpView}
   * 
   * @param configureSmtpView
   */
  public void setSmtpView(ConfigureSmtpView configureSmtpView);

  /**
   * It adds the {@link ConfigureLmtpView}
   * 
   * @param configureLmtpView
   */
  public void setLmtpView(ConfigureLmtpView configureLmtpView);

  /**
   * It adds the {@link ConfigureImapView}
   * 
   * @param configureImapView
   */
  public void setImapView(ConfigureImapView configureImapView);
}
