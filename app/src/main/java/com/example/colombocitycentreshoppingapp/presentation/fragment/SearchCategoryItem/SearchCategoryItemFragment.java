package com.example.colombocitycentreshoppingapp.presentation.fragment.SearchCategoryItem;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.colombocitycentreshoppingapp.R;
import com.example.colombocitycentreshoppingapp.presentation.activity.main.MainActivity;


public class SearchCategoryItemFragment extends DialogFragment {

    @Override
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
        View rootView = inflater.inflate(R.layout.fragment_search_category_item, container, false);

        ImageView ivBackArrow = (ImageView)rootView.findViewById(R.id.iv_back);
        EditText etCategory = (EditText) rootView.findViewById(R.id.et_search);

        ConstraintLayout clSearch = (ConstraintLayout) rootView.findViewById(R.id.cl_search);
        TextView tvClear = (TextView) clSearch.findViewById(R.id.tv_clear);

        Bundle bundle = getArguments();
        if(bundle != null){
            String itemName = bundle.getString("itemName");

            etCategory.setText(itemName);
            etCategory.setEnabled(false);

        }

        tvClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etCategory.setText(null);
            }
        });

        ivBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               dismiss();
            }
        });

        return rootView;
    }
}