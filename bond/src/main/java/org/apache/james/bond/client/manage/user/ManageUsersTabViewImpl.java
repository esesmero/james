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
package org.apache.james.bond.client.manage.user;

import org.apache.james.bond.client.serverconnection.UserProxy;

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
import com.google.gwt.user.cellview.client.ColumnSortEvent.AsyncHandler;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * Implementation of {@link ManageUsersTabView} that shows the users information
 * in a {@link DataGrid} letting the user to update, add or delete the users or
 * changes their password
 */
public class ManageUsersTabViewImpl extends Composite implements
    ManageUsersTabView {

  private static ManageUsersTabViewImplUiBinder uiBinder = GWT
      .create(ManageUsersTabViewImplUiBinder.class);
  private Presenter listener;
  private UsersAsyncDataProvider dataProvider;

  interface ManageUsersTabViewImplUiBinder extends
      UiBinder<Widget, ManageUsersTabViewImpl> {
  }

  final String COLUMN_NAME_USERNAME = "Username";
  final String COLUMN_NAME_CHANGE_PASSWORD = "Change Password";
  final String COLUMN_NAME_DELETE = "Delete User";
  final String EMPTY_MESSAGE = "No data to display";

  @UiField
  Button addUserButton;
  @UiField
  DataGrid<UserProxy> dataGrid;
  @UiField(provided = true)
  SimplePager pager;

  /**
   * It creates a {@link ManageUsersTabViewImpl} indicating the
   * {@link UsersAsyncDataProvider} used by the dataGrid
   * 
   * @param dataProvider
   */
  public ManageUsersTabViewImpl(UsersAsyncDataProvider dataProvider) {
    this.dataProvider = dataProvider;
    dataGrid = new DataGrid<UserProxy>();
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
    
    buildUsersTable();
    dataGrid.setPageSize(20);
    
    this.setSize("100%", "100%");
  }

  /**
   * It opens a new window to let the user add a new user
   * 
   * @param e
   */
  @UiHandler("addUserButton")
  void onAddUserClick(ClickEvent e) {
    final AddUserView addUserView = new AddUserView(listener);
    addUserView.center();
    addUserView.show();
  }

  private void buildUsersTable() {
    dataGrid.addColumn(buildColumnUsername(),
        buildHeader(COLUMN_NAME_USERNAME), null);
    dataGrid.addColumn(buildColumnDelete(), buildHeader(COLUMN_NAME_DELETE),
        null);
    dataGrid.addColumn(buildColumnChangePassword(),
        buildHeader(COLUMN_NAME_CHANGE_PASSWORD), null);
    setAsyncDataProvider();
  }

  /**
   * It builds a column with the users
   * 
   * @return
   */
  private Column<UserProxy, String> buildColumnUsername() {
    Column<UserProxy, String> columnUserName = new TextColumn<UserProxy>() {
      @Override
      public String getValue(UserProxy user) {
        return user.getUsername();
      }
    };

    columnUserName.setDataStoreName(COLUMN_NAME_USERNAME);
    columnUserName.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
    columnUserName.setVerticalAlignment(HasVerticalAlignment.ALIGN_TOP);
    columnUserName.setSortable(true);

    return columnUserName;
  }

  /**
   * It creates a column that is able to open a dialog to delete the user in the
   * selected row
   * 
   * @return
   */
  private Column<UserProxy, String> buildColumnDelete() {
    Column<UserProxy, String> columnDelete = new Column<UserProxy, String>(
        new ButtonCell()) {
      @Override
      public String getValue(UserProxy object) {
        return "Delete";
      }
    };
    columnDelete.setDataStoreName(COLUMN_NAME_DELETE);
    columnDelete.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
    columnDelete.setVerticalAlignment(HasVerticalAlignment.ALIGN_TOP);

    columnDelete.setFieldUpdater(new FieldUpdater<UserProxy, String>() {
      public void update(int index, final UserProxy user, String value) {
        final DeleteConfirmationView deleteConfirmationView = new DeleteConfirmationView(
            user, listener);
        deleteConfirmationView.center();
        deleteConfirmationView.show();
      }
    });

    return columnDelete;
  }

  /**
   * It creates a column that is able to open a dialog to change the password of
   * the user in the row
   * 
   * @return
   */
  private Column<UserProxy, String> buildColumnChangePassword() {
    Column<UserProxy, String> columnChangePassword = new Column<UserProxy, String>(
        new ButtonCell()) {
      @Override
      public String getValue(UserProxy object) {
        return "Change password";
      }
    };
    columnChangePassword.setDataStoreName(COLUMN_NAME_CHANGE_PASSWORD);
    columnChangePassword
        .setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
    columnChangePassword.setVerticalAlignment(HasVerticalAlignment.ALIGN_TOP);

    columnChangePassword.setFieldUpdater(new FieldUpdater<UserProxy, String>() {
      public void update(int index, final UserProxy object, String value) {
        final ChangePasswordView changePasswordView = new ChangePasswordView(
            object, listener);
        changePasswordView.center();
        changePasswordView.show();
      }
    });

    return columnChangePassword;
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
   * It sets the {@link UsersAsyncDataProvider} for the {@link DataGrid}
   */
  private void setAsyncDataProvider() {
    dataProvider.addDataDisplay(dataGrid);
    dataProvider.updateRowCount(24, true);
  }

  @Override
  public void setPresenter(Presenter listener) {
    this.listener = listener;
  }

  @Override
  public void updateData() {
    dataProvider.onRangeChanged(dataGrid);
  }
}