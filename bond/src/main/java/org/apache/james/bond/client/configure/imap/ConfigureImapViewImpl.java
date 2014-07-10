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
package org.apache.james.bond.client.configure.imap;

import org.apache.james.bond.client.BondHelp;
import org.apache.james.bond.client.configure.BaseProtocolPanel;
import org.apache.james.bond.client.configure.BaseProtocolPanelImpl;
import org.apache.james.bond.client.configure.ConfigureProtocolViewImpl;
import org.apache.james.bond.client.configure.HelloNamePanel;
import org.apache.james.bond.client.configure.TLSPanel;
import org.apache.james.bond.client.serverconnection.ImapProxy;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

/**
 * This view shows the IMAP properties to the user, allowing to update and save
 * changes
 */
public class ConfigureImapViewImpl extends ConfigureProtocolViewImpl implements
    ConfigureImapView {

  private BaseProtocolPanel baseProtocolPanel;
  private Button saveButton;
  private HelloNamePanel helloNamePanel;
  private TLSPanel tlsPanel;
  private CheckBox compress;
  private TextBox maxLineLength;
  private TextBox inMemorySizeLimit;

  private Presenter listener;
  private ImapProxy imap;

  @Override
  protected void setupTable(FlexTable table) {
    table.setCellSpacing(5);
    FlexCellFormatter cellFormatter = table.getFlexCellFormatter();

    baseProtocolPanel = new BaseProtocolPanelImpl(true);
    saveButton = new Button("Save");
    helloNamePanel = new HelloNamePanel();
    tlsPanel = new TLSPanel();

    compress = new CheckBox();
    maxLineLength = new TextBox();
    inMemorySizeLimit = new TextBox();

    maxLineLength.setWidth("50px");
    inMemorySizeLimit.setWidth("50px");

    table.setWidget(0, 0, baseProtocolPanel);
    cellFormatter.setRowSpan(0, 0, 5);

    table.setWidget(0, 2, new Label("Compress:"));
    table.setWidget(0, 3, compress);

    table.setWidget(1, 1, new Label("Maximum line length:"));
    table.setWidget(1, 2, maxLineLength);

    table.setWidget(2, 1, new Label("In memory size limit:"));
    table.setWidget(2, 2, inMemorySizeLimit);

    table.setWidget(5, 0, helloNamePanel);
    table.setWidget(3, 1, tlsPanel);

    cellFormatter.setColSpan(3, 1, 2);
    cellFormatter.setRowSpan(3, 1, 3);

    table.setWidget(8, 1, saveButton);

    saveButton.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        clearValidationError();
        clearSavedMessage();
        saveImap();
      }
    });
  }

  @Override
  protected void setTitles() {
    baseProtocolPanel.setBindTitle(BondHelp.getImapBindHelp());
    compress.setTitle(BondHelp.getImapCompressHelp());
    maxLineLength.setTitle(BondHelp.getImapMaxLineLengthHelp());
    inMemorySizeLimit.setTitle(BondHelp.getImapInMemorySizeLimitHelp());
  }

  @Override
  public void setPresenter(Presenter listener) {
    this.listener = listener;
  }

  /**
   * It sends the {@link ImapProxy} to the server to save it
   */
  private void saveImap() {
    imap = (ImapProxy) baseProtocolPanel.getData(imap);
    imap.setHelloNameAutodetect(helloNamePanel.getAutodetect());
    imap.setHelloNameValue(helloNamePanel.getHelloNameValue());
    imap.setTlsSocket(tlsPanel.getSocket());
    imap.setTlsStart(tlsPanel.getStart());
    imap.setTlsKeystore(tlsPanel.getTLSKeystore());
    imap.setTlsSecret(tlsPanel.getTLSSecret());
    imap.setTlsProvider(tlsPanel.getTLSProvider());

    imap.setCompress(compress.getValue());
    imap.setMaxLinelength(maxLineLength.getText());
    imap.setInMemSizeLimit(inMemorySizeLimit.getText());

    listener.saveImap(imap);
  }

  @Override
  public void fillData(ImapProxy imap) {
    this.imap = imap;

    baseProtocolPanel.fillData(imap);
    helloNamePanel.setAutodetect(imap.isHelloNameAutodetect());
    helloNamePanel.setHelloNameValue(imap.getHelloNameValue());
    tlsPanel.setSocket(imap.isTlsSocket());
    tlsPanel.setStart(imap.isTlsStart());
    tlsPanel.setTLSKeystore(imap.getTlsKeystore());
    tlsPanel.setTLSSecret(imap.getTlsSecret());
    tlsPanel.setTLSProvider(imap.getTlsProvider());

    compress.setValue(imap.isCompress());
    maxLineLength.setText(imap.getMaxLinelength());
    inMemorySizeLimit.setText(imap.getInMemSizeLimit());
  }
}