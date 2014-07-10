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

package org.apache.james.bond.server.configure.smtp;

import javax.validation.constraints.Min;

import org.apache.james.bond.server.configure.BaseProtocol;

/**
 * Class that describes all the properties to configure a SMTP protocol
 */
public class Smtp extends BaseProtocol {
  private String authRequired;
  private boolean verifyIdentity;
  @Min(value = 0, message = "Maximum message size must be a positive integer")
  private String maximumMessageSize;
  private boolean heloEhloEnforcement;
  private boolean addrBracketsEnforcement;
  private String greeting;
  private String authorizedAddresses; // TODO not sure if this is a list
  private String tlsAlgorithm;

  public String getAuthRequired() {
    return authRequired;
  }

  public void setAuthRequired(String authRequired) {
    this.authRequired = authRequired;
  }

  public boolean isVerifyIdentity() {
    return verifyIdentity;
  }

  public void setVerifyIdentity(boolean verifyIdentity) {
    this.verifyIdentity = verifyIdentity;
  }

  public String getMaximumMessageSize() {
    return maximumMessageSize;
  }

  public void setMaximumMessageSize(String maximumMessageSize) {
    this.maximumMessageSize = maximumMessageSize;
  }

  public boolean isHeloEhloEnforcement() {
    return heloEhloEnforcement;
  }

  public void setHeloEhloEnforcement(boolean heloEhloEnforcement) {
    this.heloEhloEnforcement = heloEhloEnforcement;
  }

  public boolean isAddrBracketsEnforcement() {
    return addrBracketsEnforcement;
  }

  public void setAddrBracketsEnforcement(boolean addrBracketsEnforcement) {
    this.addrBracketsEnforcement = addrBracketsEnforcement;
  }

  public String getGreeting() {
    return greeting;
  }

  public void setGreeting(String greeting) {
    this.greeting = greeting;
  }

  public String getAuthorizedAddresses() {
    return authorizedAddresses;
  }

  public void setAuthorizedAddresses(String authorizedAddresses) {
    this.authorizedAddresses = authorizedAddresses;
  }

  public String getTlsAlgorithm() {
    return tlsAlgorithm;
  }

  public void setTlsAlgorithm(String tlsAlgorithm) {
    this.tlsAlgorithm = tlsAlgorithm;
  }
}
