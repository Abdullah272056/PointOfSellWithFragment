package com.example.pointofsell;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SellInfoByDayFragment extends Fragment {
    String token;
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.sell_info_by_day_fragment, container, false);
        //receive token
        Bundle bundle=this.getArguments();
        token=bundle.getString("token");


        return view;
    }
}
