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
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * A {@link DialogBox} that allows the confirmation that the mapping must be
 * deleted
 */
public class DeleteConfirmationView extends DialogBox {

  private static DeleteConfirmationViewUiBinder uiBinder = GWT
      .create(DeleteConfirmationViewUiBinder.class);
  private Mapping mapping;
  private Presenter listener;
  @UiField
  Label userLabel;
  @UiField
  Label mappingLabel;
  @UiField
  Button cancelButton;
  @UiField
  Button deleteButton;

  interface DeleteConfirmationViewUiBinder extends
      UiBinder<Widget, DeleteConfirmationView> {
  }

  /**
   * It creates a {@link DeleteConfirmationView} to confirm to delete the
   * mapping through the listener
   * 
   * @param domain
   * @param listener
   */
  public DeleteConfirmationView(Mapping mapping, Presenter listener) {
    this.mapping = mapping;
    this.listener = listener;

    setHTML("Delete mapping");
    setWidget(uiBinder.createAndBindUi(this));
    userLabel.setText(mapping.getUserDomain());
    mappingLabel.setText(mapping.getMapping());
  }

  /**
   * It deletes the mapping from the server and closes the
   * {@link DeleteConfirmationView}
   * 
   * @param e
   */
  @UiHandler("deleteButton")
  void onChangeClick(ClickEvent e) {
    String mappingString = mapping.getMapping();

    if (mappingString.startsWith("regex:")) {
      listener.removeRegexMapping(mapping.getUser(), mapping.getDomain(),
          mappingString.substring(mappingString.indexOf(":") + 1));
    } else {
      listener.removeAddressMapping(mapping.getUser(), mapping.getDomain(),
          mappingString);
    }
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
