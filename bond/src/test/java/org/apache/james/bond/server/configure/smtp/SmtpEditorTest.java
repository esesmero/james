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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.apache.james.bond.TestConst;
import org.apache.james.bond.shared.BondConst;
import org.junit.Before;
import org.junit.Test;

public class SmtpEditorTest {
  private SmtpEditor smtpEditor;

  @Before
  public void setup() {
    new File(TestConst.TEST_CONF_FOLDER).mkdirs();
    BondConst.JAMES_CONF = TestConst.TEST_CONF_FOLDER;
    smtpEditor = new SmtpEditor();
  }

  @Test
  public void testReadXml() throws Exception {
    File conf = new File(TestConst.TEST_CONF_FOLDER + "/"
        + BondConst.NAME_TEMPL_SMTP + ".conf");
    conf.delete();

    Smtp smtp;
    smtp = smtpEditor.readProtocol();

    assertTrue(conf.exists());

    assertTrue(smtp.isEnabled());
    assertEquals("0.0.0.0:25", smtp.getBind());
    assertEquals("200", smtp.getConnectionBacklog());
    assertEquals("360", smtp.getConnectionTimeout());
    assertEquals("0", smtp.getConnectionLimit());
    assertEquals("0", smtp.getConnectionLimitPerIp());
    assertTrue(smtp.isHelloNameAutodetect());
    assertNull(smtp.getHelloNameValue());
    assertFalse(smtp.isTlsSocket());
    assertFalse(smtp.isTlsStart());
    assertEquals("file://conf/keystore", smtp.getTlsKeystore());
    assertEquals("yoursecret", smtp.getTlsSecret());
    assertEquals("org.bouncycastle.jce.provider.BouncyCastleProvider",
        smtp.getTlsProvider());
    assertEquals("SunX509", smtp.getTlsAlgorithm());

    assertEquals("false", smtp.getAuthRequired());
    assertTrue(smtp.isVerifyIdentity());
    assertEquals("0", smtp.getMaximumMessageSize());
    assertTrue(smtp.isHeloEhloEnforcement());
    assertTrue(smtp.isAddrBracketsEnforcement());
    assertNull(smtp.getGreeting());
    assertEquals("127.0.0.0/8", smtp.getAuthorizedAddresses());

    smtp.setConnectionBacklog("300");
    smtp.setConnectionTimeout("1400");
    smtp.setHelloNameAutodetect(false);
    smtp.setHelloNameValue("Hello");

    smtp.setAuthRequired("announce");
    smtp.setVerifyIdentity(false);
    smtp.setMaximumMessageSize("30");
    smtp.setHeloEhloEnforcement(false);
    smtp.setAddrBracketsEnforcement(false);
    smtp.setGreeting("James Smtp Hello");
    smtp.setAuthorizedAddresses("127.0.0.0/255.0.0.0");

    smtpEditor.writeProtocol(smtp);

    smtp = smtpEditor.readProtocol();

    assertEquals("300", smtp.getConnectionBacklog());
    assertEquals("1400", smtp.getConnectionTimeout());
    assertFalse(smtp.isHelloNameAutodetect());
    assertEquals("Hello", smtp.getHelloNameValue());

    assertEquals("announce", smtp.getAuthRequired());
    assertFalse(smtp.isVerifyIdentity());
    assertEquals("30", smtp.getMaximumMessageSize());
    assertFalse(smtp.isHeloEhloEnforcement());
    assertFalse(smtp.isAddrBracketsEnforcement());
    assertEquals("James Smtp Hello", smtp.getGreeting());
    assertEquals("127.0.0.0/255.0.0.0", smtp.getAuthorizedAddresses());
  }
}
