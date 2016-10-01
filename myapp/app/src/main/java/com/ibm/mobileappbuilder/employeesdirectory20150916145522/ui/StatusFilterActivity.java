

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

import com.ibm.mobileappbuilder.employeesdirectory20150916145522.ds.StatusScreen1DS;
import ibmmobileappbuilder.dialogs.ValuesSelectionDialog;
import ibmmobileappbuilder.views.ListSelectionPicker;
import java.util.ArrayList;

/**
 * StatusFilterActivity filter activity
 */
public class StatusFilterActivity extends FilterActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // set title
        setTitle(R.string.statusFilterActivity);
    }

    @Override
    protected Fragment getFragment() {
        return new PlaceholderFragment();
    }

    public static class PlaceholderFragment extends BaseFragment {
        private SearchOptions.Builder searchOptionsBuilder = SearchOptions.Builder.searchOptions();
        private SearchOptions searchOptions;

        // filter field values
            
    ArrayList<String> status_values;
    
    ArrayList<String> employee_values;

        public PlaceholderFragment() {
              searchOptions = SearchOptions.Builder.searchOptions().build();
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.status_filter, container, false);
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
                        
            status_values = bundle.getStringArrayList("status_values");
            
            employee_values = bundle.getStringArrayList("employee_values");

            // bind pickers
                        
            final ListSelectionPicker status_view = (ListSelectionPicker) view.findViewById(R.id.status_filter);
            ValuesSelectionDialog status_dialog = (ValuesSelectionDialog) getFragmentManager().findFragmentByTag("status");
            if (status_dialog == null)
                status_dialog = new ValuesSelectionDialog();
            
            // configure the dialog
            status_dialog.setColumnName("status")
                .setDatasource(StatusScreen1DS.getInstance(searchOptions))
                .setSearchOptions(searchOptions)
                .setTitle("Status")
                .setHaveSearch(true)
                .setMultipleChoice(true);
            
            // bind the dialog to the picker
            status_view.setSelectionDialog(status_dialog)
                .setTag("status")
                .setSelectedValues(status_values)
                .setSelectedListener(new ListSelectionPicker.ListSelectedListener() {
                @Override
                public void onSelected(ArrayList<String> selected) {
                    status_values = selected;
                }
            });
            
            final ListSelectionPicker employee_view = (ListSelectionPicker) view.findViewById(R.id.employee_filter);
            ValuesSelectionDialog employee_dialog = (ValuesSelectionDialog) getFragmentManager().findFragmentByTag("employee");
            if (employee_dialog == null)
                employee_dialog = new ValuesSelectionDialog();
            
            // configure the dialog
            employee_dialog.setColumnName("employee")
                .setDatasource(StatusScreen1DS.getInstance(searchOptions))
                .setSearchOptions(searchOptions)
                .setTitle("Employee")
                .setHaveSearch(true)
                .setMultipleChoice(true);
            
            // bind the dialog to the picker
            employee_view.setSelectionDialog(employee_dialog)
                .setTag("employee")
                .setSelectedValues(employee_values)
                .setSelectedListener(new ListSelectionPicker.ListSelectedListener() {
                @Override
                public void onSelected(ArrayList<String> selected) {
                    employee_values = selected;
                }
            });

            // Bind buttons
            Button okBtn = (Button) view.findViewById(R.id.ok);
            okBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();

                    // send filter result back to caller
                                        
                    intent.putStringArrayListExtra("status_values", status_values);
                    
                    intent.putStringArrayListExtra("employee_values", employee_values);

                    getActivity().setResult(RESULT_OK, intent);
                    getActivity().finish();
                }
            });

            Button cancelBtn = (Button) view.findViewById(R.id.reset);
            cancelBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Reset values
                                        
                    status_values = new ArrayList<String>();
                    status_view.setSelectedValues(null);
                    
                    employee_values = new ArrayList<String>();
                    employee_view.setSelectedValues(null);
                }
            });
        }

        @Override
        public void onSaveInstanceState(Bundle bundle) {
            super.onSaveInstanceState(bundle);

            // save current status
                        
            bundle.putStringArrayList("status_values", status_values);
            
            bundle.putStringArrayList("employee_values", employee_values);
        }
    }

}

