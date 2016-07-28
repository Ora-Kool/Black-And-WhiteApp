package xyz.bhent.production.Activities;

import android.app.TabActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

import xyz.bhent.production.R;

public class Home extends TabActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        TabHost tabHost = getTabHost();
        overridePendingTransition(R.anim.slide_left_to_right, R.anim.slide_right_to_left);

        //Tab for Drinks
        TabHost.TabSpec drinks = tabHost.newTabSpec("Drinks");

        //setting title and Icon for the tab
        drinks.setIndicator("Drinks", getResources().getDrawable(R.drawable.wine));
        Intent drinkActivity = new Intent(Home.this, Drinks.class);
        drinks.setContent(drinkActivity);

        //Tab for Food
        TabHost.TabSpec food = tabHost.newTabSpec("Food");

        //setting title and Icon for the tab
        food.setIndicator("Food", getResources().getDrawable(R.mipmap.ic_launcher));
        Intent foodActivity = new Intent(Home.this, Food.class);
        food.setContent(foodActivity);


        //Adding all tabspec to tabhost
        tabHost.addTab(drinks);
        tabHost.addTab(food);
    }
}
