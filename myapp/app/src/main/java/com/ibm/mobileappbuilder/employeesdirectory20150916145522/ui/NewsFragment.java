package com.ibm.mobileappbuilder.employeesdirectory20150916145522.ui;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.TextView;
import com.ibm.mobileappbuilder.employeesdirectory20150916145522.R;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.ui.ListGridFragment;
import ibmmobileappbuilder.util.ViewHolder;
import ibmmobileappbuilder.ds.SearchOptions;
import ibmmobileappbuilder.ds.filter.Filter;
import java.util.Arrays;
import com.ibm.mobileappbuilder.employeesdirectory20150916145522.ds.NewsScreen1DSItem;
import com.ibm.mobileappbuilder.employeesdirectory20150916145522.ds.NewsScreen1DS;
import android.content.Intent;
import ibmmobileappbuilder.util.Constants;

import static ibmmobileappbuilder.util.NavigationUtils.generateIntentToAddOrUpdateItem;

/**
 * "NewsFragment" listing
 */
public class NewsFragment extends ListGridFragment<NewsScreen1DSItem>  {

    private Datasource<NewsScreen1DSItem> datasource;


    public static NewsFragment newInstance(Bundle args) {
        NewsFragment fr = new NewsFragment();

        fr.setArguments(args);
        return fr;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        return R.layout.news_item;
    }

    @Override
    protected Datasource<NewsScreen1DSItem> getDatasource() {
      if (datasource != null) {
        return datasource;
      }
      datasource = NewsScreen1DS.getInstance(getSearchOptions());
      return datasource;
    }

    @Override
    protected void bindView(NewsScreen1DSItem item, View view, int position) {
        
        TextView title = ViewHolder.get(view, R.id.title);
        
        if (item.title != null){
            title.setText(item.title);
            
        }
        
        TextView subtitle = ViewHolder.get(view, R.id.subtitle);
        
        if (item.date != null){
            subtitle.setText(DateFormat.getMediumDateFormat(getActivity()).format(item.date));
            
        }
    }


    @Override
    public void showDetail(NewsScreen1DSItem item, int position) {
        Bundle args = new Bundle();
        args.putInt(Constants.ITEMPOS, position);
        args.putParcelable(Constants.CONTENT, item);
        Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
        intent.putExtras(args);

        if (!getResources().getBoolean(R.bool.tabletLayout)) {
            startActivityForResult(intent, Constants.DETAIL);
        } else {
            startActivity(intent);
        }
    }

}

