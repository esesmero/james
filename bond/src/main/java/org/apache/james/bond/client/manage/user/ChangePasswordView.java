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
package org.apache.james.bond.client.manage.user;

import org.apache.james.bond.client.manage.user.ManageUsersTabView.Presenter;
import org.apache.james.bond.client.serverconnection.UserProxy;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * A {@link DialogBox} that allows the user to change a password
 */

public class ChangePasswordView extends DialogBox {

  private static ChangePasswordViewUiBinder uiBinder = GWT
      .create(ChangePasswordViewUiBinder.class);
  private Presenter listener;
  private UserProxy user;

  interface ChangePasswordViewUiBinder extends
      UiBinder<Widget, ChangePasswordView> {
  }

  /**
   * It creates a new {@link DialogBox} that is able to change the password of
   * the user using the {@link Presenter}
   * 
   * @param user
   * @param listener
   */
  public ChangePasswordView(UserProxy user, Presenter listener) {
    this.user = user;
    this.listener = listener;

    setHTML("Change password");
    setWidget(uiBinder.createAndBindUi(this));
    usernameLabel.setText(user.getUsername());
  }

  @UiField
  Label usernameLabel;
  @UiField
  PasswordTextBox passwordTextBox;
  @UiField
  Button changeButton;
  @UiField
  Button cancelButton;

  /**
   * It changes the password of the user and closes the
   * {@link ChangePasswordView}
   * 
   * @param e
   */
  @UiHandler("changeButton")
  void onChangeClick(ClickEvent e) {
    listener.changePassword(user, passwordTextBox.getText());
    this.hide();
  }

  /**
   * Closes the {@link ChangePasswordView}
   * 
   * @param e
   */
  @UiHandler("cancelButton")
  void onCancelClick(ClickEvent e) {
    this.hide();
  }
}
