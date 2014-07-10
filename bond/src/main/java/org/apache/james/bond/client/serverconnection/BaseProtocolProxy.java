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
package org.apache.james.bond.client.serverconnection;

import com.google.web.bindery.requestfactory.shared.EntityProxy;

public interface BaseProtocolProxy extends EntityProxy {
  public boolean isEnabled();

  public void setEnabled(boolean enabled);

  public String getBind();

  public void setBind(String bind);

  public String getConnectionBacklog();

  public void setConnectionBacklog(String connectionBacklog);

  public String getConnectionTimeout();

  public void setConnectionTimeout(String connectionTimeout);

  public String getConnectionLimit();

  public void setConnectionLimit(String connectionLimit);

  public String getConnectionLimitPerIp();

  public void setConnectionLimitPerIp(String connectionLimitPerIp);

  public boolean isHelloNameAutodetect();

  public void setHelloNameAutodetect(boolean autodetect);

  public String getHelloNameValue();

  public void setHelloNameValue(String value);

  public boolean isTlsSocket();

  public void setTlsSocket(boolean tlsSocket);

  public boolean isTlsStart();

  public void setTlsStart(boolean tlsStart);

  public String getTlsKeystore();

  public void setTlsKeystore(String tlsKeystore);

  public String getTlsSecret();

  public void setTlsSecret(String tlsSecret);

  public String getTlsProvider();

  public void setTlsProvider(String tlsProvider);
}