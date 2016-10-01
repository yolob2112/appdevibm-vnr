
package com.ibm.mobileappbuilder.employeesdirectory20150916145522.presenters;

import com.ibm.mobileappbuilder.employeesdirectory20150916145522.R;
import com.ibm.mobileappbuilder.employeesdirectory20150916145522.ds.StatusScreen1DSItem;

import java.util.List;

import ibmmobileappbuilder.ds.CrudDatasource;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.mvp.presenter.BaseFormPresenter;
import ibmmobileappbuilder.mvp.view.FormView;

public class StatusFormPresenter extends BaseFormPresenter<StatusScreen1DSItem> {

    private final CrudDatasource<StatusScreen1DSItem> datasource;

    public StatusFormPresenter(CrudDatasource<StatusScreen1DSItem> datasource, FormView<StatusScreen1DSItem> view){
        super(view);
        this.datasource = datasource;
    }

    @Override
    public void deleteItem(StatusScreen1DSItem item) {
        datasource.deleteItem(item, new OnItemDeletedListener());
    }

    @Override
    public void save(StatusScreen1DSItem item) {
        // validate
        if (validate(item)){
            datasource.updateItem(item, new OnItemUpdatedListener());
        } else {
            view.showMessage(R.string.correct_errors, false);
        }
    }

    @Override
    public void create(StatusScreen1DSItem item) {
        // validate
        if (validate(item)){
            datasource.create(item, new OnItemCreatedListener());
        } else {
            view.showMessage(R.string.correct_errors, false);
        }
    }

    private class OnItemDeletedListener extends ShowingErrorOnFailureListener {
        @Override
        public void onSuccess(StatusScreen1DSItem  item) {
                        view.showMessage(R.string.item_deleted, true);
            view.close(true);
        }
    }

    private class OnItemUpdatedListener extends ShowingErrorOnFailureListener {
        @Override
        public void onSuccess(StatusScreen1DSItem item) {
                        view.setItem(item);
            view.showMessage(R.string.item_updated, true);
            view.close(true);
        }
    }

    private class OnItemCreatedListener extends ShowingErrorOnFailureListener {
        @Override
        public void onSuccess(StatusScreen1DSItem item) {
                        view.setItem(item);
            view.showMessage(R.string.item_created, true);
            view.close(true);
        }
    }

    private abstract class ShowingErrorOnFailureListener implements Datasource.Listener<StatusScreen1DSItem > {
        @Override
        public void onFailure(Exception e) {
            view.showMessage(R.string.error_data_generic, true);
        }
    }

}

