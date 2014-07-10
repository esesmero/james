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

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.IsWidget;

/**
 * This interface defines a view with tabs
 */
public interface CommonTabsView extends IsWidget, SelectionHandler<Integer> {
  /**
   * Sets the selected tab
   * 
   * @param index
   *          index of the tab
   */
  public void selectTab(int index);

  /**
   * It sets the prefix of the place to use it in the History
   * 
   * @param place
   */
  public void setPlaceString(String place);

  /**
   * Returns the prefix of the place
   * 
   * @return
   */
  public String getPlaceString();

  /**
   * It adds a tab to the layout
   * 
   * @param widget
   *          view inside the tab
   * @param text
   *          text of the tab
   */
  public void addTab(IsWidget widget, String text);

  /**
   * It adds a tab to the layout
   * 
   * @param widget
   *          view inside the tab
   * @param text
   *          text of the tab
   * @param title
   *          title of the tab
   */
  public void addTab(IsWidget widget, String text, String title);

  /**
   * Returns the number of tabs that the layout has
   * 
   * @return
   */
  public int getTabsCount();

  @Override
  public void onSelection(SelectionEvent<Integer> event);
}
