package com.example.a1420142.co5025assignment;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //controls whether a square is available
    Boolean[] TakenSquares = new Boolean[9];
    //controls whether a square was taken by the player
    Boolean PlayerSquares[] = new Boolean[9];
    //controls whether a square was taken by the AI
    Boolean[] AISquares = new Boolean[9];
    //a series of booleans to check when and how the game ends
    Boolean playerHasWon = false;
    Boolean AIHasWon = false;
    Boolean itIsADraw = false;
    //checks whether it's the first turn of the game to control some exceptions when it comes to blocking
    Boolean firstTurn = true;
    //similar purpose to first turn
    Boolean secondTurn=false;
    //controls whether it's the AI's turn yet
    Boolean AITurnTaken = false;
    //displays results
    TextView results;
    //resets the game
    Button reset;
    //lets the AI go first
    Button changeFirstPlayer;
    //takes the player to the main menu
    Button home;
    //plays the "Bwap" when you make a move.
    MediaPlayer bwap;
    //I didn't like that it was possible to "solve" hard mode by just repeating the same sequence of moves every game so I made it slightly more challenging by varying the AI's strategy
    //I did this by generating a random number and changing the algorithm based on what the random number was
    int hardRandomiser;
    //this Button array is my main gameboard. by having them in an array it's easier to automate things
    Button[] buttons = new Button[9];
    //displays how much money the player has if they're logged in
    TextView currencyOutput;
    //lets us change the background colour
    RelativeLayout mainActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivity = (RelativeLayout) findViewById(R.id.activity_main);
        if(accountManagement.nightMode==true){
            mainActivity.setBackgroundColor(getResources().getColor(R.color.nightMode));
        }
        else{
            mainActivity.setBackgroundColor(getResources().getColor(R.color.dayMode));
        }
        buttons[0] = (Button) findViewById(R.id.NW);
        buttons[1] = (Button) findViewById(R.id.N);
        buttons[2] = (Button) findViewById(R.id.NE);
        buttons[3] = (Button) findViewById(R.id.W);
        buttons[4] = (Button) findViewById(R.id.C);
        buttons[5] = (Button) findViewById(R.id.E);
        buttons[6] = (Button) findViewById(R.id.SW);
        buttons[7] = (Button) findViewById(R.id.S);
        buttons[8] = (Button) findViewById(R.id.SE);
        int i;
        for (i = 0; i < 9; i++) {
            buttons[i].setOnClickListener(this);
            AISquares[i]=false;
        }
        results = (TextView) findViewById(R.id.Results);
        reset = (Button) findViewById(R.id.Reset);
        reset.setOnClickListener(this);
        changeFirstPlayer = (Button) findViewById(R.id.changeFirstPlayer);
        changeFirstPlayer.setOnClickListener(this);
        home = (Button) findViewById(R.id.home);
        home.setOnClickListener(this);
        home.setText("\uD83C\uDFE0");
        //plays the music. the song is Caprisqui by Andre Jetson, obtained license free from http://freemusicarchive.org/music/Andre_Jetson/VIVALDI/caprisqui
        int resId = R.raw.andrejetsons_caprisqui;
        musicPlayer.musicPlayer(this, resId);
        currencyOutput=(TextView) findViewById(R.id.currencyOutput);







        //this blank space is here to remind me to never put anything before clearGrid because that has been the cause of at least 2 crashes
        clearGrid();
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()){
            //the compass directions represent squares on the game board. below is a representation of what each button des
            /*
            case R.id.NW:
                makeMove(0); - this code takes the user's input and runs all of the code neccesary to take their turn
                AITurnTaken=false; - this allows the AI to make it's move
                if (accountManagement.difficulty =="Easy"){ - this figures out how the AI will take it's turn
                    easyResponse(); if it's on easy it will basically just choose a random square unless it can win
                }else if (accountManagement.difficulty =="Medium"||accountManagement.difficulty=="Hard"){
                    mediumHardResponse(); Medium and Hard both start their turn in the same way so they don't get split up until later
                }
                break;*/

            case R.id.NW:
                makeMove(0);
                AITurnTaken=false;
                if (accountManagement.difficulty =="Easy"){
                    easyResponse();
                }else if (accountManagement.difficulty =="Medium"||accountManagement.difficulty=="Hard"||accountManagement.difficulty=="Perfect"){
                    mediumHardResponse();
                }
                break;
            case R.id.N:
                makeMove(1);
                AITurnTaken=false;
                if (accountManagement.difficulty =="Easy"){
                    easyResponse();
                }else if (accountManagement.difficulty =="Medium"||accountManagement.difficulty=="Hard"||accountManagement.difficulty=="Perfect"){
                    mediumHardResponse();
                }
                break;
            case R.id.NE:
                makeMove(2);
                AITurnTaken=false;
                if (accountManagement.difficulty =="Easy"){
                    easyResponse();
                }else if (accountManagement.difficulty =="Medium"||accountManagement.difficulty=="Hard"||accountManagement.difficulty=="Perfect"){
                    mediumHardResponse();
                }
                break;
            case R.id.W:
                makeMove(3);
                AITurnTaken=false;
                if (accountManagement.difficulty =="Easy"){
                    easyResponse();
                }else if (accountManagement.difficulty =="Medium"||accountManagement.difficulty=="Hard"||accountManagement.difficulty=="Perfect"){
                    mediumHardResponse();
                }
                break;
            case R.id.C:
                makeMove(4);
                AITurnTaken=false;
                if (accountManagement.difficulty =="Easy"){
                    easyResponse();
                }else if (accountManagement.difficulty =="Medium"||accountManagement.difficulty=="Hard"||accountManagement.difficulty=="Perfect"){
                    mediumHardResponse();
                }
                break;
            case R.id.E:
                makeMove(5);
                AITurnTaken=false;
                if (accountManagement.difficulty =="Easy"){
                    easyResponse();
                }else if (accountManagement.difficulty =="Medium"||accountManagement.difficulty=="Hard"||accountManagement.difficulty=="Perfect"){
                    mediumHardResponse();
                }
                break;
            case R.id.SW:
                makeMove(6);
                AITurnTaken=false;
                if (accountManagement.difficulty =="Easy"){
                    easyResponse();
                }else if (accountManagement.difficulty =="Medium"||accountManagement.difficulty=="Hard"||accountManagement.difficulty=="Perfect"){
                    mediumHardResponse();
                }
                break;
            case R.id.S:
                makeMove(7);
                AITurnTaken=false;
                if (accountManagement.difficulty =="Easy"){
                    easyResponse();
                }else if (accountManagement.difficulty =="Medium"||accountManagement.difficulty=="Hard"||accountManagement.difficulty=="Perfect"){
                    mediumHardResponse();
                }
                break;
            case R.id.SE:
                makeMove(8);
                AITurnTaken=false;
                if (accountManagement.difficulty =="Easy"){
                    easyResponse();
                }else if (accountManagement.difficulty =="Medium"||accountManagement.difficulty=="Hard"||accountManagement.difficulty=="Perfect"){
                    mediumHardResponse();
                }
                break;
            case R.id.Reset:
                if(firstTurn&&accountManagement.username!="") {
                    accountManagement.changeMind();//this lets the player quit without adding to gamesPlayed without actually playing a game
                }
                if (!playerHasWon && !AIHasWon && accountManagement.username != ""&&!firstTurn) {
                    accountManagement.drawGame(); //this means that if the player is trying to reset the game to avoid that game as
                    // going down as a draw or loss it will still show up as a draw on their statistics
                }
                clearGrid();//this prepares the board for the next game
                break;
            case R.id.changeFirstPlayer: //this lets the player go second, it does this by basically skipping the players turn
                AITurnTaken=false;
                if (accountManagement.difficulty =="Easy"){
                    easyResponse();
                }else if (accountManagement.difficulty =="Medium"||accountManagement.difficulty=="Hard"||accountManagement.difficulty=="Perfect"){
                    mediumHardResponse();
                }
                changeFirstPlayer.setEnabled(false);
                changeFirstPlayer.setVisibility(View.INVISIBLE); //this stops the player from pressing it twice
                break;
            case R.id.home://this returns to the main menu
                if(firstTurn&&accountManagement.username!="") accountManagement.changeMind(); //this lets the player quit without adding to gamesPlayed without actually playing a game
                if (!playerHasWon&&!AIHasWon&&!firstTurn){
                    accountManagement.drawGame(); //similar to the code in reset this stops the player from quitting to avoid a loss
                }
                i= new Intent(this, MainMenu.class);
                startActivity(i);
                musicPlayer.stopMusic();// different music plays in the main menu so we need to stop this song so that it doesn't play over the other music
                break;
        }
    }

    public void easyResponse(){ //easy response will check if it can win, if it can it'll win if it can't it will take a random square
        if (!playerHasWon&&!AIHasWon && !itIsADraw){ //this stops the AI moving if the game is over

            Random randomNumber = new Random();
            int randomInt = randomNumber.nextInt(8); //this generates a random number and then tries to move there, if that square is taken AIMove will take the next available square
            switch (randomInt) {
                case 0:
                    AIMove(0);
                    hasAIWon();// after it makes it's move it checks to see if the game is over so that it knows when to end the game
                    isItADraw();
                    break;
                case 1:
                    AIMove(1);
                    hasAIWon();
                    isItADraw();
                    break;
                case 2:
                    AIMove(2);
                    hasAIWon();
                    isItADraw();
                    break;
                case 3:
                    AIMove(3);
                    hasAIWon();
                    isItADraw();
                    break;
                case 4:
                    AIMove(4);
                    hasAIWon();
                    isItADraw();
                    break;
                case 5:
                    AIMove(5);
                    hasAIWon();
                    isItADraw();
                    break;
                case 6:
                    AIMove(6);
                    hasAIWon();
                    isItADraw();
                    break;
                case 7:
                    AIMove(7);
                    hasAIWon();
                    isItADraw();
                    break;
                case 8:
                    AIMove(8);
                    hasAIWon();
                    isItADraw();
                    break;
            }
        }
    }

    public void mediumHardResponse(){ //medium response works by trying to take the center square, then a corner square then a side square, it also will try to win and try to block if possible
        canAIBlock();// the split between medium response and hard response happens in canAIBlock so while all of this code will run either way if accountManagement.difficulty=="hard"
        //AITurnTaken, a Boolean checked in AIMove, will already be true so the AI will never move twice in one turn.
        if (!playerHasWon&&!AIHasWon && !itIsADraw){
            if (!TakenSquares[4]){
                AIMove(4);
                hasAIWon();
                isItADraw();
            } else if(!TakenSquares[0]||!TakenSquares[2]||!TakenSquares[6]||!TakenSquares[8]){
                if(!TakenSquares[8]){
                    AIMove(8);
                    hasAIWon();
                    isItADraw();
                } else if(!TakenSquares[6]){
                    AIMove(6);
                    hasAIWon();
                    isItADraw();
                } else if(!TakenSquares[2]){
                    AIMove(2);
                    hasAIWon();
                    isItADraw();
                } else if(!TakenSquares[0]){
                    AIMove(0);
                    hasAIWon();
                    isItADraw();
                }
            } else{
                if (!TakenSquares[7]){
                    AIMove(7);
                    hasAIWon();
                    isItADraw();
                } else if (!TakenSquares[5]){
                    AIMove(7);
                    hasAIWon();
                    isItADraw();
                } else if (!TakenSquares[3]){
                    AIMove(3);
                    hasAIWon();
                    isItADraw();
                } else {
                    AIMove(1);
                    hasAIWon();
                    isItADraw();
                }
            }
        }
    }



    public void AIMove(int i){

        firstTurn = false; // certain parts of the code only work if it's the first turn, because we know this will happen after user input regardless of whether it's making a move
        // or telling the AI to go first this is where we stop treating the game as though it's the first turn
        canAIWin(); //unlike canAIBlock, each difficulty checks if it can win so we're ok putting this here
        if (!playerHasWon && !itIsADraw&&!AITurnTaken){
            if (buttons[i].isEnabled()) {
                buttons[i].setText(accountManagement.AIPiece);
                buttons[i].setEnabled(false);
                AISquares[i]=true;
                TakenSquares[i]=true;
                AITurnTaken = true;
                if(secondTurn)secondTurn=false;
                if(firstTurn)secondTurn=true;
                firstTurn=false;
            } else {
                for (int f = 0; f < 9; f++) { //this code runs if the desired square is already taken, this should only happen in easy mode
                    if (buttons[f].isEnabled()&& !AITurnTaken) {
                        buttons[f].setText(accountManagement.AIPiece);
                        buttons[f].setEnabled(false);
                        AISquares[f]=true;
                        TakenSquares[f]=true;
                        AITurnTaken = true;
                        firstTurn=false;
                    }
                }
            }
        }
    }



    public void canAIWin(){// this code checks to see if there are any examples of 2 squares that the AI has taken in a row and then tries to move into the third square.
        if (AISquares[0]&&AISquares[1]||AISquares[6]&&AISquares[4]||AISquares[8]&&AISquares[5]){
            if (!playerHasWon && !itIsADraw&&!AITurnTaken){ // this stops it from moving after the game is over
                if (buttons[2].isEnabled()) {// this stops it from taking the square if it has already been taken
                    buttons[2].setText(accountManagement.AIPiece);// because canAIWin is called in AIMove we can't call AIMove here or it will cause an infinite loop, as such I just copied
                    // the movement code over from AIMove
                    buttons[2].setEnabled(false);
                    AISquares[2]=true;
                    TakenSquares[2]=true;
                    AITurnTaken = true;
                    firstTurn=false; //in hindsight I realise that in theory this should never be relavent but I like having it here as a precaution, better to be safe than sorry
                }
            }
            hasAIWon(); // this is placed outside of the block of code that actually makes the move just incase the AI somehow got here while the game was over but didn't realise it
            // again, probably not relavent but it's nice to have a precaution
            isItADraw();
        } else if(AISquares[4]&&AISquares[7]||AISquares[0]&&AISquares[2]){
            if (!playerHasWon && !itIsADraw&&!AITurnTaken){
                if (buttons[1].isEnabled()) {
                    buttons[1].setText(accountManagement.AIPiece);
                    buttons[1].setEnabled(false);
                    AISquares[1]=true;
                    TakenSquares[1]=true;
                    AITurnTaken = true;
                    firstTurn=false;
                }
            }
            hasAIWon();
            isItADraw();
        }else if(AISquares[2]&&AISquares[1]||AISquares[8]&&AISquares[4]||AISquares[3]&&AISquares[6]){
            if (!playerHasWon && !itIsADraw&&!AITurnTaken){
                if (buttons[0].isEnabled()) {
                    buttons[0].setText(accountManagement.AIPiece);
                    buttons[0].setEnabled(false);
                    AISquares[0]=true;
                    TakenSquares[0]=true;
                    AITurnTaken = true;
                    firstTurn=false;
                }
            }
            hasAIWon();
            isItADraw();
        }else if(AISquares[4]&&AISquares[5]||AISquares[0]&&AISquares[6]){
            if (!playerHasWon && !itIsADraw&&!AITurnTaken){
                if (buttons[3].isEnabled()) {
                    buttons[3].setText(accountManagement.AIPiece);
                    buttons[3].setEnabled(false);
                    AISquares[3]=true;
                    TakenSquares[3]=true;
                    AITurnTaken = true;
                    firstTurn=false;
                }
            }
            hasAIWon();
            isItADraw();
        }else if(AISquares[0]&&AISquares[8]||AISquares[1]&&AISquares[7]||AISquares[2]&&AISquares[6]||AISquares[3]&&AISquares[5]){
            if (!playerHasWon && !itIsADraw&&!AITurnTaken){
                if (buttons[4].isEnabled()) {
                    buttons[4].setText(accountManagement.AIPiece);
                    buttons[4].setEnabled(false);
                    AISquares[4]=true;
                    TakenSquares[4]=true;
                    AITurnTaken = true;
                    firstTurn=false;
                }
            }
            hasAIWon();
            isItADraw();
        } else if(AISquares[3]&&AISquares[4]||AISquares[2]&&AISquares[8]){
            if (!playerHasWon && !itIsADraw&&!AITurnTaken){
                if (buttons[5].isEnabled()) {
                    buttons[5].setText(accountManagement.AIPiece);
                    buttons[5].setEnabled(false);
                    AISquares[5]=true;
                    TakenSquares[5]=true;
                    AITurnTaken = true;
                    firstTurn=false;
                }
            }
            hasAIWon();
            isItADraw();
        }else if(AISquares[0]&&AISquares[3]||AISquares[2]&&AISquares[4]||AISquares[7]&&AISquares[8]){
            if (!playerHasWon && !itIsADraw&&!AITurnTaken){
                if (buttons[6].isEnabled()) {
                    buttons[6].setText(accountManagement.AIPiece);
                    buttons[6].setEnabled(false);
                    AISquares[6]=true;
                    TakenSquares[6]=true;
                    AITurnTaken = true;
                    firstTurn=false;
                }
            }
            hasAIWon();
            isItADraw();
        }else if(AISquares[1]&&AISquares[4]||AISquares[6]&&AISquares[8]){
            if (!playerHasWon && !itIsADraw&&!AITurnTaken){
                if (buttons[7].isEnabled()) {
                    buttons[7].setText(accountManagement.AIPiece);
                    buttons[7].setEnabled(false);
                    AISquares[7]=true;
                    TakenSquares[7]=true;
                    AITurnTaken = true;
                    firstTurn=false;
                }
            }
            hasAIWon();
            isItADraw();
        }else if (AISquares[6]&&AISquares[7]||AISquares[0]&&AISquares[4]||AISquares[2]&&AISquares[5]) {
            if (!playerHasWon && !itIsADraw&&!AITurnTaken){
                if (buttons[8].isEnabled()) {
                    buttons[8].setText(accountManagement.AIPiece);
                    buttons[8].setEnabled(false);
                    AISquares[8]=true;
                    TakenSquares[8]=true;
                    AITurnTaken = true;
                    firstTurn=false;
                }
            }
            hasAIWon();
            isItADraw();
        }

    }
    public void canAIBlock(){
        if (accountManagement.difficulty=="Hard"){
            hardBlock();
        }else if(accountManagement.difficulty=="Perfect"){
            perfectBlock();
        }else { //this code will only run if accountManagement.difficulty=="Medium"
            // this started out as a bug, I wanted it to run each but because of the way the conditionals are worded and because it's setup as an else if chain it would run into situations where
            // there was 2 AISquares in and 1 playerSquare in a row and not check any later blocking oppurtunities. this was annoying at first but after I thought about it I liked the idea
            // of the medium AI getting sloppier as the game went on similar to how human players might miss things on a fuller board
            if (PlayerSquares[0]&&PlayerSquares[1]||PlayerSquares[6]&&PlayerSquares[4]||PlayerSquares[8]&&PlayerSquares[5]
                    ||PlayerSquares[0]&&PlayerSquares[8]&&!PlayerSquares[1]&&!PlayerSquares[2]&&!PlayerSquares[5]||PlayerSquares[6]&&firstTurn){
                if (!playerHasWon && !itIsADraw &&!AITurnTaken){
                    if (buttons[2].isEnabled()) {
                        AIMove(2);
                    }
                }
                hasAIWon();
                isItADraw();
            } else if(PlayerSquares[4]&&PlayerSquares[7]||PlayerSquares[0]&&PlayerSquares[2]){
                if (!playerHasWon && !itIsADraw&&!AITurnTaken){
                    if (buttons[1].isEnabled()) {
                        AIMove(1);
                    }
                }
                hasAIWon();
                isItADraw();
            }else if(PlayerSquares[2]&&PlayerSquares[1]||PlayerSquares[8]&&PlayerSquares[4]||PlayerSquares[3]&&PlayerSquares[6]
                    ||PlayerSquares[2]&&PlayerSquares[6]&&!PlayerSquares[0]&&!PlayerSquares[1]&&!PlayerSquares[3]||PlayerSquares[8]&&firstTurn){
                if (!playerHasWon && !itIsADraw&&!AITurnTaken){
                    if (buttons[0].isEnabled()) {
                        AIMove(0);
                    }
                }
                hasAIWon();
                isItADraw();
            }else if(PlayerSquares[4]&&PlayerSquares[5]||PlayerSquares[0]&&PlayerSquares[6]){
                if (!playerHasWon && !itIsADraw&&!AITurnTaken){
                    if (buttons[3].isEnabled()) {
                        AIMove(3);
                    }
                }
                hasAIWon();
                isItADraw();
            }else if(PlayerSquares[0]&&PlayerSquares[8]||PlayerSquares[1]&&PlayerSquares[7]||PlayerSquares[2]&&PlayerSquares[6]||PlayerSquares[3]&&PlayerSquares[5]){
                if (!playerHasWon && !itIsADraw&&!AITurnTaken){
                    if (buttons[4].isEnabled()) {
                        AIMove(4);
                    }
                }
                hasAIWon();
                isItADraw();
            } else if(PlayerSquares[3] && PlayerSquares[4] || PlayerSquares[2] && PlayerSquares[8]){
                if (!playerHasWon && !itIsADraw&&!AITurnTaken){
                    if (buttons[5].isEnabled()) {
                        AIMove(5);
                    }
                }
                hasAIWon();
                isItADraw();
            }else if(PlayerSquares[0]&&PlayerSquares[3]||PlayerSquares[2]&&PlayerSquares[4]||PlayerSquares[7]&&PlayerSquares[8]
                    ||PlayerSquares[0]&&PlayerSquares[8]&&!PlayerSquares[3]&&!PlayerSquares[6]&&!PlayerSquares[7]||PlayerSquares[2]&&firstTurn){
                if (!playerHasWon && !itIsADraw&&!AITurnTaken){
                    if (buttons[6].isEnabled()) {
                        AIMove(6);
                    }
                }
                hasAIWon();
                isItADraw();
            }else if(PlayerSquares[1]&&PlayerSquares[4]||PlayerSquares[6]&&PlayerSquares[8]){
                if (!playerHasWon && !itIsADraw&&!AITurnTaken){
                    if (buttons[7].isEnabled()) {
                        AIMove(7);
                    }
                }
                hasAIWon();
                isItADraw();
            }else if (PlayerSquares[6]&&PlayerSquares[7]||PlayerSquares[0]&&PlayerSquares[4]||PlayerSquares[2]&&PlayerSquares[5]
                    ||PlayerSquares[2]&&PlayerSquares[6]&&!PlayerSquares[5]&&!PlayerSquares[8]&&!PlayerSquares[7]||PlayerSquares[0]&&firstTurn) {
                if (!playerHasWon && !itIsADraw && !AITurnTaken) {
                    if (buttons[8].isEnabled()) {
                        AIMove(8);
                    }
                }
                hasAIWon();
                isItADraw();
            }
        }
    }

    public void hardBlock() { // there are two parts to this code, a near perfect blocking algorithm which runs for most of the game and a series of hardcoded responses setup to answer specific
        // strategies. all of the answers to my algorithm were based on the player taking a corner square first so the code changes if it's the first turn and the player has taken a corner square
        //this response also changes at random based on a random number between 0 and 5, on a 0 it will take the next corner going clockwise, on a 1 it will take the opposite square and on
        // a 2 it will take the next corner going counter clockwise. on a 3, 4 or 5 it will take the center square, I wanted that to be more frequent than the others as I think it's a stronger strategy
        if (PlayerSquares[0] && PlayerSquares[1] || PlayerSquares[6] && PlayerSquares[4] || PlayerSquares[8] && PlayerSquares[5]
                || PlayerSquares[0] && firstTurn&&hardRandomiser==0 || PlayerSquares[6] && firstTurn&&hardRandomiser==1|| PlayerSquares[8]&&firstTurn&&hardRandomiser==2) {
            if (!playerHasWon && !itIsADraw && !AITurnTaken) {
                if (buttons[2].isEnabled()) {
                    AIMove(2);
                }
            }
            hasAIWon();
            isItADraw();
        }
        if (PlayerSquares[4] && PlayerSquares[7] || PlayerSquares[0] && PlayerSquares[2]) {
            if (!playerHasWon && !itIsADraw && !AITurnTaken) {
                if (buttons[1].isEnabled()) {
                    AIMove(1);
                }
            }
            hasAIWon();
            isItADraw();
        }
        if (PlayerSquares[2] && PlayerSquares[1] || PlayerSquares[8] && PlayerSquares[4] || PlayerSquares[3] && PlayerSquares[6]
               || PlayerSquares[6] && firstTurn&&hardRandomiser==0||PlayerSquares[8] && firstTurn&&hardRandomiser==1|| PlayerSquares[2]&&firstTurn&&hardRandomiser==2) {
            if (!playerHasWon && !itIsADraw && !AITurnTaken) {
                if (buttons[0].isEnabled()) {
                    AIMove(0);
                }
            }
            hasAIWon();
            isItADraw();
        }
        if (PlayerSquares[4] && PlayerSquares[5] || PlayerSquares[0] && PlayerSquares[6]) {
            if (!playerHasWon && !itIsADraw && !AITurnTaken) {
                if (buttons[3].isEnabled()) {
                    AIMove(3);
                }
            }
            hasAIWon();
            isItADraw();
        }
        if (PlayerSquares[0] && PlayerSquares[8] || PlayerSquares[1] && PlayerSquares[7] || PlayerSquares[2] && PlayerSquares[6] || PlayerSquares[3] && PlayerSquares[5]) {
            if (!playerHasWon && !itIsADraw && !AITurnTaken) {
                if (buttons[4].isEnabled()) {
                    AIMove(4);
                }
            }
            hasAIWon();
            isItADraw();
        }
        if (PlayerSquares[3] && PlayerSquares[4] || PlayerSquares[2] && PlayerSquares[8]) {
            if (!playerHasWon && !itIsADraw && !AITurnTaken) {
                if (buttons[5].isEnabled()) {
                    AIMove(5);
                }
            }
            hasAIWon();
            isItADraw();
        }
        if (PlayerSquares[0] && PlayerSquares[3] || PlayerSquares[2] && PlayerSquares[4] || PlayerSquares[7] && PlayerSquares[8]
                || PlayerSquares[8] && firstTurn&&hardRandomiser==0|| PlayerSquares[2] && firstTurn&&hardRandomiser==1|| PlayerSquares[0]&&firstTurn&&hardRandomiser==2) {
            if (!playerHasWon && !itIsADraw && !AITurnTaken) {
                if (buttons[6].isEnabled()) {
                    AIMove(6);
                }
            }
            hasAIWon();
            isItADraw();
        }
        if (PlayerSquares[1] && PlayerSquares[4] || PlayerSquares[6] && PlayerSquares[8]) {
            if (!playerHasWon && !itIsADraw && !AITurnTaken) {
                if (buttons[7].isEnabled()) {
                    AIMove(7);
                }
            }
            hasAIWon();
            isItADraw();
        }
        if (PlayerSquares[6] && PlayerSquares[7] || PlayerSquares[0] && PlayerSquares[4] || PlayerSquares[2] && PlayerSquares[5]
                || PlayerSquares[2] && firstTurn&&hardRandomiser==0|| PlayerSquares[0] && firstTurn&&hardRandomiser==1|| PlayerSquares[6]&&firstTurn&&hardRandomiser==2) {
            if (!playerHasWon && !itIsADraw && !AITurnTaken) {
                if (buttons[8].isEnabled()) {
                    AIMove(8);
                }
            }
            hasAIWon();
            isItADraw();
        }
    }

    public void perfectBlock() { //I was just about to finish working on the app, annoyed that I hadn't found the perfect algorithm when it occurred to me to Google
        // noughts and crosses to see if I could find one anywhere. it turns out Google have made their own game of Noughts and Crosses that shows up when you google
        // Noughts and Crosses and it has a perfect algorithm. they don't have the code publicly available so all of the code below is original but the strategy
        // was designed by Google
        if (PlayerSquares[1] && PlayerSquares[4] || PlayerSquares[6] && PlayerSquares[8]||
                PlayerSquares[0]&&PlayerSquares[8]&&!TakenSquares[1]&&!TakenSquares[2]&&!TakenSquares[3]&&!TakenSquares[5]&&!TakenSquares[6]&&!TakenSquares[7]||
                PlayerSquares[2]&&PlayerSquares[6]&&!TakenSquares[1]&&!TakenSquares[0]&&!TakenSquares[3]&&!TakenSquares[5]&&!TakenSquares[8]&&!TakenSquares[7]) {
            if (!playerHasWon && !itIsADraw && !AITurnTaken) {
                if (buttons[7].isEnabled()) {
                    AIMove(7);
                }
            }
            hasAIWon();
            isItADraw();
        }
           if (PlayerSquares[0] && PlayerSquares[1] || PlayerSquares[6] && PlayerSquares[4] || PlayerSquares[8] && PlayerSquares[5]) {
            if (!playerHasWon && !itIsADraw && !AITurnTaken) {
                if (buttons[2].isEnabled()) {
                    AIMove(2);
                }
            }
            hasAIWon();
            isItADraw();
        }
        if (PlayerSquares[4] && PlayerSquares[7] || PlayerSquares[0] && PlayerSquares[2]) {
            if (!playerHasWon && !itIsADraw && !AITurnTaken) {
                if (buttons[1].isEnabled()) {
                    AIMove(1);
                }
            }
            hasAIWon();
            isItADraw();
        }
        if (PlayerSquares[2] && PlayerSquares[1] || PlayerSquares[8] && PlayerSquares[4] || PlayerSquares[3] && PlayerSquares[6]) {
            if (!playerHasWon && !itIsADraw && !AITurnTaken) {
                if (buttons[0].isEnabled()) {
                    AIMove(0);
                }
            }
            hasAIWon();
            isItADraw();
        }
        if (PlayerSquares[4] && PlayerSquares[5] || PlayerSquares[0] && PlayerSquares[6]) {
            if (!playerHasWon && !itIsADraw && !AITurnTaken) {
                if (buttons[3].isEnabled()) {
                    AIMove(3);
                }
            }
            hasAIWon();
            isItADraw();
        }
        if (PlayerSquares[0]&&PlayerSquares[8]&&secondTurn ||PlayerSquares[1] && PlayerSquares[7] || PlayerSquares[2] && PlayerSquares[6] || PlayerSquares[3] && PlayerSquares[5]
                ||PlayerSquares[0]&&firstTurn||PlayerSquares[2]&&firstTurn||PlayerSquares[6]&&firstTurn||PlayerSquares[8]&&firstTurn) {
            if (!playerHasWon && !itIsADraw && !AITurnTaken) {
                if (buttons[4].isEnabled()) {
                    AIMove(4);
                }
            }
            hasAIWon();
            isItADraw();
        }
        if (PlayerSquares[3] && PlayerSquares[4] || PlayerSquares[2] && PlayerSquares[8]) {
            if (!playerHasWon && !itIsADraw && !AITurnTaken) {
                if (buttons[5].isEnabled()) {
                    AIMove(5);
                }
            }
            hasAIWon();
            isItADraw();
        }
        if (PlayerSquares[0] && PlayerSquares[3] || PlayerSquares[2] && PlayerSquares[4] || PlayerSquares[7] && PlayerSquares[8]) {
            if (!playerHasWon && !itIsADraw && !AITurnTaken) {
                if (buttons[6].isEnabled()) {
                    AIMove(6);
                }
            }
            hasAIWon();
            isItADraw();
        }

        if (PlayerSquares[6] && PlayerSquares[7] || PlayerSquares[0] && PlayerSquares[4] || PlayerSquares[2] && PlayerSquares[5]) {
            if (!playerHasWon && !itIsADraw && !AITurnTaken) {
                if (buttons[8].isEnabled()) {
                    AIMove(8);
                }
            }
            hasAIWon();
            isItADraw();
        }
    }

    public void makeMove(int i){ //this is the code that proccesses the player's move
        if (!playerHasWon&&!AIHasWon && !itIsADraw) {// this makes sure that if the game is over the player can't move, this should never come up as the buttons are disabled after the game
            // finishes but I am a big fan of probably needless precautions
            int resId = R.raw.bwap; // this code plays a bwap sound whenever the player takes a square. the sound was obtained license free from
            // https://www.freesound.org/people/willy_ineedthatapp_com/sounds/167338/
            if (bwap != null){
                bwap.release();
            }
            if (resId!=0){
                bwap = MediaPlayer.create(this,resId);
                if (bwap.isPlaying()== false){
                    bwap.start();
                }
            }
            buttons[i].setText(accountManagement.playerPiece); //this sets the squares text to whatever the player chooses as their piece
            buttons[i].setEnabled(false);
            PlayerSquares[i]=true;
            TakenSquares[i]=true;
            AITurnTaken = false;
            hasPlayerWon();
            isItADraw();
            changeFirstPlayer.setEnabled(false);
            changeFirstPlayer.setVisibility(View.INVISIBLE); // this means the player can't press the changeFirstPlayer button after they've already moved
        }
    }

    public void hasPlayerWon(){
        if (PlayerSquares[0]&&PlayerSquares[1]&&PlayerSquares[2]||PlayerSquares[3]&&PlayerSquares[4]&&PlayerSquares[5]||PlayerSquares[6]&&PlayerSquares[7]&&PlayerSquares[8]||
                PlayerSquares[0]&&PlayerSquares[3]&&PlayerSquares[6]||PlayerSquares[1]&&PlayerSquares[4]&&PlayerSquares[7]||PlayerSquares[2]&&PlayerSquares[5]&&PlayerSquares[8]||
                PlayerSquares[0]&&PlayerSquares[4]&&PlayerSquares[8]||PlayerSquares[2]&&PlayerSquares[4]&&PlayerSquares[6]){ //this is super clunky but it checks every possible row of three
            //squares to see if the player has won
            playerHasWon = true;

            results.setText(R.string.youWin);
            if(accountManagement.username!=""){ accountManagement.winGame();
                if (accountManagement.difficulty=="Easy"){ //this awards the player an amount of ingame currency based on what difficulty level they were playing at
                    accountManagement.currency=accountManagement.currency+30;
                } else if(accountManagement.difficulty=="Medium"){
                    accountManagement.currency=accountManagement.currency+50;
                } else if (accountManagement.difficulty=="Hard"){
                    accountManagement.currency=accountManagement.currency+100;
                } else if (accountManagement.difficulty=="Perfect"){
                    accountManagement.currency=accountManagement.currency+300;
                }
            }
            if (PlayerSquares[0]&&PlayerSquares[1]&&PlayerSquares[2]){
                highlight(0,1,2);
            } else if(PlayerSquares[3]&&PlayerSquares[4]&&PlayerSquares[5]){
                highlight(3,4,5);
            }else if(PlayerSquares[6]&&PlayerSquares[7]&&PlayerSquares[8]){
                highlight(6,7,8);
            }else if(PlayerSquares[0]&&PlayerSquares[3]&&PlayerSquares[6]){
                highlight(0,3,6);
            }else if(PlayerSquares[1]&&PlayerSquares[4]&&PlayerSquares[7]){
                highlight(1,4,7);
            }else if(PlayerSquares[2]&&PlayerSquares[8]&&PlayerSquares[5]){
                highlight(2,8,5);
            }else if(PlayerSquares[0]&&PlayerSquares[4]&&PlayerSquares[8]){
                highlight(0,4,8);
            }else if(PlayerSquares[2]&&PlayerSquares[4]&&PlayerSquares[6]){
                highlight(2,4,6);
            }
            if (hardRandomiser==0) { //this code plays a sound when you win. because games of naughts and crosses go very quickly I thought that having the same sound play everytime
                //you win would get really annoying so I made it play one of 5 victory noises instead of the same one every time. I used the int hardRandomiser because I figured seeing
                // as I was already generating a random number with about the right range I might as well just reuse it instead of generating a new one
                // all sounds are open source
                int resId = R.raw.victory1;// victory 1 source: https://www.freesound.org/people/Tuudurt/sounds/258142/
                if (bwap != null) {
                    bwap.release();
                }
                if (resId != 0) {
                    bwap = MediaPlayer.create(this, resId);
                    if (bwap.isPlaying() == false) {
                        bwap.start();
                    }
                }
            } else if (hardRandomiser==1) {
                int resId = R.raw.victory2;// victory 2 source: https://www.freesound.org/people/mickleness/sounds/269198/
                if (bwap != null) {
                    bwap.release();
                }
                if (resId != 0) {
                    bwap = MediaPlayer.create(this, resId);
                    if (bwap.isPlaying() == false) {
                        bwap.start();
                    }
                }
            } else if (hardRandomiser==2) {
                int resId = R.raw.victory3;// victory 3 source: https://www.freesound.org/people/xtrgamr/sounds/277441/
                if (bwap != null) {
                    bwap.release();
                }
                if (resId != 0) {
                    bwap = MediaPlayer.create(this, resId);
                    if (bwap.isPlaying() == false) {
                        bwap.start();
                    }
                }
            } else if (hardRandomiser==3) {
                int resId = R.raw.victory4;//victory 4 source: https://www.freesound.org/people/unadamlar/sounds/341985/
                if (bwap != null) {
                    bwap.release();
                }
                if (resId != 0) {
                    bwap = MediaPlayer.create(this, resId);
                    if (bwap.isPlaying() == false) {
                        bwap.start();
                    }
                }
            } else {
                int resId = R.raw.victory5; //victory 5 source: https://www.freesound.org/people/Scrampunk/sounds/344696/
                if (bwap != null) {
                    bwap.release();
                }
                if (resId != 0) {
                    bwap = MediaPlayer.create(this, resId);
                    if (bwap.isPlaying() == false) {
                        bwap.start();
                    }
                }
            }
            for (int i = 0; i<9; i++){
                buttons[i].setEnabled(false);//this stops the player moving after they've won
            }
            if(accountManagement.username!=""){
                currencyOutput.setText("\uD83D\uDCB5"+accountManagement.currency); // this updates how much money the player has so that if they're saving up for something they know when they
                //can stop
            } else currencyOutput.setText("");
        }
    }

    public void hasAIWon(){
        if (AISquares[0]&&AISquares[1]&&AISquares[2]||AISquares[3]&&AISquares[4]&&AISquares[5]||AISquares[6]&&AISquares[7]&&AISquares[8]||
                AISquares[0]&&AISquares[3]&&AISquares[6]||AISquares[1]&&AISquares[4]&&AISquares[7]||AISquares[2]&&AISquares[5]&&AISquares[8]||
                AISquares[0]&&AISquares[4]&&AISquares[8]||AISquares[2]&&AISquares[4]&&AISquares[6]){ // this is just the counterpart to the hasPlayerWon code
            results.setText(R.string.youLose);
            AIHasWon = true;
            if (AISquares[0]&&AISquares[1]&&AISquares[2]){
                highlight(0,1,2);
            } else if(AISquares[3]&&AISquares[4]&&AISquares[5]){
                highlight(3,4,5);
            }else if(AISquares[6]&&AISquares[7]&&AISquares[8]){
                highlight(6,7,8);
            }else if(AISquares[0]&&AISquares[3]&&AISquares[6]){
                highlight(0,3,6);
            }else if(AISquares[1]&&AISquares[4]&&AISquares[7]){
                highlight(1,4,7);
            }else if(AISquares[2]&&AISquares[8]&&AISquares[5]){
                highlight(2,8,5);
            }else if(AISquares[0]&&AISquares[4]&&AISquares[8]){
                highlight(0,4,8);
            }else if(AISquares[2]&&AISquares[4]&&AISquares[6]){
                highlight(2,4,6);
            }
            if(accountManagement.username!="") accountManagement.loseGame(); //this resets the players win streak and adds a loss to their statistics
            if (hardRandomiser==0) { //this code plays a sound when you lose. because games of naughts and crosses go very quickly I thought that having the same sound play everytime
                //you lose would get really annoying so I made it play one of 5 loss noises instead of the same one every time. I used the int hardRandomiser because I figured seeing
                // as I was already generating a random number with about the right range I might as well just reuse it instead of generating a new one
                // all sounds are open source
                int resId = R.raw.loss1;// loss 1 source: https://www.freesound.org/people/RandomationPictures/sounds/138490/
                if (bwap != null) {
                    bwap.release();
                }
                if (resId != 0) {
                    bwap = MediaPlayer.create(this, resId);
                    if (bwap.isPlaying() == false) {
                        bwap.start();
                    }
                }
            } else if (hardRandomiser==1) {
                int resId = R.raw.loss2;// loss 2 source: https://www.freesound.org/people/cabled_mess/sounds/371451/
                if (bwap != null) {
                    bwap.release();
                }
                if (resId != 0) {
                    bwap = MediaPlayer.create(this, resId);
                    if (bwap.isPlaying() == false) {
                        bwap.start();
                    }
                }
            } else if (hardRandomiser==2) {
                int resId = R.raw.loss3;// victory 3 source: https://www.freesound.org/people/TaranP/sounds/362204/
                if (bwap != null) {
                    bwap.release();
                }
                if (resId != 0) {
                    bwap = MediaPlayer.create(this, resId);
                    if (bwap.isPlaying() == false) {
                        bwap.start();
                    }
                }
            } else if (hardRandomiser==3) {
                int resId = R.raw.loss4;//loss 4 source: https://www.freesound.org/people/LittleRobotSoundFactory/sounds/270467/
                if (bwap != null) {
                    bwap.release();
                }
                if (resId != 0) {
                    bwap = MediaPlayer.create(this, resId);
                    if (bwap.isPlaying() == false) {
                        bwap.start();
                    }
                }
            } else {
                int resId = R.raw.loss5; //loss 5 source: https://www.freesound.org/people/LittleRobotSoundFactory/sounds/270329/
                if (bwap != null) {
                    bwap.release();
                }
                if (resId != 0) {
                    bwap = MediaPlayer.create(this, resId);
                    if (bwap.isPlaying() == false) {
                        bwap.start();
                    }
                }
            }
            for (int i = 0; i<9; i++){
                buttons[i].setEnabled(false);
            }
        }
    }
    public void highlight(int a, int b, int c){
        if(playerHasWon){
            buttons[a].setBackgroundColor(getResources().getColor(R.color.winner));
            buttons[b].setBackgroundColor(getResources().getColor(R.color.winner));
            buttons[c].setBackgroundColor(getResources().getColor(R.color.winner));
        }else if(AIHasWon){
            buttons[a].setBackgroundColor(getResources().getColor(R.color.loser));
            buttons[b].setBackgroundColor(getResources().getColor(R.color.loser));
            buttons[c].setBackgroundColor(getResources().getColor(R.color.loser));
        }
    }
    public void isItADraw(){
        if (TakenSquares[0]&&TakenSquares[1]&&TakenSquares[2]&&TakenSquares[3]&&TakenSquares[4]&&
                TakenSquares[5]&&TakenSquares[6]&&TakenSquares[7]&&TakenSquares[8]&&!playerHasWon&&!AIHasWon){ //this checks if all of the squares are taken and that nobody has won
            itIsADraw = true;
            results.setText(R.string.draw);
            if(accountManagement.username!="") accountManagement.drawGame();// this resets the win streak and adds a draw to their statistics
        }
    }

    public void clearGrid() { //this resets the board ready for another game, it also runs when the activity is loaded
        int i;
        for (i=0; i<9; i++) { //this for loop resets all of the buttons and all of the facts about the buttons
            buttons[i].setText("");
            buttons[i].setEnabled(true);
            buttons[i].setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            AISquares[i]=false;
            TakenSquares[i]=false;
            PlayerSquares[i]=false;
        }
        playerHasWon=false;
        AIHasWon=false;
        itIsADraw=false;
        reset.setEnabled(true);
        changeFirstPlayer.setEnabled(true);
        changeFirstPlayer.setVisibility(View.VISIBLE);
        results.setText("");
        firstTurn=true;
        if(accountManagement.username!="") {
            accountManagement.newGame();
        }
        Random randomNumber = new Random();
        hardRandomiser = randomNumber.nextInt(6); //this makes sure the game uses a random hard strategy for each game
        if(accountManagement.username!=""){ //this tracks the player's money
            currencyOutput.setText("\uD83D\uDCB5"+accountManagement.currency);
        } else currencyOutput.setText("");
    }
}