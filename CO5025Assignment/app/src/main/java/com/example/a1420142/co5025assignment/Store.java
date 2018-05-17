package com.example.a1420142.co5025assignment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
// as the player plays the game they earn currency for winning. here they can spend that currency on things to replace their game piece. in this prototype build the player is given
// 1000 units of currency to spend for debugging purposes but in the final release they wouldn't. I would also like to at some point let players exchange real money for ingame currency
// but that is a longterm goal, and a lot of planning would have to take place before I went ahead with that
public class Store extends AppCompatActivity implements View.OnClickListener {

    Button home, catDog, happySad, sunMoon, goatRooster, reset;
    TextView checkLogin, currencyOutput;
    RelativeLayout store;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        store=(RelativeLayout)findViewById(R.id.activity_store);
        if(accountManagement.nightMode==true){
            store.setBackgroundColor(getResources().getColor(R.color.nightMode));
        }
        else{
            store.setBackgroundColor(getResources().getColor(R.color.dayMode));
        }
        home = (Button) findViewById(R.id.home);
        home.setOnClickListener(this);
        home.setText("\uD83C\uDFE0");
        catDog = (Button) findViewById(R.id.catDog);
        catDog.setOnClickListener(this);
        happySad = (Button) findViewById(R.id.happySad);
        happySad.setOnClickListener(this);
        sunMoon = (Button) findViewById(R.id.sunMoon);
        sunMoon.setOnClickListener(this);
        goatRooster = (Button) findViewById(R.id.goatRooster);
        goatRooster.setOnClickListener(this);
        currencyOutput=(TextView) findViewById(R.id.currencyOutput);
        reset = (Button) findViewById(R.id.reset);
        reset.setOnClickListener(this);
        if(accountManagement.username!=""){
        } else {
        }
        checkLogin = (TextView) findViewById(R.id.checkLogin);
        if (accountManagement.username!=""){ // this code stops the player doing anything on this page if they're not logged in
            checkLogin.setText("Player Piece = "+accountManagement.playerPiece+"       AI Piece = "+accountManagement.AIPiece);
            currencyOutput.setText("\uD83D\uDCB5"+accountManagement.currency);
            if(!accountManagement.goatRooster) {
                goatRooster.setText("\uD83D\uDC10\uD83D\uDC13 - \uD83D\uDCB5300");
            } else goatRooster.setText("\uD83D\uDC10\uD83D\uDC13");
            if(!accountManagement.sunMoon) {
                sunMoon.setText("\uD83C\uDF1E\uD83C\uDF19  - \uD83D\uDCB5300");
            } else sunMoon.setText("\uD83C\uDF1E\uD83C\uDF19");
            if (!accountManagement.happySad) {
                happySad.setText("\uD83D\uDE00\uD83D\uDE1E  - \uD83D\uDCB5300");
            } else happySad.setText("\uD83D\uDE00\uD83D\uDE1E");
            if (!accountManagement.catDog) {
                catDog.setText("\uD83D\uDC36\uD83D\uDC31  - \uD83D\uDCB5300");
            } else catDog.setText("\uD83D\uDC36\uD83D\uDC31");
        } else{
            checkLogin.setText("Login to access store");
            currencyOutput.setText("");
            goatRooster.setText("");
            goatRooster.setEnabled(false);
            goatRooster.setVisibility(View.INVISIBLE);
            sunMoon.setText("");
            sunMoon.setEnabled(false);
            sunMoon.setVisibility(View.INVISIBLE);
            happySad.setText("");
            happySad.setEnabled(false);
            happySad.setVisibility(View.INVISIBLE);
            catDog.setText("");
            catDog.setEnabled(false);
            catDog.setVisibility(View.INVISIBLE);
            reset.setText("");
            reset.setEnabled(false);
            reset.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()){
            case R.id.home:
                i= new Intent(this, MainMenu.class);
                startActivity(i);
                musicPlayer.stopMusic();
                break;
            case R.id.catDog:// the following code takes currency and lets the user change their game piece. the new pieces are made using Emoji
                if (accountManagement.catDog==false&&accountManagement.currency>=300){// this code takes 300 units of currency from the player and lets them set their piece to a dog and
                    accountManagement.currency= accountManagement.currency-300;       // the AI piece to a cat.
                    accountManagement.catDog=true;
                    accountManagement.playerPiece="\uD83D\uDC36";
                    accountManagement.AIPiece="\uD83D\uDC31";
                    catDog.setText("\uD83D\uDC36\uD83D\uDC31");
                    checkLogin.setText("Player Piece = "+accountManagement.playerPiece+"       AI Piece = "+accountManagement.AIPiece);
                    currencyOutput.setText("\uD83D\uDCB5"+accountManagement.currency);
                } else { //this else statement exists so that if the player already owns this pack then they don't have to pay for it again
                    accountManagement.playerPiece="\uD83D\uDC36";
                    accountManagement.AIPiece="\uD83D\uDC31";
                    checkLogin.setText("Player Piece = "+accountManagement.playerPiece+"       AI Piece = "+accountManagement.AIPiece);
                } break;


            case R.id.happySad:
                if (accountManagement.happySad==false&&accountManagement.currency>=300){// this code make the player piece a smiling face and the AI piece a frowning face
                    accountManagement.currency= accountManagement.currency-300;
                    accountManagement.happySad=true;
                    accountManagement.playerPiece="\uD83D\uDE00";
                    accountManagement.AIPiece="\uD83D\uDE1E";
                    happySad.setText("\uD83D\uDE00\uD83D\uDE1E");
                    checkLogin.setText("Player Piece = "+accountManagement.playerPiece+"       AI Piece = "+accountManagement.AIPiece);
                    currencyOutput.setText("\uD83D\uDCB5"+accountManagement.currency);
                } else {
                    accountManagement.playerPiece="\uD83D\uDE00";
                    accountManagement.AIPiece="\uD83D\uDE1E";
                    checkLogin.setText("Player Piece = "+accountManagement.playerPiece+"       AI Piece = "+accountManagement.AIPiece);
                } break;

            case R.id.sunMoon:
                if (accountManagement.sunMoon==false&&accountManagement.currency>=300){// this code makes the player piece a sun and the AI piece a moon
                    accountManagement.currency= accountManagement.currency-300;
                    accountManagement.sunMoon=true;
                    accountManagement.playerPiece="\uD83C\uDF1E";
                    accountManagement.AIPiece="\uD83C\uDF19";
                    sunMoon.setText("\uD83C\uDF1E\uD83C\uDF19");
                    checkLogin.setText("Player Piece = "+accountManagement.playerPiece+"       AI Piece = "+accountManagement.AIPiece);
                    currencyOutput.setText("\uD83D\uDCB5"+accountManagement.currency);
                } else {
                    accountManagement.playerPiece="\uD83C\uDF1E";
                    accountManagement.AIPiece="\uD83C\uDF19";
                    checkLogin.setText("Player Piece = "+accountManagement.playerPiece+"       AI Piece = "+accountManagement.AIPiece);
                } break;

            case R.id.goatRooster:
                if (accountManagement.goatRooster==false&&accountManagement.currency>=300){ //this code makes the player piece a goat and the AI piece a rooster
                    accountManagement.currency= accountManagement.currency-300;
                    accountManagement.goatRooster=true;
                    accountManagement.playerPiece="\uD83D\uDC10";
                    accountManagement.AIPiece="\uD83D\uDC13";
                    goatRooster.setText("\uD83D\uDC10\uD83D\uDC13");
                    checkLogin.setText("Player Piece = "+accountManagement.playerPiece+"       AI Piece = "+accountManagement.AIPiece);
                    currencyOutput.setText("\uD83D\uDCB5"+accountManagement.currency);
                } else {
                    accountManagement.playerPiece="\uD83D\uDC10";
                    accountManagement.AIPiece="\uD83D\uDC13";
                    checkLogin.setText("Player Piece = "+accountManagement.playerPiece+"       AI Piece = "+accountManagement.AIPiece);
                } break;
            case R.id.reset:
                accountManagement.playerPiece="X";
                accountManagement.AIPiece="0";
                checkLogin.setText("Player Piece = "+accountManagement.playerPiece+"       AI Piece = "+accountManagement.AIPiece);
        }
    }
}
