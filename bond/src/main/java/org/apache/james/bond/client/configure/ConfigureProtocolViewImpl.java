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

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * This class creates a table layout with a {@link FlexTable} to show and edit
 * all the information to configure a protocol
 */
public abstract class ConfigureProtocolViewImpl extends Composite implements
    ConfigureProtocolView {

  private static ConfigureTabViewImplUiBinder uiBinder = GWT
      .create(ConfigureTabViewImplUiBinder.class);

  interface ConfigureTabViewImplUiBinder extends
      UiBinder<Widget, ConfigureProtocolViewImpl> {
  }

  /**
   * It creates a {@link ConfigureProtocolViewImpl} with a {@link FlexTable}
   */
  public ConfigureProtocolViewImpl() {
    table = new FlexTable();
    setupTable(table);
    initWidget(uiBinder.createAndBindUi(this));
    setTitles();
    clearValidationError();
    clearSavedMessage();
  }

  /**
   * It attaches the help information of the different views as titles
   */
  protected abstract void setTitles();

  @UiField(provided = true)
  FlexTable table;
  @UiField
  Label errorLabel;
  @UiField
  Label savedLabel;
  @UiField
  HTMLPanel protocolPanel;

  /**
   * It adds all the widgets to the {@link FlexTable}
   * 
   * @param table
   */
  protected abstract void setupTable(FlexTable table);

  @Override
  public void publishValidationError(String msg) {
    errorLabel.setText(msg);
    errorLabel.setVisible(true);
  }

  @Override
  public void publishSaved() {
    savedLabel.setText("Saved");
    savedLabel.setVisible(true);
    new Timer() {
      public void run() {
        clearSavedMessage();
      }
    }.schedule(4000);
  }

  @Override
  public void clearValidationError() {
    errorLabel.setText("");
    errorLabel.setVisible(false);
  }

  @Override
  public void clearSavedMessage() {
    savedLabel.setText("");
    savedLabel.setVisible(false);
  }
}
