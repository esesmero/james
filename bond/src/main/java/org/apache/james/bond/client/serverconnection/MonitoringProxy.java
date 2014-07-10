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

import org.apache.james.bond.server.monitor.Monitoring;
import org.apache.james.bond.server.monitor.MonitoringLocator;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(value = Monitoring.class, locator = MonitoringLocator.class)
public interface MonitoringProxy extends EntityProxy {
  public int getClassLoadingLoadedClassCount();

  public long getClassLoadingTotalLoadedClassCount();

  public long getClassLoadingUnloadedClassCount();

  public int getMemoryObjectPendingFinalizationCount();

  public long getHeapMemoryUsageInit();

  public long getHeapMemoryUsageMax();

  public long getHeapMemoryUsageUsed();

  public long getHeapMemoryUsageComitted();

  public long getNonHeapMemoryUsageInit();

  public long getNonHeapMemoryUsageMax();

  public long getNonHeapMemoryUsageUsed();

  public long getNonHeapMemoryUsageComitted();

  public long getCurrentThreadCpuTime();

  public long getCurrentThreadUserTime();

  public int getDaemonThreadCount();

  public int getPeakThreadCount();

  public int getThreadCount();

  public long getTotalStartedThreadCount();

  public String getRuntimeBootClassPath();

  public String getRuntimeClassPath();

  public String getRuntimeLibraryPath();

  public String getRuntimeManagementSpecVersion();

  public String getRuntimeName();

  public String getRuntimeSpecName();

  public String getRuntimeSpecVendor();

  public String getRuntimeSpecVersion();

  public long getRuntimeStartTime();

  public long getRuntimeUpTime();

  public String getRuntimeVmName();

  public String getRuntimeVmVendor();

  public String getRuntimeVmVersion();

  public String getOSArch();

  public int getOSAvailableProcessors();

  public String getOSName();

  public double getOSSystemLoadAverage();

  public String getOSVersion();
}