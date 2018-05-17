package com.example.a1420142.co5025assignment;

import android.content.Intent;
import android.icu.text.DecimalFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Settings extends AppCompatActivity implements View.OnClickListener {
    Button home, difficultyEasy, difficultyMedium, difficultyHard,difficultyPerfect, colourScheme;
    TextView gamesPlayed, gamesWon, winStreak, highestWinStreak, userInfo, difficultyLabel, draws, losses, winPercentage;
    RelativeLayout settings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        home = (Button) findViewById(R.id.home);
        home.setOnClickListener(this);
        home.setText("\uD83C\uDFE0");
        settings=(RelativeLayout) findViewById(R.id.activity_settings);
        difficultyEasy=(Button) findViewById(R.id.difficultyEasy);
        difficultyEasy.setOnClickListener(this);
        difficultyMedium=(Button) findViewById(R.id.difficultyMedium);
        difficultyMedium.setOnClickListener(this);
        difficultyHard=(Button) findViewById(R.id.difficultyHard);
        difficultyHard.setOnClickListener(this);
        difficultyPerfect=(Button)findViewById(R.id.difficultyPerfect);
        difficultyPerfect.setOnClickListener(this);
        difficultyLabel=(TextView) findViewById(R.id.difficultyLabel);
        userInfo = (TextView) findViewById(R.id.userInfo);
        gamesPlayed = (TextView) findViewById(R.id.gamesPlayed);
        gamesWon = (TextView) findViewById(R.id.gamesWon);
        winStreak = (TextView) findViewById(R.id.winStreak);
        highestWinStreak = (TextView) findViewById(R.id.highestWinStreak);
        draws=(TextView) findViewById(R.id.draws);
        losses=(TextView)findViewById(R.id.losses);
        winPercentage=(TextView)findViewById(R.id.winPercentage);
        colourScheme=(Button) findViewById(R.id.colourScheme);
        colourScheme.setOnClickListener(this);
        if(accountManagement.difficulty=="Easy"){
            difficultyLabel.setText("Difficulty - Easy");
        } else if(accountManagement.difficulty=="Medium"){
            difficultyLabel.setText("Difficulty - Medium");
        } else if (accountManagement.difficulty=="Hard"){
            difficultyLabel.setText("Difficulty - Hard");
        } else if (accountManagement.difficulty=="Perfect"){
            difficultyLabel.setText("Difficulty - Perfect");
        }
        if(accountManagement.nightMode==true){
            settings.setBackgroundColor(getResources().getColor(R.color.nightMode));
            colourScheme.setText(R.string.daymode);
        }
        else{
            colourScheme.setText(R.string.nightmode);
            settings.setBackgroundColor(getResources().getColor(R.color.dayMode));
        }
        if (accountManagement.username==""){ //this prevents user information showing up if they're not logged in
            userInfo.setText(R.string.notLoggedIn);
            gamesPlayed.setText("");
            gamesWon.setText("");
            winStreak.setText("");
            highestWinStreak.setText("");
            draws.setText("");
            losses.setText("");
            winPercentage.setText("");
        } else { // this shows the user's statistics if they're logged in
            int played= (int)accountManagement.gamesPlayed,won=(int)accountManagement.gamesWon,streak=(int)accountManagement.winStreak,
                    highestStreak=(int)accountManagement.highestWinStreak,draw=(int)accountManagement.draws,loss=(int)accountManagement.losses;
            userInfo.setText("player info - "+accountManagement.username);
            gamesPlayed.setText("games played: "+played);
            gamesWon.setText("games won: " +won);
            winStreak.setText("current win streak: "+ streak);
            highestWinStreak.setText("highest win streak: "+highestStreak);
            draws.setText("games drawn: "+draw);
            losses.setText("games lost: " +loss);
            winPercentage.setText("win percentage: "+ accountManagement.winPercentage());
        }
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()){
            case R.id.home:
                i= new Intent(this, MainMenu.class);
                startActivity(i);
                //this is here so that it doesn't play 2 copies of the same song over each other
                musicPlayer.stopMusic();
                break;
            case R.id.difficultyEasy: // these buttons control the player's difficulty level
                accountManagement.difficulty="Easy";
                difficultyLabel.setText("Difficulty - Easy");
                break;
            case R.id.difficultyMedium:
                accountManagement.difficulty="Medium";
                difficultyLabel.setText("Difficulty - Medium");
                break;
            case R.id.difficultyHard:
                accountManagement.difficulty="Hard";
                difficultyLabel.setText("Difficulty - Hard");
                break;
            case R.id.difficultyPerfect: //perfect is an unbeatable algorithm. originally it was called perfect because unbeatable wouldn't fit without changing up a
                // bunch of stuff in activity_settings.xml and I didn't want to do that however in the end I did have to as the display wasn't working properly
                // but at that point I already had everything working with it called perfect so I just stuck with it. also I think calling it perfect sounds cooler.
                accountManagement.difficulty="Perfect";
                difficultyLabel.setText("Difficulty - Perfect");
                break;
            case R.id.colourScheme:
                if(accountManagement.nightMode==false) {
                    settings.setBackgroundColor(getResources().getColor(R.color.nightMode));
                    accountManagement.nightMode = true;
                    colourScheme.setText("daymode");
                } else{
                    accountManagement.nightMode=false;
                    settings.setBackgroundColor(getResources().getColor(R.color.dayMode));
                    colourScheme.setText("nightmode");
                }
        }
    }
}
