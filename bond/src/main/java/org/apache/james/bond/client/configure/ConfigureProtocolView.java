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
package org.apache.james.bond.client.configure;

import com.google.gwt.user.client.ui.IsWidget;

/**
 * This interface defines the common behaviour of all the configure protocol
 * views where the different validation errors are publish to the user
 */
public interface ConfigureProtocolView extends IsWidget {
  /**
   * It shows to the user the error message received in the validation
   * 
   * @param errorMsg
   */
  void publishValidationError(String errorMsg);

  /**
   * It clears the current validation error from the view
   */
  void clearValidationError();

  /**
   * It informs the user that the saving process has been successful
   * 
   * @param msg
   */
  void publishSaved();

  /**
   * It clears the saved message
   */
  void clearSavedMessage();
}
