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
package org.apache.james.bond.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

import org.apache.commons.io.IOUtils;

public class Util {
  private static final String HEADER = "<!-- This configuration file has been"
      + " created using the Apache James Bond tool, a JAMES administration"
      + " console. It is not advisable to edit this file manually -->";

  /**
   * It copies the file templateName on the file destPath
   * 
   * @param templateName
   * @param destPath
   * @throws Exception
   */
  public static void copyFile(String templateName, String destPath)
      throws Exception {
    InputStream is = null;
    String template = null;

    // templates files are distributed in the apache-james.jar under any of
    // these folders
    for (String folder : Arrays.asList("/", "/WEB-INF/conf/",
        "/WEB-INF/classes/")) {
      template = folder + templateName;
      is = System.class.getResourceAsStream(template);
      if (is != null)
        break;
    }

    if (is == null) {
      String msg = "Unable to find james template file in classpath: "
          + templateName;
      System.err.println(msg);
      throw new IOException(msg);
    }

    File destFile = new File(destPath);
    if (!destFile.getParentFile().canWrite()) {
      String msg = "Destination Folder is not writtable: "
          + destFile.getParentFile();
      System.err.println(msg);
      throw new IOException(msg);
    }

    OutputStream os = new FileOutputStream(destFile);
    System.err.println("Created new conf file: " + destPath
        + " from template: " + template);
    IOUtils.copy(is, os);
  }

  /**
   * It adds the a comment on top of the file explaining that the files are
   * edited with the Apache James Bond tool
   * 
   * @param path
   *          the path of the file
   * @throws Exception
   */
  public static void copyFileWithHeader(String path) throws Exception {
    try {
      BufferedReader in = new BufferedReader(new FileReader(path));
      String content = "";
      while (in.ready()) {
        content += in.readLine() + System.getProperty("line.separator");
      }
      in.close();

      String output = content;

      String header = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";

      output = output.replace(
          header,
          header + System.getProperty("line.separator") + HEADER
              + System.getProperty("line.separator"));

      FileWriter fstream = new FileWriter(path);
      BufferedWriter out = new BufferedWriter(fstream);
      out.write(output);
      out.close();
    } catch (IOException e) {
      System.err.println("Error: " + e.getMessage());
    }
  }
}