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
package org.apache.james.bond.server.monitor;

import static org.apache.james.bond.shared.BondConst.JAMES_ADDRESS;
import static org.apache.james.bond.shared.BondConst.JAMES_PORT;

import java.io.IOException;
import java.lang.management.ClassLoadingMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadMXBean;

import javax.management.MBeanServerConnection;
import javax.management.MXBean;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

/**
 * It connects to the JAMES server through JMX to monitor the system
 */
public class JMXBondConnector {
  private ClassLoadingMXBean classLoadingBean;
  private MemoryMXBean memoryBean;
  private ThreadMXBean threadBean;
  private RuntimeMXBean runtimeBean;
  private OperatingSystemMXBean osBean;

  /**
   * It connects to the James server through JMX and retrieves all the
   * information accessible through several {@link MXBean}:
   * {@link ClassLoadingMXBean}, {@link MemoryMXBean}, {@link ThreadMXBean},
   * {@link RuntimeMXBean} and {@link OperatingSystemMXBean}
   * 
   * @return
   * @throws IOException
   */
  public Monitoring getMonitoringInformation() throws IOException {
    if (classLoadingBean == null) {
      String url = "service:jmx:rmi:///jndi/rmi://" + JAMES_ADDRESS + ":"
          + JAMES_PORT + "/jmxrmi";

      JMXServiceURL serviceUrl = new JMXServiceURL(url);
      JMXConnector jmxConnector = JMXConnectorFactory.connect(serviceUrl);
      MBeanServerConnection mbeanConn = jmxConnector.getMBeanServerConnection();

      classLoadingBean = ManagementFactory
          .newPlatformMXBeanProxy(mbeanConn,
              ManagementFactory.CLASS_LOADING_MXBEAN_NAME,
              ClassLoadingMXBean.class);
      memoryBean = ManagementFactory.newPlatformMXBeanProxy(mbeanConn,
          ManagementFactory.MEMORY_MXBEAN_NAME, MemoryMXBean.class);
      threadBean = ManagementFactory.newPlatformMXBeanProxy(mbeanConn,
          ManagementFactory.THREAD_MXBEAN_NAME, ThreadMXBean.class);
      runtimeBean = ManagementFactory.newPlatformMXBeanProxy(mbeanConn,
          ManagementFactory.RUNTIME_MXBEAN_NAME, RuntimeMXBean.class);
      osBean = ManagementFactory.newPlatformMXBeanProxy(mbeanConn,
          ManagementFactory.OPERATING_SYSTEM_MXBEAN_NAME,
          OperatingSystemMXBean.class);
    }

    Monitoring monitoring = new Monitoring();

    monitoring.setClassLoadingBean(classLoadingBean);
    monitoring.setMemoryBean(memoryBean);
    monitoring.setThreadBean(threadBean);
    monitoring.setRuntimeBean(runtimeBean);
    monitoring.setOsBean(osBean);

    return monitoring;
  }
}
