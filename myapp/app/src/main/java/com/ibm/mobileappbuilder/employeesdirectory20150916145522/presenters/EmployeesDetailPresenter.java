
package com.ibm.mobileappbuilder.employeesdirectory20150916145522.presenters;

import com.ibm.mobileappbuilder.employeesdirectory20150916145522.R;
import com.ibm.mobileappbuilder.employeesdirectory20150916145522.ds.EmployeesDBDSItem;

import ibmmobileappbuilder.ds.CrudDatasource;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.mvp.presenter.BasePresenter;
import ibmmobileappbuilder.mvp.presenter.DetailCrudPresenter;
import ibmmobileappbuilder.mvp.view.DetailView;

public class EmployeesDetailPresenter extends BasePresenter implements DetailCrudPresenter<EmployeesDBDSItem>,
      Datasource.Listener<EmployeesDBDSItem> {

    private final CrudDatasource<EmployeesDBDSItem> datasource;
    private final DetailView view;

    public EmployeesDetailPresenter(CrudDatasource<EmployeesDBDSItem> datasource, DetailView view){
        this.datasource = datasource;
        this.view = view;
    }

    @Override
    public void deleteItem(EmployeesDBDSItem item) {
        datasource.deleteItem(item, this);
    }

    @Override
    public void editForm(EmployeesDBDSItem item) {
        view.navigateToEditForm();
    }

    @Override
    public void onSuccess(EmployeesDBDSItem item) {
                view.showMessage(R.string.item_deleted, true);
        view.close(true);
    }

    @Override
    public void onFailure(Exception e) {
        view.showMessage(R.string.error_data_generic, true);
    }
}

