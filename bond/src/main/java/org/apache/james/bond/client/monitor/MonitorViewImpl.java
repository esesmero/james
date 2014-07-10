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

import org.apache.james.bond.client.CommonTabsViewImpl;

import com.google.gwt.user.client.ui.IsWidget;

/**
 * This class defines the view to monitor the James server
 */
public class MonitorViewImpl extends CommonTabsViewImpl implements MonitorView {
  /**
   * {@inheritDoc}
   */
  public MonitorViewImpl(String place) {
    super(place);
  }

  @Override
  public void setSummaryTab(IsWidget view) {
    addTab(view, "Summary");
  }
}