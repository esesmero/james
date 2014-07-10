package org.apache.james.bond.server.monitor;

import java.io.IOException;

import junit.framework.Assert;

import org.junit.Test;

public class JMXBondConnectorTest {
  @Test
  public void testConnection() {
    try {
      JMXBondConnector jmxConnector = new JMXBondConnector();
      
      Monitoring m = jmxConnector.getMonitoringInformation();
      
      Assert.assertTrue(m.getDaemonThreadCount() > 0);
    } catch (IOException e) {
      System.err.println("James server not reachable");
    }
  }
}
