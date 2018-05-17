package com.example.a1420142.co5025assignment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
// this is the version of the login code that appears in the final build
public class NoDatabaseLogin extends AppCompatActivity implements View.OnClickListener {

    TextView UsernameView, PasswordView, error;
    EditText EditUsername, EditPassword;
    Button LoginButton;
    Button home;
    RelativeLayout noDatabaseLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_database_login);
        noDatabaseLogin = (RelativeLayout) findViewById(R.id.activity_no_database_login);
        if(accountManagement.nightMode==true){
            noDatabaseLogin.setBackgroundColor(getResources().getColor(R.color.nightMode));
        }
        else{
            noDatabaseLogin.setBackgroundColor(getResources().getColor(R.color.dayMode));
        }
        UsernameView = (TextView) findViewById(R.id.usernameLabel);
        PasswordView = (TextView) findViewById(R.id.usernameLabel);
        error=(TextView) findViewById(R.id.error);
        EditUsername = (EditText) findViewById(R.id.editUsername);
        EditPassword = (EditText) findViewById(R.id.editPassword);
        LoginButton = (Button) findViewById(R.id.loginButton);
        LoginButton.setOnClickListener(this);
        error.setText("");

        home = (Button) findViewById(R.id.home);
        home.setOnClickListener(this);
        home.setText("\uD83C\uDFE0");
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()){
            case R.id.loginButton:
                checkPassword();
                if (accountManagement.loggedIn==true){
                    i= new Intent(this, MainMenu.class);
                    startActivity(i);
                    //this is here so that it doesn't play 2 copies of the same song over each other
                    musicPlayer.stopMusic();
                    break;
                } else break;
            case R.id.home:
                i= new Intent(this, MainMenu.class);
                startActivity(i);
                //this is here so that it doesn't play 2 copies of the same song over each other
                musicPlayer.stopMusic();
                break;
        }

    }
    public void checkPassword(){
        if (EditUsername.getText().toString().equals("test")&&(EditPassword.getText().toString().equals("1234"))){
            //this makes it so that when the player logs in it sets the loggedIn boolean to true and sets the users username.
            accountManagement.loggedIn=true;
            accountManagement.username=EditUsername.getText().toString();
        } else error.setText(R.string.loginError); //this displays an error message if the info is wrong
    }
}
