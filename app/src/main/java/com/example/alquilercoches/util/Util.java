package com.example.alquilercoches.util;

import android.content.SharedPreferences;

//Clase que sirve para guardar el usuario y contraseña en el ActivityMain
public class Util {

    //Devuelve el email guardado
    public static String getUserMailPrefs(SharedPreferences preferences) {
        return preferences.getString("email", "");
    }

    //Devuelve la contraseña guardada
    public static String getUserClavePrefs(SharedPreferences preferences) {
        return preferences.getString("clave", "");
    }

    //Devuelve el nombre guardado
    public static String getUserNombrePrefs(SharedPreferences preferences) {
        return preferences.getString("nombre", "");
    }

    //Devuelve los apellidos guardados
    public static String getUserApellidosPrefs(SharedPreferences preferences) {
        return preferences.getString("apellidos", "");
    }

    //Devuelve el dni guardado
    public static String getUserDniPrefs(SharedPreferences preferences) {
        return preferences.getString("dni", "");
    }

    //Devuelve el rol guardado
    public static String getUserRolPrefs(SharedPreferences preferences) {
        return preferences.getString("rol", "");
    }


    //Borra los valores guardados
    public static void removeSharedPreferences(SharedPreferences preferences) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("email");
        editor.remove("clave");
        editor.remove("nombre");
        editor.remove("apellidos");
        editor.remove("dni");
        editor.remove("rol");
        editor.apply();
    }
}
