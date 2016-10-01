
package com.ibm.mobileappbuilder.employeesdirectory20150916145522.presenters;

import com.ibm.mobileappbuilder.employeesdirectory20150916145522.R;
import com.ibm.mobileappbuilder.employeesdirectory20150916145522.ds.StatusScreen1DSItem;

import ibmmobileappbuilder.ds.CrudDatasource;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.mvp.presenter.BasePresenter;
import ibmmobileappbuilder.mvp.presenter.DetailCrudPresenter;
import ibmmobileappbuilder.mvp.view.DetailView;

public class StatusDetailPresenter extends BasePresenter implements DetailCrudPresenter<StatusScreen1DSItem>,
      Datasource.Listener<StatusScreen1DSItem> {

    private final CrudDatasource<StatusScreen1DSItem> datasource;
    private final DetailView view;

    public StatusDetailPresenter(CrudDatasource<StatusScreen1DSItem> datasource, DetailView view){
        this.datasource = datasource;
        this.view = view;
    }

    @Override
    public void deleteItem(StatusScreen1DSItem item) {
        datasource.deleteItem(item, this);
    }

    @Override
    public void editForm(StatusScreen1DSItem item) {
        view.navigateToEditForm();
    }

    @Override
    public void onSuccess(StatusScreen1DSItem item) {
                view.showMessage(R.string.item_deleted, true);
        view.close(true);
    }

    @Override
    public void onFailure(Exception e) {
        view.showMessage(R.string.error_data_generic, true);
    }
}

