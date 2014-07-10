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
package org.apache.james.bond.client.mvp;

import org.apache.james.bond.client.configure.ConfigureActivity;
import org.apache.james.bond.client.configure.ConfigurePlace;
import org.apache.james.bond.client.ioc.ClientFactory;
import org.apache.james.bond.client.manage.ManageActivity;
import org.apache.james.bond.client.manage.ManagePlace;
import org.apache.james.bond.client.monitor.MonitorActivity;
import org.apache.james.bond.client.monitor.MonitorPlace;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

/**
 * This class maps the {@link Place} with the right {@link Activity}
 */
public class AppActivityMapper implements ActivityMapper {
  private ClientFactory clientFactory;

  /**
   * It creates received the {@link ClientFactory} to get all the views and
   * controllers from
   * 
   * @param clientFactory
   */
  public AppActivityMapper(ClientFactory clientFactory) {
    super();
    this.clientFactory = clientFactory;
  }

  @Override
  public Activity getActivity(Place place) {
    if (place instanceof ManagePlace) {
      return new ManageActivity((ManagePlace) place, clientFactory);
    } else if (place instanceof ConfigurePlace) {
      return new ConfigureActivity((ConfigurePlace) place, clientFactory);
    } else if (place instanceof MonitorPlace) {
      return new MonitorActivity((MonitorPlace) place, clientFactory);
    }
    return null;
  }
}