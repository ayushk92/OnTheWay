package com.example.mynanodegreeapps.ontheway;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CostForTwoFragment extends Fragment {

    public static final int COST_FOR_TWO_CHANGED = 1;

    private void setResult(int priceRange){
        RestaurantFilters.getInstance().setSelectedCostForTwo(priceRange);
        ((FilterFragment.Callback)getActivity()).onFilterChanged(COST_FOR_TWO_CHANGED);
    }

    @OnClick(R.id.underfivehundred)
    void onClickUnderFiveHundred(){
        setResult(1);
    }

    @OnClick(R.id.underthousand)
    void onClickUnderThousand(){
        setResult(2);
    }

    @OnClick(R.id.underfifteenhundred)
    void onClickUnderFifteenHundred(){
        setResult(3);
    }

    @OnClick(R.id.abovetwothousand)
    void onClickAboveFifteenHundered(){
        setResult(4);
    }

    @OnClick(R.id.homeFilter)
    void onClickHomeFilter(){
        setResult(0);
    }

    public CostForTwoFragment() {
        // Required empty public constructor
    }

    public static CostForTwoFragment newInstance(String param1, String param2) {
        CostForTwoFragment fragment = new CostForTwoFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {}
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_cost_for_two, container, false);
        ButterKnife.bind(this,rootView);

        return  rootView;
    }
}
