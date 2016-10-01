
package com.ibm.mobileappbuilder.employeesdirectory20150916145522.ui;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import com.ibm.mobileappbuilder.employeesdirectory20150916145522.ds.StatusScreen1DSItem;
import com.ibm.mobileappbuilder.employeesdirectory20150916145522.ds.StatusScreen1DSService;
import com.ibm.mobileappbuilder.employeesdirectory20150916145522.presenters.StatusFormPresenter;
import com.ibm.mobileappbuilder.employeesdirectory20150916145522.R;
import ibmmobileappbuilder.ds.restds.GeoPoint;
import ibmmobileappbuilder.ui.FormFragment;
import ibmmobileappbuilder.views.DateTimePicker;
import ibmmobileappbuilder.views.GeoPicker;
import ibmmobileappbuilder.views.TextWatcherAdapter;
import java.util.Date;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.ds.CrudDatasource;
import ibmmobileappbuilder.ds.SearchOptions;
import ibmmobileappbuilder.ds.filter.Filter;
import java.util.Arrays;
import com.ibm.mobileappbuilder.employeesdirectory20150916145522.ds.StatusScreen1DSItem;
import com.ibm.mobileappbuilder.employeesdirectory20150916145522.ds.StatusScreen1DS;

public class StatusScreen1DSItemFormFragment extends FormFragment<StatusScreen1DSItem> {

    private CrudDatasource<StatusScreen1DSItem> datasource;

    public static StatusScreen1DSItemFormFragment newInstance(Bundle args){
        StatusScreen1DSItemFormFragment fr = new StatusScreen1DSItemFormFragment();
        fr.setArguments(args);

        return fr;
    }

    public StatusScreen1DSItemFormFragment(){
        super();
    }

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);

        // the presenter for this view
        setPresenter(new StatusFormPresenter(
                (CrudDatasource) getDatasource(),
                this));

            }

    @Override
    protected StatusScreen1DSItem newItem() {
        return new StatusScreen1DSItem();
    }

    private StatusScreen1DSService getRestService(){
        return StatusScreen1DSService.getInstance();
    }

    @Override
    protected int getLayout() {
        return R.layout.status_form;
    }

    @Override
    @SuppressLint("WrongViewCast")
    public void bindView(final StatusScreen1DSItem item, View view) {
        
        bindString(R.id.statusscreen1ds_status, item.status, new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                item.status = s.toString();
            }
        });
        
        
        bindLocation(R.id.statusscreen1ds_location, item.location,
            new GeoPicker.PointChangedListener() {
                @Override
                public void onPointChanged(GeoPoint point) {
                    item.location = point;
                }
            }
        );
        
        
        bindString(R.id.statusscreen1ds_employee, item.employee, new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                item.employee = s.toString();
            }
        });
        
        
        bindDateTimePicker(R.id.statusscreen1ds_startdate, item.startDate, new DateTimePicker.DateSelectedListener() {
            @Override
            public void onDateSelected(Date selected) {
                item.startDate = selected;
            }
        });
        
        
        bindDateTimePicker(R.id.statusscreen1ds_enddate, item.endDate, new DateTimePicker.DateSelectedListener() {
            @Override
            public void onDateSelected(Date selected) {
                item.endDate = selected;
            }
        });
        
    }

    @Override
    public Datasource<StatusScreen1DSItem> getDatasource() {
      if (datasource != null) {
        return datasource;
      }
       datasource = StatusScreen1DS.getInstance(new SearchOptions());
        return datasource;
    }
}

