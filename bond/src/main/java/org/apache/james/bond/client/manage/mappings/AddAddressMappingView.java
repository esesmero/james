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
package org.apache.james.bond.client.manage.mappings;

import org.apache.james.bond.client.manage.mappings.ManageMappingsView.Presenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * A {@link DialogBox} that allows the user to add a new address mapping
 */
public class AddAddressMappingView extends DialogBox {
  private Presenter listener;

  @UiField
  TextBox userTextBox;
  @UiField
  TextBox domainTextBox;
  @UiField
  TextBox addressTextBox;
  @UiField
  Button addButton;
  @UiField
  Button cancelButton;

  private static AddAddressMappingViewUiBinder uiBinder = GWT
      .create(AddAddressMappingViewUiBinder.class);

  interface AddAddressMappingViewUiBinder extends
      UiBinder<Widget, AddAddressMappingView> {
  }

  /**
   * It creates a new {@link DialogBox} that is able to add a new address
   * mapping using the {@link Presenter}
   * 
   * @param listener
   */
  public AddAddressMappingView(Presenter listener) {
    this.listener = listener;
    setHTML("Add address mapping");
    setWidget(uiBinder.createAndBindUi(this));
  }

  /**
   * Closes the {@link AddAddressMappingView}
   * 
   * @param e
   */
  @UiHandler("cancelButton")
  void onCancelClick(ClickEvent e) {
    this.hide();
  }

  /**
   * Adds the input mapping and closes the {@link AddAddressMappingView}
   * 
   * @param e
   */
  @UiHandler("addButton")
  void onAddClick(ClickEvent e) {
    listener.addAddressMapping(userTextBox.getText(), domainTextBox.getText(),
        addressTextBox.getText());
    this.hide();
  }
}
