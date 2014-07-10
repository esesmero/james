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

import org.apache.james.bond.client.BondHelp;
import org.apache.james.bond.client.CommonTabsViewImpl;
import org.apache.james.bond.client.configure.dns.ConfigureDNSView;
import org.apache.james.bond.client.configure.imap.ConfigureImapView;
import org.apache.james.bond.client.configure.lmtp.ConfigureLmtpView;
import org.apache.james.bond.client.configure.pop3.ConfigurePop3View;
import org.apache.james.bond.client.configure.smtp.ConfigureSmtpView;

/**
 * This class defines the view to configure all the protocols allowing to add
 * the different tabs for each protocol
 */
public class ConfigureViewImpl extends CommonTabsViewImpl implements
    ConfigureView {
  /**
   * {@inheritDoc}
   */
  public ConfigureViewImpl(String place) {
    super(place);
  }

  @Override
  public void setDnsView(ConfigureDNSView configureDNSView) {
    addTab(configureDNSView, "DNS", BondHelp.getDnsServiceHelp());
  }

  @Override
  public void setPop3View(ConfigurePop3View configurePop3View) {
    addTab(configurePop3View, "POP3", BondHelp.getPop3ServiceHelp());
  }

  @Override
  public void setSmtpView(ConfigureSmtpView configureSmtpView) {
    addTab(configureSmtpView, "SMTP", BondHelp.getSmtpServiceHelp());
  }

  @Override
  public void setLmtpView(ConfigureLmtpView configureLmtpView) {
    addTab(configureLmtpView, "LMTP", BondHelp.getLmtpServiceHelp());
  }

  @Override
  public void setImapView(ConfigureImapView configureImapView) {
    addTab(configureImapView, "IMAP", BondHelp.getImapServiceHelp());
  }
}