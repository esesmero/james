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
package org.apache.james.bond.client.manage.mappings;

import java.util.List;

import org.apache.james.bond.client.serverconnection.MappingProxy;

import com.google.gwt.user.client.ui.IsWidget;

/**
 * It defines the methods to show, edit, deleta and save all mappings
 */
public interface ManageMappingsView extends IsWidget {
  /**
   * It sets the {@link Presenter} that manages the view
   * 
   * @param listener
   */
  void setPresenter(final Presenter listener);

  /**
   * It adds to the view the {@link MappingProxy} list
   * 
   * @param mappingList
   *          mappings to show
   */
  void showMappings(List<MappingProxy> mappingList);

  /**
   * It updates the view
   */
  void updateData();

  /**
   * It defines the behavior of the presenter that manages the
   * {@link ManageMappingsView}
   */
  public interface Presenter {
    /**
     * It adds a new address mapping to the user and domain received
     * 
     * @param user
     * @param domain
     * @param address
     */
    void addAddressMapping(String user, String domain, String address);

    /**
     * It removes the address mapping that matches the user, domain and address
     * 
     * @param user
     * @param domain
     * @param address
     */
    void removeAddressMapping(String user, String domain, String address);

    /**
     * It adds a new regex mapping for the user and domain
     * 
     * @param user
     * @param domain
     * @param regex
     */
    void addRegexMapping(String user, String domain, String regex);

    /**
     * It deletes the mapping that matches the user, domain and regex
     * 
     * @param user
     * @param domain
     * @param regex
     */
    void removeRegexMapping(String user, String domain, String regex);
  }
}