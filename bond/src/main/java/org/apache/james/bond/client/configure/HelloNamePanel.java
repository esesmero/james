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
 * and edit the HelloName element
 */
public class HelloNamePanel extends DecoratorPanel {

  private CheckBox autodetectCheckBox;
  private TextBox valueTextBox;

  /**
   * Creates a {@link DecoratorPanel} with a {@link CheckBox} and a
   * {@link TextBox} to hold the HelloName properties
   */
  public HelloNamePanel() {
    FlexTable table = new FlexTable();

    autodetectCheckBox = new CheckBox();
    valueTextBox = new TextBox();

    table.setWidget(0, 0, new Label("HelloName"));
    table.setWidget(1, 0, new Label("Autodetect"));
    table.setWidget(1, 1, autodetectCheckBox);
    table.setWidget(2, 0, new Label("Value"));
    table.setWidget(2, 1, valueTextBox);

    this.add(table);
    this.setTitle(BondHelp.getHelloNameHelp());
  }

  /**
   * Sets the value of the autodetect {@link CheckBox}
   * 
   * @param autodetect
   */
  public void setAutodetect(boolean autodetect) {
    this.autodetectCheckBox.setValue(autodetect);
  }

  /**
   * Sets the value of the HelloName
   * 
   * @param value
   */
  public void setHelloNameValue(String value) {
    this.valueTextBox.setValue(value);
  }

  /**
   * Retrieves the value of autodetect {@link CheckBox}
   * 
   * @return
   */
  public boolean getAutodetect() {
    return this.autodetectCheckBox.getValue();
  }

  /**
   * Retrieves the value of the HelloName
   * 
   * @return
   */
  public String getHelloNameValue() {
    return this.valueTextBox.getText();
  }
}