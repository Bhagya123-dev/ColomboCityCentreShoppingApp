package com.example.colombocitycentreshoppingapp.presentation.fragment.SearchCategory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search_category, container, false);

        ImageView ivBackArrow = (ImageView)rootView.findViewById(R.id.iv_back);
        ConstraintLayout clCategories = (ConstraintLayout) rootView.findViewById(R.id.cl_categories);
        ConstraintLayout clSearches = (ConstraintLayout) rootView.findViewById(R.id.cl_searches);
        EditText etSearch = (EditText) rootView.findViewById(R.id.et_search);
        rvRecentSearches = (RecyclerView) rootView.findViewById(R.id.rv_recent_searches);
        rvTopCategorySearches = (RecyclerView) rootView.findViewById(R.id.rv_top_search_categories);

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