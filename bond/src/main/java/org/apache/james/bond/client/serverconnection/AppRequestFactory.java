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

import java.util.List;

import org.apache.james.bond.server.configure.dns.DNSService;
import org.apache.james.bond.server.configure.dns.DNSServiceLocator;
import org.apache.james.bond.server.configure.imap.ImapService;
import org.apache.james.bond.server.configure.imap.ImapServiceLocator;
import org.apache.james.bond.server.configure.lmtp.LmtpService;
import org.apache.james.bond.server.configure.lmtp.LmtpServiceLocator;
import org.apache.james.bond.server.configure.pop3.Pop3Service;
import org.apache.james.bond.server.configure.pop3.Pop3ServiceLocator;
import org.apache.james.bond.server.configure.smtp.SmtpService;
import org.apache.james.bond.server.configure.smtp.SmtpServiceLocator;
import org.apache.james.bond.server.manage.domain.DomainService;
import org.apache.james.bond.server.manage.domain.DomainServiceLocator;
import org.apache.james.bond.server.manage.mappings.MappingService;
import org.apache.james.bond.server.manage.mappings.MappingServiceLocator;
import org.apache.james.bond.server.manage.user.UserService;
import org.apache.james.bond.server.manage.user.UserServiceLocator;
import org.apache.james.bond.server.monitor.MonitoringService;
import org.apache.james.bond.server.monitor.MonitoringServiceLocator;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.RequestFactory;
import com.google.web.bindery.requestfactory.shared.Service;

/**
 * {@link RequestFactory} that manages all the connections with the server
 */
public interface AppRequestFactory extends RequestFactory {

  UserRequest createUserRequest();

  @Service(value = UserService.class, locator = UserServiceLocator.class)
  public interface UserRequest extends RequestContext {
    /**
     * It retrieves all the users from the server
     * 
     * @return
     */
    Request<List<UserProxy>> listUsers();

    /**
     * It saves the user if it does not exist
     * 
     * @param user
     * @return
     */
    Request<Void> persist(UserProxy user);

    /**
     * It removes the user from the server
     * 
     * @param user
     * @return
     */
    Request<Void> remove(UserProxy user);

    /**
     * 
     * @param user
     * @return
     */
    Request<Void> changePassword(UserProxy user);
  }

  DomainRequest createDomainRequest();

  @Service(value = DomainService.class, locator = DomainServiceLocator.class)
  public interface DomainRequest extends RequestContext {
    /**
     * It retrieves all the domains from the server
     * 
     * @return
     */
    Request<List<DomainProxy>> listDomains();

    /**
     * It saves the domain in the server
     * 
     * @param domain
     * @return
     */
    Request<Void> persist(DomainProxy domain);

    /**
     * It removes the domain from the server
     * 
     * @param domain
     * @return
     */
    Request<Void> remove(DomainProxy domain);
  }

  MappingRequest createMappingRequest();

  @Service(value = MappingService.class, locator = MappingServiceLocator.class)
  public interface MappingRequest extends RequestContext {
    /**
     * It retrieves all the mappings from the server
     * 
     * @return
     */
    Request<List<MappingProxy>> listMappings();

    /**
     * It adds a new address mapping to the user and domain received
     * 
     * @param user
     * @param domain
     * @param address
     * @return
     */
    Request<Void> addAddressMapping(String user, String domain, String address);

    /**
     * It removes the address mapping of the user and domain received
     * 
     * @param user
     * @param domain
     * @param address
     * @return
     */
    Request<Void> removeAddressMapping(String user, String domain,
        String address);

    /**
     * It adds a new regular expression mapping to the user and domain received
     * 
     * @param user
     * @param domain
     * @param regex
     * @return
     */
    Request<Void> addRegexMapping(String user, String domain, String regex);

    /**
     * It removes the regular expression mapping of the user and domain received
     * 
     * @param user
     * @param domain
     * @param regex
     * @return
     */
    Request<Void> removeRegexMapping(String user, String domain, String regex);

  }

  DnsRequest createDnsRequest();

  @Service(value = DNSService.class, locator = DNSServiceLocator.class)
  public interface DnsRequest extends RequestContext {
    /**
     * It retrieves all the information of the DNS protocol
     * 
     * @return
     */
    Request<DNSProxy> getAllDNSData();

    /**
     * It saves all the changes made on the {@link DNSProxy}
     * 
     * @param dns
     * @return
     */
    Request<Void> persist(DNSProxy dns);
  }

  Pop3Request createPop3Request();

  @Service(value = Pop3Service.class, locator = Pop3ServiceLocator.class)
  public interface Pop3Request extends RequestContext {
    /**
     * It retrieves all the information of the POP3 protocol
     * 
     * @return
     */
    Request<Pop3Proxy> getAllPop3Data();

    /**
     * It saves all the changes made on the {@link Pop3Proxy}
     * 
     * @param pop3
     * @return
     */
    Request<Void> persist(Pop3Proxy pop3);
  }

  SmtpRequest createSmtpRequest();

  @Service(value = SmtpService.class, locator = SmtpServiceLocator.class)
  public interface SmtpRequest extends RequestContext {
    /**
     * It retrieves all the information of the SMTP protocol
     * 
     * @return
     */
    Request<SmtpProxy> getAllSmtpData();

    /**
     * It saves all the changes made on the {@link SmtpProxy}
     * 
     * @param smtp
     * @return
     */
    Request<Void> persist(SmtpProxy smtp);
  }

  LmtpRequest createLmtpRequest();

  @Service(value = LmtpService.class, locator = LmtpServiceLocator.class)
  public interface LmtpRequest extends RequestContext {
    /**
     * It retrieves all the information of the LMTP protocol
     * 
     * @return
     */
    Request<LmtpProxy> getAllLmtpData();

    /**
     * It saves all the changes made on the {@link LmtpProxy}
     * 
     * @param lmtp
     * @return
     */
    Request<Void> persist(LmtpProxy lmtp);
  }

  ImapRequest createImapRequest();

  @Service(value = ImapService.class, locator = ImapServiceLocator.class)
  public interface ImapRequest extends RequestContext {
    /**
     * It retrieves all the information of the IMAP protocol
     * 
     * @return
     */
    Request<ImapProxy> getAllImapData();

    /**
     * It saves all the changes made on the {@link ImapProxy}
     * 
     * @param imap
     * @return
     */
    Request<Void> persist(ImapProxy imap);
  }

  MonitoringRequest createMonitoringRequest();

  @Service(value = MonitoringService.class, locator = MonitoringServiceLocator.class)
  public interface MonitoringRequest extends RequestContext {
    /**
     * It retrieves all the monitoring information that the server supply
     * 
     * @return
     */
    Request<MonitoringProxy> getMonitoringInformation();
  }
}
