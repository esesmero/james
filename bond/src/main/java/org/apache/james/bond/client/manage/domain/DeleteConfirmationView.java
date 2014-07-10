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
import org.apache.james.bond.client.serverconnection.DomainProxy;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * A {@link DialogBox} that allows the confirmation that the domain must be
 * deleted
 */
public class DeleteConfirmationView extends DialogBox {

  private static DeleteConfirmationViewUiBinder uiBinder = GWT
      .create(DeleteConfirmationViewUiBinder.class);
  private DomainProxy domain;
  private Presenter listener;
  @UiField
  Label domainLabel;
  @UiField
  Button cancelButton;
  @UiField
  Button deleteButton;

  interface DeleteConfirmationViewUiBinder extends
      UiBinder<Widget, DeleteConfirmationView> {
  }

  /**
   * It creates a {@link DeleteConfirmationView} to confirm to delete the domain
   * through the listener
   * 
   * @param domain
   * @param listener
   */
  public DeleteConfirmationView(DomainProxy domain, Presenter listener) {
    this.domain = domain;
    this.listener = listener;

    setHTML("Delete domain");
    setWidget(uiBinder.createAndBindUi(this));
    domainLabel.setText(domain.getDomain());
  }

  /**
   * It deletes the domain from the server and closes the
   * {@link DeleteConfirmationView}
   * 
   * @param e
   */
  @UiHandler("deleteButton")
  void onChangeClick(ClickEvent e) {
    listener.deleteDomain(domain);
    this.hide();
  }

  /**
   * It closes the {@link DeleteConfirmationView}
   * 
   * @param e
   */
  @UiHandler("cancelButton")
  void onCancelClick(ClickEvent e) {
    this.hide();
  }
}
