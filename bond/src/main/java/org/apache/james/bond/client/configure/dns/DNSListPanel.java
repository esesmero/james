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

import java.util.List;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

/**
 * A {@link DecoratorPanel} to list the dns servers ip adresses
 */
public class DNSListPanel extends DecoratorPanel {
  private CellList<String> cellList;
  private String selectedDns;

  public DNSListPanel() {
    TextCell textCell = new TextCell();

    cellList = new CellList<String>(textCell);
    cellList.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

    final SingleSelectionModel<String> selectionModel = new SingleSelectionModel<String>();
    cellList.setSelectionModel(selectionModel);
    selectionModel
        .addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
          public void onSelectionChange(SelectionChangeEvent event) {
            String selected = selectionModel.getSelectedObject();
            if (selected != null) {
              selectedDns = selected;
            }
          }
        });

    cellList.setTitle("IP Servers");

    ScrollPanel scrollPanel = new ScrollPanel(cellList);
    scrollPanel.setSize("120px", "90px");
    this.setWidget(scrollPanel);
  }

  /**
   * It sets the server IP addresses to show to the user
   * 
   * @param serverIPList
   */
  public void setData(List<String> serverIPList) {
    cellList.setRowData(serverIPList);
    cellList.setRowCount(serverIPList.size());
  }

  /**
   * It retrieves the selected DNS ip
   * 
   * @return
   */
  public String getSelectedDns() {
    return selectedDns;
  }
}
