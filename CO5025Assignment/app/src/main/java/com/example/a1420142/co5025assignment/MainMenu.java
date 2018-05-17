package com.example.a1420142.co5025assignment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainMenu extends AppCompatActivity implements View.OnClickListener {

    TextView username, currencyOutput;
    Animation horizontalSlide;
    Animation verticalSlide;
    ImageView leftVerticalLine;
    ImageView rightVerticalLine;
    ImageView topHorizontalLine;
    ImageView bottomHorizontalLine;
    Button playGame;
    Button settings;
    Button login;
    Boolean loggedIn=false;
    Button store;
    RelativeLayout mainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        mainMenu=(RelativeLayout) findViewById(R.id.activity_main_menu);
        if(accountManagement.nightMode==true){
            mainMenu.setBackgroundColor(getResources().getColor(R.color.nightMode));
        }
        else{
            mainMenu.setBackgroundColor(getResources().getColor(R.color.dayMode));
        }
        username=(TextView) findViewById(R.id.username);
        username.setText("");
        //this lets the user see if their logged in and who they're logged in as
        if (accountManagement.loggedIn==true){
            username.setText(accountManagement.username);
        }
        //this simple animation greets the user when they start the app
        horizontalSlide = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.horizontalslide);
        verticalSlide = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.verticalslide);
        leftVerticalLine = (ImageView) findViewById(R.id.leftVertical);
        rightVerticalLine = (ImageView) findViewById(R.id.rightVertical);
        topHorizontalLine = (ImageView) findViewById(R.id.topHorizontal);
        bottomHorizontalLine = (ImageView) findViewById(R.id.bottomHorizontal);
        if(accountManagement.animationPlayed==false) {
            leftVerticalLine.startAnimation(verticalSlide);
            rightVerticalLine.startAnimation(verticalSlide);
            topHorizontalLine.startAnimation(horizontalSlide);
            bottomHorizontalLine.startAnimation(horizontalSlide);
            accountManagement.animationPlayed=true;
        }
        playGame = (Button) findViewById(R.id.playGame);
        playGame.setOnClickListener(this);
        settings = (Button) findViewById(R.id.settings);
        settings.setOnClickListener(this);
        login = (Button) findViewById(R.id.Login);
        login.setOnClickListener(this);
        //this shows the amount of currency that the player has if they're logged in. I check if the username isn't empty instead of checking a boolean because during testing
        // the boolean method wasn't working properly and I knew that this method worked so I just switched to this
        currencyOutput=(TextView) findViewById(R.id.currencyOutput);
        if (accountManagement.username!=""){
            currencyOutput.setText("\uD83D\uDCB5"+accountManagement.currency);
        } else currencyOutput.setText("");
        if (accountManagement.username!=""){
            login.setText(R.string.logout);
            loggedIn=true;
        } else loggedIn=false;
        store = (Button) findViewById(R.id.store);
        store.setOnClickListener(this);
        // this plays the song that plays in all activities accept mainActivity
        int resId = R.raw.andrejetsons_stepbystep;
        musicPlayer.musicPlayer(this,resId);

    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()){
            case R.id.playGame:
                i= new Intent(this, MainActivity.class);
                startActivity(i);
                //this is the only activity that plays a different song so it's the only link where we have to stop the current music
                musicPlayer.stopMusic();
                break;
            case R.id.settings:
                i=new Intent(this, Settings.class);
                startActivity(i);
                break;
            case R.id.Login: // if the player is already logged in we want to give them the option to logout so I have this button serve double duty by making it log them out if
                // they're logged in, or link to the login page if they're logged out
                if (!loggedIn) {
                    i = new Intent(this, NoDatabaseLogin.class);
                    startActivity(i);
                    break;
                } else {
                    accountManagement.logout();
                    loggedIn=false;
                    login.setText(R.string.login);
                    username.setText("");
                    currencyOutput.setText("");
                    break;
                }
            case R.id.store:
                i=new Intent(this, Store.class);
                startActivity(i);
                break;
        }
    }
}
