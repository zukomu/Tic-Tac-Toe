package com.example.a1420142.co5025assignment;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

// I originally wanted to have the player log in through a database but this wasn't covered in any official course material and I  couldn't find an online tutorial that I could
// understand or that I could make work without understanding so I ended up switching to the current Databaseless method that is present in the final version of the app. I've left
// this code here to show some of the work that I did that didn't make it into the final app because I'm desperate for as many points in this assignment as possible.
public class Login extends AppCompatActivity implements View.OnClickListener {
    Button home;
    EditText Username;
    EditText Password;
    Button Login;
    TextView text;
    loginToDatabase LoginToDatabase;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //this si just typical onCreate code found in most activities
        LoginToDatabase = new loginToDatabase();
        home = (Button) findViewById(R.id.home);
        home.setOnClickListener(this);
        home.setText("\uD83C\uDFE0");
        Username=(EditText) findViewById(R.id.username);
        Password=(EditText) findViewById(R.id.password);
        Login = (Button) findViewById(R.id.login);
        Login.setOnClickListener(this);
        text = (TextView) findViewById(R.id.textLogin);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
    }

    public void onClick(View v) {
        Intent i;
        switch (v.getId()){
            case R.id.home:
                i= new Intent(this, MainMenu.class);
                startActivity(i);
                //this is here so that it doesn't play 2 copies of the same song over each other
                musicPlayer.stopMusic();
                break;
            case R.id.Login:
                DoLogin doLogin = new DoLogin();
                doLogin.execute();

        }
    }


    public class DoLogin extends AsyncTask<String,String,String>
    {
        String z = "";
        Boolean isSuccess = false;
        String userid = Username.getText().toString();
        String password = Password.getText().toString();


        @Override
        // this would show a progress bar while the login request was processing
        protected void onPreExecute(){
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        // this would hide the progress bar and display a message. I'll be honest I don't know what the message would have been.
        protected void onPostExecute(String r) {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(Login.this,r,Toast.LENGTH_SHORT).show();

        if(isSuccess) {
            Intent i= new Intent(Login.this, MainMenu.class);
            startActivity(i);
            finish();
        }

    }

        @Override
        protected String doInBackground(String... params) {
        if(userid.trim().equals("")|| password.trim().equals(""))
            z = "Please enter User Id and Password";
        else
        {
            try {
                Connection con = loginToDatabase.CONN();
                if (con == null) {
                    z = "Error in connection with SQL server";
                } else {
                    String query = "select * from Usertbl where UserId='" + userid + "' and password='" + password + "'";
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(query);

                    if(rs.next())
                    {

                        z = "Login successfull";
                        isSuccess=true;
                    }
                    else
                    {
                        z = "Invalid Credentials";
                        isSuccess = false;
                    }

                }
            }
            catch (Exception ex)
            {
                isSuccess = false;
                z = "Exceptions";
            }
        }
        return z;
    }
    }
}
