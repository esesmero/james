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
package org.apache.james.bond.client.manage.domain;

import org.apache.james.bond.client.manage.domain.ManageDomainsTabView.Presenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * A {@link DialogBox} that allows the user to insert a new dns ip address
 */
public class AddDomainView extends DialogBox implements HasText {

  @UiField
  TextBox domainTextBox;
  @UiField
  Button addButton;
  @UiField
  Button cancelButton;

  private static AddDomainViewUiBinder uiBinder = GWT
      .create(AddDomainViewUiBinder.class);
  private Presenter listener;

  interface AddDomainViewUiBinder extends UiBinder<Widget, AddDomainView> {
  }

  /**
   * It creates a new {@link DialogBox} that is able to add a new domain using
   * the {@link Presenter}
   * 
   * @param listener
   */
  public AddDomainView(Presenter listener) {
    this.listener = listener;
    setHTML("Add domain");
    setWidget(uiBinder.createAndBindUi(this));
  }

  /**
   * Closes the {@link AddDomainView}
   * 
   * @param e
   */
  @UiHandler("cancelButton")
  void onCancelClick(ClickEvent e) {
    this.hide();
  }

  /**
   * Adds the domain and closes the {@link AddDomainView}
   * 
   * @param e
   */
  @UiHandler("addButton")
  void onAddClick(ClickEvent e) {
    listener.addDomain(domainTextBox.getText());
    this.hide();
  }
}