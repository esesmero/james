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
package org.apache.james.bond.server.configure.lmtp;

import javax.validation.constraints.Min;

import org.apache.james.bond.server.configure.BaseProtocol;

/**
 * Class that describes all the properties to configure a LMTP protocol
 */
public class Lmtp extends BaseProtocol {
  @Min(value = 0, message = "Maximum message size must be a positive integer")
  private String maximumMessageSize;
  private String greeting;

  public String getGreeting() {
    return greeting;
  }

  public void setGreeting(String greeting) {
    this.greeting = greeting;
  }

  public String getMaximumMessageSize() {
    return maximumMessageSize;
  }

  public void setMaximumMessageSize(String maximumMessageSize) {
    this.maximumMessageSize = maximumMessageSize;
  }
}
