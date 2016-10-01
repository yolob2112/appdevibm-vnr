

package com.ibm.mobileappbuilder.employeesdirectory20150916145522.ui;

import android.os.Bundle;

import com.ibm.mobileappbuilder.employeesdirectory20150916145522.R;

import java.util.ArrayList;
import java.util.List;

import ibmmobileappbuilder.MenuItem;

import ibmmobileappbuilder.actions.StartActivityAction;
import ibmmobileappbuilder.util.Constants;

/**
 * MenuFragment menu fragment.
 */
public class MenuFragment extends ibmmobileappbuilder.ui.MenuFragment {

    /**
     * Default constructor
     */
    public MenuFragment(){
        super();
    }

    // Factory method
    public static MenuFragment newInstance(Bundle args) {
        MenuFragment fragment = new MenuFragment();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
      public void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
                }

    // Menu Fragment interface
    @Override
    public List<MenuItem> getMenuItems() {
        ArrayList<MenuItem> items = new ArrayList<MenuItem>();
        items.add(new MenuItem()
            .setLabel("PROFESSORS")
            .setIcon(R.drawable.png_employees251)
            .setAction(new StartActivityAction(EmployeesActivity.class, Constants.DETAIL))
        );
        items.add(new MenuItem()
            .setLabel("STATUS")
            .setIcon(R.drawable.png_status126)
            .setAction(new StartActivityAction(StatusActivity.class, Constants.DETAIL))
        );
        items.add(new MenuItem()
            .setLabel("NEWS")
            .setIcon(R.drawable.png_news859)
            .setAction(new StartActivityAction(NewsActivity.class, Constants.DETAIL))
        );
        items.add(new MenuItem()
            .setLabel("CONTACT")
            .setIcon(R.drawable.png_contact504)
            .setAction(new StartActivityAction(ContactInformationActivity.class, Constants.DETAIL))
        );
        return items;
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_grid;
    }

    @Override
    public int getItemLayout() {
        return R.layout.menu_item;
    }
}

