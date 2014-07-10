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
package org.apache.james.bond.client.manage.user;

import java.util.List;

import org.apache.james.bond.client.serverconnection.AppRequestFactory;
import org.apache.james.bond.client.serverconnection.BasicReceiver;
import org.apache.james.bond.client.serverconnection.UserProxy;

import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;
import com.google.web.bindery.requestfactory.shared.Receiver;

/**
 * An {@link AsyncDataProvider} that keeps the information about users
 */
public class UsersAsyncDataProvider extends AsyncDataProvider<UserProxy> {
  private AppRequestFactory requestFactory;

  /**
   * It creates a {@link UsersAsyncDataProvider} defining the
   * {@link AppRequestFactory} to use to get the data
   * 
   * @param requestFactory
   */
  public UsersAsyncDataProvider(AppRequestFactory requestFactory) {
    this.requestFactory = requestFactory;
  }

  @Override
  protected void onRangeChanged(HasData<UserProxy> display) {
    final Range range = display.getVisibleRange();
    Receiver<List<UserProxy>> rec = new BasicReceiver<List<UserProxy>>() {
      /**
       * It updates the data with the users received
       * 
       * @param users
       */
      public void onSuccess(List<UserProxy> users) {
        updateRowData(range.getStart(), users);
        updateRowCount(users.size(), true);
      }
    };
    this.requestFactory.createUserRequest().listUsers().fire(rec);
  }
}