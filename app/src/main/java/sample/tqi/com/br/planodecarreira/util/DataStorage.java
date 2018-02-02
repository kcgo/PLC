package sample.tqi.com.br.planodecarreira.util;

import android.content.Context;
import android.content.SharedPreferences;

import sample.tqi.com.br.planodecarreira.PlanoCarreiraApplication;

public class DataStorage {

    private static final String PLC_PREFERENCE = "PLC_PREFERENCE";
    private static final String ACCESS_TOKEN = "ACCESS_TOKEN";
    private static final String TYPE_ACCESS = "TYPE_ACCESS";
    private static final String REFRESH_TOKEN = "REFRESH_TOKEN";

    private static final String USER_NAME = "USER_NAME";

    public static void setAccessToken(String token) {
        SharedPreferences sharedPreferences = PlanoCarreiraApplication.get().getSharedPreferences(PLC_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(ACCESS_TOKEN, token);
        editor.commit();
    }

    public static String getAccessToken() {
        SharedPreferences sharedPreferences = PlanoCarreiraApplication.get().getSharedPreferences(PLC_PREFERENCE, Context.MODE_PRIVATE);
        return sharedPreferences.getString(ACCESS_TOKEN, null);
    }

    public static void setSaveUser(String token) {
        SharedPreferences sharedPreferences = PlanoCarreiraApplication.get().getSharedPreferences(PLC_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_NAME, token);
        editor.commit();
    }

    public static String getUserName() {
        SharedPreferences sharedPreferences = PlanoCarreiraApplication.get().getSharedPreferences(PLC_PREFERENCE, Context.MODE_PRIVATE);
        return sharedPreferences.getString(USER_NAME, null);
    }

    public static void setTypeAccess(String token) {
        SharedPreferences sharedPreferences = PlanoCarreiraApplication.get().getSharedPreferences(PLC_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TYPE_ACCESS, token);
        editor.commit();
    }

    public static String getTypeAccess() {
        SharedPreferences sharedPreferences = PlanoCarreiraApplication.get().getSharedPreferences(PLC_PREFERENCE, Context.MODE_PRIVATE);
        return sharedPreferences.getString(TYPE_ACCESS, null);
    }
}