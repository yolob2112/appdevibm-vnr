

package com.ibm.mobileappbuilder.employeesdirectory20150916145522.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Arrays;

import com.ibm.mobileappbuilder.employeesdirectory20150916145522.R;

import ibmmobileappbuilder.ui.BaseFragment;
import ibmmobileappbuilder.ui.FilterActivity;
import ibmmobileappbuilder.ds.SearchOptions;
import ibmmobileappbuilder.ds.filter.Filter;

import com.ibm.mobileappbuilder.employeesdirectory20150916145522.ds.EmployeesDBDS;
import ibmmobileappbuilder.dialogs.ValuesSelectionDialog;
import ibmmobileappbuilder.views.ListSelectionPicker;
import java.util.ArrayList;

/**
 * EmployeesFilterActivity filter activity
 */
public class EmployeesFilterActivity extends FilterActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // set title
        setTitle(R.string.employeesFilterActivity);
    }

    @Override
    protected Fragment getFragment() {
        return new PlaceholderFragment();
    }

    public static class PlaceholderFragment extends BaseFragment {
        private SearchOptions.Builder searchOptionsBuilder = SearchOptions.Builder.searchOptions();
        private SearchOptions searchOptions;

        // filter field values
            
    ArrayList<String> name_values;

        public PlaceholderFragment() {
              searchOptions = SearchOptions.Builder.searchOptions().build();
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.employees_filter, container, false);
        }

        @Override
        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            // Get saved values
            Bundle bundle = savedInstanceState;
            if(bundle == null) {
                bundle = getArguments();
            }
            // get initial data
                        
            name_values = bundle.getStringArrayList("name_values");

            // bind pickers
                        
            final ListSelectionPicker name_view = (ListSelectionPicker) view.findViewById(R.id.name_filter);
            ValuesSelectionDialog name_dialog = (ValuesSelectionDialog) getFragmentManager().findFragmentByTag("name");
            if (name_dialog == null)
                name_dialog = new ValuesSelectionDialog();
            
            // configure the dialog
            name_dialog.setColumnName("name")
                .setDatasource(EmployeesDBDS.getInstance(searchOptions))
                .setSearchOptions(searchOptions)
                .setTitle("Name")
                .setHaveSearch(true)
                .setMultipleChoice(true);
            
            // bind the dialog to the picker
            name_view.setSelectionDialog(name_dialog)
                .setTag("name")
                .setSelectedValues(name_values)
                .setSelectedListener(new ListSelectionPicker.ListSelectedListener() {
                @Override
                public void onSelected(ArrayList<String> selected) {
                    name_values = selected;
                }
            });

            // Bind buttons
            Button okBtn = (Button) view.findViewById(R.id.ok);
            okBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();

                    // send filter result back to caller
                                        
                    intent.putStringArrayListExtra("name_values", name_values);

                    getActivity().setResult(RESULT_OK, intent);
                    getActivity().finish();
                }
            });

            Button cancelBtn = (Button) view.findViewById(R.id.reset);
            cancelBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Reset values
                                        
                    name_values = new ArrayList<String>();
                    name_view.setSelectedValues(null);
                }
            });
        }

        @Override
        public void onSaveInstanceState(Bundle bundle) {
            super.onSaveInstanceState(bundle);

            // save current status
                        
            bundle.putStringArrayList("name_values", name_values);
        }
    }

}

