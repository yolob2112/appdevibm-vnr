
package com.ibm.mobileappbuilder.employeesdirectory20150916145522.ui;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.TextView;
import com.ibm.mobileappbuilder.employeesdirectory20150916145522.R;
import ibmmobileappbuilder.behaviors.ShareBehavior;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.ds.SearchOptions;
import ibmmobileappbuilder.ds.filter.Filter;
import java.util.Arrays;
import com.ibm.mobileappbuilder.employeesdirectory20150916145522.ds.NewsScreen1DSItem;
import com.ibm.mobileappbuilder.employeesdirectory20150916145522.ds.NewsScreen1DS;

public class NewsDetailFragment extends ibmmobileappbuilder.ui.DetailFragment<NewsScreen1DSItem> implements ShareBehavior.ShareListener  {

    private Datasource<NewsScreen1DSItem> datasource;
    public static NewsDetailFragment newInstance(Bundle args){
        NewsDetailFragment fr = new NewsDetailFragment();
        fr.setArguments(args);

        return fr;
    }

    public NewsDetailFragment(){
        super();
    }

    @Override
    public Datasource<NewsScreen1DSItem> getDatasource() {
      if (datasource != null) {
        return datasource;
      }
       datasource = NewsScreen1DS.getInstance(new SearchOptions());
        return datasource;
    }

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        addBehavior(new ShareBehavior(getActivity(), this));

    }

    // Bindings

    @Override
    protected int getLayout() {
        return R.layout.newsdetail_detail;
    }

    @Override
    @SuppressLint("WrongViewCast")
    public void bindView(final NewsScreen1DSItem item, View view) {
        if (item.date != null){
            
            TextView view0 = (TextView) view.findViewById(R.id.view0);
            view0.setText(DateFormat.getMediumDateFormat(getActivity()).format(item.date));
            
        }
        if (item.content != null){
            
            TextView view1 = (TextView) view.findViewById(R.id.view1);
            view1.setText(item.content);
            
        }
    }

    @Override
    protected void onShow(NewsScreen1DSItem item) {
        // set the title for this fragment
        getActivity().setTitle(item.title);
    }
    @Override
    public void onShare() {
        NewsScreen1DSItem item = getItem();

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");

        intent.putExtra(Intent.EXTRA_TEXT, (item.date != null ? DateFormat.getMediumDateFormat(getActivity()).format(item.date) : "" ) + "\n" +
                    (item.content != null ? item.content : "" ));
        intent.putExtra(Intent.EXTRA_SUBJECT, item.title);
        startActivityForResult(Intent.createChooser(intent, getString(R.string.share)), 1);
    }
}

