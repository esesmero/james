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

import java.lang.management.ClassLoadingMXBean;
import java.lang.management.MemoryMXBean;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadMXBean;

import javax.management.MXBean;

/**
 * This represents the monitoring information extracted from several
 * {@link MXBean}
 */
public class Monitoring {
  private Integer version;
  private ClassLoadingMXBean classLoadingBean;
  private MemoryMXBean memoryBean;
  private ThreadMXBean threadBean;
  private RuntimeMXBean runtimeBean;
  private OperatingSystemMXBean osBean;

  public void setClassLoadingBean(ClassLoadingMXBean classLoadingBean) {
    this.classLoadingBean = classLoadingBean;
  }

  public void setMemoryBean(MemoryMXBean memoryBean) {
    this.memoryBean = memoryBean;
  }

  public void setThreadBean(ThreadMXBean threadBean) {
    this.threadBean = threadBean;
  }

  public void setRuntimeBean(RuntimeMXBean runtimeBean) {
    this.runtimeBean = runtimeBean;
  }

  public void setOsBean(OperatingSystemMXBean osBean) {
    this.osBean = osBean;
  }

  public int getClassLoadingLoadedClassCount() {
    return classLoadingBean.getLoadedClassCount();
  }

  public long getClassLoadingTotalLoadedClassCount() {
    return classLoadingBean.getTotalLoadedClassCount();
  }

  public long getClassLoadingUnloadedClassCount() {
    return classLoadingBean.getUnloadedClassCount();
  }

  public int getMemoryObjectPendingFinalizationCount() {
    return memoryBean.getObjectPendingFinalizationCount();
  }

  public long getHeapMemoryUsageInit() {
    return memoryBean.getHeapMemoryUsage().getInit();
  }

  public long getHeapMemoryUsageMax() {
    return memoryBean.getHeapMemoryUsage().getMax();
  }

  public long getHeapMemoryUsageUsed() {
    return memoryBean.getHeapMemoryUsage().getUsed();
  }

  public long getHeapMemoryUsageComitted() {
    return memoryBean.getHeapMemoryUsage().getCommitted();
  }

  public long getNonHeapMemoryUsageInit() {
    return memoryBean.getNonHeapMemoryUsage().getInit();
  }

  public long getNonHeapMemoryUsageMax() {
    return memoryBean.getNonHeapMemoryUsage().getMax();
  }

  public long getNonHeapMemoryUsageUsed() {
    return memoryBean.getNonHeapMemoryUsage().getUsed();
  }

  public long getNonHeapMemoryUsageComitted() {
    return memoryBean.getNonHeapMemoryUsage().getCommitted();
  }

  public long getCurrentThreadCpuTime() {
    return threadBean.getCurrentThreadCpuTime();
  }

  public long getCurrentThreadUserTime() {
    return threadBean.getCurrentThreadUserTime();
  }

  public int getDaemonThreadCount() {
    return threadBean.getDaemonThreadCount();
  }

  public int getPeakThreadCount() {
    return threadBean.getPeakThreadCount();
  }

  public int getThreadCount() {
    return threadBean.getThreadCount();
  }

  public long getTotalStartedThreadCount() {
    return threadBean.getTotalStartedThreadCount();
  }

  public String getRuntimeBootClassPath() {
    return runtimeBean.getBootClassPath();
  }

  public String getRuntimeClassPath() {
    return runtimeBean.getClassPath();
  }

  public String getRuntimeLibraryPath() {
    return runtimeBean.getLibraryPath();
  }

  public String getRuntimeManagementSpecVersion() {
    return runtimeBean.getManagementSpecVersion();
  }

  public String getRuntimeName() {
    return runtimeBean.getName();
  }

  public String getRuntimeSpecName() {
    return runtimeBean.getSpecName();
  }

  public String getRuntimeSpecVendor() {
    return runtimeBean.getSpecVendor();
  }

  public String getRuntimeSpecVersion() {
    return runtimeBean.getSpecVersion();
  }

  public long getRuntimeStartTime() {
    return runtimeBean.getStartTime();
  }

  public long getRuntimeUpTime() {
    return runtimeBean.getUptime();
  }

  public String getRuntimeVmName() {
    return runtimeBean.getVmName();
  }

  public String getRuntimeVmVendor() {
    return runtimeBean.getVmVendor();
  }

  public String getRuntimeVmVersion() {
    return runtimeBean.getVmVersion();
  }

  public String getOSArch() {
    return osBean.getArch();
  }

  public int getOSAvailableProcessors() {
    return osBean.getAvailableProcessors();
  }

  public String getOSName() {
    return osBean.getName();
  }

  public double getOSSystemLoadAverage() {
    return osBean.getSystemLoadAverage();
  }

  public String getOSVersion() {
    return osBean.getVersion();
  }

  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }
}