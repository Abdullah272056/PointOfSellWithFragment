package com.example.pointofsell;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pointofsell.retrofit.ApiInterface;
import com.example.pointofsell.retrofit.RetrofitClient;

public class AboutMeFragment extends Fragment {
    String token;
    ApiInterface apiInterface;
    TextView companyNameTextView,companyEmailTextView,companyPhoneTextView,
            companyAddressTextView, memberSinceTextView;
    Button uploadPictureButton,changePasswordButton,deleteAccountButton;


    //change password dialog box view
    TextView oldPasswordEditText,newPasswordEditText,confirmPasswordEditText;
    Button saveChangePasswordButton;
    ProgressBar changePasswordProgressBar;
   // ChangePasswordSetResponse changePasswordSetResponse;

    ///delete password dialog box view
    Button deleteCancelButton,deleteConfirmButton;
    EditText passwordEditText;

    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.about_me_fragment,container,false);

        //receive user token
        Bundle bundle=this.getArguments();
        token=bundle.getString("token");




        apiInterface = RetrofitClient.getRetrofit("http://mern-pos.herokuapp.com/").create(ApiInterface.class);
       // getUserAllInfo();
        // textView finding
        companyNameTextView=view.findViewById(R.id.companyNameTextViewId);
        companyEmailTextView=view.findViewById(R.id.companyEmailTextViewId);
        companyPhoneTextView=view.findViewById(R.id.companyPhoneTextViewId);
        companyAddressTextView=view.findViewById(R.id.companyAddressTextViewId);
        memberSinceTextView=view.findViewById(R.id.memberSinceTextViewId);
        // button finding
        uploadPictureButton=view.findViewById(R.id.uploadPictureButtonId);
        changePasswordButton=view.findViewById(R.id.changePasswordButtonId);
        deleteAccountButton=view.findViewById(R.id.deleteAccountButtonId);



        return view;
}
}
