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
package org.apache.james.bond.client.manage.domain;

import org.apache.james.bond.client.serverconnection.DomainProxy;

import com.google.gwt.user.client.ui.IsWidget;

/**
 * It defines the methods to show, edit and save a domains
 */
public interface ManageDomainsTabView extends IsWidget {
  /**
   * It sets the {@link Presenter} that manages the view
   * 
   * @param listener
   */
  void setPresenter(final Presenter listener);

  /**
   * It reads the domains from the server to update the information
   */
  void updateData();

  /**
   * It defines the behavior of the presenter that manages the
   * {@link ManageDomainsTabView}
   */
  public interface Presenter {
    /**
     * It deletes a domain from the server
     * 
     * @param domain
     */
    void deleteDomain(final DomainProxy domain);

    /**
     * It adds a domain to the server
     * 
     * @param domain
     */
    void addDomain(final String domain);
  }
}
