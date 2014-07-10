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
import org.apache.james.bond.client.configure.dns.ConfigureDNSView;
import org.apache.james.bond.client.configure.imap.ConfigureImapView;
import org.apache.james.bond.client.configure.lmtp.ConfigureLmtpView;
import org.apache.james.bond.client.configure.pop3.ConfigurePop3View;
import org.apache.james.bond.client.configure.smtp.ConfigureSmtpView;
import org.apache.james.bond.client.manage.ManageView;
import org.apache.james.bond.client.manage.domain.ManageDomainsTabView;
import org.apache.james.bond.client.manage.mappings.ManageMappingsView;
import org.apache.james.bond.client.manage.user.ManageUsersTabView;
import org.apache.james.bond.client.monitor.MonitorSummaryView;
import org.apache.james.bond.client.monitor.MonitorView;
import org.apache.james.bond.client.serverconnection.AppRequestFactory;

import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;

/**
 * It retrieves the instance of all the views and controllers of the system
 */
public interface ClientFactory {
  /**
   * Returns the {@link AppRequestFactory} of the application
   * 
   * @return
   */
  AppRequestFactory getRequestFactory();

  /**
   * Returns the {@link EventBus} of the application
   * 
   * @return
   */
  EventBus getEventBus();

  /**
   * Returns the {@link PlaceController} of the application
   * 
   * @return
   */
  PlaceController getPlaceController();

  /**
   * Returns the {@link ManageView} of the application with the views for the
   * tabs added
   * 
   * 
   * @param place
   *          the place prefix string
   * @return
   */
  ManageView getManageView(String place);

  /**
   * Returns the {@link ManageUsersTabView} of the application
   * 
   * @return
   */
  ManageUsersTabView getManageUsersTabView();

  /**
   * Returns the {@link ManageDomainsTabView} of the application
   * 
   * @return
   */
  ManageDomainsTabView getManageDomainsTabView();

  /**
   * Returns the {@link ManageMappingsView} of the application
   * 
   * @return
   */
  ManageMappingsView getManageMappingsView();

  /**
   * Returns the {@link ConfigureView} of the application with the views for the
   * tabs added
   * 
   * 
   * @param place
   *          the place prefix string
   * @return
   */
  ConfigureView getConfigureView(String place);

  /**
   * Returns the {@link ConfigureDNSView} of the application
   * 
   * @return
   */
  ConfigureDNSView getConfigureDNSView();

  /**
   * Returns the {@link ConfigurePop3View} of the application
   * 
   * @return
   */
  ConfigurePop3View getConfigurePop3View();

  /**
   * Returns the {@link ConfigureSmtpView} of the application
   * 
   * @return
   */
  ConfigureSmtpView getConfigureSmtpView();

  /**
   * Returns the {@link ConfigureLmtpView} of the application
   * 
   * @return
   */
  ConfigureLmtpView getConfigureLmtpView();

  /**
   * Returns the {@link ConfigureImapView} of the application
   * 
   * @return
   */
  ConfigureImapView getConfigureImapView();

  /**
   * Returns the {@link MonitorView} of the application with the views for the
   * tabs added
   * 
   * @param place
   *          the place prefix string
   * @return
   */
  MonitorView getMonitorView(String place);

  /**
   * Returns the {@link MonitorSummaryView} of the application
   * 
   * @return
   */
  MonitorSummaryView getMonitorSummaryView();
}
