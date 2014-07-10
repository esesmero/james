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

import java.util.ArrayList;
import java.util.List;

import org.apache.james.bond.client.serverconnection.AppRequestFactory;
import org.apache.james.bond.client.serverconnection.BasicReceiver;
import org.apache.james.bond.client.serverconnection.MappingProxy;

import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;
import com.google.web.bindery.requestfactory.shared.Receiver;

/**
 * An {@link AsyncDataProvider} that keeps the information about mappings
 */
public class MappingsAsyncDataProvider extends AsyncDataProvider<Mapping> {
  private AppRequestFactory requestFactory;

  /**
   * It creates a {@link MappingsAsyncDataProvider} defining the
   * {@link AppRequestFactory} to use to get the data
   * 
   * @param requestFactory
   */
  public MappingsAsyncDataProvider(AppRequestFactory requestFactory) {
    this.requestFactory = requestFactory;
  }

  @Override
  protected void onRangeChanged(HasData<Mapping> display) {
    final Range range = display.getVisibleRange();
    Receiver<List<MappingProxy>> rec = new BasicReceiver<List<MappingProxy>>() {
      /**
       * It updates the data with the mappings received
       * 
       * @param mappings
       */
      public void onSuccess(List<MappingProxy> mappings) {
        List<Mapping> mappingList = new ArrayList<Mapping>();
        for (MappingProxy mapping : mappings) {
          String userDomain = mapping.getUserAndDomain();
          for (String mappingString : mapping.getMappings()) {
            mappingList.add(new Mapping(userDomain, mappingString));
          }
        }

        updateRowData(range.getStart(), mappingList);
        updateRowCount(mappingList.size(), true);
      }
    };
    this.requestFactory.createMappingRequest().listMappings().fire(rec);
  }
}