
package com.ibm.mobileappbuilder.employeesdirectory20150916145522.ui;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.view.View;
import com.ibm.mobileappbuilder.employeesdirectory20150916145522.ds.EmployeesDBDSItem;
import com.ibm.mobileappbuilder.employeesdirectory20150916145522.ds.EmployeesDBDSService;
import com.ibm.mobileappbuilder.employeesdirectory20150916145522.presenters.EmployeesDetailFormPresenter;
import com.ibm.mobileappbuilder.employeesdirectory20150916145522.R;
import ibmmobileappbuilder.ui.FormFragment;
import ibmmobileappbuilder.util.StringUtils;
import ibmmobileappbuilder.views.ImagePicker;
import ibmmobileappbuilder.views.TextWatcherAdapter;
import java.io.IOException;
import java.io.File;

import static android.net.Uri.fromFile;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.ds.CrudDatasource;
import ibmmobileappbuilder.ds.SearchOptions;
import ibmmobileappbuilder.ds.filter.Filter;
import java.util.Arrays;
import com.ibm.mobileappbuilder.employeesdirectory20150916145522.ds.EmployeesDBDSItem;
import com.ibm.mobileappbuilder.employeesdirectory20150916145522.ds.EmployeesDBDS;

public class EmployeesDBDSItemFormFragment extends FormFragment<EmployeesDBDSItem> {

    private CrudDatasource<EmployeesDBDSItem> datasource;

    public static EmployeesDBDSItemFormFragment newInstance(Bundle args){
        EmployeesDBDSItemFormFragment fr = new EmployeesDBDSItemFormFragment();
        fr.setArguments(args);

        return fr;
    }

    public EmployeesDBDSItemFormFragment(){
        super();
    }

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);

        // the presenter for this view
        setPresenter(new EmployeesDetailFormPresenter(
                (CrudDatasource) getDatasource(),
                this));

            }

    @Override
    protected EmployeesDBDSItem newItem() {
        return new EmployeesDBDSItem();
    }

    private EmployeesDBDSService getRestService(){
        return EmployeesDBDSService.getInstance();
    }

    @Override
    protected int getLayout() {
        return R.layout.employeesdetail_form;
    }

    @Override
    @SuppressLint("WrongViewCast")
    public void bindView(final EmployeesDBDSItem item, View view) {
        
        bindString(R.id.employeesdbds_name, item.name, new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                item.name = s.toString();
            }
        });
        
        
        bindString(R.id.employeesdbds_lastname, item.lastname, new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                item.lastname = s.toString();
            }
        });
        
        
        bindImage(R.id.employeesdbds_picture,
            item.picture != null ?
                getRestService().getImageUrl(item.picture) : null,
            0,
            new ImagePicker.Callback(){
                @Override
                public void imageRemoved(){
                    item.picture = null;
                    item.pictureUri = null;
                    ((ImagePicker) getView().findViewById(R.id.employeesdbds_picture)).clear();
                }
            }
        );
        
        
        bindString(R.id.employeesdbds_role, item.role, new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                item.role = s.toString();
            }
        });
        
        
        bindString(R.id.employeesdbds_email, item.email, new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                item.email = s.toString();
            }
        });
        
        
        bindString(R.id.employeesdbds_phone, item.phone, new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                item.phone = s.toString();
            }
        });
        
    }

    @Override
    public Datasource<EmployeesDBDSItem> getDatasource() {
      if (datasource != null) {
        return datasource;
      }
       datasource = EmployeesDBDS.getInstance(new SearchOptions());
        return datasource;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK) {
            ImagePicker picker = null;
            Uri imageUri = null;
            EmployeesDBDSItem item = getItem();

            if((requestCode & ImagePicker.GALLERY_REQUEST_CODE) == ImagePicker.GALLERY_REQUEST_CODE) {
              imageUri = data.getData();
              switch (requestCode - ImagePicker.GALLERY_REQUEST_CODE) {
                        
                        case 0:   // picture field
                            item.pictureUri = imageUri;
                            item.picture = "cid:picture";
                            picker = (ImagePicker) getView().findViewById(R.id.employeesdbds_picture);
                            break;
                        
                default:
                  return;
              }

              picker.setImageUri(imageUri);
            } else if((requestCode & ImagePicker.CAPTURE_REQUEST_CODE) == ImagePicker.CAPTURE_REQUEST_CODE) {
				      switch (requestCode - ImagePicker.CAPTURE_REQUEST_CODE) {
                        
                        case 0:   // picture field
                            picker = (ImagePicker) getView().findViewById(R.id.employeesdbds_picture);
                            imageUri = fromFile(picker.getImageFile());
                        		item.pictureUri = imageUri;
                            item.picture = "cid:picture";
                            break;
                        
                default:
                  return;
              }
              picker.setImageUri(imageUri);
            }
        }
    }
}

