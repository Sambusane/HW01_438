package com.example.hw01_438;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    User user1;
    ArrayList<User> users;
    EditText username;
    EditText password;
    String uUsername;
    String uPassword;
    Button loginBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        users = new ArrayList<User>();
        users.add(user1=new User(1,"test1","1234"));
        users.add(user1=new User(2,"test2","4321"));
        users.add(user1=new User(3,"test3","password"));
        wireUpDisplay();
    }

    private void wireUpDisplay() {
        username = findViewById(R.id.Username_login);
        password = findViewById(R.id.Userpass_login);
        loginBtn = findViewById(R.id.login_button);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uPassword=password.getText().toString();
                uUsername=username.getText().toString();
                if (isInList()){
                    username.setError(null);
                    if (passwordMatch()){
                        password.setError(null);
                        Intent intent = LandingActivity.intentFactory(MainActivity.this);
                        intent.putExtra("USER_ID",user1.getuId());
                        Toast.makeText(MainActivity.this,"welcome userId:"+user1.getuId(),Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    }else{
                        password.setError("Incorrect Password");
                        Toast.makeText(MainActivity.this,"Wrong Password",Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this,"User not in List",Toast.LENGTH_SHORT).show();
                    username.setError("Incorrect username");
                }

            }
        });
    }

    private boolean passwordMatch() {
        if (user1.getPassword().equals(uPassword)){
            return true;
        }
        return false;

    }

    private boolean isInList() {
        for (User user : users){
            if (user.getUserName().equals(uUsername)){
                user1=new User(user.getuId(),user.getUserName(),user.getPassword());
                return true;
            }
        }
        return false;
    }
}