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
package org.apache.james.bond.client.manage.domain;

import org.apache.james.bond.client.serverconnection.DomainProxy;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.Header;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * Implementation of {@link ManageDomainsTabView} that shows the domains
 * information in a {@link DataGrid} letting the user to update, add or delete
 * the domains
 */
public class ManageDomainsTabViewImpl extends Composite implements
    ManageDomainsTabView {
  private static ManageDomainsTabViewImplUiBinder uiBinder = GWT
      .create(ManageDomainsTabViewImplUiBinder.class);
  private Presenter listener;
  private DomainsAsyncDataProvider dataProvider;
  final String COLUMN_NAME_DOMAIN = "Domain";
  final String COLUMN_NAME_DELETE = "Delete Domain";
  final String EMPTY_MESSAGE = "No data to display";

  @UiField
  Button addDomainButton;
  @UiField
  DataGrid<DomainProxy> dataGrid;
  @UiField(provided = true)
  SimplePager pager;

  interface ManageDomainsTabViewImplUiBinder extends
      UiBinder<Widget, ManageDomainsTabViewImpl> {
  }

  /**
   * It creates a {@link ManageDomainsTabViewImpl} indicating the
   * {@link DomainsAsyncDataProvider} used by the dataGrid
   * 
   * @param dataProvider
   */
  public ManageDomainsTabViewImpl(DomainsAsyncDataProvider dataProvider) {
    this.dataProvider = dataProvider;
    dataGrid = new DataGrid<DomainProxy>();
    dataGrid.setWidth("100%");

    dataGrid.setAutoHeaderRefreshDisabled(true);
    dataGrid.setEmptyTableWidget(new Label(EMPTY_MESSAGE));

    SimplePager.Resources pagerResources = GWT
        .create(SimplePager.Resources.class);
    pager = new SimplePager(TextLocation.CENTER, pagerResources, false, 0, true);
    pager.setDisplay(dataGrid);

    initWidget(uiBinder.createAndBindUi(this));

    buildDomainsTable();
    dataGrid.setPageSize(20);
    this.setSize("100%", "100%");
  }

  /**
   * It opens a new window to let the user add a new domain
   * 
   * @param e
   */
  @UiHandler("addDomainButton")
  void onAddDomainClick(ClickEvent e) {
    final AddDomainView addDomainView = new AddDomainView(listener);
    addDomainView.center();
    addDomainView.show();
  }

  /**
   * It creates the columns of the {@link DataGrid}
   */
  private void buildDomainsTable() {
    dataGrid.addColumn(buildColumnDomain(), buildHeader(COLUMN_NAME_DOMAIN),
        null);
    dataGrid.addColumn(buildColumnDelete(), buildHeader(COLUMN_NAME_DELETE),
        null);
    setAsyncDataProvider();
  }

  /**
   * It builds a column with the domains
   * 
   * @return
   */
  private Column<DomainProxy, String> buildColumnDomain() {
    Column<DomainProxy, String> columnDomain = new TextColumn<DomainProxy>() {
      @Override
      public String getValue(DomainProxy domain) {
        return domain.getDomain();
      }
    };

    columnDomain.setDataStoreName(COLUMN_NAME_DOMAIN);
    columnDomain.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
    columnDomain.setVerticalAlignment(HasVerticalAlignment.ALIGN_TOP);

    return columnDomain;
  }

  /**
   * It creates a column that is able to open a dialog to delete the domain in
   * the selected row
   * 
   * @return
   */
  private Column<DomainProxy, String> buildColumnDelete() {
    Column<DomainProxy, String> columnDelete = new Column<DomainProxy, String>(
        new ButtonCell()) {
      @Override
      public String getValue(DomainProxy object) {
        return "Delete";
      }
    };

    columnDelete.setCellStyleNames("delete");
    columnDelete.setDataStoreName(COLUMN_NAME_DELETE);
    columnDelete.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
    columnDelete.setVerticalAlignment(HasVerticalAlignment.ALIGN_TOP);

    columnDelete.setFieldUpdater(new FieldUpdater<DomainProxy, String>() {
      public void update(int index, final DomainProxy domain, String value) {
        final DeleteConfirmationView deleteConfirmationView = new DeleteConfirmationView(
            domain, listener);
        deleteConfirmationView.center();
        deleteConfirmationView.show();
      }
    });

    return columnDelete;
  }

  /**
   * It creates a header with the text
   * 
   * @param text
   * @return
   */
  private Header<String> buildHeader(final String text) {
    return new Header<String>(new TextCell()) {
      @Override
      public String getValue() {
        return text;
      }
    };
  }

  /**
   * It sets the {@link DomainsAsyncDataProvider} for the {@link DataGrid}
   */
  private void setAsyncDataProvider() {
    dataProvider.addDataDisplay(dataGrid);
    dataProvider.updateRowCount(24, true);
  }

  @Override
  public void setPresenter(final Presenter listener) {
    this.listener = listener;
  }

  @Override
  public void updateData() {
    dataProvider.onRangeChanged(dataGrid);
  }
}