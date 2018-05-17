package com.example.a1420142.co5025assignment;

    // this file handles all of the information regarding the player's account. most of this page is never called unless username!="" to insure that the players stats don't change unless
    // they're logged in. the exception to this is the difficulty settings that can be changed while not logged in
public class accountManagement {
    static Boolean loggedIn=false, catDog=false,sunMoon=false,happySad=false,goatRooster=false;
    static double gamesPlayed;
    static double gamesWon;
    static double winStreak;
    static double highestWinStreak;
    static double losses;
    static double draws;
    static int currency = 1000;
    static String username = "";
    static String difficulty = "Easy";
    static String playerPiece = "X";
    static String AIPiece = "0";
    static double winPercent;
    static boolean nightMode=false;
    static Boolean animationPlayed=false;

    //this increases the number of games played whenever the gameboard is cleared
    public static void newGame(){
        gamesPlayed++;
    }

    public static void winGame(){
        gamesWon++;
        winStreak++;
        if (winStreak>highestWinStreak){
            highestWinStreak=winStreak;
        }
    }

    public static String winPercentString(){
        String percentage=Double.toString(winPercent);
        return percentage;
    }

    public static void loseGame(){
        winStreak=0;
        losses++;
    }

    public static void drawGame(){
        winStreak=0;
        draws++;
    }
    // if the player clears the game board but then closes mainActivity before playing the game we don't want to increase gamesPlayed so we reduce the value of gamesPlayed to make up
    // for the increase that happens when the board is reset
    public static void changeMind(){
        gamesPlayed--;
    }
    // this is the logout method called from mainMenu.
    public static void logout(){
        loggedIn=false;
        username="";
        playerPiece="X";
        AIPiece="0";
    }
    public static Double winPercentage(){ // this is the equation used to find the win percentage
        winPercent=(gamesWon/gamesPlayed)*100;
        return winPercent;
    }
}
