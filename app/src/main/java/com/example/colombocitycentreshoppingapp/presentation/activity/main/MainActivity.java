package com.example.colombocitycentreshoppingapp.presentation.activity.main;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.colombocitycentreshoppingapp.R;
import com.example.colombocitycentreshoppingapp.presentation.fragment.CartFragment;
import com.example.colombocitycentreshoppingapp.presentation.fragment.Home.ConfirmLocationFragment;
import com.example.colombocitycentreshoppingapp.presentation.fragment.Home.HomeFragment;
import com.example.colombocitycentreshoppingapp.presentation.fragment.OrdersFragment;
import com.example.colombocitycentreshoppingapp.presentation.fragment.ProfileFragment;
import com.example.colombocitycentreshoppingapp.presentation.fragment.SearchCategory.SearchCategoryFragment;
import com.example.colombocitycentreshoppingapp.presentation.fragment.SearchCategoryItem.SearchCategoryItemFragment;
import com.example.colombocitycentreshoppingapp.presentation.fragment.ShopFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bnvMenu = findViewById(R.id.bnv_menu);

        getWindow().setBackgroundDrawable(null);
        bnvMenu.getMenu().getItem(0).setIcon(R.drawable.home_fill);

        bnvMenu.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.i_home) {
                callFragment(getFragment("HomeFragment"));
            } else if (item.getItemId() == R.id.i_shops) {
                callFragment(getFragment("ShopFragment"));
            } else if (item.getItemId() == R.id.i_orders) {
                callFragment(getFragment("OrdersFragment"));
            } else if (item.getItemId() == R.id.i_cart) {
                callFragment(getFragment("CartFragment"));
            } else if (item.getItemId() == R.id.i_profile) {
                callFragment(getFragment("ProfileFragment"));
            }
            return true;
        });

        callFragment(getFragment("HomeFragment"));

    }

    public Fragment getFragment(String tag) {

        if (tag.equals("HomeFragment")) {
            return new HomeFragment();
        } else if (tag.equals("ShopFragment")) {
            return new ShopFragment();
        } else if (tag.equals("OrdersFragment")) {
            return new OrdersFragment();
        } else if (tag.equals("CartFragment")) {
            return new CartFragment();
        }else if (tag.equals("ProfileFragment")) {
            return new ProfileFragment();
        }else if(tag.equals("SearchCategoryFragment")){
            return new SearchCategoryFragment();
        }
        return null;
    }

    public void callFragment(Fragment fragmentObj) {
        try {
            FragmentTransaction fragTransaction = getSupportFragmentManager().beginTransaction();
            fragTransaction.replace(R.id.f_container, fragmentObj);
            fragTransaction.commitAllowingStateLoss();

        } catch (Exception ex) {
            Log.e(TAG, "callFragment fragmentObj: " + ex.toString());
        }
    }

    public void callDialogFragmentManager(String tag, Bundle args){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (tag.equals("SearchCategoryItemFragment")) {
            SearchCategoryItemFragment dialog = new SearchCategoryItemFragment();
            dialog.setArguments(args);
            dialog.show(ft, tag);
        } else if (tag.equals("ConfirmLocationFragment")) {
            ConfirmLocationFragment dialog = new ConfirmLocationFragment();
            dialog.setArguments(args);
            dialog.show(ft, tag);
        }
    }
}