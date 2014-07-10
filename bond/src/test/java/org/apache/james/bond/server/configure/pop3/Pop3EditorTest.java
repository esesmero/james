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
package org.apache.james.bond.server.configure.pop3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.apache.james.bond.TestConst;
import org.apache.james.bond.shared.BondConst;
import org.junit.Before;
import org.junit.Test;

public class Pop3EditorTest {
  private Pop3Editor pop3Editor;
  
  @Before
  public void setup() {
    new File(TestConst.TEST_CONF_FOLDER).mkdirs();
    BondConst.JAMES_CONF = TestConst.TEST_CONF_FOLDER;
    pop3Editor = new Pop3Editor();
  }

  @Test
  public void testReadXml() throws Exception {
    File conf = new File(TestConst.TEST_CONF_FOLDER + "/"
        + BondConst.NAME_TEMPL_POP3 + ".conf");
    conf.delete();

    Pop3 pop3;
    pop3 = pop3Editor.readProtocol();
    
    assertTrue(conf.exists());

    assertTrue(pop3.isEnabled());
    assertEquals("0.0.0.0:110", pop3.getBind());
    assertEquals("200", pop3.getConnectionBacklog());
    assertEquals("1200", pop3.getConnectionTimeout());
    assertEquals("0", pop3.getConnectionLimit());
    assertEquals("0", pop3.getConnectionLimitPerIp());
    assertTrue(pop3.isHelloNameAutodetect());
    assertNull(pop3.getHelloNameValue());
    assertFalse(pop3.isTlsSocket());
    assertFalse(pop3.isTlsStart());
    assertEquals("file://conf/keystore", pop3.getTlsKeystore());
    assertEquals("yoursecret", pop3.getTlsSecret());
    assertEquals("org.bouncycastle.jce.provider.BouncyCastleProvider",
        pop3.getTlsProvider());

    // pop3.setEnabled(false);
    pop3.setConnectionBacklog("300");
    pop3.setConnectionTimeout("1400");
    pop3.setHelloNameAutodetect(false);
    pop3.setHelloNameValue("Hello");

    pop3Editor.writeProtocol(pop3);

    pop3 = pop3Editor.readProtocol();

    // assertFalse(pop3.isEnabled());
    assertEquals("300", pop3.getConnectionBacklog());
    assertEquals("1400", pop3.getConnectionTimeout());
    assertFalse(pop3.isHelloNameAutodetect());
    assertEquals("Hello", pop3.getHelloNameValue());
  }
}