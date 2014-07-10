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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.apache.james.bond.TestConst;
import org.apache.james.bond.shared.BondConst;
import org.junit.Before;
import org.junit.Test;

public class LmtpEditorTest {
  private LmtpEditor lmtpEditor;

  @Before
  public void setup() {
    new File(TestConst.TEST_CONF_FOLDER).mkdirs();
    BondConst.JAMES_CONF = TestConst.TEST_CONF_FOLDER;
    lmtpEditor = new LmtpEditor();
  }

  @Test
  public void testReadXml() throws Exception {
    File conf = new File(TestConst.TEST_CONF_FOLDER + "/"
        + BondConst.NAME_TEMPL_LMTP + ".conf");
    conf.delete();

    Lmtp lmtp;
    lmtp = lmtpEditor.readProtocol();

    assertTrue(conf.exists());

    assertFalse(lmtp.isEnabled());
    assertEquals("127.0.0.1:24", lmtp.getBind());
    assertEquals("200", lmtp.getConnectionBacklog());
    assertEquals("1200", lmtp.getConnectionTimeout());
    assertEquals("0", lmtp.getConnectionLimit());
    assertEquals("0", lmtp.getConnectionLimitPerIp());
    assertTrue(lmtp.isHelloNameAutodetect());
    assertNull(lmtp.getHelloNameValue());
    assertFalse(lmtp.isTlsSocket());
    assertFalse(lmtp.isTlsStart());
    assertNull(lmtp.getTlsKeystore());
    assertNull(lmtp.getTlsSecret());
    assertNull(lmtp.getTlsProvider());

    assertEquals("0", lmtp.getMaximumMessageSize());
    assertNull(lmtp.getGreeting());

    lmtp.setConnectionBacklog("300");
    lmtp.setConnectionTimeout("1400");
    lmtp.setHelloNameAutodetect(false);
    lmtp.setHelloNameValue("Hello");

    lmtp.setMaximumMessageSize("30");
    lmtp.setGreeting("James Lmtp Hello");

    lmtpEditor.writeProtocol(lmtp);

    lmtp = lmtpEditor.readProtocol();

    assertEquals("300", lmtp.getConnectionBacklog());
    assertEquals("1400", lmtp.getConnectionTimeout());
    assertFalse(lmtp.isHelloNameAutodetect());
    assertEquals("Hello", lmtp.getHelloNameValue());

    assertEquals("30", lmtp.getMaximumMessageSize());
    assertEquals("James Lmtp Hello", lmtp.getGreeting());
  }
}
