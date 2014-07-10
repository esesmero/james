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
 */
package org.apache.james.bond.shared;


/**
 * abstract class to define static stuff used anywhere in the app
 */
public abstract class BondConst {
  
  public static final String BOND = "Apache James Bond"; 
  
  public static String JAMES_CONF = "/opt/apache-james-3.0-beta4/conf";
  public static String JAMES_ADDRESS = "127.0.0.1";
  public static int JAMES_PORT = 9999;
  
  public static final String SYSPROP_JAMES_CONF = "james.conf";
  public static final String SYSPROP_JAMES_JMX = "james.jmx";
  public static final String SYSPROP_AUTHORIZED_IPS = "ip.range";
  
  public static final String NAME_TEMPL_DNS = "dnsservice";
  public static final String NAME_TEMPL_POP3 = "pop3server";
  public static final String NAME_TEMPL_SMTP = "smtpserver";
  public static final String NAME_TEMPL_LMTP = "lmtpserver";
  public static final String NAME_TEMPL_IMAP = "imapserver";
}
