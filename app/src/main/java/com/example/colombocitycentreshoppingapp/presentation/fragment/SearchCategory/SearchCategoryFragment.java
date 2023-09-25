package com.example.colombocitycentreshoppingapp.presentation.fragment.SearchCategory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.colombocitycentreshoppingapp.R;
import com.example.colombocitycentreshoppingapp.presentation.activity.main.MainActivity;
import com.example.colombocitycentreshoppingapp.presentation.adapter.SearchCategoriesAdapters.SearchCategoryAdapter;

import java.util.ArrayList;


public class SearchCategoryFragment extends Fragment {

    RecyclerView rvRecentSearches;
    RecyclerView rvTopCategorySearches;
    ArrayList<String> recentSearchesList = new ArrayList<>();
    ArrayList<String> topCategoryList = new ArrayList<>();
    ImageView ivFashion, ivElectronics, ivHomeGarden, ivBeauty, ivToys,
                ivJewelry, ivHealthCare, ivFood;

    TextView tvFashion, tvElectronics, tvHomeGarden, tvBeauty, tvToys,
            tvJewelry, tvHealthCare, tvFood;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search_category, container, false);

        ImageView ivBackArrow = (ImageView)rootView.findViewById(R.id.iv_back);
        ConstraintLayout clCategories = (ConstraintLayout) rootView.findViewById(R.id.cl_categories);
        ConstraintLayout clSearches = (ConstraintLayout) rootView.findViewById(R.id.cl_searches);
        EditText etSearch = (EditText) rootView.findViewById(R.id.et_search);
        ConstraintLayout clSearch = (ConstraintLayout) rootView.findViewById(R.id.cl_search);
        TextView tvClear = (TextView) clSearch.findViewById(R.id.tv_clear);
        rvRecentSearches = (RecyclerView) rootView.findViewById(R.id.rv_recent_searches);
        rvTopCategorySearches = (RecyclerView) rootView.findViewById(R.id.rv_top_search_categories);

        ivFashion = (ImageView) rootView.findViewById(R.id.iv_fashion);
        ivElectronics = (ImageView) rootView.findViewById(R.id.iv_electronics);
        ivHomeGarden = (ImageView) rootView.findViewById(R.id.iv_home_garden);
        ivBeauty = (ImageView) rootView.findViewById(R.id.iv_beauty);
        ivToys = (ImageView) rootView.findViewById(R.id.iv_toys);
        ivJewelry = (ImageView) rootView.findViewById(R.id.iv_jewelry);
        ivHealthCare = (ImageView) rootView.findViewById(R.id.iv_health_care);
        ivFood = (ImageView) rootView.findViewById(R.id.iv_food);

        tvFashion = (TextView) rootView.findViewById(R.id.tv_fashion);
        tvElectronics = (TextView) rootView.findViewById(R.id.tv_electronics);
        tvHomeGarden = (TextView) rootView.findViewById(R.id.tv_home_garden);
        tvBeauty = (TextView) rootView.findViewById(R.id.tv_beauty);
        tvToys = (TextView) rootView.findViewById(R.id.tv_toys);
        tvJewelry = (TextView) rootView.findViewById(R.id.tv_jewelry);
        tvHealthCare = (TextView) rootView.findViewById(R.id.tv_health_care);
        tvFood = (TextView) rootView.findViewById(R.id.tv_food);

        setListItemsData();
        tvClear.setVisibility(View.GONE);

        etSearch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                clCategories.setVisibility(View.GONE);
                clSearches.setVisibility(View.VISIBLE);

                return false;
            }
        });

        ivBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragmentObj = ((MainActivity) getContext()).getFragment("HomeFragment");
                ((MainActivity) getContext()).callFragment(fragmentObj);
            }
        });

        setGridViewData();
        setRecentSearchListData();
        setTopCategorySearchListData();

        return rootView;
    }

    private void setListItemsData(){
        ivFashion.setImageDrawable(getContext().getDrawable(R.drawable.fashion));
        ivElectronics.setImageDrawable(getContext().getDrawable(R.drawable.electronics));
        ivHomeGarden.setImageDrawable(getContext().getDrawable(R.drawable.home_garden));
        ivBeauty.setImageDrawable(getContext().getDrawable(R.drawable.beauty));
        ivToys.setImageDrawable(getContext().getDrawable(R.drawable.toys));
        ivJewelry.setImageDrawable(getContext().getDrawable(R.drawable.jewelry));
        ivHealthCare.setImageDrawable(getContext().getDrawable(R.drawable.health_care));
        ivFood.setImageDrawable(getContext().getDrawable(R.drawable.food));

        tvFashion.setText(getString(R.string.fashion));
        tvElectronics.setText(getString(R.string.electronics));
        tvHomeGarden.setText(getString(R.string.home_garden));
        tvBeauty.setText(getString(R.string.beauty));
        tvToys.setText(getString(R.string.toys));
        tvJewelry.setText(getString(R.string.jewelry));
        tvHealthCare.setText(getString(R.string.health_care));
        tvFood.setText(getString(R.string.food));
    }

    private void setGridViewData() {

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        rvRecentSearches.setLayoutManager(gridLayoutManager);
        rvRecentSearches.setHasFixedSize(true);

        GridLayoutManager gridLayoutManager1= new GridLayoutManager(getActivity(), 1);
        rvTopCategorySearches.setLayoutManager(gridLayoutManager1);
        rvTopCategorySearches.setHasFixedSize(true);
    }


    private void setRecentSearchListData(){

        recentSearchesList.add("Body Spray");
        recentSearchesList.add("White Range Gift Box");
        recentSearchesList.add("Eye Brow Nourishing");

        SearchCategoryAdapter searchCategoryAdapter = new SearchCategoryAdapter(getContext(), recentSearchesList, new SearchCategoryAdapter.CustomClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("itemName", recentSearchesList.get(position));
                ((MainActivity) getActivity()).callDialogFragmentManager("SearchCategoryItemFragment", bundle);
            }
        });
        rvRecentSearches.setAdapter(searchCategoryAdapter);
    }

    private void setTopCategorySearchListData(){

        topCategoryList.add(getString(R.string.fashion));
        topCategoryList.add(getString(R.string.home_garden));
        topCategoryList.add(getString(R.string.jewelry));
        topCategoryList.add(getString(R.string.electronics));
        topCategoryList.add(getString(R.string.toys));
        topCategoryList.add(getString(R.string.food));
        topCategoryList.add(getString(R.string.health_care));

        SearchCategoryAdapter searchCategoryAdapter = new SearchCategoryAdapter(getContext(), topCategoryList, new SearchCategoryAdapter.CustomClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("itemName", topCategoryList.get(position));
                ((MainActivity) getActivity()).callDialogFragmentManager("SearchCategoryItemFragment", bundle);
            }
        });
        rvTopCategorySearches.setAdapter(searchCategoryAdapter);
    }
}