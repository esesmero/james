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
package org.apache.james.bond.server.configure.dns;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.apache.james.bond.TestConst;
import org.apache.james.bond.server.configure.dns.DNS;
import org.apache.james.bond.server.configure.dns.DNSEditor;
import org.apache.james.bond.shared.BondConst;
import org.junit.Before;
import org.junit.Test;

public class DNSEditorTest {

  @Before
  public void setup() {
    new File(TestConst.TEST_CONF_FOLDER).mkdirs();
    BondConst.JAMES_CONF = TestConst.TEST_CONF_FOLDER;
  }

  @Test
  public void testReadXml() throws Exception {
    File conf = new File(TestConst.TEST_CONF_FOLDER + "/"
        + BondConst.NAME_TEMPL_DNS + ".conf");
    conf.delete();

    DNS dns = DNSEditor.readProtocol();

    assertTrue(conf.exists());

    List<String> dnsServerList = dns.getServersIp();
    assertTrue(dnsServerList.isEmpty());

    assertTrue(dns.isAutodiscover());
    assertFalse(dns.isAuthoritative());
    assertFalse(dns.isSingleIPperMX());
    assertEquals("50000", dns.getMaxCacheSize());
    assertEquals(0, dns.getServersIp().size());

    dns.setAutodiscover(false);
    dns.setAuthoritative(true);
    dns.setSingleIPperMX(true);
    dns.setMaxCacheSize("10000");
    dns.setServersIp(Arrays.asList("127.0.0.1", "192.168.1.1"));

    DNSEditor.writeProtocol(dns);

    dns = DNSEditor.readProtocol();
    assertFalse(dns.isAutodiscover());
    assertTrue(dns.isAuthoritative());
    assertTrue(dns.isSingleIPperMX());
    assertEquals("10000", dns.getMaxCacheSize());
    assertEquals(2, dns.getServersIp().size());
    assertTrue(dns.getServersIp().contains("127.0.0.1"));
    assertTrue(dns.getServersIp().contains("192.168.1.1"));

    dns.setServersIp(Arrays.asList("127.0.0.1", "192.168.1.1", "10.0.0.1"));
    assertEquals(3, dns.getServersIp().size());
    assertTrue(dns.getServersIp().contains("127.0.0.1"));
    assertTrue(dns.getServersIp().contains("192.168.1.1"));
    assertTrue(dns.getServersIp().contains("10.0.0.1"));

    DNSEditor.writeProtocol(dns);
    dns = DNSEditor.readProtocol();
    assertFalse(dns.isAutodiscover());
    assertTrue(dns.isAuthoritative());
    assertTrue(dns.isSingleIPperMX());
    assertEquals("10000", dns.getMaxCacheSize());
    assertEquals(3, dns.getServersIp().size());

    conf.delete();
  }
}