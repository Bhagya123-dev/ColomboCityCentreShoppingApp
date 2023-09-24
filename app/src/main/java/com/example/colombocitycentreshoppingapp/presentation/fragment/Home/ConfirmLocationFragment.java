package com.example.colombocitycentreshoppingapp.presentation.fragment.Home;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.example.colombocitycentreshoppingapp.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;


public class ConfirmLocationFragment extends DialogFragment {

    MapView mvMap;
    public ConfirmLocationFragment() {
        // Required empty public constructor
    }

    public void onStart() {
        super.onStart();

        Window window = getDialog().getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        getDialog().setCanceledOnTouchOutside(true);

    }

    @Override
    public int getTheme() {
        return R.style.FullScreenDialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View rootView = inflater.inflate(R.layout.fragment_confirm_location, container, false);

       TextView tvLocationName = rootView.findViewById(R.id.tv_location_name);
       TextView tvLocationAddress = rootView.findViewById(R.id.tv_location_address);
       Button btConfirmLocation = rootView.findViewById(R.id.bt_confirm_location);
       ImageView ivBack = rootView.findViewById(R.id.iv_back);
       mvMap = rootView.findViewById(R.id.mv_map);

       mvMap.onCreate(savedInstanceState);

        mvMap.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(new LatLng(7.8731, 80.7718)).zoom(18f).build();
                googleMap.moveCamera(CameraUpdateFactory
                        .newCameraPosition(cameraPosition));
            }
        });

       Bundle bundle = getArguments();
       if(bundle != null){
           String locationName = bundle.getString("locationName");
           String locationAddress = bundle.getString("locationAddress");

           tvLocationName.setText(locationName);
           tvLocationAddress.setText(locationAddress);
       }

       btConfirmLocation.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

           }
       });

       ivBack.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               dismiss();
           }
       });
        return rootView;
    }

    public Bitmap resizeMapIcons(String iconName, int width, int height) {
        Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(), getResources().getIdentifier(iconName, "drawable", getActivity().getPackageName()));
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, width, height, false);
        return resizedBitmap;
    }

    @Override
    public void onResume() {
        super.onResume();
        mvMap.onResume();
    }

    @Override
    public void onPause() {
        mvMap.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mvMap.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mvMap.onLowMemory();
    }
}