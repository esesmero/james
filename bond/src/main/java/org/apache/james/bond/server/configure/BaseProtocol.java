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

package org.apache.james.bond.server.configure;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

/**
 * Class that describes the common behavior of most of the protocols
 */
public class BaseProtocol {
  private Integer version;

  private boolean enabled;
  @Pattern(regexp = "(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}"
      + "(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?):[0-9]{1,5}", message = "Bind has invalid characters: it must follow the pattern IP:PORT")
  private String bind;
  @Min(value = 0, message = "Connection backlog must be a positive integer")
  private String connectionBacklog;
  @Min(value = 0, message = "Connection timeout must be a positive integer")
  private String connectionTimeout;
  @Min(value = 0, message = "Connection limit must be a positive integer")
  private String connectionLimit;
  @Min(value = 0, message = "Connection limit per IP must be a positive integer")
  private String connectionLimitPerIp;
  private boolean helloNameAutodetect;
  private String helloNameValue;
  private boolean tlsSocket;
  private boolean tlsStart;
  private String tlsKeystore;
  private String tlsSecret;
  private String tlsProvider;

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public String getBind() {
    return bind;
  }

  public void setBind(String bind) {
    this.bind = bind;
  }

  public String getConnectionBacklog() {
    return connectionBacklog;
  }

  public void setConnectionBacklog(String connectionBacklog) {
    this.connectionBacklog = connectionBacklog;
  }

  public String getConnectionTimeout() {
    return connectionTimeout;
  }

  public void setConnectionTimeout(String connectionTimeout) {
    this.connectionTimeout = connectionTimeout;
  }

  public String getConnectionLimit() {
    return connectionLimit;
  }

  public void setConnectionLimit(String connectionLimit) {
    this.connectionLimit = connectionLimit;
  }

  public String getConnectionLimitPerIp() {
    return connectionLimitPerIp;
  }

  public void setConnectionLimitPerIp(String connectionLimitPerIp) {
    this.connectionLimitPerIp = connectionLimitPerIp;
  }

  public boolean isHelloNameAutodetect() {
    return helloNameAutodetect;
  }

  public void setHelloNameAutodetect(boolean helloNameAutodetect) {
    this.helloNameAutodetect = helloNameAutodetect;
  }

  public String getHelloNameValue() {
    return helloNameValue;
  }

  public void setHelloNameValue(String helloNameValue) {
    this.helloNameValue = helloNameValue;
  }

  public boolean isTlsSocket() {
    return tlsSocket;
  }

  public void setTlsSocket(boolean tlsSocket) {
    this.tlsSocket = tlsSocket;
  }

  public boolean isTlsStart() {
    return tlsStart;
  }

  public void setTlsStart(boolean tlsStart) {
    this.tlsStart = tlsStart;
  }

  public String getTlsKeystore() {
    return tlsKeystore;
  }

  public void setTlsKeystore(String tlsKeystore) {
    this.tlsKeystore = tlsKeystore;
  }

  public String getTlsSecret() {
    return tlsSecret;
  }

  public void setTlsSecret(String tlsSecret) {
    this.tlsSecret = tlsSecret;
  }

  public String getTlsProvider() {
    return tlsProvider;
  }

  public void setTlsProvider(String tlsProvider) {
    this.tlsProvider = tlsProvider;
  }

  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }
}
