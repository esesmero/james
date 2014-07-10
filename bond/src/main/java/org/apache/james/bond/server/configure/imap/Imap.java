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
package org.apache.james.bond.server.configure.imap;

import javax.validation.constraints.Min;

import org.apache.james.bond.server.configure.BaseProtocol;

/**
 * Class that describes all the properties to configure a IMAP protocol
 */
public class Imap extends BaseProtocol {
  private boolean compress;
  @Min(value = 0, message = "Maximum line length must be a positive integer")
  private String maxLinelength;
  @Min(value = 0, message = "In memory size limit must be a positive integer")
  private String inMemSizeLimit;

  @Override
  public String getConnectionTimeout() {
    return "1800";
  }

  public boolean isCompress() {
    return compress;
  }

  public void setCompress(boolean compress) {
    this.compress = compress;
  }

  public String getMaxLinelength() {
    return maxLinelength;
  }

  public void setMaxLinelength(String maxLinelength) {
    this.maxLinelength = maxLinelength;
  }

  public String getInMemSizeLimit() {
    return inMemSizeLimit;
  }

  public void setInMemSizeLimit(String inMemSizeLimit) {
    this.inMemSizeLimit = inMemSizeLimit;
  }
}
