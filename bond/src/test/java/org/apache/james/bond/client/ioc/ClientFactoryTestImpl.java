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
import org.apache.james.bond.client.configure.lmtp.ConfigureLmtpView;
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
import org.apache.james.bond.client.manage.user.ManageUsersTabView;
import org.apache.james.bond.client.manage.user.ManageUsersTabViewImpl;
import org.apache.james.bond.client.manage.user.UsersAsyncDataProvider;
import org.apache.james.bond.client.monitor.MonitorSummaryView;
import org.apache.james.bond.client.monitor.MonitorView;
import org.apache.james.bond.client.serverconnection.AppRequestFactory;

import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.server.ExceptionHandler;
import com.google.web.bindery.requestfactory.server.ServiceLayer;
import com.google.web.bindery.requestfactory.server.SimpleRequestProcessor;
import com.google.web.bindery.requestfactory.server.testing.InProcessRequestTransport;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import com.google.web.bindery.requestfactory.vm.RequestFactorySource;

/**
 * Test implementation of ClientFactory.
 * 
 * @author manolo
 * 
 */
public class ClientFactoryTestImpl implements ClientFactory {

  private static final EventBus eventBus = new SimpleEventBus();

  private final AppRequestFactory requestFactory;
  private static PlaceController placeController;
  private static ManageUsersTabView manageUsersTabView;
  private static ManageDomainsTabView manageDomainsTabView;
  private static ManageView manageView;
  private static ConfigureView configureView;
  private static ConfigureDNSView configureDNSView;
  private static ConfigurePop3View configurePop3View;
  private static ConfigureSmtpView configureSmtpView;

  public ClientFactoryTestImpl() {
    // Create a requstFactory which works in JVM
    requestFactory = RequestFactorySource.create(AppRequestFactory.class);
    SimpleRequestProcessor processor = new SimpleRequestProcessor(
        ServiceLayer.create());

    processor.setExceptionHandler(new ExceptionHandler() {
      public ServerFailure createServerFailure(Throwable e) {
        e.printStackTrace();
        return new ServerFailure(e.getMessage());
      }
    });
    requestFactory.initialize(new SimpleEventBus(),
        new InProcessRequestTransport(processor));
  }

  @Override
  public AppRequestFactory getRequestFactory() {
    return requestFactory;
  }

  @Override
  public EventBus getEventBus() {
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
    if (manageView == null)
      manageView = new ManageViewImpl(place);
    return manageView;
  }

  @Override
  public ConfigureView getConfigureView(String place) {
    if (configureView == null)
      configureView = new ConfigureViewImpl(place);
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
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ConfigureImapView getConfigureImapView() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public MonitorView getMonitorView(String place) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public MonitorSummaryView getMonitorSummaryView() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ManageMappingsView getManageMappingsView() {
    // TODO Auto-generated method stub
    return null;
  }
}
