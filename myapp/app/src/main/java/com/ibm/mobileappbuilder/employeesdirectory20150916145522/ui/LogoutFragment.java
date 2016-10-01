package com.ibm.mobileappbuilder.employeesdirectory20150916145522.ui;

import android.os.Bundle;

import com.ibm.mobileappbuilder.employeesdirectory20150916145522.MyApplication;

import ibmmobileappbuilder.ui.BaseFragment;
import ibmmobileappbuilder.util.LoginUtils;

public class LogoutFragment extends BaseFragment {

    public static LogoutFragment newInstance(Bundle bundle) {
        return new LogoutFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoginUtils.logOut(((MyApplication) getActivity().getApplication()).getSecureSharedPreferences(),
                LoginActivity.class,
                getActivity()
        );
    }
}

