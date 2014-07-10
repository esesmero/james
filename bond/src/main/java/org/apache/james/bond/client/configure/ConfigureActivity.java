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
package org.apache.james.bond.client.configure;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.apache.james.bond.client.CommonActivity;
import org.apache.james.bond.client.CommonTabsView;
import org.apache.james.bond.client.configure.dns.ConfigureDNSView;
import org.apache.james.bond.client.configure.imap.ConfigureImapView;
import org.apache.james.bond.client.configure.lmtp.ConfigureLmtpView;
import org.apache.james.bond.client.configure.pop3.ConfigurePop3View;
import org.apache.james.bond.client.configure.smtp.ConfigureSmtpView;
import org.apache.james.bond.client.ioc.ClientFactory;
import org.apache.james.bond.client.serverconnection.AppRequestFactory.DnsRequest;
import org.apache.james.bond.client.serverconnection.AppRequestFactory.ImapRequest;
import org.apache.james.bond.client.serverconnection.AppRequestFactory.LmtpRequest;
import org.apache.james.bond.client.serverconnection.AppRequestFactory.Pop3Request;
import org.apache.james.bond.client.serverconnection.AppRequestFactory.SmtpRequest;
import org.apache.james.bond.client.serverconnection.BasicReceiver;
import org.apache.james.bond.client.serverconnection.DNSProxy;
import org.apache.james.bond.client.serverconnection.ImapProxy;
import org.apache.james.bond.client.serverconnection.LmtpProxy;
import org.apache.james.bond.client.serverconnection.Pop3Proxy;
import org.apache.james.bond.client.serverconnection.SmtpProxy;

import com.google.gwt.core.shared.GWT;
import com.google.web.bindery.requestfactory.shared.Receiver;

/**
 * This class is a {@link CommonActivity} that contains all the tabs needed to
 * configure the server: one tab for each protocol.
 */
public class ConfigureActivity extends CommonActivity implements
    ConfigureDNSView.Presenter, ConfigurePop3View.Presenter,
    ConfigureSmtpView.Presenter, ConfigureLmtpView.Presenter,
    ConfigureImapView.Presenter {
  private ConfigureDNSView dnsView;
  private ConfigurePop3View pop3View;
  private ConfigureSmtpView smtpView;
  private ConfigureLmtpView lmtpView;
  private ConfigureImapView imapView;
  private DnsRequest editableDnsRequest;
  private DNSProxy editableDnsProxy;
  private Pop3Request editablePop3Request;
  private Pop3Proxy editablePop3Proxy;
  private SmtpRequest editableSmtpRequest;
  private SmtpProxy editableSmtpProxy;
  private LmtpRequest editableLmtpRequest;
  private LmtpProxy editableLmtpProxy;
  private ImapRequest editableImapRequest;
  private ImapProxy editableImapProxy;

  /**
   * It creates a new ConfigureActivity setting the presenter of the views for
   * each protocol. It retrieves the current configuration and shows it in the
   * views.
   * 
   * @see CommonActivity
   * 
   * @param place
   * @param clientFactory
   */
  public ConfigureActivity(ConfigurePlace place, ClientFactory clientFactory) {
    super(place, clientFactory);
    dnsView = clientFactory.getConfigureDNSView();
    pop3View = clientFactory.getConfigurePop3View();
    smtpView = clientFactory.getConfigureSmtpView();
    lmtpView = clientFactory.getConfigureLmtpView();
    imapView = clientFactory.getConfigureImapView();

    dnsView.setPresenter(this);
    getAllDNSData();

    pop3View.setPresenter(this);
    getAllPop3Data();

    smtpView.setPresenter(this);
    getAllSmtpData();

    lmtpView.setPresenter(this);
    getAllLmtpData();

    imapView.setPresenter(this);
    getAllImapData();
  }

  @Override
  public void getAllDNSData() {
    Receiver<DNSProxy> rec = new BasicReceiver<DNSProxy>() {
      @Override
      public void onSuccess(DNSProxy dns) {
        editableDnsRequest = requestFactory.createDnsRequest();
        editableDnsProxy = editableDnsRequest.edit(dns);
        if (GWT.isClient())
          dnsView.fillData(editableDnsProxy);
      }
    };

    this.requestFactory.createDnsRequest().getAllDNSData().fire(rec);
  }

  @Override
  public void saveDNS(final DNSProxy dns) {
    Receiver<Void> rec = new SaveReceiver(dnsView) {
      @Override
      public void onSuccess(Void voidDns) {
        editableDnsRequest = requestFactory.createDnsRequest();
        editableDnsProxy = editableDnsRequest.edit(dns);
        if (GWT.isClient()) {
          dnsView.publishSaved();
          dnsView.fillData(editableDnsProxy);
        }
      }
    };

    editableDnsRequest.persist(dns).fire(rec);
  }

  @Override
  public void getAllPop3Data() {
    Receiver<Pop3Proxy> rec = new BasicReceiver<Pop3Proxy>() {
      @Override
      public void onSuccess(Pop3Proxy pop3) {
        editablePop3Request = requestFactory.createPop3Request();
        editablePop3Proxy = editablePop3Request.edit(pop3);
        if (GWT.isClient())
          pop3View.fillData(editablePop3Proxy);
      }
    };

    this.requestFactory.createPop3Request().getAllPop3Data().fire(rec);
  }

  @Override
  public void savePop3(final Pop3Proxy pop3) {
    Receiver<Void> rec = new SaveReceiver(pop3View) {
      @Override
      public void onSuccess(Void voidDns) {
        editablePop3Request = requestFactory.createPop3Request();
        editablePop3Proxy = editablePop3Request.edit(pop3);
        if (GWT.isClient()) {
          pop3View.publishSaved();
          pop3View.fillData(editablePop3Proxy);
        }
      }
    };

    editablePop3Request.persist(pop3).fire(rec);
  }

  @Override
  public void getAllSmtpData() {
    Receiver<SmtpProxy> rec = new BasicReceiver<SmtpProxy>() {
      @Override
      public void onSuccess(SmtpProxy smtp) {
        editableSmtpRequest = requestFactory.createSmtpRequest();
        editableSmtpProxy = editableSmtpRequest.edit(smtp);
        if (GWT.isClient())
          smtpView.fillData(editableSmtpProxy);
      }
    };

    this.requestFactory.createSmtpRequest().getAllSmtpData().fire(rec);
  }

  @Override
  public void saveSmtp(final SmtpProxy smtp) {
    Receiver<Void> rec = new SaveReceiver(smtpView) {
      @Override
      public void onSuccess(Void voidSmtp) {
        editableSmtpRequest = requestFactory.createSmtpRequest();
        editableSmtpProxy = editableSmtpRequest.edit(smtp);
        if (GWT.isClient()) {
          smtpView.publishSaved();
          smtpView.fillData(editableSmtpProxy);
        }
      }
    };

    editableSmtpRequest.persist(smtp).fire(rec);
  }

  @Override
  public void getAllLmtpData() {
    Receiver<LmtpProxy> rec = new BasicReceiver<LmtpProxy>() {
      @Override
      public void onSuccess(LmtpProxy lmtp) {
        editableLmtpRequest = requestFactory.createLmtpRequest();
        editableLmtpProxy = editableLmtpRequest.edit(lmtp);
        if (GWT.isClient()) {
          lmtpView.fillData(editableLmtpProxy);
        }
      }
    };

    this.requestFactory.createLmtpRequest().getAllLmtpData().fire(rec);
  }

  @Override
  public void saveLmtp(final LmtpProxy lmtp) {
    Receiver<Void> rec = new SaveReceiver(lmtpView) {
      @Override
      public void onSuccess(Void voidSmtp) {
        editableLmtpRequest = requestFactory.createLmtpRequest();
        editableLmtpProxy = editableLmtpRequest.edit(lmtp);
        if (GWT.isClient()) {
          lmtpView.publishSaved();
          lmtpView.fillData(editableLmtpProxy);
        }
      }
    };

    editableLmtpRequest.persist(lmtp).fire(rec);
  }

  @Override
  public void getAllImapData() {
    Receiver<ImapProxy> rec = new BasicReceiver<ImapProxy>() {
      @Override
      public void onSuccess(ImapProxy imap) {
        editableImapRequest = requestFactory.createImapRequest();
        editableImapProxy = editableImapRequest.edit(imap);
        if (GWT.isClient()) {
          imapView.fillData(editableImapProxy);
        }
      }
    };

    this.requestFactory.createImapRequest().getAllImapData().fire(rec);
  }

  @Override
  public void saveImap(final ImapProxy imap) {
    Receiver<Void> rec = new SaveReceiver(imapView) {
      @Override
      public void onSuccess(Void voidImap) {
        editableImapRequest = requestFactory.createImapRequest();
        editableImapProxy = editableImapRequest.edit(imap);
        if (GWT.isClient()) {
          imapView.publishSaved();
          imapView.fillData(editableImapProxy);
        }
      }
    };

    editableImapRequest.persist(imap).fire(rec);
  }

  @Override
  protected CommonTabsView createMainView(ClientFactory clientFactory,
      String place) {
    return clientFactory.getConfigureView(place);
  }

  abstract class SaveReceiver extends BasicReceiver<Void> {
    private ConfigureProtocolView view;

    public SaveReceiver(ConfigureProtocolView view) {
      super();
      this.view = view;
    }

    @Override
    public void onConstraintViolation(Set<ConstraintViolation<?>> violations) {
      String msg = "";
      for (ConstraintViolation<?> violation : violations) {
        msg += violation.getMessage() + "\n";
      }
      view.publishValidationError(msg);
    }
  }
}