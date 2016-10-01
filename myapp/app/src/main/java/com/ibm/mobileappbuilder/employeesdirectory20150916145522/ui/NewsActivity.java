

package com.ibm.mobileappbuilder.employeesdirectory20150916145522.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.ibm.mobileappbuilder.employeesdirectory20150916145522.R;

import ibmmobileappbuilder.ui.BaseListingActivity;
/**
 * NewsActivity list activity
 */
public class NewsActivity extends BaseListingActivity {

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(getString(R.string.newsActivity));
    }

    @Override
    protected Class<? extends Fragment> getFragmentClass() {
        return NewsFragment.class;
    }

}

