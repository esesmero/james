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
package org.apache.james.bond.client;

import org.apache.james.bond.client.ioc.ClientFactory;
import org.apache.james.bond.client.serverconnection.AppRequestFactory;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

/**
 * This class represents the common behaviour of the Activity that takes care of
 * selecting a tab in a view when the place contains the number of the tab in
 * its token.
 */
public abstract class CommonActivity extends AbstractActivity {
  protected AppRequestFactory requestFactory;
  private CommonTabsView view;

  /**
   * Constructor for Common Activity.
   * 
   * @param place
   *          CommonPlace that selects a tab in its token
   * @param clientFactory
   */
  public CommonActivity(CommonPlace place, ClientFactory clientFactory) {
    this.requestFactory = clientFactory.getRequestFactory();

    view = createMainView(clientFactory, place.getPrefixPlace());

    String token;
    if ((token = place.getToken()) != null) {
      int tokenInt = Util.convertStringToInt(token);
      if (tokenInt > 0 && tokenInt < view.getTabsCount())
        view.selectTab(tokenInt);
      else
        view.selectTab(0);
    }
  }

  /**
   * Obtains the necessary CommonTabsView from the clientFactory
   * 
   * @param clientFactory
   * @param prefixPlace
   *          the prefixPlace of the place
   * @return
   */
  protected abstract CommonTabsView createMainView(ClientFactory clientFactory,
      String prefixPlace);

  @Override
  public void start(AcceptsOneWidget panel, EventBus eventBus) {
    panel.setWidget(view);
  }
}