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
package org.apache.james.app.spring;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Calendar;

import org.apache.commons.daemon.Daemon;
import org.apache.commons.daemon.DaemonContext;
import org.apache.james.container.spring.context.JamesServerApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Bootstraps James using a Spring container.
 */
public class JamesAppSpringMain implements Daemon {

	private static final Logger log = LoggerFactory
			.getLogger(JamesAppSpringMain.class.getName());
	private JamesServerApplicationContext context;

	public static void main(String[] args) throws Exception {

		long start = Calendar.getInstance().getTimeInMillis();

		JamesAppSpringMain main = new JamesAppSpringMain();
		main.init(null);

		main.initBondConsole();
		main.initHupa();

		long end = Calendar.getInstance().getTimeInMillis();
		log.info("Apache James Server is successfully started in "
				+ (end - start) + " milliseconds.");
	}

	/**
	 * @see org.apache.commons.daemon.Daemon#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see org.apache.commons.daemon.Daemon#init(org.apache.commons.daemon.DaemonContext)
	 */
	public void init(DaemonContext arg0) throws Exception {
		context = new JamesServerApplicationContext(
				new String[] { "META-INF/org/apache/james/spring-server.xml" });
		context.registerShutdownHook();
		context.start();
	}

	/**
	 * @see org.apache.commons.daemon.Daemon#start()
	 */
	public void start() throws Exception {
		context.start();
	}

	/**
	 * @see org.apache.commons.daemon.Daemon#stop()
	 */
	public void stop() throws Exception {
		if (context != null) {
			context.stop();
		}
	}

	private void initBondConsole() throws Exception {
		if(System.getProperty("initBond", "false").equals("true"))
			initProcess("bond");
	}
	
	private void initHupa() throws Exception {
		if(System.getProperty("initHupa", "false").equals("true"))
			initProcess("hupa");
	}

	private void initProcess(final String nameProcess) throws Exception {
		System.err.println(System.getProperty("sun.java.command"));
		File lib = new File("../lib");
		File conf = new File("../conf");
		if (lib.isDirectory() && conf.isDirectory()) {
			FilenameFilter warFilter = new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					return name.startsWith(nameProcess)
							&& name.endsWith(".war");
				}
			};

			for (File f : lib.listFiles(warFilter)) {
				final Process process = Runtime.getRuntime().exec(
						"java -Djames.conf=" + conf + " -jar " + f);
				new Thread() {
					public void run() {
						connectStreams(process.getInputStream(), System.out);
					}
				}.start();
				new Thread() {
					public void run() {
						connectStreams(process.getErrorStream(), System.err);
					}
				}.start();
				Runtime.getRuntime().addShutdownHook(new Thread() {
					public void run() {
						process.destroy();
					}
				});
			}
		}
	}

	private void connectStreams(InputStream is, PrintStream os) {
		int d;
		try {
			while ((d = is.read()) != -1) {
				os.write(d);
			}
		} catch (IOException e) {
			// Just print the error but not break the app
			e.printStackTrace();
		}
	}
}
