package com.example.pointofsell;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomePage extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        bottomNavigationView=findViewById(R.id.bottomBarId);
        if (savedInstanceState==null){
            getSupportFragmentManager().beginTransaction().replace(R.id.frameViewId,new OthersFragment()).commit();

        }
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment=null;

                if (item.getItemId()==R.id.homeItemId){
                    fragment=new OthersFragment();
                }
                if (item.getItemId()==R.id.customerItemId){
                    fragment=new OthersFragment();
                }
                if (item.getItemId()==R.id.productItemId){
                    fragment=new OthersFragment();
                } if (item.getItemId()==R.id.invoiceItemId){
                    fragment=new OthersFragment();
//                    Toast.makeText(MainActivity.this, "clicked", Toast.LENGTH_SHORT).show();
                }
                if (item.getItemId()==R.id.othersItemId){
                    fragment=new OthersFragment();
//                    Toast.makeText(MainActivity.this, "clicked", Toast.LENGTH_SHORT).show();
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.frameViewId,fragment).commit();

                return true;
            }
        });
    }
}