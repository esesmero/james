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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.apache.james.bond.TestConst;
import org.apache.james.bond.shared.BondConst;
import org.junit.Before;
import org.junit.Test;

public class ImapEditorTest {
  private ImapEditor imapEditor;

  @Before
  public void setup() {
    new File(TestConst.TEST_CONF_FOLDER).mkdirs();
    BondConst.JAMES_CONF = TestConst.TEST_CONF_FOLDER;
    imapEditor = new ImapEditor();
  }

  @Test
  public void testReadXml() throws Exception {
    File conf = new File(TestConst.TEST_CONF_FOLDER + "/"
        + BondConst.NAME_TEMPL_IMAP + ".conf");
    conf.delete();

    Imap imap;
    imap = imapEditor.readProtocol();

    assertTrue(conf.exists());

    assertTrue(imap.isEnabled());
    assertEquals("0.0.0.0:143", imap.getBind());
    assertEquals("200", imap.getConnectionBacklog());
    assertEquals("1800", imap.getConnectionTimeout());
    assertEquals("0", imap.getConnectionLimit());
    assertEquals("0", imap.getConnectionLimitPerIp());
    assertTrue(imap.isHelloNameAutodetect());
    assertNull(imap.getHelloNameValue());
    assertFalse(imap.isTlsSocket());
    assertFalse(imap.isTlsStart());
    assertEquals("file://conf/keystore", imap.getTlsKeystore());
    assertEquals("yoursecret", imap.getTlsSecret());
    assertEquals("org.bouncycastle.jce.provider.BouncyCastleProvider",
        imap.getTlsProvider());

    // imap.setConnectionBacklog(300);
    // imap.setConnectionTimeout(1400);
    // imap.setHelloNameAutodetect(false);
    // imap.setHelloNameValue("Hello");
    //
    // imapEditor.writeProtocol(imap);
    //
    // imap = imapEditor.readProtocol();
    //
    // assertEquals(300, imap.getConnectionBacklog());
    // assertEquals(1400, imap.getConnectionTimeout());
    // assertFalse(imap.isHelloNameAutodetect());
    // assertEquals("Hello", imap.getHelloNameValue());
  }
}
