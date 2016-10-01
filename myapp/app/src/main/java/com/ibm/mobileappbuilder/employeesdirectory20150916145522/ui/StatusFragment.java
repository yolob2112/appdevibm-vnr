package com.ibm.mobileappbuilder.employeesdirectory20150916145522.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.ibm.mobileappbuilder.employeesdirectory20150916145522.ds.StatusScreen1DSService;
import com.ibm.mobileappbuilder.employeesdirectory20150916145522.presenters.StatusPresenter;
import com.ibm.mobileappbuilder.employeesdirectory20150916145522.R;
import ibmmobileappbuilder.behaviors.FabBehaviour;
import ibmmobileappbuilder.behaviors.SearchBehavior;
import ibmmobileappbuilder.behaviors.SelectionBehavior;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.ui.ListGridFragment;
import ibmmobileappbuilder.util.ColorUtils;
import ibmmobileappbuilder.util.Constants;
import ibmmobileappbuilder.util.ViewHolder;
import java.util.ArrayList;
import java.util.List;
import ibmmobileappbuilder.ds.SearchOptions;
import ibmmobileappbuilder.ds.filter.Filter;
import java.util.Arrays;
import com.ibm.mobileappbuilder.employeesdirectory20150916145522.ds.StatusScreen1DSItem;
import com.ibm.mobileappbuilder.employeesdirectory20150916145522.ds.StatusScreen1DS;
import ibmmobileappbuilder.mvp.view.CrudListView;
import ibmmobileappbuilder.ds.CrudDatasource;
import android.content.Intent;
import ibmmobileappbuilder.util.Constants;

import static ibmmobileappbuilder.util.NavigationUtils.generateIntentToAddOrUpdateItem;

/**
 * "StatusFragment" listing
 */
public class StatusFragment extends ListGridFragment<StatusScreen1DSItem> implements CrudListView<StatusScreen1DSItem> {

    private CrudDatasource<StatusScreen1DSItem> datasource;

    
    ArrayList<String> status_values;
    
    ArrayList<String> employee_values;
    // "Add" button
    private FabBehaviour fabBehavior;

    public static StatusFragment newInstance(Bundle args) {
        StatusFragment fr = new StatusFragment();

        fr.setArguments(args);
        return fr;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPresenter(new StatusPresenter(
            (CrudDatasource) getDatasource(),
            this
        ));
        addBehavior(new SearchBehavior(this));
        // Multiple selection
        SelectionBehavior<StatusScreen1DSItem> selectionBehavior = new SelectionBehavior<>(
            this,
            R.string.remove_items,
            R.drawable.ic_delete_alpha);

        selectionBehavior.setCallback(new SelectionBehavior.Callback<StatusScreen1DSItem>() {
            @Override
            public void onSelected(List<StatusScreen1DSItem> selectedItems) {
                getPresenter().deleteItems(selectedItems);
            }
        });
        addBehavior(selectionBehavior);
        // FAB button
        fabBehavior = new FabBehaviour(this, R.drawable.ic_add_white, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().addForm();
            }
        });
        addBehavior(fabBehavior);
    }

    protected SearchOptions getSearchOptions() {
      SearchOptions.Builder searchOptionsBuilder = SearchOptions.Builder.searchOptions();
      return searchOptionsBuilder.build();
    }


    /**
    * Layout for the list itselft
    */
    @Override
    protected int getLayout() {
        return R.layout.fragment_list;
    }

    /**
    * Layout for each element in the list
    */
    @Override
    protected int getItemLayout() {
        return R.layout.status_item;
    }

    @Override
    protected Datasource<StatusScreen1DSItem> getDatasource() {
      if (datasource != null) {
        return datasource;
      }
      datasource = StatusScreen1DS.getInstance(getSearchOptions());
      return datasource;
    }

    @Override
    protected void bindView(StatusScreen1DSItem item, View view, int position) {
        
        TextView title = ViewHolder.get(view, R.id.title);
        
        if (item.employee != null){
            title.setText(item.employee);
            
        }
        
        TextView subtitle = ViewHolder.get(view, R.id.subtitle);
        
        if (item.status != null){
            subtitle.setText(item.status);
            
        }
    }

    @Override
    protected void itemClicked(final StatusScreen1DSItem item, final int position) {
        fabBehavior.hide(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                getPresenter().detail(item, position);
            }
        });
    }

    @Override
    public void showDetail(StatusScreen1DSItem item, int position) {
        Bundle args = new Bundle();
        args.putInt(Constants.ITEMPOS, position);
        args.putParcelable(Constants.CONTENT, item);
        Intent intent = new Intent(getActivity(), StatusDetailActivity.class);
        intent.putExtras(args);

        if (!getResources().getBoolean(R.bool.tabletLayout)) {
            startActivityForResult(intent, Constants.DETAIL);
        } else {
            startActivity(intent);
        }
    }

    @Override
    public void showAdd() {
        startActivityForResult(generateIntentToAddOrUpdateItem(null,
                        0,
                        getActivity(),
                        StatusScreen1DSItemFormActivity.class
                ), Constants.MODE_CREATE
        );
    }

    @Override
    public void showEdit(StatusScreen1DSItem item, int position) {
    startActivityForResult(
                generateIntentToAddOrUpdateItem(item,
                        position,
                        getActivity(),
                        StatusScreen1DSItemFormActivity.class
                ), Constants.MODE_EDIT
        );
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        // inflate menu options and tint icon
        inflater.inflate(R.menu.filter_menu, menu);
        ColorUtils.tintIcon(menu.findItem(R.id.filter),
                            R.color.textBarColor,
                            getActivity());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.filter){
            Intent intent = new Intent(getActivity(), StatusFilterActivity.class);

            // pass current values to filter activity
                        
            intent.putStringArrayListExtra("status_values", status_values);
            
            intent.putStringArrayListExtra("employee_values", employee_values);

            // launch filter screen
            startActivityForResult(intent, 1);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == Activity.RESULT_OK){
            // store the incoming selection
                        
            status_values = data.getStringArrayListExtra("status_values");
            
            employee_values = data.getStringArrayListExtra("employee_values");
            // apply filter to datasource
            clearFilters();

                        
            if(status_values != null && status_values.size() > 0)
                addStringFilter("status", status_values);
            
            if(employee_values != null && employee_values.size() > 0)
                addStringFilter("employee", employee_values);
            // and finally refresh the list
            refresh();

            // and redraw menu (to refresh tinted icons, like the search icon)
            getActivity().invalidateOptionsMenu();
        }
    }
}

