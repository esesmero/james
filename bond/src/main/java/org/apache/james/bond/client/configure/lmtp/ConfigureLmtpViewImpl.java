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
package org.apache.james.bond.client.configure.lmtp;

import org.apache.james.bond.client.BondHelp;
import org.apache.james.bond.client.configure.BaseProtocolPanel;
import org.apache.james.bond.client.configure.BaseProtocolPanelImpl;
import org.apache.james.bond.client.configure.ConfigureProtocolViewImpl;
import org.apache.james.bond.client.configure.HelloNamePanel;
import org.apache.james.bond.client.configure.TLSPanel;
import org.apache.james.bond.client.serverconnection.LmtpProxy;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

/**
 * This view shows the LMTP properties to the user, allowing to update and save
 * changes
 */
public class ConfigureLmtpViewImpl extends ConfigureProtocolViewImpl implements
    ConfigureLmtpView {
  private BaseProtocolPanel baseProtocolPanel;
  private Button saveButton;
  private HelloNamePanel helloNamePanel;
  private TLSPanel tlsPanel;
  private TextBox maxMessageSize;
  private TextBox greeting;
  private TextBox authorizedAddresses;

  private Presenter listener;
  private LmtpProxy lmtp;

  @Override
  protected void setupTable(FlexTable table) {
    table.setCellSpacing(5);
    FlexCellFormatter cellFormatter = table.getFlexCellFormatter();

    baseProtocolPanel = new BaseProtocolPanelImpl();
    saveButton = new Button("Save");
    helloNamePanel = new HelloNamePanel();
    tlsPanel = new TLSPanel();

    maxMessageSize = new TextBox();
    greeting = new TextBox();
    authorizedAddresses = new TextBox();

    maxMessageSize.setWidth("50px");
    greeting.setWidth("80px");
    authorizedAddresses.setWidth("80px");

    table.setWidget(0, 0, baseProtocolPanel);
    cellFormatter.setRowSpan(0, 0, 4);

    table.setWidget(0, 2, new Label("Maximum message size:"));
    table.setWidget(0, 3, maxMessageSize);

    table.setWidget(1, 1, new Label("Greeting:"));
    table.setWidget(1, 2, greeting);

    table.setWidget(4, 0, helloNamePanel);
    table.setWidget(2, 1, tlsPanel);

    cellFormatter.setColSpan(2, 1, 2);
    cellFormatter.setRowSpan(2, 1, 3);

    table.setWidget(8, 1, saveButton);

    saveButton.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        clearValidationError();
        clearSavedMessage();
        saveLmtp();
      }
    });
  }

  @Override
  protected void setTitles() {
    baseProtocolPanel.setBindTitle(BondHelp.getLmtpBindHelp());
    maxMessageSize.setTitle(BondHelp.getLmtpMaxMessageSizeHelp());
    greeting.setTitle(BondHelp.getLmtpGreetingsHelp());
  }

  @Override
  public void setPresenter(Presenter listener) {
    this.listener = listener;
  }

  /**
   * It sends the {@link LmtpProxy} to the server to save it
   */
  private void saveLmtp() {
    lmtp = (LmtpProxy) baseProtocolPanel.getData(lmtp);
    lmtp.setHelloNameAutodetect(helloNamePanel.getAutodetect());
    lmtp.setHelloNameValue(helloNamePanel.getHelloNameValue());
    lmtp.setTlsSocket(tlsPanel.getSocket());
    lmtp.setTlsStart(tlsPanel.getStart());
    lmtp.setTlsKeystore(tlsPanel.getTLSKeystore());
    lmtp.setTlsSecret(tlsPanel.getTLSSecret());
    lmtp.setTlsProvider(tlsPanel.getTLSProvider());

    lmtp.setMaximumMessageSize(maxMessageSize.getText());
    lmtp.setGreeting(greeting.getText());

    listener.saveLmtp(lmtp);
  }

  @Override
  public void fillData(LmtpProxy lmtp) {
    this.lmtp = lmtp;

    baseProtocolPanel.fillData(lmtp);
    helloNamePanel.setAutodetect(lmtp.isHelloNameAutodetect());
    helloNamePanel.setHelloNameValue(lmtp.getHelloNameValue());
    tlsPanel.setSocket(lmtp.isTlsSocket());
    tlsPanel.setStart(lmtp.isTlsStart());
    tlsPanel.setTLSKeystore(lmtp.getTlsKeystore());
    tlsPanel.setTLSSecret(lmtp.getTlsSecret());
    tlsPanel.setTLSProvider(lmtp.getTlsProvider());

    maxMessageSize.setText(lmtp.getMaximumMessageSize());
    greeting.setText(lmtp.getGreeting());
  }
}
