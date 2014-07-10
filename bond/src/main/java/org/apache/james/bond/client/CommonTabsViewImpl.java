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

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * This class implements a {@link CommonTabsView} adding the different tabs to
 * the History
 */
public class CommonTabsViewImpl extends Composite implements CommonTabsView {
  private String place;

  private static CommonViewUiBinder uiBinder = GWT
      .create(CommonViewUiBinder.class);

  interface CommonViewUiBinder extends UiBinder<Widget, CommonTabsViewImpl> {
  }

  /**
   * Creates a {@link CommonTabsView} with the place prefix received
   * 
   * @param place
   */
  public CommonTabsViewImpl(String place) {
    this();
    setPlaceString(place);
  }

  /**
   * Creates a {@link CommonTabsView}
   * 
   */
  public CommonTabsViewImpl() {
    initWidget(uiBinder.createAndBindUi(this));
    tabPanel.addSelectionHandler(this);

    tabPanel.setAnimationDuration(1000);
    tabPanel.setAnimationVertical(false);

    tabPanel.getElement().getStyle().setMarginBottom(10.0, Unit.PX);
  }

  @UiField
  TabLayoutPanel tabPanel;

  @Override
  public void selectTab(int index) {
    if (index >= 0 && index < tabPanel.getWidgetCount()) {
      if (index != tabPanel.getSelectedIndex()) {
        tabPanel.selectTab(index);
      }
    }
  }

  @Override
  public void onSelection(SelectionEvent<Integer> event) {
    int eventSelection = event.getSelectedItem();
    if (eventSelection > 0 && eventSelection < tabPanel.getWidgetCount())
      History.newItem(this.getPlaceString() + ":" + eventSelection);
    else
      History.newItem(this.getPlaceString() + ":0");
  }

  @Override
  public void setPlaceString(String place) {
    this.place = place;
  }

  @Override
  public String getPlaceString() {
    return place;
  }

  @Override
  public void addTab(IsWidget widget, String text, String title) {
    Label tabLabel = new Label(text);
    tabLabel.setTitle(title);
    tabPanel.add(widget, tabLabel);
  }

  @Override
  public void addTab(IsWidget widget, String text) {
    Label tabLabel = new Label(text);
    tabPanel.add(widget, tabLabel);
  }

  @Override
  public int getTabsCount() {
    return tabPanel.getWidgetCount();
  }
}
