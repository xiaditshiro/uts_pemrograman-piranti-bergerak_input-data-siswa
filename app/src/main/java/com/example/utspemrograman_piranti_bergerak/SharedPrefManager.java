package com.example.utspemrograman_piranti_bergerak;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {

    private static final String KEY_USERNAME = "username";
    private static final String KEY_NAME = "name";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";

    private static SharedPrefManager instance;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private SharedPrefManager(Context context){
        sharedPreferences = context.getApplicationContext().getSharedPreferences("UTS_Prefs", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static synchronized SharedPrefManager getInstance(Context context){
        if(instance == null){
            instance = new SharedPrefManager(context);
        }
        return instance;
    }

    public void saveLoginSession(String username, String name){
        editor.putString(KEY_USERNAME, username);
        editor.putString(KEY_NAME, name);
        editor.putBoolean(KEY_IS_LOGGED_IN, true);
        editor.apply();
    }

    public String getName(){
        return sharedPreferences.getString(KEY_NAME, "User");
    }

    public String getUsername(){
        return sharedPreferences.getString(KEY_USERNAME, "");
    }

    public boolean isLoggedIn(){
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    public void logout() {
        editor.clear();
        editor.apply();
    }
}
