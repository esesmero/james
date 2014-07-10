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

import org.apache.james.bond.client.CommonPlace;

import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

/**
 * This class is a {@link CommonPlace} with "ManagePlace" as prefix
 */
public class ManagePlace extends CommonPlace {
  /**
   * {@inheritDoc}
   */
  public ManagePlace(String token) {
    super(token);
  }

  @Override
  public String getPrefixPlace() {
    return "ManagePlace";
  }

  @Prefix("ManagePlace")
  public static class Tokenizer implements PlaceTokenizer<ManagePlace> {
    @Override
    public String getToken(ManagePlace place) {
      return place.getToken();
    }

    @Override
    public ManagePlace getPlace(String token) {
      return new ManagePlace(token);
    }
  }
}