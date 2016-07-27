package xyz.bhent.production;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by root on 7/22/16.
 */
public class PreManager {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;

    //shared pref mode
    int PRIVATE_MODE = 0;

    //shared pref file name
    private static final String PREF_NAME = "blach-and-white";

    private static final String FIRST_TIME_LAUNCH = "isFirstTime";

    //constructor
    public PreManager(Context mContext){
        this.context = mContext;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public void setFirstLaunch(boolean state){
        editor.putBoolean(FIRST_TIME_LAUNCH, state);
        editor.commit();
    }

    public boolean isFirstLaunch(){
        return sharedPreferences.getBoolean(FIRST_TIME_LAUNCH, true);
    }
}
