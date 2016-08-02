package xyz.bhent.production.Activities;

import android.app.ActionBar;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;

import java.util.Map;

import xyz.bhent.production.Connectivity.DatabaseHelper;
import xyz.bhent.production.R;

public class Home extends TabActivity {
    private DatabaseHelper databaseHelper;
    private int count = 0;
    private Button checkoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        checkoutButton = (Button)findViewById(R.id.checkout);
        databaseHelper = new DatabaseHelper(Home.this);
        

        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = databaseHelper.itemsCount();
                if(count == 0){
                    Toast.makeText(Home.this, "No Item in Cart!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Home.this, "Found something!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        final TabHost tabHost = getTabHost();
        overridePendingTransition(R.anim.slide_left_to_right, android.R.anim.fade_out);

        //Tab for Drinks
        TabHost.TabSpec drinks = tabHost.newTabSpec("Drinks").setIndicator("Drinks", getResources().getDrawable(R.drawable.drinksselector));

        //setting title and Icon for the tab
        //drinks.setIndicator("Drinks", getResources().getDrawable(R.drawable.drinksselector));
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
