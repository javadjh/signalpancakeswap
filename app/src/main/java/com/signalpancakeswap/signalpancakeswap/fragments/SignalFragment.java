package com.signalpancakeswap.signalpancakeswap.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.signalpancakeswap.signalpancakeswap.R;
import com.signalpancakeswap.signalpancakeswap.WebService.APIClient;
import com.signalpancakeswap.signalpancakeswap.WebService.APIInterface;
import com.signalpancakeswap.signalpancakeswap.adapters.CustomAdapterSignal;
import com.signalpancakeswap.signalpancakeswap.models.signal.getSignalModel;


import java.util.ArrayList;
import java.util.List;

import info.hoang8f.android.segmented.SegmentedGroup;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignalFragment extends Fragment {
    RecyclerView rectSignal;
    String date = "today";
    SegmentedGroup switchDate;
    RadioButton lastMonth,lastWeek,today;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signal, container, false);
        findViewPage(view);
        return view;


    }

    private void findViewPage(View view) {
        rectSignal = view.findViewById(R.id.rectSignal);
        switchDate = view.findViewById(R.id.switchDate);
        lastMonth = view.findViewById(R.id.lastMonth);
        lastWeek = view.findViewById(R.id.lastWeek);
        today = view.findViewById(R.id.today);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        filter();
        setData();
    }

    private void filter() {
        switchDate.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(today.isChecked())
                    date="today";
                if(lastMonth.isChecked())
                    date = "lastMonth";
                if(lastWeek.isChecked())
                    date = "lastWeek";
                setData();
            }
        });
    }

    private void setData() {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<List<getSignalModel>> call = apiInterface.getSignals(date);
        call.enqueue(new Callback<List<getSignalModel>>() {
            @Override
            public void onResponse(Call<List<getSignalModel>> call, Response<List<getSignalModel>> response) {
                if(response.code()==200){
                    rectSignal.setAdapter(new CustomAdapterSignal(getContext(),response.body()));
                }
            }

            @Override
            public void onFailure(Call<List<getSignalModel>> call, Throwable t) {

            }
        });
    }

}