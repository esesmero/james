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

import com.google.gwt.place.shared.Place;

/**
 * This class extracts the common characteristic of a Place than contains a
 * prefix (that selects the place) and a token (that selects the tab) in the url
 */
public abstract class CommonPlace extends Place {
  private String token;

  /**
   * It constructs a CommonPlace with a token that selects the tab with a number
   * 
   * @param token
   *          String representation of a positive integer selecting a tab
   */
  public CommonPlace(String token) {
    this.token = token;
  }

  /**
   * Returns the selected tab
   * 
   * @return
   */
  public String getToken() {
    return token;
  }

  /**
   * It returns the prefix part of the place (the string between # and : in the
   * url)
   * 
   * @return
   */
  public abstract String getPrefixPlace();
}