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
import org.apache.james.bond.client.serverconnection.BaseProtocolProxy;

import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

public class BaseProtocolPanelImpl extends FlexTable implements
    BaseProtocolPanel {
  private CheckBox enabled;
  private TextBox bindPort;
  private TextBox connectionBacklog;
  private TextBox connectionTimeout;
  private TextBox connectionLimit;
  private TextBox connectionLimitPerIp;

  /**
   * Constructs an empty panel with all the widgets needed to show a
   * BaseProtocolProxy.
   */
  public BaseProtocolPanelImpl() {
    this(false);
  }

  /**
   * Constructs an empty panel with all the widgets needed to show a
   * BaseProtocolProxy without the timeout widget.
   * 
   * @param hideTimeout
   */
  public BaseProtocolPanelImpl(boolean hideTimeout) {
    setupTable(hideTimeout);
    setTitles();
  }

  /**
   * Creates the table with a layout to fit all the necessary widgets
   * 
   * @param hideTimeout
   *          true if the timeout widget must not be included, false otherwise
   */
  private void setupTable(boolean hideTimeout) {
    this.setCellSpacing(3);

    enabled = new CheckBox();
    bindPort = new TextBox();
    connectionBacklog = new TextBox();
    connectionTimeout = new TextBox();
    connectionLimit = new TextBox();
    connectionLimitPerIp = new TextBox();

    bindPort.setWidth("80px");
    connectionBacklog.setWidth("50px");
    connectionTimeout.setWidth("50px");
    connectionLimit.setWidth("50px");
    connectionLimitPerIp.setWidth("50px");

    this.setWidget(0, 0, new Label("Enabled:"));
    this.setWidget(0, 1, enabled);

    this.setWidget(1, 0, new Label("Bind port:"));
    this.setWidget(1, 1, bindPort);

    this.setWidget(2, 0, new Label("Connection backlog:"));
    this.setWidget(2, 1, connectionBacklog);

    if (!hideTimeout) {
      this.setWidget(3, 0, new Label("Connection timeout (seconds):"));
      this.setWidget(3, 1, connectionTimeout);
    }

    this.setWidget(4, 0, new Label("Connection limit:"));
    this.setWidget(4, 1, connectionLimit);

    this.setWidget(5, 0, new Label("Connection limit per IP:"));
    this.setWidget(5, 1, connectionLimitPerIp);
  }

  /**
   * It sets the titles with configuration tips for connectionLimit and
   * connectionLimitPerIp
   */
  private void setTitles() {
    enabled.setTitle(BondHelp.getEnabledHelp());
    bindPort.setTitle(BondHelp.getBindPortHelp());
    connectionBacklog.setTitle(BondHelp.getConnectionBacklogHelp());
    connectionTimeout.setTitle(BondHelp.getConnectionTimeoutHelp());
    connectionLimit.setTitle(BondHelp.getConnectionLimitHelp());
    connectionLimitPerIp.setTitle(BondHelp.getConnectionLimitPerIpHelp());
  }

  @Override
  public void setBindTitle(String title) {
    bindPort.setTitle(title);
  }

  @Override
  public void fillData(final BaseProtocolProxy protocol) {
    enabled.setValue(protocol.isEnabled());
    bindPort.setText(protocol.getBind());
    connectionBacklog.setText(protocol.getConnectionBacklog());
    connectionTimeout.setText(protocol.getConnectionTimeout());
    connectionLimit.setText(protocol.getConnectionLimit());
    connectionLimitPerIp.setText(protocol.getConnectionLimitPerIp());
  }

  @Override
  public BaseProtocolProxy getData(BaseProtocolProxy protocol) {
    protocol.setEnabled(enabled.getValue());
    protocol.setBind(bindPort.getValue());
    protocol.setConnectionBacklog(connectionBacklog.getValue());
    protocol.setConnectionTimeout(connectionTimeout.getValue());
    protocol.setConnectionLimit(connectionLimit.getValue());
    protocol.setConnectionLimitPerIp(connectionLimitPerIp.getValue());

    return protocol;
  }
}