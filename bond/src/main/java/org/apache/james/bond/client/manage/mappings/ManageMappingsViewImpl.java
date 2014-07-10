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
package org.apache.james.bond.client.manage.mappings;

import java.util.List;

import org.apache.james.bond.client.serverconnection.MappingProxy;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent.AsyncHandler;
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
 * Implementation of {@link ManageMappingsView} that shows the mapping
 * information in a {@link DataGrid} letting the user to add or delete the
 * mappings
 */
public class ManageMappingsViewImpl extends Composite implements
    ManageMappingsView {
  private Presenter listener;
  private MappingsAsyncDataProvider dataProvider;

  Label label = new Label();
  @UiField
  Button addAddressMappingButton;
  @UiField
  Button addRegexMappingButton;

  @UiField(provided = true)
  DataGrid<Mapping> dataGrid;
  @UiField(provided = true)
  SimplePager pager;

  final String COLUMN_NAME_USER = "User";
  final String COLUMN_NAME_MAPPING = "Mapping";
  final String COLUMN_NAME_DELETE = "Delete Mapping";
  final String EMPTY_MESSAGE = "No data to display";

  private static ManageMappingsViewImplUiBinder uiBinder = GWT
      .create(ManageMappingsViewImplUiBinder.class);

  interface ManageMappingsViewImplUiBinder extends
      UiBinder<Widget, ManageMappingsViewImpl> {
  }

  /**
   * It creates a {@link ManageMappingsViewImpl} indicating the
   * {@link MappingsAsyncDataProvider} used by the dataGrid
   * 
   * @param dataProvider
   */
  public ManageMappingsViewImpl(MappingsAsyncDataProvider dataProvider) {
    this.dataProvider = dataProvider;
    dataGrid = new DataGrid<Mapping>();
    dataGrid.setWidth("100%");

    dataGrid.setAutoHeaderRefreshDisabled(true);
    dataGrid.setEmptyTableWidget(new Label(EMPTY_MESSAGE));

    SimplePager.Resources pagerResources = GWT
        .create(SimplePager.Resources.class);
    pager = new SimplePager(TextLocation.CENTER, pagerResources, false, 0, true);
    pager.setDisplay(dataGrid);

    initWidget(uiBinder.createAndBindUi(this));
    
    AsyncHandler columnSortHandler = new AsyncHandler(dataGrid);
    dataGrid.addColumnSortHandler(columnSortHandler);
    
    buildMappingTable();
    dataGrid.setPageSize(20);
    
    this.setSize("100%", "100%");
  }

  /**
   * It creates the columns of the {@link DataGrid}
   */
  private void buildMappingTable() {
    dataGrid.addColumn(buildColumnUser(), buildHeader(COLUMN_NAME_USER), null);
    dataGrid.addColumn(buildColumnMapping(), buildHeader(COLUMN_NAME_MAPPING),
        null);
    dataGrid.addColumn(buildColumnDelete(), buildHeader(COLUMN_NAME_DELETE),
        null);
    setAsyncDataProvider();

  }

  /**
   * It sets the {@link MappingsAsyncDataProvider} for the {@link DataGrid}
   */
  private void setAsyncDataProvider() {
    dataProvider.addDataDisplay(dataGrid);
    dataProvider.updateRowCount(24, true);
  }

  /**
   * It builds a column with the users including domains
   * 
   * @return
   */
  private Column<Mapping, String> buildColumnUser() {
    Column<Mapping, String> columnUser = new TextColumn<Mapping>() {
      @Override
      public String getValue(Mapping mapping) {
        return mapping.getUserDomain();
      }
    };
    columnUser.setDataStoreName(COLUMN_NAME_USER);
    columnUser.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
    columnUser.setVerticalAlignment(HasVerticalAlignment.ALIGN_TOP);
    columnUser.setSortable(true);

    return columnUser;
  }

  /**
   * It builds a column with all the mappings separated with commas
   * 
   * @return
   */
  private Column<Mapping, String> buildColumnMapping() {
    Column<Mapping, String> columnMapping = new TextColumn<Mapping>() {
      @Override
      public String getValue(Mapping mapping) {
        return mapping.getMapping();
      }
    };
    columnMapping.setDataStoreName(COLUMN_NAME_MAPPING);
    columnMapping.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
    columnMapping.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
    columnMapping.setSortable(true);

    return columnMapping;
  }

  /**
   * It creates a column that is able to open a dialog to delete the mapping in
   * the selected row
   * 
   * @return
   */
  private Column<Mapping, String> buildColumnDelete() {
    Column<Mapping, String> columnDelete = new Column<Mapping, String>(
        new ButtonCell()) {
      @Override
      public String getValue(Mapping object) {
        return "Delete";
      }
    };

    columnDelete.setCellStyleNames("delete");
    columnDelete.setDataStoreName(COLUMN_NAME_DELETE);
    columnDelete.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
    columnDelete.setVerticalAlignment(HasVerticalAlignment.ALIGN_TOP);

    columnDelete.setFieldUpdater(new FieldUpdater<Mapping, String>() {
      public void update(int index, final Mapping mapping, String value) {
        final DeleteConfirmationView deleteConfirmationView = new DeleteConfirmationView(
            mapping, listener);
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

  @Override
  public void setPresenter(Presenter listener) {
    this.listener = listener;
  }

  /**
   * It opens a new window to let the user add a new address mapping associated
   * to a user and a domain
   * 
   * @param e
   */
  @UiHandler("addAddressMappingButton")
  void onAddAddressMappingClick(ClickEvent e) {
    final AddAddressMappingView addAddressMappingView = new AddAddressMappingView(
        listener);
    addAddressMappingView.center();
    addAddressMappingView.show();
  }

  /**
   * It opens a new window to let the user add a new address mapping associated
   * to a user and a domain
   * 
   * @param e
   */
  @UiHandler("addRegexMappingButton")
  void onAddRegexMappingClick(ClickEvent e) {
    final AddRegexMappingView addRegexMappingView = new AddRegexMappingView(
        listener);
    addRegexMappingView.center();
    addRegexMappingView.show();
  }

  @Override
  public void showMappings(List<MappingProxy> mappingList) {
    String text = "";
    for (MappingProxy mapping : mappingList) {
      text += mapping.getUserAndDomain() + "\n";
      for (String mappingString : mapping.getMappings()) {
        text += "*" + mappingString + "\n";
      }
    }

    label.setText(text);
  }

  @Override
  public void updateData() {
    dataProvider.onRangeChanged(dataGrid);
  }
}
