package Shared;

import android.content.Context;
import android.content.SharedPreferences;

public class UserSession {
    private static final String PREF_NAME = "UserSessionPref";
    private static final String KEY_ROLE = "userRole";
    private static final String KEY_EMAIL = "userEmail";
    private static final String KEY_USER_NO = "userNo";

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private static UserSession instance;

    private UserSession(Context context) {
        pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    public static UserSession getInstance(Context context) {
        if (instance == null) {
            instance = new UserSession(context.getApplicationContext());
        }
        return instance;
    }

    // Save user session data
    public void saveUserSession(String role, String email, String userNo) {
        editor.putString(KEY_ROLE, role);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_USER_NO, userNo);
        editor.apply();
    }

    // Get user role
    public String getRole() {
        return pref.getString(KEY_ROLE, "");
    }

    // Get user email
    public String getEmail() {
        return pref.getString(KEY_EMAIL, "");
    }

    // Get user number
    public String getUserNo() {
        return pref.getString(KEY_USER_NO, "");
    }

    // Clear session (for logout)
    public void clearSession() {
        editor.clear();
        editor.apply();
    }
}