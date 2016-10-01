
package com.ibm.mobileappbuilder.employeesdirectory20150916145522.presenters;

import com.ibm.mobileappbuilder.employeesdirectory20150916145522.R;
import com.ibm.mobileappbuilder.employeesdirectory20150916145522.ds.EmployeesDBDSItem;

import java.util.List;

import ibmmobileappbuilder.ds.CrudDatasource;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.mvp.presenter.BaseFormPresenter;
import ibmmobileappbuilder.mvp.view.FormView;

public class EmployeesDetailFormPresenter extends BaseFormPresenter<EmployeesDBDSItem> {

    private final CrudDatasource<EmployeesDBDSItem> datasource;

    public EmployeesDetailFormPresenter(CrudDatasource<EmployeesDBDSItem> datasource, FormView<EmployeesDBDSItem> view){
        super(view);
        this.datasource = datasource;
    }

    @Override
    public void deleteItem(EmployeesDBDSItem item) {
        datasource.deleteItem(item, new OnItemDeletedListener());
    }

    @Override
    public void save(EmployeesDBDSItem item) {
        // validate
        if (validate(item)){
            datasource.updateItem(item, new OnItemUpdatedListener());
        } else {
            view.showMessage(R.string.correct_errors, false);
        }
    }

    @Override
    public void create(EmployeesDBDSItem item) {
        // validate
        if (validate(item)){
            datasource.create(item, new OnItemCreatedListener());
        } else {
            view.showMessage(R.string.correct_errors, false);
        }
    }

    private class OnItemDeletedListener extends ShowingErrorOnFailureListener {
        @Override
        public void onSuccess(EmployeesDBDSItem  item) {
                        view.showMessage(R.string.item_deleted, true);
            view.close(true);
        }
    }

    private class OnItemUpdatedListener extends ShowingErrorOnFailureListener {
        @Override
        public void onSuccess(EmployeesDBDSItem item) {
                        view.setItem(item);
            view.showMessage(R.string.item_updated, true);
            view.close(true);
        }
    }

    private class OnItemCreatedListener extends ShowingErrorOnFailureListener {
        @Override
        public void onSuccess(EmployeesDBDSItem item) {
                        view.setItem(item);
            view.showMessage(R.string.item_created, true);
            view.close(true);
        }
    }

    private abstract class ShowingErrorOnFailureListener implements Datasource.Listener<EmployeesDBDSItem > {
        @Override
        public void onFailure(Exception e) {
            view.showMessage(R.string.error_data_generic, true);
        }
    }

}

