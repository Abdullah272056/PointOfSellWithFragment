package com.example.pointofsell;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class OthersFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewP=inflater.inflate(R.layout.others_fragment,container,false);

        Bundle bundle=this.getArguments();
        String token=bundle.getString("token");
        TextView txt=viewP.findViewById(R.id.txt);
        txt.setText(token);
      //  Toast.makeText(HomePage.this, "", Toast.LENGTH_SHORT).show();



        return viewP;
    }


}
