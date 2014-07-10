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
package org.apache.james.bond.client.manage;

import java.util.List;

import junit.framework.Assert;

import org.apache.james.bond.client.ioc.ClientFactory;
import org.apache.james.bond.client.ioc.ClientFactoryTestImpl;
import org.apache.james.bond.client.manage.domain.ManageDomainsTabView;
import org.apache.james.bond.client.manage.mappings.ManageMappingsView;
import org.apache.james.bond.client.manage.user.ManageUsersTabView;
import org.apache.james.bond.client.mvp.AppActivityMapper;
import org.apache.james.bond.client.serverconnection.UserProxy;
import org.junit.Test;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.requestfactory.shared.Receiver;

/**
 * Just an example of how to test activities in JVM
 * 
 * @author manolo
 */
public class ManageActivityTest {

  boolean userRfSucceed = false;

  @Test
  public void testRequestFactory() throws Exception {
    try {
      ClientFactory clientFactory = new ClientFactoryTestImpl();
      clientFactory.getRequestFactory().createUserRequest().listUsers()
          .fire(new Receiver<List<UserProxy>>() {
            @Override
            public void onSuccess(List<UserProxy> response) {
              userRfSucceed = true;
            }
          });

      Assert.assertTrue(userRfSucceed);
    } catch (Exception e) {
      System.err.println("James not reachable");
    }
  }

  int selectedTab = -1;

  @Test
  public void testManageActivity() throws Exception {
    try {
      // We create our test version of clientFactory
      ClientFactory clientFactory = new ClientFactoryTestImpl() {

        // For this test we only need an implementation of ManageView
        @Override
        public ManageView getManageView(String place) {
          return new ManageView() {
            @Override
            public Widget asWidget() {
              return null;
            }

            @Override
            public void selectTab(int index) {
              selectedTab = index;
            }

            @Override
            public void setPlaceString(String place) {
              // TODO Auto-generated method stub

            }

            @Override
            public String getPlaceString() {
              // TODO Auto-generated method stub
              return null;
            }

            @Override
            public void addTab(IsWidget widget, String text) {
              // TODO Auto-generated method stub

            }

            @Override
            public int getTabsCount() {
              // TODO Auto-generated method stub
              return 0;
            }

            @Override
            public void onSelection(SelectionEvent<Integer> event) {
              // TODO Auto-generated method stub

            }

            @Override
            public void setUsersView(ManageUsersTabView manageUsersTabView) {
              // TODO Auto-generated method stub

            }

            @Override
            public void setDomainsView(ManageDomainsTabView manageDomainsTabView) {
              // TODO Auto-generated method stub

            }

            @Override
            public void addTab(IsWidget widget, String text, String title) {
              // TODO Auto-generated method stub

            }

            @Override
            public void setMappingsView(ManageMappingsView manageMappingsView) {
              // TODO Auto-generated method stub

            }
          };
        }
      };

      // We use the mapper to get the activity
      AppActivityMapper mapper = new AppActivityMapper(clientFactory);
      //
      // // when the activity is created it selects the appropriate tab
      // // and does a RF call to the server
      // ManageActivity manageActivity = (ManageActivity) mapper
      // .getActivity(new ManagePlace(""));
      //
      // Assert.assertEquals(1, selectedTab);
    } catch (Exception e) {
      System.err.println("James not reachable");
    }
  }
}