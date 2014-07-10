package org.apache.james.bond.server.servlet;

import static org.apache.james.bond.shared.BondConst.*;

import java.io.File;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.james.bond.server.JamesConnector;

/**
 * Filter to authorize certain ip addresses requests.
 * 
 * Configure the filter in the web.xml with the parameter 'ip.range'
 * or using the System property 'ip.range'
 */
public class JamesBondFilter implements Filter {

  private String allowed_ips_regex = "127.0.0.1";

  /**
   * check if IP address match pattern
   * 
   * @param pattern *, *.*.*.*, 192.168.1.0-255
   * @param address n.n.n.n
   */
  public boolean checkIPMatching(String pattern, String address) {
    if (!address.equals("127.0.0.1") && !pattern.equals("*.*.*.*") && !pattern.equals("*")) {
      String[] mask = pattern.split("\\.");
      String[] ip_address = address.split("\\.");
      for (int i = 0; i < mask.length; i++) {
        if (mask[i].equals("*") || mask[i].equals(ip_address[i])) {
          continue;
        } else if (mask[i].contains("-")) {
          byte min = Byte.parseByte(mask[i].split("-")[0]);
          byte max = Byte.parseByte(mask[i].split("-")[1]);
          byte ip = Byte.parseByte(ip_address[i]);
          if (ip < min || ip > max) {
            return false;
          }
        } else {
          return false;
        }
      }
    }
    return true;
  }
  
  public boolean checkJamesJMXConnection() {
    try {
      return JamesConnector.getServerProbe() != null;
    } catch (Exception e) {
    }
    return false;
  }
  
  public boolean checkJamesConfFolder() {
    File conf = new File(JAMES_CONF);
    return conf.isDirectory() && conf.canWrite();
  }

  public void doFilter(ServletRequest servletRequest,
      ServletResponse servletResponse, FilterChain filterChain)
      throws IOException, ServletException {
    
    HttpServletResponse resp = (HttpServletResponse) servletResponse;
    HttpServletRequest req = (HttpServletRequest) servletRequest;
    String address = req.getRemoteAddr();
    
    if (!checkIPMatching(allowed_ips_regex, address)) {
      resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, 
          BOND + "\n\nYour Ip Address (" + address + ") does not match the range of authorized ip addresses." +
          getConfigurationHint(SYSPROP_AUTHORIZED_IPS, allowed_ips_regex));
    } else if (!checkJamesConfFolder()) {
      resp.sendError(HttpServletResponse.SC_EXPECTATION_FAILED, 
          BOND + "\n\nFolder with James configuration files (" + JAMES_CONF + ") is not writable." +
          getConfigurationHint(SYSPROP_JAMES_CONF, JAMES_CONF));
    } else if (!checkJamesJMXConnection()) {
      resp.sendError(HttpServletResponse.SC_EXPECTATION_FAILED, 
          BOND + "\n\nUnable to connect with the JMX service (" + JAMES_ADDRESS + ":"  + JAMES_PORT + ")." +
          "\n\n-- make sure James is running." +
          getConfigurationHint(SYSPROP_JAMES_JMX, JAMES_ADDRESS + ":" + JAMES_PORT));
    } else if (filterChain != null) {
      filterChain.doFilter(req, resp);
    }
  }
  

  @Override
  public void destroy() {
  }
  
  private String getConfigurationHint(String prop, String value) {
    return "\n\n-- run Bond with the parameter:\n -D" + prop + "=" + value +
           "\n\n-- or configure your web.xml file appropriately:" +
           "\n <env-entry>" + 
           "\n  <env-entry-name>" + prop + "</env-entry-name>" + 
           "\n  <env-entry-value>" + value + "</env-entry-value>" +
           "\n  <env-entry-type>java.lang.String</env-entry-type>" +
           "\n </evn-entry>";
  }
 
  @Override
  public void init(FilterConfig config) throws ServletException {
    String value = getProperty(config, SYSPROP_AUTHORIZED_IPS);
    if (!value.isEmpty()) {
      allowed_ips_regex = value;
    }

    value = getProperty(config, SYSPROP_JAMES_JMX);
    if (!value.isEmpty()) {
      String arr[] = value.split(":");
      JAMES_ADDRESS = arr[0];
      if (arr.length > 1) {
        JAMES_PORT = Integer.parseInt(arr[1]);
      }
    }

    value = getProperty(config, SYSPROP_JAMES_CONF);
    if (!value.isEmpty()) {
      JAMES_CONF = value;
    }
  }
  
  private String getProperty(FilterConfig config, String name) {
    String value = System.getProperty(name);
    if (value == null || value.isEmpty()) {
      value = config.getServletContext().getInitParameter(name);
    }
    if (value == null || value.isEmpty()) {
      value = config.getInitParameter(name);
    }
    return value == null ? "" : value;
  }
}