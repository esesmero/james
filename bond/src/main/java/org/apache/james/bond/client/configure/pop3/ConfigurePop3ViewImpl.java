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

package org.apache.james.bond.client.configure.pop3;

import org.apache.james.bond.client.BondHelp;
import org.apache.james.bond.client.configure.BaseProtocolPanel;
import org.apache.james.bond.client.configure.BaseProtocolPanelImpl;
import org.apache.james.bond.client.configure.ConfigureProtocolViewImpl;
import org.apache.james.bond.client.configure.HelloNamePanel;
import org.apache.james.bond.client.configure.TLSPanel;
import org.apache.james.bond.client.serverconnection.Pop3Proxy;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;

/**
 * This view shows the POP3 properties to the user, allowing to update and save
 * changes
 */
public class ConfigurePop3ViewImpl extends ConfigureProtocolViewImpl implements
    ConfigurePop3View {
  private BaseProtocolPanel baseProtocolPanel;
  private Button saveButton;
  private HelloNamePanel helloNamePanel;
  private TLSPanel tlsPanel;

  private Presenter listener;
  private Pop3Proxy pop3;

  @Override
  protected void setupTable(FlexTable table) {
    table.setCellSpacing(5);

    baseProtocolPanel = new BaseProtocolPanelImpl();
    saveButton = new Button("Save");
    helloNamePanel = new HelloNamePanel();
    tlsPanel = new TLSPanel();

    table.setWidget(0, 0, baseProtocolPanel);

    table.setWidget(1, 0, helloNamePanel);

    table.setWidget(0, 2, tlsPanel);

    table.setWidget(2, 1, saveButton);

    saveButton.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        clearValidationError();
        clearSavedMessage();
        savePop3();
      }
    });
  }

  @Override
  protected void setTitles() {
    baseProtocolPanel.setBindTitle(BondHelp.getPop3BindHelp());
  }

  @Override
  public void setPresenter(Presenter listener) {
    this.listener = listener;
  }

  /**
   * It sends the {@link Pop3Proxy} to the server to save it
   */
  private void savePop3() {
    pop3 = (Pop3Proxy) baseProtocolPanel.getData(pop3);

    pop3.setHelloNameAutodetect(helloNamePanel.getAutodetect());
    pop3.setHelloNameValue(helloNamePanel.getHelloNameValue());
    pop3.setTlsSocket(tlsPanel.getSocket());
    pop3.setTlsStart(tlsPanel.getStart());
    pop3.setTlsKeystore(tlsPanel.getTLSKeystore());
    pop3.setTlsSecret(tlsPanel.getTLSSecret());
    pop3.setTlsProvider(tlsPanel.getTLSProvider());

    listener.savePop3(pop3);
  }

  @Override
  public void fillData(Pop3Proxy pop3) {
    this.pop3 = pop3;

    baseProtocolPanel.fillData(pop3);
    helloNamePanel.setAutodetect(pop3.isHelloNameAutodetect());
    helloNamePanel.setHelloNameValue(pop3.getHelloNameValue());
    tlsPanel.setSocket(pop3.isTlsSocket());
    tlsPanel.setStart(pop3.isTlsStart());
    tlsPanel.setTLSKeystore(pop3.getTlsKeystore());
    tlsPanel.setTLSSecret(pop3.getTlsSecret());
    tlsPanel.setTLSProvider(pop3.getTlsProvider());
  }
}