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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.List;

import org.apache.james.bond.TestConst;
import org.apache.james.bond.client.ioc.ClientFactory;
import org.apache.james.bond.client.ioc.ClientFactoryTestImpl;
import org.apache.james.bond.client.serverconnection.AppRequestFactory.DnsRequest;
import org.apache.james.bond.client.serverconnection.AppRequestFactory.Pop3Request;
import org.apache.james.bond.client.serverconnection.AppRequestFactory.SmtpRequest;
import org.apache.james.bond.client.serverconnection.AppRequestFactory.UserRequest;
import org.apache.james.bond.shared.BondConst;
import org.junit.Before;
import org.junit.Test;

import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

public class AppRequestFactoryTest {
  ClientFactory clientFactory = new ClientFactoryTestImpl();
  AppRequestFactory requestFactory = clientFactory.getRequestFactory();

  @Before
  public void setup() {
    new File(TestConst.TEST_CONF_FOLDER).mkdirs();
    BondConst.JAMES_CONF = TestConst.TEST_CONF_FOLDER;
  }

  @Test
  public void userFunctionalityTest() {
    clearUser();
    addUser();
    removeUser();
  }

  private void clearUser() {
    Receiver<List<UserProxy>> rec = new Receiver<List<UserProxy>>() {
      public void onSuccess(List<UserProxy> users) {
        UserRequest context = requestFactory.createUserRequest();
        UserProxy user1234 = context.create(UserProxy.class);
        String username = "user1234@yo.es";
        user1234.setUsername(username);
        user1234.setPassword("1234");

        boolean contains = false;

        for (UserProxy user : users) {
          if (user.getId().equals(username)) {
            contains = true;
          }
        }

        if (contains) {
          context.remove(user1234).fire();
        }
      }

      public void onFailure(ServerFailure error) {
        String msg = "Server error: " + error.getMessage();
        System.err.println(msg);
      }
    };
    requestFactory.createUserRequest().listUsers().fire(rec);
  }

  private void addUser() {
    Receiver<List<UserProxy>> rec = new Receiver<List<UserProxy>>() {
      public void onSuccess(List<UserProxy> users) {
        UserRequest context = requestFactory.createUserRequest();
        UserProxy user1234 = context.create(UserProxy.class);
        String username = "user1234@yo.es";
        user1234.setUsername(username);
        user1234.setPassword("1234");

        boolean contains = false;

        for (UserProxy user : users) {

          if (user.getId().equals(username)) {
            contains = true;
          }
        }
        assertFalse(contains);

        context.persist(user1234).fire();
      }

      public void onFailure(ServerFailure error) {
        String msg = "Server error: " + error.getMessage();
        System.err.println(msg);
      }
    };
    requestFactory.createUserRequest().listUsers().fire(rec);
  }

  private void removeUser() {
    Receiver<List<UserProxy>> rec = new Receiver<List<UserProxy>>() {
      public void onSuccess(List<UserProxy> users) {
        UserRequest context = requestFactory.createUserRequest();
        UserProxy user1234 = context.create(UserProxy.class);
        String username = "user1234@yo.es";
        user1234.setUsername(username);
        user1234.setPassword("1234");

        boolean contains = false;

        for (UserProxy user : users) {

          if (user.getId().equals(username)) {
            contains = true;
          }
        }
        assertFalse(!contains);

        context.remove(user1234).fire();
      }

      public void onFailure(ServerFailure error) {
        String msg = "Server error: " + error.getMessage();
        System.err.println(msg);
      }
    };
    requestFactory.createUserRequest().listUsers().fire(rec);
  }

  DnsRequest editableDnsRequest;
  DNSProxy editableDnsProxy;

  @Test
  public void readDnsTest() {
    File conf = new File(TestConst.TEST_CONF_FOLDER + "/"
        + BondConst.NAME_TEMPL_DNS + ".conf");
    conf.delete();

    Receiver<DNSProxy> rec = new Receiver<DNSProxy>() {
      public void onSuccess(DNSProxy dns) {
        editableDnsRequest = requestFactory.createDnsRequest();
        editableDnsProxy = editableDnsRequest.edit(dns);

        List<String> dnsServerList = dns.getServersIp();
        for (String server : dnsServerList) {
          System.err.println(server);
        }
        assertTrue(dnsServerList.isEmpty());

        assertTrue(dns.isAutodiscover());
        assertFalse(dns.isAuthoritative());
        assertFalse(dns.isSingleIPperMX());
        assertEquals("50000", dns.getMaxCacheSize());
        assertEquals(0, dns.getServersIp().size());
      }

      public void onFailure(ServerFailure error) {
        String msg = "Server error: " + error.getMessage();
        fail(msg);
      }
    };

    this.requestFactory.createDnsRequest().getAllDNSData().fire(rec);
  }

  Pop3Request editablePop3Request;
  Pop3Proxy editablePop3Proxy;

  @Test
  public void readPop3Test() {
    File conf = new File(TestConst.TEST_CONF_FOLDER + "/"
        + BondConst.NAME_TEMPL_POP3 + ".conf");
    conf.delete();

    Receiver<Pop3Proxy> rec = new Receiver<Pop3Proxy>() {
      public void onSuccess(Pop3Proxy pop3) {
        editablePop3Request = requestFactory.createPop3Request();
        editablePop3Proxy = editablePop3Request.edit(pop3);

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
      }

      public void onFailure(ServerFailure error) {
        String msg = "Server error: " + error.getMessage();
        fail(msg);
      }
    };

    this.requestFactory.createPop3Request().getAllPop3Data().fire(rec);
  }

  SmtpRequest editableSmtpRequest;
  SmtpProxy editableSmtpProxy;

  @Test
  public void readSmtpTest() {
    File conf = new File(TestConst.TEST_CONF_FOLDER + "/"
        + BondConst.NAME_TEMPL_SMTP + ".conf");
    conf.delete();

    Receiver<SmtpProxy> rec = new Receiver<SmtpProxy>() {
      public void onSuccess(SmtpProxy smtp) {
        editableSmtpRequest = requestFactory.createSmtpRequest();
        editableSmtpProxy = editableSmtpRequest.edit(smtp);

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
      }

      public void onFailure(ServerFailure error) {
        String msg = "Server error: " + error.getMessage();
        fail(msg);
      }
    };

    this.requestFactory.createSmtpRequest().getAllSmtpData().fire(rec);
  }
}
