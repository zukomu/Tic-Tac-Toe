package com.example.a1420142.co5025assignment;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


// this was also related to logging in with a database but I don't fully understand how it works
//code taken from http://www.parallelcodes.com/connect-android-to-ms-sql-database-2/

public class loginToDatabase {
    static String ip = "194.82.34.247";
    static String classs = "SQL2016.FSE.Network";
    static String databaseName = "db_1420142_programmingassignment";
    static String databaseUsername = "user_db_1420142_programmingassignment";
    static String datbasePassword = "Snow2015";

    @SuppressLint("NewApi")
    public static Connection CONN() {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Connection conn = null;
            String ConnURL = null;
            try {

                Class.forName(classs);
                ConnURL = "jdbc:jtds:sqlserver://" + ip + ";"
                        + "databaseName=" + databaseName + ";user=" + databaseUsername + ";password="
                        + datbasePassword + ";";
                conn = DriverManager.getConnection(ConnURL);
            } catch (SQLException se) {
                Log.e("ERRO", se.getMessage());
            } catch (ClassNotFoundException e) {
                Log.e("ERRO", e.getMessage());
            } catch (Exception e) {
                Log.e("ERRO", e.getMessage());
            }
            return conn;
        }
}
