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
package org.apache.james.bond.client.configure.smtp;

import org.apache.james.bond.client.BondHelp;
import org.apache.james.bond.client.configure.BaseProtocolPanel;
import org.apache.james.bond.client.configure.BaseProtocolPanelImpl;
import org.apache.james.bond.client.configure.ConfigureProtocolViewImpl;
import org.apache.james.bond.client.configure.HelloNamePanel;
import org.apache.james.bond.client.configure.TLSPanel;
import org.apache.james.bond.client.serverconnection.SmtpProxy;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;

/**
 * This view shows the SMTP properties to the user, allowing to update and save
 * changes
 */
public class ConfigureSmtpViewImpl extends ConfigureProtocolViewImpl implements
    ConfigureSmtpView {
  private BaseProtocolPanel baseProtocolPanel;
  private Button saveButton;
  private HelloNamePanel helloNamePanel;
  private TLSPanel tlsPanel;
  private ListBox autheticationRequired;
  private CheckBox verifyIdentity;
  private TextBox maxMessageSize;
  private CheckBox heloEhloEnforcement;
  private CheckBox addrBracketsEnforcement;
  private TextBox greeting;
  private TextBox authorizedAddresses;

  private String[] authRequiredValues;

  private Presenter listener;
  private SmtpProxy smtp;

  @Override
  protected void setupTable(FlexTable table) {
    table.setCellSpacing(5);
    FlexCellFormatter cellFormatter = table.getFlexCellFormatter();

    baseProtocolPanel = new BaseProtocolPanelImpl();
    saveButton = new Button("Save");
    helloNamePanel = new HelloNamePanel();
    tlsPanel = new TLSPanel();

    authRequiredValues = new String[] { "false", "announce", "true" };
    autheticationRequired = new ListBox();
    verifyIdentity = new CheckBox();
    maxMessageSize = new TextBox();
    heloEhloEnforcement = new CheckBox();
    addrBracketsEnforcement = new CheckBox();
    greeting = new TextBox();
    authorizedAddresses = new TextBox();

    maxMessageSize.setWidth("50px");
    greeting.setWidth("80px");
    authorizedAddresses.setWidth("80px");

    for (String value : authRequiredValues) {
      autheticationRequired.addItem(value);
    }

    table.setWidget(0, 0, baseProtocolPanel);
    cellFormatter.setRowSpan(0, 0, 7);

    table.setWidget(0, 2, new Label("Authetication required:"));
    table.setWidget(0, 3, autheticationRequired);

    table.setWidget(1, 1, new Label("Verify identity:"));
    table.setWidget(1, 2, verifyIdentity);

    table.setWidget(2, 1, new Label("Maximum message size:"));
    table.setWidget(2, 2, maxMessageSize);

    table.setWidget(3, 1, new Label("HELO/EHLO enforcement:"));
    table.setWidget(3, 2, heloEhloEnforcement);

    table.setWidget(4, 1, new Label("Addresse Brackets Enforcement:"));
    table.setWidget(4, 2, addrBracketsEnforcement);

    table.setWidget(5, 1, new Label("Greeting:"));
    table.setWidget(5, 2, greeting);

    table.setWidget(6, 1, new Label("Authorized addresses:"));
    table.setWidget(6, 2, authorizedAddresses);

    table.setWidget(7, 0, tlsPanel);
    table.setWidget(7, 2, helloNamePanel);

    cellFormatter.setColSpan(7, 2, 2);

    table.setWidget(8, 1, saveButton);

    saveButton.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        clearValidationError();
        clearSavedMessage();
        saveSmtp();
      }
    });
  }

  @Override
  protected void setTitles() {
    baseProtocolPanel.setBindTitle(BondHelp.getSmtpBindHelp());
    autheticationRequired.setTitle(BondHelp.getSmtpAuthRequiredHelp());
    verifyIdentity.setTitle(BondHelp.getSmtpVerifyIdentityHelp());
    maxMessageSize.setTitle(BondHelp.getSmtpMaxMessageSizeHelp());
    heloEhloEnforcement.setTitle(BondHelp.getSmtpHeloEhloEnforcementHelp());
    addrBracketsEnforcement.setTitle(BondHelp
        .getSmtpAddressBracketsEnforcHelp());
    greeting.setTitle(BondHelp.getSmtpGreetingHelp());
    authorizedAddresses.setTitle(BondHelp.getSmtpAuthorizedAddressesHelp());
  }

  @Override
  public void setPresenter(Presenter listener) {
    this.listener = listener;
  }

  /**
   * It sends the {@link SmtpProxy} to the server to save it
   */
  private void saveSmtp() {
    smtp = (SmtpProxy) baseProtocolPanel.getData(smtp);
    smtp.setHelloNameAutodetect(helloNamePanel.getAutodetect());
    smtp.setHelloNameValue(helloNamePanel.getHelloNameValue());
    smtp.setTlsSocket(tlsPanel.getSocket());
    smtp.setTlsStart(tlsPanel.getStart());
    smtp.setTlsKeystore(tlsPanel.getTLSKeystore());
    smtp.setTlsSecret(tlsPanel.getTLSSecret());
    smtp.setTlsProvider(tlsPanel.getTLSProvider());

    smtp.setAuthRequired(autheticationRequired.getValue(autheticationRequired
        .getSelectedIndex()));
    smtp.setVerifyIdentity(verifyIdentity.getValue());
    smtp.setMaximumMessageSize(maxMessageSize.getText());
    smtp.setHeloEhloEnforcement(heloEhloEnforcement.getValue());
    smtp.setAddrBracketsEnforcement(addrBracketsEnforcement.getValue());
    smtp.setGreeting(greeting.getText());
    smtp.setAuthorizedAddresses(authorizedAddresses.getText());

    listener.saveSmtp(smtp);
  }

  public void setAuthRequiredValue(String authRequiredValue) {
    if (authRequiredValue.equals("false"))
      autheticationRequired.setSelectedIndex(0);
    else if (authRequiredValue.equals("announce"))
      autheticationRequired.setSelectedIndex(1);
    else if (authRequiredValue.equals("true"))
      autheticationRequired.setSelectedIndex(2);
  }

  @Override
  public void fillData(SmtpProxy smtp) {
    this.smtp = smtp;

    baseProtocolPanel.fillData(smtp);
    helloNamePanel.setAutodetect(smtp.isHelloNameAutodetect());
    helloNamePanel.setHelloNameValue(smtp.getHelloNameValue());
    tlsPanel.setSocket(smtp.isTlsSocket());
    tlsPanel.setStart(smtp.isTlsStart());
    tlsPanel.setTLSKeystore(smtp.getTlsKeystore());
    tlsPanel.setTLSSecret(smtp.getTlsSecret());
    tlsPanel.setTLSProvider(smtp.getTlsProvider());
    tlsPanel.setTLSAlgorithm(smtp.getTlsAlgorithm());

    setAuthRequiredValue(smtp.getAuthRequired());
    verifyIdentity.setValue(smtp.isVerifyIdentity());
    maxMessageSize.setText(smtp.getMaximumMessageSize());
    heloEhloEnforcement.setValue(smtp.isHeloEhloEnforcement());
    addrBracketsEnforcement.setValue(smtp.isAddrBracketsEnforcement());
    greeting.setText(smtp.getGreeting());
    authorizedAddresses.setText(smtp.getAuthorizedAddresses());
  }
}
