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
package org.apache.james.bond.client.ui;

import org.apache.james.bond.client.configure.ConfigurePlace;
import org.apache.james.bond.client.ioc.ClientFactory;
import org.apache.james.bond.client.manage.ManagePlace;
import org.apache.james.bond.client.monitor.MonitorPlace;

import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.Widget;

/**
 * It is a widget with the main menu of the application: manage, configure and
 * monitor
 */
public class MenuBar extends Composite {
  private static MenuBarUiBinder uiBinder = GWT.create(MenuBarUiBinder.class);
  private static ClientFactory clientFactory = GWT.create(ClientFactory.class);
  @UiField
  MenuItem manageItem;
  @UiField
  MenuItem configureItem;
  @UiField
  MenuItem monitorItem;

  interface MenuBarUiBinder extends UiBinder<Widget, MenuBar> {
  }

  /**
   * It creates a {@link MenuBar}
   */
  public MenuBar() {
    initWidget(uiBinder.createAndBindUi(this));
    setUp();
  }

  private void setUp() {
    manageItem.setScheduledCommand(new Command() {
      public void execute() {
        goTo(new ManagePlace("0"));
      }
    });
    configureItem.setScheduledCommand(new Command() {
      public void execute() {
        goTo(new ConfigurePlace("0"));
      }
    });
    monitorItem.setScheduledCommand(new Command() {
      public void execute() {
        goTo(new MonitorPlace("0"));
      }
    });
  }

  /**
   * It selects the Place the application has to go
   * 
   * @param place
   */
  private void goTo(Place place) {
    clientFactory.getPlaceController().goTo(place);
  }
}