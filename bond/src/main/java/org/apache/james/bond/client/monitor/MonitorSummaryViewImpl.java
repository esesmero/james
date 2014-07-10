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
package org.apache.james.bond.client.monitor;

import org.apache.james.bond.client.serverconnection.MonitoringProxy;

import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Implementation of {@link MonitorSummaryView} that shows all the summary
 * monitoring information that the JAMES server allows
 */
public class MonitorSummaryViewImpl extends ScrollPanel implements
    MonitorSummaryView {
  private Label totalLoadedClasses, loadedClasses, unloadedClasses, initHeap,
      usedHeap, committedHeap, maxHeap, initNonHeap, usedNonHeap,
      committedNonHeap, maxNonHeap, objectsPending, threadCpuTime,
      threadUserTime, threadDaemon, threadPeak, threadTotal, threadCreated,
      bootClasspath, classpath, libraryClasspath, mgmSpecVersion, name,
      specName, specVendor, specVersion, startTime, uptime, vmName, vmVendor,
      vmVersion, architecture, availableProcessors, osName, systemLoad,
      osVersion;

  /**
   * It creates a view that shows the monitoring information: heap memory,
   * threads, operating system, class loading and runtime information
   */
  public MonitorSummaryViewImpl() {
    super();

    FlexTable flexTable = new FlexTable();
    FlexCellFormatter cellFormatter = flexTable.getFlexCellFormatter();

    flexTable.setWidget(0, 0, getHeapMemoryView());
    flexTable.setWidget(0, 1, getThreadView());
    flexTable.setWidget(1, 0, getOSView());
    flexTable.setWidget(1, 1, getClassLoadingView());
    flexTable.setWidget(2, 0, getRuntimeView());

    cellFormatter.setColSpan(2, 0, 2);
    this.add(flexTable);
  }

  /**
   * It creates a view for the class loading information
   * 
   * @return
   */
  private Widget getClassLoadingView() {
    CaptionPanel captionPanel = new CaptionPanel("Class Loading");
    FlexTable flexTable = new FlexTable();
    FlexCellFormatter cellFormatter = flexTable.getFlexCellFormatter();
    flexTable.setCellPadding(3);

    totalLoadedClasses = new Label();
    loadedClasses = new Label();
    unloadedClasses = new Label();

    flexTable.setWidget(0, 0, new Label("Total loaded class count:"));
    flexTable.setWidget(1, 0, new Label("Loaded class count:"));
    flexTable.setWidget(2, 0, new Label("Unloaded class count:"));

    flexTable.setWidget(0, 1, totalLoadedClasses);
    flexTable.setWidget(1, 1, loadedClasses);
    flexTable.setWidget(2, 1, unloadedClasses);

    cellFormatter.setWidth(0, 0, "200px");
    cellFormatter.setWidth(0, 1, "200px");

    captionPanel.add(flexTable);
    captionPanel.setHeight("130px");
    return captionPanel;
  }

  /**
   * It creates a view for the heap memory information
   * 
   * @return
   */
  private Widget getHeapMemoryView() {
    CaptionPanel captionPanel = new CaptionPanel("Memory Use");
    FlexTable flexTable = new FlexTable();
    FlexCellFormatter cellFormatter = flexTable.getFlexCellFormatter();
    flexTable.setCellPadding(3);

    initHeap = new Label();
    usedHeap = new Label();
    committedHeap = new Label();
    maxHeap = new Label();
    initNonHeap = new Label();
    usedNonHeap = new Label();
    committedNonHeap = new Label();
    maxNonHeap = new Label();
    objectsPending = new Label();

    flexTable.setWidget(0, 0, new Label("Heap memory init:"));
    flexTable.setWidget(1, 0, new Label("Heap memory used:"));
    flexTable.setWidget(2, 0, new Label("Heap memory committed:"));
    flexTable.setWidget(3, 0, new Label("Heap memory max:"));

    flexTable.setWidget(4, 0, new Label("Non heap memory init:"));
    flexTable.setWidget(5, 0, new Label("Non heap memory used:"));
    flexTable.setWidget(6, 0, new Label("Non heap memory committed:"));
    flexTable.setWidget(7, 0, new Label("Non heap memory max:"));

    flexTable.setWidget(8, 0, new Label("Objects pending finalization:"));

    flexTable.setWidget(0, 1, initHeap);
    flexTable.setWidget(1, 1, usedHeap);
    flexTable.setWidget(2, 1, committedHeap);
    flexTable.setWidget(3, 1, maxHeap);

    flexTable.setWidget(4, 1, initNonHeap);
    flexTable.setWidget(5, 1, usedNonHeap);
    flexTable.setWidget(6, 1, committedNonHeap);
    flexTable.setWidget(7, 1, maxNonHeap);

    flexTable.setWidget(8, 1, objectsPending);

    cellFormatter.setWidth(0, 0, "200px");
    cellFormatter.setWidth(0, 1, "200px");

    captionPanel.add(flexTable);
    return captionPanel;
  }

  /**
   * It creates a view for the thread information
   * 
   * @return
   */
  private Widget getThreadView() {
    CaptionPanel captionPanel = new CaptionPanel("Threads");
    FlexTable flexTable = new FlexTable();
    FlexCellFormatter cellFormatter = flexTable.getFlexCellFormatter();
    flexTable.setCellPadding(3);

    threadCpuTime = new Label();
    threadUserTime = new Label();
    threadDaemon = new Label();
    threadPeak = new Label();
    threadTotal = new Label();
    threadCreated = new Label();

    flexTable.setWidget(0, 0, new Label(
        "Current thread CPU time (nanoseconds):"));
    flexTable.setWidget(1, 0, new Label(
        "Current thread user time (nanoseconds):"));
    flexTable.setWidget(2, 0, new Label("Daemon threads:"));
    flexTable.setWidget(3, 0, new Label("Peak threads:"));
    flexTable.setWidget(4, 0, new Label("Total lived threads:"));
    flexTable.setWidget(5, 0, new Label("Total threads created:"));

    flexTable.setWidget(0, 1, threadCpuTime);
    flexTable.setWidget(1, 1, threadUserTime);
    flexTable.setWidget(2, 1, threadDaemon);
    flexTable.setWidget(3, 1, threadPeak);
    flexTable.setWidget(4, 1, threadTotal);
    flexTable.setWidget(5, 1, threadCreated);

    cellFormatter.setWidth(0, 0, "200px");
    cellFormatter.setWidth(0, 1, "200px");

    captionPanel.add(flexTable);
    captionPanel.setHeight("222px");
    return captionPanel;
  }

  /**
   * It creates a view for the runtime information
   * 
   * @return
   */
  private Widget getRuntimeView() {
    CaptionPanel captionPanel = new CaptionPanel("Runtime JVM");
    FlexTable flexTable = new FlexTable();
    FlexCellFormatter cellFormatter = flexTable.getFlexCellFormatter();
    flexTable.setCellPadding(3);

    bootClasspath = new Label();
    classpath = new Label();
    libraryClasspath = new Label();
    mgmSpecVersion = new Label();
    name = new Label();
    specName = new Label();
    specVendor = new Label();
    specVersion = new Label();
    startTime = new Label();
    uptime = new Label();
    vmName = new Label();
    vmVendor = new Label();
    vmVersion = new Label();

    flexTable.setWidget(0, 0, new Label("VM name:"));
    flexTable.setWidget(1, 0, new Label("VM vendor:"));
    flexTable.setWidget(2, 0, new Label("VM version:"));
    flexTable.setWidget(3, 0, new Label("Management Specification Version:"));
    flexTable.setWidget(4, 0, new Label("Start time (miliseconds):"));

    flexTable.setWidget(0, 2, new Label("Name:"));
    flexTable.setWidget(1, 2, new Label("Specification name:"));
    flexTable.setWidget(2, 2, new Label("Specification vendor:"));
    flexTable.setWidget(3, 2, new Label("Specification version:"));
    flexTable.setWidget(4, 2, new Label("Uptime (miliseconds):"));

    flexTable.setWidget(10, 0, new Label("Library path:"));
    flexTable.setWidget(11, 0, new Label("Boot classpath:"));
    flexTable.setWidget(12, 0, new Label("Classpath:"));

    flexTable.setWidget(0, 1, vmName);
    flexTable.setWidget(1, 1, vmVendor);
    flexTable.setWidget(2, 1, vmVersion);
    flexTable.setWidget(3, 1, mgmSpecVersion);
    flexTable.setWidget(4, 1, startTime);

    flexTable.setWidget(0, 3, name);
    flexTable.setWidget(1, 3, specName);
    flexTable.setWidget(2, 3, specVendor);
    flexTable.setWidget(3, 3, specVersion);
    flexTable.setWidget(4, 3, uptime);

    flexTable.setWidget(10, 1, libraryClasspath);
    flexTable.setWidget(11, 1, bootClasspath);
    flexTable.setWidget(12, 1, classpath);

    cellFormatter.setColSpan(10, 1, 6);
    cellFormatter.setColSpan(11, 1, 6);
    cellFormatter.setColSpan(12, 1, 6);

    cellFormatter.setWidth(0, 0, "200px");
    cellFormatter.setWidth(0, 1, "200px");
    cellFormatter.setWidth(0, 2, "200px");
    cellFormatter.setWidth(0, 3, "200px");

    captionPanel.add(flexTable);
    return captionPanel;
  }

  /**
   * It creates a view for the operating system information
   * 
   * @return
   */
  private Widget getOSView() {
    CaptionPanel captionPanel = new CaptionPanel("Operating System");
    FlexTable flexTable = new FlexTable();
    FlexCellFormatter cellFormatter = flexTable.getFlexCellFormatter();
    flexTable.setCellPadding(3);

    architecture = new Label();
    availableProcessors = new Label();
    osName = new Label();
    systemLoad = new Label();
    osVersion = new Label();

    flexTable.setWidget(0, 0, new Label("Architecture:"));
    flexTable.setWidget(1, 0, new Label("Available processors:"));
    flexTable.setWidget(2, 0, new Label("Name:"));
    flexTable.setWidget(3, 0, new Label("System load average:"));
    flexTable.setWidget(4, 0, new Label("Version:"));

    flexTable.setWidget(0, 1, architecture);
    flexTable.setWidget(1, 1, availableProcessors);
    flexTable.setWidget(2, 1, osName);
    flexTable.setWidget(3, 1, systemLoad);
    flexTable.setWidget(4, 1, osVersion);

    cellFormatter.setWidth(0, 0, "200px");
    cellFormatter.setWidth(0, 1, "200px");

    captionPanel.add(flexTable);
    return captionPanel;
  }

  @Override
  public void showInformation(MonitoringProxy monitoring) {
    totalLoadedClasses.setText(Long.toString(monitoring
        .getClassLoadingTotalLoadedClassCount()));
    loadedClasses.setText(Long.toString(monitoring
        .getClassLoadingLoadedClassCount()));
    unloadedClasses.setText(Long.toString(monitoring
        .getClassLoadingUnloadedClassCount()));

    initHeap.setText(Long.toString(monitoring.getHeapMemoryUsageInit()));
    usedHeap.setText(Long.toString(monitoring.getHeapMemoryUsageUsed()));
    committedHeap
        .setText(Long.toString(monitoring.getHeapMemoryUsageComitted()));
    maxHeap.setText(Long.toString(monitoring.getHeapMemoryUsageMax()));

    initNonHeap.setText(Long.toString(monitoring.getNonHeapMemoryUsageInit()));
    usedNonHeap.setText(Long.toString(monitoring.getNonHeapMemoryUsageUsed()));
    committedNonHeap.setText(Long.toString(monitoring
        .getNonHeapMemoryUsageComitted()));
    maxNonHeap.setText(Long.toString(monitoring.getNonHeapMemoryUsageMax()));
    objectsPending.setText(Integer.toString(monitoring
        .getMemoryObjectPendingFinalizationCount()));

    threadCpuTime.setText(Long.toString(monitoring.getCurrentThreadCpuTime()));
    threadUserTime
        .setText(Long.toString(monitoring.getCurrentThreadUserTime()));
    threadDaemon.setText(Integer.toString(monitoring.getDaemonThreadCount()));
    threadPeak.setText(Integer.toString(monitoring.getPeakThreadCount()));
    threadTotal.setText(Integer.toString(monitoring.getThreadCount()));
    threadCreated
        .setText(Long.toString(monitoring.getTotalStartedThreadCount()));

    bootClasspath.setText(monitoring.getRuntimeBootClassPath());
    classpath.setText(monitoring.getRuntimeClassPath());
    libraryClasspath.setText(monitoring.getRuntimeLibraryPath());
    mgmSpecVersion.setText(monitoring.getRuntimeManagementSpecVersion());
    name.setText(monitoring.getRuntimeName());
    specName.setText(monitoring.getRuntimeSpecName());
    specVendor.setText(monitoring.getRuntimeSpecVendor());
    specVersion.setText(monitoring.getRuntimeSpecVersion());
    startTime.setText(Long.toString(monitoring.getRuntimeStartTime()));
    uptime.setText(Long.toString(monitoring.getRuntimeUpTime()));
    vmName.setText(monitoring.getRuntimeVmName());
    vmVendor.setText(monitoring.getRuntimeVmVendor());
    vmVersion.setText(monitoring.getRuntimeVmVersion());

    architecture.setText(monitoring.getOSArch());
    availableProcessors.setText(Integer.toString(monitoring
        .getOSAvailableProcessors()));
    osName.setText(monitoring.getOSName());
    systemLoad.setText(Double.toString(monitoring.getOSSystemLoadAverage()));
    osVersion.setText(monitoring.getOSVersion());
  }
}