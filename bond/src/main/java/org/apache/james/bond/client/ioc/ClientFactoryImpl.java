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
package org.apache.james.bond.client.ioc;

import org.apache.james.bond.client.configure.ConfigureView;
import org.apache.james.bond.client.configure.ConfigureViewImpl;
import org.apache.james.bond.client.configure.dns.ConfigureDNSView;
import org.apache.james.bond.client.configure.dns.ConfigureDNSViewImpl;
import org.apache.james.bond.client.configure.imap.ConfigureImapView;
import org.apache.james.bond.client.configure.imap.ConfigureImapViewImpl;
import org.apache.james.bond.client.configure.lmtp.ConfigureLmtpView;
import org.apache.james.bond.client.configure.lmtp.ConfigureLmtpViewImpl;
import org.apache.james.bond.client.configure.pop3.ConfigurePop3View;
import org.apache.james.bond.client.configure.pop3.ConfigurePop3ViewImpl;
import org.apache.james.bond.client.configure.smtp.ConfigureSmtpView;
import org.apache.james.bond.client.configure.smtp.ConfigureSmtpViewImpl;
import org.apache.james.bond.client.manage.ManageView;
import org.apache.james.bond.client.manage.ManageViewImpl;
import org.apache.james.bond.client.manage.domain.DomainsAsyncDataProvider;
import org.apache.james.bond.client.manage.domain.ManageDomainsTabView;
import org.apache.james.bond.client.manage.domain.ManageDomainsTabViewImpl;
import org.apache.james.bond.client.manage.mappings.ManageMappingsView;
import org.apache.james.bond.client.manage.mappings.ManageMappingsViewImpl;
import org.apache.james.bond.client.manage.mappings.MappingsAsyncDataProvider;
import org.apache.james.bond.client.manage.user.ManageUsersTabView;
import org.apache.james.bond.client.manage.user.ManageUsersTabViewImpl;
import org.apache.james.bond.client.manage.user.UsersAsyncDataProvider;
import org.apache.james.bond.client.monitor.MonitorSummaryView;
import org.apache.james.bond.client.monitor.MonitorSummaryViewImpl;
import org.apache.james.bond.client.monitor.MonitorView;
import org.apache.james.bond.client.monitor.MonitorViewImpl;
import org.apache.james.bond.client.serverconnection.AppRequestFactory;

import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

/**
 * This class implements the {@link ClientFactory} providing unique instances of
 * the views and controllers
 */
public class ClientFactoryImpl implements ClientFactory {
  private static EventBus eventBus;
  private static AppRequestFactory requestFactory;
  private static PlaceController placeController;
  private static ManageUsersTabView manageUsersTabView;
  private static ManageDomainsTabView manageDomainsTabView;
  private static ManageMappingsView manageMappingsView;
  private static ManageView manageView;
  private static ConfigureView configureView;
  private static ConfigureDNSView configureDNSView;
  private static ConfigurePop3View configurePop3View;
  private static ConfigureSmtpView configureSmtpView;
  private static ConfigureLmtpView configureLmtpView;
  private static ConfigureImapView configureImapView;
  private static MonitorView monitorView;
  private static MonitorSummaryView monitorSummaryView;

  @Override
  public AppRequestFactory getRequestFactory() {
    if (requestFactory == null) {
      requestFactory = GWT.create(AppRequestFactory.class);
      requestFactory.initialize(getEventBus());
      requestFactory.initialize(getEventBus());
    }
    return requestFactory;
  }

  @Override
  public EventBus getEventBus() {
    if (eventBus == null)
      eventBus = new SimpleEventBus();
    return eventBus;
  }

  @Override
  public PlaceController getPlaceController() {
    if (placeController == null)
      placeController = new PlaceController(getEventBus());
    return placeController;
  }

  @Override
  public ManageView getManageView(String place) {
    if (manageView == null) {
      manageView = new ManageViewImpl(place);
      manageView.setUsersView(this.getManageUsersTabView());
      manageView.setDomainsView(this.getManageDomainsTabView());
      manageView.setMappingsView(this.getManageMappingsView());
    }
    return manageView;
  }

  @Override
  public ConfigureView getConfigureView(String place) {
    if (configureView == null) {
      configureView = new ConfigureViewImpl(place);
      configureView.setDnsView(getConfigureDNSView());
      configureView.setPop3View(getConfigurePop3View());
      configureView.setSmtpView(getConfigureSmtpView());
      configureView.setLmtpView(getConfigureLmtpView());
      configureView.setImapView(getConfigureImapView());
    }
    return configureView;
  }

  @Override
  public ManageUsersTabView getManageUsersTabView() {
    if (manageUsersTabView == null)
      manageUsersTabView = new ManageUsersTabViewImpl(
          new UsersAsyncDataProvider(getRequestFactory()));
    return manageUsersTabView;
  }

  @Override
  public ManageDomainsTabView getManageDomainsTabView() {
    if (manageDomainsTabView == null)
      manageDomainsTabView = new ManageDomainsTabViewImpl(
          new DomainsAsyncDataProvider(getRequestFactory()));
    return manageDomainsTabView;
  }

  @Override
  public ManageMappingsView getManageMappingsView() {
    if (manageMappingsView == null)
      manageMappingsView = new ManageMappingsViewImpl(
          new MappingsAsyncDataProvider(getRequestFactory()));
    return manageMappingsView;
  }

  @Override
  public ConfigureDNSView getConfigureDNSView() {
    if (configureDNSView == null)
      configureDNSView = new ConfigureDNSViewImpl();
    return configureDNSView;
  }

  @Override
  public ConfigurePop3View getConfigurePop3View() {
    if (configurePop3View == null)
      configurePop3View = new ConfigurePop3ViewImpl();
    return configurePop3View;
  }

  @Override
  public ConfigureSmtpView getConfigureSmtpView() {
    if (configureSmtpView == null)
      configureSmtpView = new ConfigureSmtpViewImpl();
    return configureSmtpView;
  }

  @Override
  public ConfigureLmtpView getConfigureLmtpView() {
    if (configureLmtpView == null)
      configureLmtpView = new ConfigureLmtpViewImpl();
    return configureLmtpView;
  }

  @Override
  public ConfigureImapView getConfigureImapView() {
    if (configureImapView == null)
      configureImapView = new ConfigureImapViewImpl();
    return configureImapView;
  }

  @Override
  public MonitorView getMonitorView(String place) {
    if (monitorView == null) {
      monitorView = new MonitorViewImpl(place);
      monitorView.setSummaryTab(getMonitorSummaryView());
    }
    return monitorView;
  }

  @Override
  public MonitorSummaryView getMonitorSummaryView() {
    if (monitorSummaryView == null) {
      monitorSummaryView = new MonitorSummaryViewImpl();
    }
    return monitorSummaryView;
  }
}
