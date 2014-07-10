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
package org.apache.james.bond.client;

public abstract class BondHelp {

  /**
   * Returns the help information for the DNS service
   * 
   * @return
   */
  public static String getDnsServiceHelp() {
    return "Specifies DNS Server information for use by various components "
        + "inside James.\nIf autodiscover is true, James will attempt to "
        + "autodiscover the DNS servers configured on your underlying system."
        + "\nCurrently, this works if the OS has a unix-like /etc/resolv.conf, "
        + "or the system is Windows based with ipconfig or winipcfg.\nIf no "
        + "DNS servers are found and you have not specified any, "
        + "127.0.0.1 will be used\nIf you use autodiscover and add DNS servers "
        + "manually a combination of all the dns servers will be used"
        + "\nInformation includes a list of DNS Servers to be used by James.";
  }

  /**
   * Returns the help information for the autodiscovery field of the DNS
   * protocol
   * 
   * @return
   */
  public static String getDnsAutodiscoveryHelp() {
    return "Change autodiscover to false if you would like to turn off "
        + "autodiscovery and set the DNS servers manually in the servers"
        + " section";
  }

  /**
   * Returns the help information for the authoritative field of the DNS
   * protocol
   * 
   * @return
   */
  public static String getDnsAuthoritativeHelp() {
    return "It specifies whether or not to require authoritative (non-cached)"
        + " DNS records; to only accept DNS responses that are authoritative"
        + " for the domain. It is primarily useful in an intranet/extranet"
        + " environment. This should always be unchecked unless you understand"
        + " the implications.";
  }

  /**
   * Returns the help information for the maximum cache size field of the DNS
   * protocol
   * 
   * @return
   */
  public static String getDnsMaxCacheSizeHelp() {
    return "Maximum number of entries to maintain in the DNS cache";
  }

  /**
   * Returns the help information for the single ip per MX field of the DNS
   * protocol
   * 
   * @return
   */
  public static String getDnsSingleIpPerMxHelp() {
    return "Set true if you want James to try a single server for each"
        + " multihomed mx host.";
  }

  /**
   * Returns the help information for the servers ip field of the DNS protocol
   * 
   * @return
   */
  public static String getDnsServerHelp() {
    return "Enter ip address of your DNS server";
  }

  /**
   * Returns the help information for the delete field of the DNS protocol
   * 
   * @return
   */
  public static String getDnsDeteleServerHelp() {
    return "Delete selected ip server";
  }

  /**
   * Returns the help information for the Pop3 service
   * 
   * @return
   */
  public static String getPop3ServiceHelp() {
    return "Specifies Pop3 service information";
  }

  /**
   * Returns the help information for the bind port field of the Pop3 protocol
   * 
   * @return
   */
  public static String getPop3BindHelp() {
    return "Configure this to bind to a specific inetaddress\nport 995 is the "
        + "well-known/IANA registered port for POP3S ie over SSL/TLS\nport 110 "
        + "is the well-known/IANA registered port for Standard POP3 ";
  }

  /**
   * Returns the help information for the TLS field
   * 
   * @return
   */
  public static String getTLSHelp() {
    return "Enable this to support STARTTLS or SSL for the Socket.\nTo use this"
        + " you need to copy sunjce_provider.jar to /path/james/lib directory.";
  }

  /**
   * Returns the help information for the TLS keystore field
   * 
   * @return
   */
  public static String getTLSKeystoreHelp() {
    return "To create a new keystore execute: keytool -genkey -alias james"
        + " -keyalg RSA -keystore /path/to/james/conf/keystore";
  }

  /**
   * Returns the help information for the TLS algorithm field
   * 
   * @return
   */
  public static String getTLSAlgorithmHelp() {
    return "The algorithm is optional and only needs to be specified when using"
        + " something other than the Sun JCE provider - You could use IbmX509"
        + " with IBM Java runtime.";
  }

  /**
   * Returns the help information for the HelloName field
   * 
   * @return
   */
  public static String getHelloNameHelp() {
    return "This is the name used by the server to identify itself in this "
        + "protocol.  If autodetect is TRUE, the server will discover its own "
        + "host name and use that in the protocol.  If discovery fails, the "
        + "value of 'localhost' is used.  If autodetect is FALSE, James will "
        + "use the specified value.";
  }

  /**
   * Returns the help information for the enabled field
   * 
   * @return
   */
  public static String getEnabledHelp() {
    return "Set if this protocol is enabled";
  }

  /**
   * Returns the help information for the bind port field
   * 
   * @return
   */
  public static String getBindPortHelp() {
    return "Set the ip and port to bind the protocol";
  }

  /**
   * Returns the help information for the connection backlog field
   * 
   * @return
   */
  public static String getConnectionBacklogHelp() {
    return "Connection backlog";
  }

  /**
   * Returns the help information for the timeout field
   * 
   * @return
   */
  public static String getConnectionTimeoutHelp() {
    return "Connection timeout in secconds";
  }

  /**
   * Returns the help information for the connection limit field
   * 
   * @return
   */
  public static String getConnectionLimitHelp() {
    return "Set the maximum simultaneous incoming connections for this service";
  }

  /**
   * Returns the help information for the connection limit per ip field
   * 
   * @return
   */
  public static String getConnectionLimitPerIpHelp() {
    return "Set the maximum simultaneous incoming connections per IP for this"
        + " service";
  }

  /**
   * Returns the help information for the Smtp service
   * 
   * @return
   */
  public static String getSmtpServiceHelp() {
    return "It encloses all the relevant configuration for the SMTP server";
  }

  /**
   * Returns the help information for the bind port field for the SMTP protocol
   * 
   * @return
   */
  public static String getSmtpBindHelp() {
    return " Configure this to bind to a specific inetaddress\nPlease NOTE: "
        + "you should add this IP also to your RemoteAddrNotInNetwork in order "
        + "to avoid relay check for locallly generated bounces\nPort 25 is the "
        + "well-known/IANA registered port for SMTP.\nPort 465 is the "
        + "well-known/IANA registered port for SMTP over TLS.";
  }

  /**
   * Returns the help information for the authentication required field for the
   * SMTP protocol
   * 
   * @return
   */
  public static String getSmtpAuthRequiredHelp() {
    return "supported values:\ntrue: required but announced only to not "
        + "authorizedAddresses\nfalse: don't use AUTH\nannounce: like true, "
        + "but always announce AUTH capability to clients\nThe correct "
        + "behaviour per RFC value would be false or announce but we still "
        + "support true for backward compatibility and because some webmail "
        + "client fails when AUTH is announced but no authentication "
        + "information has been provided";
  }

  /**
   * Returns the help information for the authorized addresses field for the
   * SMTP protocol
   * 
   * @return
   */
  public static String getSmtpAuthorizedAddressesHelp() {
    return "This is used to authorize specific addresses/networks. If you use "
        + "SMTP AUTH, addresses that match those specified here will be "
        + "permitted to relay without SMTP AUTH.  If you do not use SMTP AUTH, "
        + "and you specify addreses here, then only addresses that match those "
        + "specified will be permitted to relay.\nAddresses may be specified "
        + "as a an IP address or domain name, with an optional netmask, "
        + "e.g.,\n127.*, 127.0.0.0/8, 127.0.0.0/255.0.0.0, and localhost/8 "
        + "are all the same\nSee also the RemoteAddrNotInNetwork matcher"
        + " in the transport processor. You would generally use one OR the"
        + " other approach.";
  }

  /**
   * Returns the help information for the verify identity field for the SMTP
   * protocol
   * 
   * @return
   */
  public static String getSmtpVerifyIdentityHelp() {
    return "Set true if you want to verify sender addresses, ensuring that "
        + "the sender address matches the user who has authenticated. This "
        + "prevents a user of your mail server from acting as someone else\n"
        + "If unspecified, default value is true";
  }

  /**
   * Returns the help information for the maximum message size field for the
   * SMTP protocol
   * 
   * @return
   */
  public static String getSmtpMaxMessageSizeHelp() {
    return "This sets the maximum allowed message size (in kilobytes) for this "
        + "SMTP service. The value defaults to 0, which means no limit.";
  }

  /**
   * Returns the help information for the HeloEhlo field for the SMTP protocol
   * 
   * @return
   */
  public static String getSmtpHeloEhloEnforcementHelp() {
    return "This sets whether to enforce the use of HELO/EHLO salutation before"
        + " a MAIL command is accepted. The value defaults to true";
  }

  /**
   * Returns the help information for the address brackets enforcement field for
   * the SMTP protocol
   * 
   * @return
   */
  public static String getSmtpAddressBracketsEnforcHelp() {
    return "WARNING: This is Non-RFC compliant (default value: true)\n"
        + "See: http://wiki.apache.org/james/StandardsComplianceStatement\n"
        + "TODO: CHANGE TO OFFICIAL URL LATER";
  }

  /**
   * Returns the help information for the greetings field for the SMTP protocol
   * 
   * @return
   */
  public static String getSmtpGreetingHelp() {
    return "This sets the SMTPGreeting which will be used when connect to the "
        + "smtpserver.\nIf none is specified a default is generated -";
  }

  /**
   * Returns the help information for the Lmtp service
   * 
   * @return
   */
  public static String getLmtpServiceHelp() {
    return "It encloses all the relevant configuration for the LMTP server";
  }

  /**
   * Returns the help information for the bind port field for the LMTP protocol
   * 
   * @return
   */
  public static String getLmtpBindHelp() {
    return "LMTP should not be reachable from outside your network so bind it "
        + "to loopback";
  }

  /**
   * Returns the help information for the maximum message size field for the
   * LMTP protocol
   * 
   * @return
   */
  public static String getLmtpMaxMessageSizeHelp() {
    return "This sets the maximum allowed message size (in kilobytes) for this"
        + " LMTP service. The value defaults to 0, which means no limit.";
  }

  /**
   * Returns the help information for the greetings field for the LMTP protocol
   * 
   * @return
   */
  public static String getLmtpGreetingsHelp() {
    return "This sets the LMTPGreeting which will be used when connect to the"
        + " lmtpserver.\nIf none is specified a default is generated";
  }

  /**
   * Returns the help information for the Imap service
   * 
   * @return
   */
  public static String getImapServiceHelp() {
    return "It encloses all the relevant configuration for the IMAP4 server";
  }

  /**
   * Returns the help information for the bind port field for the IMAP protocol
   * 
   * @return
   */
  public static String getImapBindHelp() {
    return "Configure this if you want to bind to a specific inetaddress\nport "
        + "143 is the well-known/IANA registered port for IMAP\nport 993 is the"
        + " well-known/IANA registered port for IMAPS  ie over SSL/TLS\nPlease "
        + "NOTE: you should add this IP also to your RemoteAddrNotInNetwork in "
        + "order to avoid relay check for locally generated bounces";
  }

  /**
   * Returns the help information for the compress field for the IMAP protocol
   * 
   * @return
   */
  public static String getImapCompressHelp() {
    return "Use or don't use COMPRESS extension";
  }

  /**
   * Returns the help information for the maximum line length field for the IMAP
   * protocol
   * 
   * @return
   */
  public static String getImapMaxLineLengthHelp() {
    return "Maximal allowed line-length before a BAD response will get returned"
        + " to the client. This should be set with caution as a too high value"
        + " can make the server a target for DOS (Denial of Service)!";
  }

  /**
   * Returns the help information for the in memory size limit field for the
   * IMAP protocol
   * 
   * @return
   */
  public static String getImapInMemorySizeLimitHelp() {
    return "10MB size limit before we will start to stream to a temporary file";
  }

}
