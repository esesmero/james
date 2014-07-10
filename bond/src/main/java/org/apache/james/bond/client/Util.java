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

public class Util {
  /**
   * It converts the string value received into an integer. If the received
   * value is not a proper integer, 0 is returned
   * 
   * @param value
   *          string value
   * @return the integer value of the string received, or 0 if it is not correct
   */
  public static int convertStringToInt(String value) {
    return convertStringToInt(value, 0);
  }

  /**
   * It converts the string value received into an integer. If the received
   * value is not a proper integer, the default value is returned
   * 
   * @param value
   *          string value
   * @return the integer value of the string received, or defaultValue if it is
   *         not correct
   */
  public static int convertStringToInt(String value, int defaultValue) {
    try {
      return Integer.parseInt(value);
    } catch (NumberFormatException e) {
      return defaultValue;
    }
  }

  /**
   * It checks if the string received is an ip address
   * 
   * @param ip
   * @return
   */
  public static boolean isIPAddress(String ip) {
    return ip.matches("(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}"
        + "(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)");
  }
}
