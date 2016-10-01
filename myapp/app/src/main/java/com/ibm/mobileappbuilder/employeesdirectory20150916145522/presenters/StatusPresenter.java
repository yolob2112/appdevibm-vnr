
package com.ibm.mobileappbuilder.employeesdirectory20150916145522.presenters;

import com.ibm.mobileappbuilder.employeesdirectory20150916145522.R;
import com.ibm.mobileappbuilder.employeesdirectory20150916145522.ds.StatusScreen1DSItem;

import java.util.List;

import ibmmobileappbuilder.ds.CrudDatasource;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.mvp.presenter.BasePresenter;
import ibmmobileappbuilder.mvp.presenter.ListCrudPresenter;
import ibmmobileappbuilder.mvp.view.CrudListView;

public class StatusPresenter extends BasePresenter implements ListCrudPresenter<StatusScreen1DSItem>,
      Datasource.Listener<StatusScreen1DSItem>{

    private final CrudDatasource<StatusScreen1DSItem> crudDatasource;
    private final CrudListView<StatusScreen1DSItem> view;

    public StatusPresenter(CrudDatasource<StatusScreen1DSItem> crudDatasource,
                                         CrudListView<StatusScreen1DSItem> view) {
       this.crudDatasource = crudDatasource;
       this.view = view;
    }

    @Override
    public void deleteItem(StatusScreen1DSItem item) {
        crudDatasource.deleteItem(item, this);
    }

    @Override
    public void deleteItems(List<StatusScreen1DSItem> items) {
        crudDatasource.deleteItems(items, this);
    }

    @Override
    public void addForm() {
        view.showAdd();
    }

    @Override
    public void editForm(StatusScreen1DSItem item, int position) {
        view.showEdit(item, position);
    }

    @Override
    public void detail(StatusScreen1DSItem item, int position) {
        view.showDetail(item, position);
    }

    @Override
    public void onSuccess(StatusScreen1DSItem item) {
                view.showMessage(R.string.items_deleted);
        view.refresh();
    }

    @Override
    public void onFailure(Exception e) {
        view.showMessage(R.string.error_data_generic);
    }

}

