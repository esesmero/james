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
package org.apache.james.bond.client.manage;

import java.util.List;

import org.apache.james.bond.client.CommonActivity;
import org.apache.james.bond.client.CommonTabsView;
import org.apache.james.bond.client.ioc.ClientFactory;
import org.apache.james.bond.client.manage.domain.ManageDomainsTabView;
import org.apache.james.bond.client.manage.mappings.ManageMappingsView;
import org.apache.james.bond.client.manage.user.ManageUsersTabView;
import org.apache.james.bond.client.serverconnection.AppRequestFactory.DomainRequest;
import org.apache.james.bond.client.serverconnection.AppRequestFactory.MappingRequest;
import org.apache.james.bond.client.serverconnection.AppRequestFactory.UserRequest;
import org.apache.james.bond.client.serverconnection.BasicReceiver;
import org.apache.james.bond.client.serverconnection.DomainProxy;
import org.apache.james.bond.client.serverconnection.MappingProxy;
import org.apache.james.bond.client.serverconnection.UserProxy;

import com.google.web.bindery.requestfactory.shared.Receiver;

/**
 * This {@link CommonActivity} manages all the views to manage the server
 * retrieving, updating and saving users and domains data.
 */
public class ManageActivity extends CommonActivity implements
    ManageUsersTabView.Presenter, ManageDomainsTabView.Presenter,
    ManageMappingsView.Presenter {
  private ManageUsersTabView usersView;
  private ManageDomainsTabView domainsView;
  private ManageMappingsView mappingsView;

  /**
   * It creates a {@link ManageActivity} setting it as a presenter for the views
   * 
   * @see CommonActivity
   * 
   * @param place
   * @param clientFactory
   */
  public ManageActivity(ManagePlace place, ClientFactory clientFactory) {
    super(place, clientFactory);
    this.usersView = clientFactory.getManageUsersTabView();
    this.domainsView = clientFactory.getManageDomainsTabView();
    this.mappingsView = clientFactory.getManageMappingsView();

    usersView.setPresenter(this);
    domainsView.setPresenter(this);
    mappingsView.setPresenter(this);
  }

  /**
   * It displays all domains in console
   */
  public void printDomains() {
    Receiver<List<DomainProxy>> rec = new BasicReceiver<List<DomainProxy>>() {
      public void onSuccess(List<DomainProxy> domains) {
        for (DomainProxy domain : domains) {
          System.out.println(domain.getDomain());
        }
      }
    };
    this.requestFactory.createDomainRequest().listDomains().fire(rec);
  }

  /**
   * It displays all users in console
   */
  public void printUsers() {
    Receiver<List<UserProxy>> rec = new BasicReceiver<List<UserProxy>>() {
      public void onSuccess(List<UserProxy> users) {
        for (UserProxy user : users) {
          System.out.println(user.getUsername());
        }
      }
    };
    this.requestFactory.createUserRequest().listUsers().fire(rec);
  }

  /**
   * It displays all mappings in console
   */
  public void listMappings() {
    Receiver<List<MappingProxy>> rec = new BasicReceiver<List<MappingProxy>>() {
      public void onSuccess(List<MappingProxy> mappingList) {
        for (MappingProxy mapping : mappingList) {
          System.out.println(mapping.getUserAndDomain());
          for (String mappingString : mapping.getMappings()) {
            System.out.println("*" + mappingString);
          }
        }
      }
    };
    this.requestFactory.createMappingRequest().listMappings().fire(rec);
  }

  @Override
  public void deleteUser(UserProxy user) {
    UserRequest context = requestFactory.createUserRequest();
    UserProxy userToDelete = context.create(UserProxy.class);
    userToDelete.setUsername(user.getId());

    Receiver<Void> receiver = new BasicReceiver<Void>() {
      public void onSuccess(Void response) {
        usersView.updateData();
      }
    };
    context.remove(userToDelete).fire(receiver);
  }

  @Override
  public void addUser(String username, String password) {
    UserRequest context = requestFactory.createUserRequest();
    UserProxy user = context.create(UserProxy.class);
    user.setUsername(username);
    user.setPassword(password);

    Receiver<Void> receiver = new BasicReceiver<Void>() {
      public void onSuccess(Void response) {
        usersView.updateData();
      }
    };
    context.persist(user).fire(receiver);
  }

  @Override
  public void changePassword(UserProxy user, String newPassword) {
    UserRequest context = requestFactory.createUserRequest();
    UserProxy userToChange = context.create(UserProxy.class);
    userToChange.setUsername(user.getUsername());
    userToChange.setPassword(newPassword);
    context.changePassword(userToChange).fire();
  }

  @Override
  public void deleteDomain(DomainProxy domain) {
    DomainRequest context = requestFactory.createDomainRequest();
    DomainProxy domainToDelete = context.create(DomainProxy.class);
    domainToDelete.setDomain(domain.getDomain());

    Receiver<Void> receiver = new BasicReceiver<Void>() {
      public void onSuccess(Void response) {
        domainsView.updateData();
      }
    };
    context.remove(domainToDelete).fire(receiver);
  }

  @Override
  public void addDomain(String domainName) {
    DomainRequest context = requestFactory.createDomainRequest();
    DomainProxy domain = context.create(DomainProxy.class);
    domain.setDomain(domainName);

    Receiver<Void> receiver = new BasicReceiver<Void>() {
      public void onSuccess(Void response) {
        domainsView.updateData();
      }
    };
    context.persist(domain).fire(receiver);
  }

  @Override
  protected CommonTabsView createMainView(ClientFactory clientFactory,
      String place) {
    return clientFactory.getManageView(place);
  }

  @Override
  public void addAddressMapping(String user, String domain, String address) {
    MappingRequest context = requestFactory.createMappingRequest();

    Receiver<Void> receiver = new BasicReceiver<Void>() {
      public void onSuccess(Void response) {
        mappingsView.updateData();
      }
    };
    context.addAddressMapping(user, domain, address).fire(receiver);
  }

  @Override
  public void removeAddressMapping(String user, String domain, String address) {
    MappingRequest context = requestFactory.createMappingRequest();

    Receiver<Void> receiver = new BasicReceiver<Void>() {
      public void onSuccess(Void response) {
        mappingsView.updateData();
      }
    };
    context.removeAddressMapping(user, domain, address).fire(receiver);
  }

  @Override
  public void addRegexMapping(String user, String domain, String regex) {
    MappingRequest context = requestFactory.createMappingRequest();

    Receiver<Void> receiver = new BasicReceiver<Void>() {
      public void onSuccess(Void response) {
        mappingsView.updateData();
      }
    };
    context.addRegexMapping(user, domain, regex).fire(receiver);
  }

  @Override
  public void removeRegexMapping(String user, String domain, String regex) {
    MappingRequest context = requestFactory.createMappingRequest();

    Receiver<Void> receiver = new BasicReceiver<Void>() {
      public void onSuccess(Void response) {
        mappingsView.updateData();
      }
    };
    context.removeRegexMapping(user, domain, regex).fire(receiver);
  }
}
