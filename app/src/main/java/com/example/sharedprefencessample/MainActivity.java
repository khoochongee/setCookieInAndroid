package com.example.sharedprefencessample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText edtUsername;
    private Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(checkLoginStatus()) {
            Intent intent=new Intent(this,HomeActivity.class);
            startActivity(intent);
        }else{
            findViews();
            setListeners();
        }
    }

    private void findViews(){
        edtUsername=findViewById(R.id.edt_temp_username);
        btnLogin=findViewById(R.id.btn_login);
    }

    private void setListeners(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Pass username to the next activity, then start the activity
                String username=edtUsername.getText().toString();
                if(username.isEmpty()){
                    edtUsername.setError("Username Must Not Be Empty");
                }else{
                    UserData userData=new UserData();
                    userData.setLogin(true);
                    userData.setUsername(username);

                    UserDataManager.saveUserData(MainActivity.this,userData);

                    Intent intent=new Intent(MainActivity.this,HomeActivity.class);
                    intent.putExtra("USERNAME",username);
                    startActivity(intent);
                    finish();
                }

            }
        });
    }

    private boolean checkLoginStatus(){
        UserData userData=UserDataManager.getUserData(this);
        return userData.isLogin();
    }
}
