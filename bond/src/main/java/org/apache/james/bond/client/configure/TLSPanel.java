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

import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

/**
 * It is a {@link DecoratorPanel} that contains the widgets necessary to show
 * and edit the TLS properties
 */
public class TLSPanel extends DecoratorPanel {

  private CheckBox socketCheckBox;
  private CheckBox startCheckBox;
  private TextBox keystoreTextBox;
  private TextBox secretTextBox;
  private TextBox providerTextBox;
  private TextBox algorithm;

  private FlexTable table;

  /**
   * Creates a {@link DecoratorPanel} with the widgets that hold the TLS
   * properties
   */
  public TLSPanel() {
    table = new FlexTable();

    socketCheckBox = new CheckBox();
    startCheckBox = new CheckBox();
    keystoreTextBox = new TextBox();
    secretTextBox = new TextBox();
    providerTextBox = new TextBox();
    algorithm = new TextBox();

    table.setWidget(0, 0, new Label("TLS"));

    table.setWidget(1, 0, new Label("Socket"));
    table.setWidget(1, 1, socketCheckBox);

    table.setWidget(2, 0, new Label("Start"));
    table.setWidget(2, 1, startCheckBox);

    table.setWidget(3, 0, new Label("Keystore"));
    table.setWidget(3, 1, keystoreTextBox);

    table.setWidget(4, 0, new Label("Secret"));
    table.setWidget(4, 1, secretTextBox);

    table.setWidget(5, 0, new Label("Provider"));
    table.setWidget(5, 1, providerTextBox);

    this.add(table);

    this.setTitle(BondHelp.getTLSHelp());
    keystoreTextBox.setTitle(BondHelp.getTLSKeystoreHelp());
    algorithm.setTitle(BondHelp.getTLSAlgorithmHelp());
  }

  public void setTLSAlgorithm(String algorithm) {
    this.algorithm.setValue(algorithm);

    table.setWidget(6, 0, new Label("Algorithm"));
    table.setWidget(6, 1, this.algorithm);
  }

  public void setSocket(boolean socket) {
    this.socketCheckBox.setValue(socket);
  }

  public void setStart(boolean start) {
    this.startCheckBox.setValue(start);
  }

  public void setTLSKeystore(String keystore) {
    this.keystoreTextBox.setValue(keystore);
  }

  public void setTLSSecret(String secret) {
    this.secretTextBox.setValue(secret);
  }

  public void setTLSProvider(String provider) {
    this.providerTextBox.setValue(provider);
  }

  public boolean getSocket() {
    return socketCheckBox.getValue();
  }

  public boolean getStart() {
    return this.startCheckBox.getValue();
  }

  public String getTLSKeystore() {
    return this.keystoreTextBox.getValue();
  }

  public String getTLSSecret() {
    return this.secretTextBox.getValue();
  }

  public String getTLSProvider() {
    return this.providerTextBox.getValue();
  }
}
