package com.example.sharedprefencessample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    private TextView tvUsername;
    private Button btnLogout;
    private String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        findViews();
        setListener();
        username=UserDataManager.getUserData(this).getUsername();
        tvUsername.setText(username);
    }
    private void findViews(){
        tvUsername=findViewById(R.id.tv_username);
        btnLogout=findViewById(R.id.btn_logout);
    }

    private void setListener(){
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserDataManager.clearUserData(HomeActivity.this);
                finish();
            }
        });
    }

}

