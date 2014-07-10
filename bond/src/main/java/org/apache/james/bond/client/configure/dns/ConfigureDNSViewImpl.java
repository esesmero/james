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
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.james.bond.client.BondHelp;
import org.apache.james.bond.client.configure.ConfigureProtocolViewImpl;
import org.apache.james.bond.client.serverconnection.DNSProxy;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.validation.client.impl.Validation;

/**
 * This {@link ConfigureDNSView} allows the user to see, update and save changes
 * from a {@link DNSProxy}
 */
public class ConfigureDNSViewImpl extends ConfigureProtocolViewImpl implements
    ConfigureDNSView {

  private Presenter listener;
  private DNSProxy dns;
  private CheckBox autodiscovery;
  private CheckBox authoritative;
  private TextBox maxCacheSize;
  private CheckBox singleIPperMX;
  private Button addDNSButton;
  private Button deleteDNSButton;
  private Button saveButton;
  private List<String> serverIPList;
  private DNSListPanel dnsListPanel;

  @Override
  protected void setupTable(FlexTable table) {
    table.setCellSpacing(5);
    table.setCellPadding(5);

    FlexCellFormatter cellFormatter = table.getFlexCellFormatter();

    autodiscovery = new CheckBox();
    authoritative = new CheckBox();
    maxCacheSize = new TextBox();
    maxCacheSize.setWidth("50px");
    singleIPperMX = new CheckBox();
    addDNSButton = new Button("Add new DNS");
    deleteDNSButton = new Button("Delete DNS");
    saveButton = new Button("Save");
    dnsListPanel = new DNSListPanel();

    table.setWidget(0, 0, new Label("Autodiscovery:"));
    table.setWidget(0, 1, autodiscovery);

    table.setWidget(1, 0, new Label("Authoritative:"));
    table.setWidget(1, 1, authoritative);

    table.setWidget(2, 0, new Label("Maximum cache size:"));
    table.setWidget(2, 1, maxCacheSize);

    table.setWidget(3, 0, new Label("Single IP per MX:"));
    table.setWidget(3, 1, singleIPperMX);

    table.setWidget(0, 3, addDNSButton);
    table.setWidget(0, 4, deleteDNSButton);

    table.setWidget(1, 3, new Label("IP servers:"));
    table.setWidget(1, 4, dnsListPanel);

    cellFormatter.setRowSpan(1, 4, 3);

    table.setWidget(7, 2, saveButton);

    addDNSButton.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        final AddNewDNS addNewDNSView = new AddNewDNS(ConfigureDNSViewImpl.this);
        addNewDNSView.center();
        addNewDNSView.show();
      }
    });

    deleteDNSButton.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        String selectedDns = dnsListPanel.getSelectedDns();
        if (selectedDns != null)
          serverIPList.remove(serverIPList.indexOf(selectedDns));
        dnsListPanel.setData(serverIPList);
      }
    });

    saveButton.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        clearValidationError();
        clearSavedMessage();
        saveDNS();
      }
    });
  }

  @Override
  protected void setTitles() {
    autodiscovery.setTitle(BondHelp.getDnsAutodiscoveryHelp());
    authoritative.setTitle(BondHelp.getDnsAuthoritativeHelp());
    maxCacheSize.setTitle(BondHelp.getDnsMaxCacheSizeHelp());
    singleIPperMX.setTitle(BondHelp.getDnsSingleIpPerMxHelp());
    addDNSButton.setTitle(BondHelp.getDnsServerHelp());
    deleteDNSButton.setTitle(BondHelp.getDnsDeteleServerHelp());
  }

  /**
   * It saves the dns if there is no validations constraints in the client
   */
  private void saveDNS() {
    dns.setAutodiscover(autodiscovery.getValue());
    dns.setAuthoritative(authoritative.getValue());
    dns.setMaxCacheSize(maxCacheSize.getValue());
    dns.setSingleIPperMX(singleIPperMX.getValue());
    dns.setServersIp(serverIPList);

    Validator validator = Validation.buildDefaultValidatorFactory()// TODO does
                                                                   // not work
        .getValidator();
    Set<ConstraintViolation<DNSProxy>> violations = validator.validate(dns);

    if (!violations.isEmpty()) {
      // client-side violation
      System.out.println("Client-side violation");
      for (ConstraintViolation<DNSProxy> violation : violations) {
        System.out.println(violation.getMessage());
      }
    } else {
      listener.saveDNS(dns);
    }
  }

  /**
   * It adds a server to the servers ip list and updates its view
   * 
   * @param ip
   */
  protected void addServerIp(String ip) {
    if (!serverIPList.contains(ip)) {
      serverIPList.add(ip);
      dnsListPanel.setData(serverIPList);
    }
  }

  @Override
  public void fillData(DNSProxy dns) {
    this.dns = dns;

    autodiscovery.setValue(dns.isAutodiscover());
    authoritative.setValue(dns.isAuthoritative());
    maxCacheSize.setText(dns.getMaxCacheSize());
    singleIPperMX.setValue(dns.isSingleIPperMX());

    serverIPList = dns.getServersIp();
    dnsListPanel.setData(serverIPList);
  }

  @Override
  public void setPresenter(Presenter listener) {
    this.listener = listener;
  }
}
