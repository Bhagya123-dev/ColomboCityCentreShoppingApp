package com.example.colombocitycentreshoppingapp.presentation.fragment.Home;

import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.colombocitycentreshoppingapp.R;
import com.example.colombocitycentreshoppingapp.domain.model.RecentLocation;
import com.example.colombocitycentreshoppingapp.domain.model.Shops;
import com.example.colombocitycentreshoppingapp.domain.model.TrendingItem;
import com.example.colombocitycentreshoppingapp.presentation.activity.main.MainActivity;
import com.example.colombocitycentreshoppingapp.presentation.adapter.HomeAdapters.HomeTrendingItemsAdapter;
import com.example.colombocitycentreshoppingapp.presentation.adapter.HomeAdapters.ImageViewPagerAdapter;
import com.example.colombocitycentreshoppingapp.presentation.adapter.HomeAdapters.RecentLocationAdapter;
import com.example.colombocitycentreshoppingapp.presentation.adapter.HomeAdapters.ShopsAdapter;
import com.example.colombocitycentreshoppingapp.presentation.adapter.HomeAdapters.TopMerchantAdapter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator3;


public class HomeFragment extends Fragment {
    View rootView;
    RecyclerView rvTrendingItemList;
    RecyclerView rvShopsList;
    RecyclerView rvTopMerchant;
    RecyclerView rvProducts;
    RecyclerView rvAds;

    RecyclerView rvRecentLocation;
    ViewPager2 viewPager2;

    CircleIndicator3 circleIndicator;
    CircleIndicator3 circleIndicator3Ads;
    ArrayList<TrendingItem> trendingItemList = new ArrayList<>();
    ArrayList<Shops> shopsArrayList = new ArrayList<>();
    ArrayList<Shops> topMerchantArrayList = new ArrayList<>();
    ArrayList<Shops> productsArrayList = new ArrayList<>();
    ArrayList<Shops> adsList = new ArrayList<>();
    ArrayList<Drawable> imageDrawableList = new ArrayList<>();

    ArrayList<RecentLocation> recentLocationArrayList = new ArrayList<>();

    private GoogleMap mMap;
    public HomeFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);

        getActivity().getWindow().getDecorView().setSystemUiVisibility(0);
        getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.yellow));

        rvTrendingItemList = rootView.findViewById(R.id.rv_trending_items);
        rvShopsList = rootView.findViewById(R.id.rv_shops);
        rvTopMerchant = rootView.findViewById(R.id.rv_top_merchant);
        rvProducts = rootView.findViewById(R.id.rv_products);
        viewPager2 = rootView.findViewById(R.id.view_pager2);
        rvAds = rootView.findViewById(R.id.rv_ads);
        circleIndicator = rootView.findViewById(R.id.indicator);
        circleIndicator3Ads = rootView.findViewById(R.id.indicator_ads);
        ConstraintLayout clDeliverNow = rootView.findViewById(R.id.cl_deliverNow);
        ConstraintLayout clSearch = rootView.findViewById(R.id.cl_search);

        clDeliverNow.bringToFront();

        setHomeBannersData();
        setGridViewData();
        setListData();
        setShopsListData();
        setTopMerchantListData();
        setHomeAdsBannersData();
        setProductsListData();

        clDeliverNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheetUpdateDeliveryLocationDialog();
            }
        });

        clSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragmentObj = ((MainActivity) getContext()).getFragment("SearchCategoryFragment");
                ((MainActivity) getContext()).callFragment(fragmentObj);
            }
        });

        return rootView;
    }

    private void setHomeBannersData(){
        imageDrawableList.add(getContext().getDrawable(R.drawable.home_banner1));
        imageDrawableList.add(getContext().getDrawable(R.drawable.home_banner2));
        imageDrawableList.add(getContext().getDrawable(R.drawable.home_banner3));

        ImageViewPagerAdapter imageViewPagerAdapter = new ImageViewPagerAdapter(getContext(), imageDrawableList);
        viewPager2.setAdapter(imageViewPagerAdapter);
        circleIndicator.setViewPager(viewPager2);
    }

    private void setGridViewData() {
        LinearLayoutManager linearLayoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvTrendingItemList.setLayoutManager(linearLayoutManager);

        LinearLayoutManager linearLayoutManager1
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvTopMerchant.setLayoutManager(linearLayoutManager1);

        LinearLayoutManager linearLayoutManager2
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvAds.setLayoutManager(linearLayoutManager2);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        rvShopsList.setLayoutManager(gridLayoutManager);
        rvShopsList.setHasFixedSize(true);

        GridLayoutManager gridLayoutManager1= new GridLayoutManager(getActivity(), 1);
        rvProducts.setLayoutManager(gridLayoutManager1);
        rvProducts.setHasFixedSize(true);
    }

    private void setListData(){
        TrendingItem trendingItem1 = new TrendingItem(getContext().getDrawable(R.drawable.mi_band),getContext().getString(R.string.mi_band_smart), false, "Rs.3500");
        trendingItemList.add(trendingItem1);

        TrendingItem trendingItem2 = new TrendingItem(getContext().getDrawable(R.drawable.wireless_earpods),getContext().getString(R.string.jovroom_wireless), true, "Rs.5500");
        trendingItemList.add(trendingItem2);

        TrendingItem trendingItem3 = new TrendingItem(getContext().getDrawable(R.drawable.mi_band),getContext().getString(R.string.toys), false,"Rs.2000");
        trendingItemList.add(trendingItem3);


        HomeTrendingItemsAdapter homeTrendingItemsAdapter = new HomeTrendingItemsAdapter(getContext(), trendingItemList, new HomeTrendingItemsAdapter.CustomClickListener() {
            @Override
            public void onItemClick(View v, int position) {

            }
        });
        rvTrendingItemList.setAdapter(homeTrendingItemsAdapter);
    }

    private void setShopsListData(){
        Shops shops1 = new Shops(getContext().getDrawable(R.drawable.miniso));
        shopsArrayList.add(shops1);

        Shops shops2 = new Shops(getContext().getDrawable(R.drawable.mango));
        shopsArrayList.add(shops2);

        ShopsAdapter shopsAdapter = new ShopsAdapter(getContext(), shopsArrayList, new ShopsAdapter.CustomClickListener() {
            @Override
            public void onItemClick(View v, int position) {

            }
        });
        rvShopsList.setAdapter(shopsAdapter);
    }

    private void setTopMerchantListData(){
        Shops shops1 = new Shops(getContext().getDrawable(R.drawable.titan));
        topMerchantArrayList.add(shops1);

        Shops shops2 = new Shops(getContext().getDrawable(R.drawable.shop1));
        topMerchantArrayList.add(shops2);

        topMerchantArrayList.add(shops1);
        topMerchantArrayList.add(shops2);

        TopMerchantAdapter topMerchantAdapter = new TopMerchantAdapter(getContext(), topMerchantArrayList, new TopMerchantAdapter.CustomClickListener() {
            @Override
            public void onItemClick(View v, int position) {

            }
        });
        rvTopMerchant.setAdapter(topMerchantAdapter);
    }

    private void setHomeAdsBannersData(){
        Shops shops1 = new Shops(getContext().getDrawable(R.drawable.last_unit));
        adsList.add(shops1);

        Shops shops2 = new Shops(getContext().getDrawable(R.drawable.last_unit));
        adsList.add(shops2);

        adsList.add(shops1);
        adsList.add(shops2);

        ShopsAdapter topMerchantAdapter = new ShopsAdapter(getContext(), adsList, new ShopsAdapter.CustomClickListener() {
            @Override
            public void onItemClick(View v, int position) {

            }
        });
        rvAds.setAdapter(topMerchantAdapter);
    }

    private void setProductsListData(){
        Shops shops1 = new Shops(getContext().getDrawable(R.drawable.mothercare));
        productsArrayList.add(shops1);

        ShopsAdapter shopsAdapter = new ShopsAdapter(getContext(), productsArrayList, new ShopsAdapter.CustomClickListener() {
            @Override
            public void onItemClick(View v, int position) {

            }
        });
        rvProducts.setAdapter(shopsAdapter);
    }


    //Show Update Delivery Location Bottom Sheet
    private void showBottomSheetUpdateDeliveryLocationDialog() {

        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext());
        bottomSheetDialog.setContentView(R.layout.update_delivery_location);

        ImageView ivBack = bottomSheetDialog.findViewById(R.id.iv_back);
        rvRecentLocation = bottomSheetDialog.findViewById(R.id.rv_recent_locations);

        GridLayoutManager gridLayoutManager1= new GridLayoutManager(getActivity(), 1);
        rvRecentLocation.setLayoutManager(gridLayoutManager1);
        rvRecentLocation.setHasFixedSize(true);

        setRecentLocationData();

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.cancel();
            }
        });
        bottomSheetDialog.show();
    }

    private void setRecentLocationData(){
        recentLocationArrayList.clear();

        RecentLocation recentLocation1 = new RecentLocation("Kavindu Motor Engineering","44 Aponso Mawatham Moratuwa");
        recentLocationArrayList.add(recentLocation1);

        RecentLocation recentLocation2 = new RecentLocation("Home","148/8 Agulana, STation Road, Moratuwa");
        recentLocationArrayList.add(recentLocation2);

        RecentLocation recentLocation3 = new RecentLocation("1st Lane","66/1/1 Yogashwarama Mawatha, Thelawala");
        recentLocationArrayList.add(recentLocation3);

        RecentLocationAdapter recentLocationAdapter = new RecentLocationAdapter(getContext(), recentLocationArrayList, new RecentLocationAdapter.CustomClickListener() {
            @Override
            public void onItemClick(View v, int position) {

                showChangeDeliveryAddressBottomSheet(recentLocationArrayList.get(position).getLocationName(), recentLocationArrayList.get(position).getLocationAddress());
            }
        });
        rvRecentLocation.setAdapter(recentLocationAdapter);
    }

    //Change Delivery Details
    private void showChangeDeliveryAddressBottomSheet(String locationName, String locationAddress){
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext());
        bottomSheetDialog.setContentView(R.layout.change_delivery_address);

        ImageView ivBack = bottomSheetDialog.findViewById(R.id.iv_back);
        ConstraintLayout clAdjustPin = bottomSheetDialog.findViewById(R.id.cl_adjust_pin);
        TextView tvLocationName = bottomSheetDialog.findViewById(R.id.tv_location_name);
        TextView tvLocationAddress = bottomSheetDialog.findViewById(R.id.tv_location_address);

        //Map View
        showMapFragment();

        if(locationName != null) {
            tvLocationName.setText(locationName);
        }else {
            tvLocationName.setText("-");
        }

        if(tvLocationAddress != null) {
            tvLocationAddress.setText(locationAddress);
        }else {
            tvLocationAddress.setText("-");
        }

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.cancel();
            }
        });

        clAdjustPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("locationName", locationName);
                bundle.putString("locationAddress", locationAddress);
                ((MainActivity) getActivity()).callDialogFragmentManager("ConfirmLocationFragment", bundle);
            }
        });
        bottomSheetDialog.show();
    }

    private void showMapFragment() {
        SupportMapFragment mapFragment = (SupportMapFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {

                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(new LatLng(7.8731, 80.7718)).zoom(18f).build();
                googleMap.moveCamera(CameraUpdateFactory
                        .newCameraPosition(cameraPosition));
            }
        });
    }
}