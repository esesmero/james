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
package org.apache.james.bond.client.configure.dns;

import org.apache.james.bond.client.Util;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * A {@link DialogBox} that allows the user to insert a new dns ip address
 */
public class AddNewDNS extends DialogBox {

  private ConfigureDNSViewImpl triggerView;

  private static AddNewDNSUiBinder uiBinder = GWT
      .create(AddNewDNSUiBinder.class);

  interface AddNewDNSUiBinder extends UiBinder<Widget, AddNewDNS> {
  }

  /**
   * It creates a new {@link DecoratorPanel} taking the
   * {@link ConfigureDNSViewImpl} that created it as an argument
   * 
   * @param triggerView
   */
  public AddNewDNS(ConfigureDNSViewImpl triggerView) {
    this.triggerView = triggerView;
    setHTML("Add DNS");
    setWidget(uiBinder.createAndBindUi(this));
  }

  @UiField
  Button addButton;
  @UiField
  Button cancelButton;
  @UiField
  TextBox newDnsTextBox;
  @UiField
  Label errorLabel;

  /**
   * Closes the {@link AddNewDNS} when the cancelButton is clicked
   * 
   * @param e
   */
  @UiHandler("cancelButton")
  void onCancelClick(ClickEvent e) {
    this.hide();
  }

  /**
   * When the addButton is clicked, it adds a dns ip address to the
   * {@link ConfigureDNSViewImpl}. If the ip is incorrect, it shows the user an
   * error message
   * 
   * @param e
   */
  @UiHandler("addButton")
  void onClick(ClickEvent e) {
    String ip = newDnsTextBox.getText();
    if (Util.isIPAddress(ip)) {
      triggerView.addServerIp(ip);
      this.hide();
    } else {
      errorLabel.setText("It is not a valid ip");
    }
  }
}
