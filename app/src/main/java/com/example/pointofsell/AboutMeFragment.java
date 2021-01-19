package com.example.pointofsell;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.pointofsell.delete_user.DeleteUserGetDataResponse;
import com.example.pointofsell.delete_user.DeleteUserSetDataResponse;
import com.example.pointofsell.owner_all_information.OwnerDataWithResponse;
import com.example.pointofsell.retrofit.ApiInterface;
import com.example.pointofsell.retrofit.RetrofitClient;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        getUserAllInfo();
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

    public void getUserAllInfo (){
        apiInterface.getUserAllInformation("Bearer "+token).enqueue(new Callback<OwnerDataWithResponse>() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onResponse(Call<OwnerDataWithResponse> call, Response<OwnerDataWithResponse> response) {

                if (response.code()==200){
                    companyNameTextView.setText(String.valueOf(response.body().getData().getCompanyName()));
                    companyEmailTextView.setText(String.valueOf(response.body().getData().getEmail()));
                    companyPhoneTextView.setText(String.valueOf(response.body().getData().getPhone()));
                    companyAddressTextView.setText(String.valueOf(response.body().getData().getAddress()));

                    SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy  hh:mm a", Locale.forLanguageTag(String.valueOf(response.body().getData().getCreatedAt())));
                    String getCreatedAt = df.format(new Date());

                    memberSinceTextView.setText(String.valueOf(getCreatedAt));
                    //  Toast.makeText(AboutMeActivity.this, "success", Toast.LENGTH_SHORT).show();
                }else if (response.code()==500){
                    Toast.makeText(getActivity(), "invalid user", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<OwnerDataWithResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "failed! try again", Toast.LENGTH_SHORT).show();

            }
        });
    }

    
}
