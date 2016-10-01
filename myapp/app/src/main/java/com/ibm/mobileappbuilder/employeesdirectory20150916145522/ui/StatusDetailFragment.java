
package com.ibm.mobileappbuilder.employeesdirectory20150916145522.ui;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.ibm.mobileappbuilder.employeesdirectory20150916145522.ds.StatusScreen1DSService;
import com.ibm.mobileappbuilder.employeesdirectory20150916145522.presenters.StatusDetailPresenter;
import com.ibm.mobileappbuilder.employeesdirectory20150916145522.R;
import ibmmobileappbuilder.actions.ActivityIntentLauncher;
import ibmmobileappbuilder.actions.MapsAction;
import ibmmobileappbuilder.behaviors.FabBehaviour;
import ibmmobileappbuilder.behaviors.ShareBehavior;
import ibmmobileappbuilder.mvp.presenter.DetailCrudPresenter;
import ibmmobileappbuilder.util.ColorUtils;
import ibmmobileappbuilder.util.Constants;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.ds.CrudDatasource;
import ibmmobileappbuilder.ds.SearchOptions;
import ibmmobileappbuilder.ds.filter.Filter;
import java.util.Arrays;
import com.ibm.mobileappbuilder.employeesdirectory20150916145522.ds.StatusScreen1DSItem;
import com.ibm.mobileappbuilder.employeesdirectory20150916145522.ds.StatusScreen1DS;

public class StatusDetailFragment extends ibmmobileappbuilder.ui.DetailFragment<StatusScreen1DSItem> implements ShareBehavior.ShareListener  {

    private CrudDatasource<StatusScreen1DSItem> datasource;
    public static StatusDetailFragment newInstance(Bundle args){
        StatusDetailFragment fr = new StatusDetailFragment();
        fr.setArguments(args);

        return fr;
    }

    public StatusDetailFragment(){
        super();
    }

    @Override
    public Datasource<StatusScreen1DSItem> getDatasource() {
      if (datasource != null) {
        return datasource;
      }
       datasource = StatusScreen1DS.getInstance(new SearchOptions());
        return datasource;
    }

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        // the presenter for this view
        setPresenter(new StatusDetailPresenter(
                (CrudDatasource) getDatasource(),
                this));
        // Edit button
        addBehavior(new FabBehaviour(this, R.drawable.ic_edit_white, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((DetailCrudPresenter<StatusScreen1DSItem>) getPresenter()).editForm(getItem());
            }
        }));
        addBehavior(new ShareBehavior(getActivity(), this));

    }

    // Bindings

    @Override
    protected int getLayout() {
        return R.layout.statusdetail_detail;
    }

    @Override
    @SuppressLint("WrongViewCast")
    public void bindView(final StatusScreen1DSItem item, View view) {
        if (item.status != null){
            
            TextView view0 = (TextView) view.findViewById(R.id.view0);
            view0.setText(item.status);
            
        }
        if (item.startDate != null){
            
            TextView view1 = (TextView) view.findViewById(R.id.view1);
            view1.setText(DateFormat.getMediumDateFormat(getActivity()).format(item.startDate) + " " + DateFormat.getTimeFormat(getActivity()).format(item.startDate));
            
        }
        if (item.endDate != null){
            
            TextView view2 = (TextView) view.findViewById(R.id.view2);
            view2.setText(DateFormat.getMediumDateFormat(getActivity()).format(item.endDate) + " " + DateFormat.getTimeFormat(getActivity()).format(item.endDate));
            
        }
        
        TextView view3 = (TextView) view.findViewById(R.id.view3);
        view3.setText("Find on map");
        bindAction(view3, new MapsAction(
        new ActivityIntentLauncher()
        , "http://maps.google.com/maps?q=" + item.location.toString()));
    }

    @Override
    protected void onShow(StatusScreen1DSItem item) {
        // set the title for this fragment
        getActivity().setTitle(item.employee);
    }

    @Override
    public void navigateToEditForm() {
        Bundle args = new Bundle();

        args.putInt(Constants.ITEMPOS, 0);
        args.putParcelable(Constants.CONTENT, getItem());
        args.putInt(Constants.MODE, Constants.MODE_EDIT);

        Intent intent = new Intent(getActivity(), StatusScreen1DSItemFormActivity.class);
        intent.putExtras(args);
        startActivityForResult(intent, Constants.MODE_EDIT);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.delete_menu, menu);

        MenuItem item = menu.findItem(R.id.action_delete);
        ColorUtils.tintIcon(item, R.color.textBarColor, getActivity());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_delete){
            ((DetailCrudPresenter<StatusScreen1DSItem>) getPresenter()).deleteItem(getItem());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onShare() {
        StatusScreen1DSItem item = getItem();

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");

        intent.putExtra(Intent.EXTRA_TEXT, (item.status != null ? item.status : "" ) + "\n" +
                    (item.startDate != null ? DateFormat.getMediumDateFormat(getActivity()).format(item.startDate) + " " + DateFormat.getTimeFormat(getActivity()).format(item.startDate) : "" ) + "\n" +
                    (item.endDate != null ? DateFormat.getMediumDateFormat(getActivity()).format(item.endDate) + " " + DateFormat.getTimeFormat(getActivity()).format(item.endDate) : "" ) + "\n" +
                    "Find on map");
        intent.putExtra(Intent.EXTRA_SUBJECT, item.employee);
        startActivityForResult(Intent.createChooser(intent, getString(R.string.share)), 1);
    }
}

