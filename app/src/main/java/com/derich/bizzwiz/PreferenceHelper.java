package com.derich.bizzwiz;

public class PreferenceHelper {

    final public static String KEY_USERNAME = "Demo Name";

    public static void setUsername(String value) {
        SignUpActivity.sharedPreferences.edit().putString(KEY_USERNAME,value).commit();
    }

    public static String getUsername() {
        return SignUpActivity.sharedPreferences.getString(KEY_USERNAME, "");
    }
}
