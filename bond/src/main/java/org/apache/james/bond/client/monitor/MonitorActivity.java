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
package org.apache.james.bond.client.monitor;

import org.apache.james.bond.client.CommonActivity;
import org.apache.james.bond.client.CommonTabsView;
import org.apache.james.bond.client.ioc.ClientFactory;
import org.apache.james.bond.client.monitor.MonitorSummaryView.Presenter;
import org.apache.james.bond.client.serverconnection.BasicReceiver;
import org.apache.james.bond.client.serverconnection.MonitoringProxy;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.requestfactory.shared.Receiver;

/**
 * This {@link CommonActivity} manages all the views to monitor the server.
 */
public class MonitorActivity extends CommonActivity implements Presenter {
  private MonitorSummaryView monitorSummaryView;
  private Timer timer;

  /**
   * It creates a {@link MonitorActivity} setting it as a presenter for the
   * views
   * 
   * @see CommonActivity
   * 
   * @param place
   * @param clientFactory
   */
  public MonitorActivity(MonitorPlace place, ClientFactory clientFactory) {
    super(place, clientFactory);
    monitorSummaryView = clientFactory.getMonitorSummaryView();

    getMonitoringInformation();
  }

  @Override
  protected CommonTabsView createMainView(ClientFactory clientFactory,
      String prefixPlace) {
    return clientFactory.getMonitorView(prefixPlace);
  }

  @Override
  public void start(AcceptsOneWidget panel, EventBus eventBus) {
    super.start(panel, eventBus);
    timer.scheduleRepeating(1500);
  }

  @Override
  public void onStop() {
    super.onStop();
    timer.cancel();
  }

  @Override
  public void getMonitoringInformation() {

    final Receiver<MonitoringProxy> rec = new BasicReceiver<MonitoringProxy>() {
      @Override
      public void onSuccess(MonitoringProxy monitoring) {
        if (monitoring != null)
          monitorSummaryView.showInformation(monitoring);
      }
    };

    timer = new Timer() {
      @Override
      public void run() {
        MonitorActivity.this.requestFactory.createMonitoringRequest()
            .getMonitoringInformation().fire(rec);
      }
    };

    timer.scheduleRepeating(1500);
  }
}
