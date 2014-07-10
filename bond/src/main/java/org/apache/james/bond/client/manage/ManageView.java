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

import org.apache.james.bond.client.CommonTabsView;
import org.apache.james.bond.client.manage.domain.ManageDomainsTabView;
import org.apache.james.bond.client.manage.mappings.ManageMappingsView;
import org.apache.james.bond.client.manage.user.ManageUsersTabView;

/**
 * It defines the methods necessary to add all the necessary tabs to manage the
 * server
 */
public interface ManageView extends CommonTabsView {
  /**
   * It adds the {@link ManageUsersTabView}
   * 
   * @param manageUsersTabView
   */
  public void setUsersView(ManageUsersTabView manageUsersTabView);

  /**
   * It adds the {@link ManageDomainsTabView}
   * 
   * @param manageDomainsTabView
   */
  public void setDomainsView(ManageDomainsTabView manageDomainsTabView);

  /**
   * It adds the {@link ManageMappingsView}
   * 
   * @param manageMappingsView
   */
  public void setMappingsView(ManageMappingsView manageMappingsView);
}