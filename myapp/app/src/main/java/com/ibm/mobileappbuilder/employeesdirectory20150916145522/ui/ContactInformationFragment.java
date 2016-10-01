
package com.ibm.mobileappbuilder.employeesdirectory20150916145522.ui;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.ibm.mobileappbuilder.employeesdirectory20150916145522.R;
import ibmmobileappbuilder.ds.Datasource;
import android.widget.ImageView;
import android.widget.TextView;
import ibmmobileappbuilder.ds.restds.AppNowDatasource;
import ibmmobileappbuilder.util.image.ImageLoader;
import ibmmobileappbuilder.util.image.PicassoImageLoader;
import ibmmobileappbuilder.util.StringUtils;
import java.net.URL;
import static ibmmobileappbuilder.util.image.ImageLoaderRequest.Builder.imageLoaderRequest;
import ibmmobileappbuilder.ds.SearchOptions;
import ibmmobileappbuilder.ds.filter.Filter;
import java.util.Arrays;
import com.ibm.mobileappbuilder.employeesdirectory20150916145522.ds.EmployeesDBDSItem;
import com.ibm.mobileappbuilder.employeesdirectory20150916145522.ds.EmployeesDBDS;

public class ContactInformationFragment extends ibmmobileappbuilder.ui.DetailFragment<EmployeesDBDSItem>  {

    private Datasource<EmployeesDBDSItem> datasource;
    private SearchOptions searchOptions;

    public static ContactInformationFragment newInstance(Bundle args){
        ContactInformationFragment card = new ContactInformationFragment();
        card.setArguments(args);

        return card;
    }

    public ContactInformationFragment(){
        super();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            searchOptions = SearchOptions.Builder.searchOptions().build();
    }

    @Override
    public Datasource getDatasource() {
      if (datasource != null) {
          return datasource;
      }
          datasource = EmployeesDBDS.getInstance(searchOptions);
          return datasource;
    }

    // Bindings

    @Override
    protected int getLayout() {
        return R.layout.contactinformation_custom;
    }

    @Override
    @SuppressLint("WrongViewCast")
    public void bindView(final EmployeesDBDSItem item, View view) {
        if (item.name != null){
            
            TextView view0 = (TextView) view.findViewById(R.id.view0);
            view0.setText(item.name);
            
        }
        if (item.lastname != null){
            
            TextView view1 = (TextView) view.findViewById(R.id.view1);
            view1.setText(item.lastname);
            
        }
        
        ImageView view2 = (ImageView) view.findViewById(R.id.view2);
        URL view2Media = ((AppNowDatasource) getDatasource()).getImageUrl(item.picture);
        if(view2Media != null){
          ImageLoader imageLoader = new PicassoImageLoader(view2.getContext());
          imageLoader.load(imageLoaderRequest()
                                   .withPath(view2Media.toExternalForm())
                                   .withTargetView(view2)
                                   .fit()
                                   .build()
                    );
        	
        } else {
          view2.setImageDrawable(null);
        }
        if (item.role != null){
            
            TextView view3 = (TextView) view.findViewById(R.id.view3);
            view3.setText(item.role);
            
        }
        if (item.email != null){
            
            TextView view4 = (TextView) view.findViewById(R.id.view4);
            view4.setText(item.email);
            
        }
        if (item.phone != null){
            
            TextView view5 = (TextView) view.findViewById(R.id.view5);
            view5.setText(item.phone);
            
        }
    }

    @Override
    protected void onShow(EmployeesDBDSItem item) {
        // set the title for this fragment
        getActivity().setTitle("Contact Information");
    }

}

