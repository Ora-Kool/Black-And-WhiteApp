package xyz.bhent.production.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Fade;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

import xyz.bhent.production.Connectivity.DatabaseHelper;
import xyz.bhent.production.R;

public class Selections extends AppCompatActivity {

    private static int  REQUEST_CODE = 0001;
    private  CheckBox halfCheckBox, oneCheckBox, twoCheckBox, threeCheckBox;
    private  TextView halfBottle, oneBottle, twoBottles, threeBottles;
    private  TextView halfPrice, onePrice, twoPrice, threePrice;
    private HashMap<String, String> selected = new HashMap<>();
    private boolean state;
    private String selectedDrink = "";
    private DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selections);
        Bundle bundle = getIntent().getExtras();//  getting the data passed from the previous activity
        databaseHelper = new DatabaseHelper(this);

        //UI binding to java
        ImageButton add = (ImageButton)findViewById(R.id.add_to_card);
        TextView header = (TextView)findViewById(R.id.selected_item);
        Button cancel = (Button)findViewById(R.id.cancel);

        //checkbox UI --> Java binding
        halfCheckBox = (CheckBox)findViewById(R.id.half_checked);
        oneCheckBox = (CheckBox)findViewById(R.id.one_checked);
        twoCheckBox = (CheckBox)findViewById(R.id.two_checked);
        threeCheckBox = (CheckBox)findViewById(R.id.three_checked);
        //end

        //TextView for quantity  UI ---> Java binding
        halfBottle = (TextView)findViewById(R.id.half_bottle);
        oneBottle = (TextView)findViewById(R.id.one_bottle);
        twoBottles = (TextView)findViewById(R.id.two_bottles);
        threeBottles = (TextView)findViewById(R.id.three_bottles);
        //end

        //TextView for price UI ---> Java binding
        halfPrice = (TextView)findViewById(R.id.half_price);
        onePrice = (TextView)findViewById(R.id.one_price);
        twoPrice = (TextView)findViewById(R.id.two_price);
        threePrice = (TextView)findViewById(R.id.three_price);
        //end


        header.setText(bundle.getString("selectedItem")); // setting the selected items as the header
        selectedDrink = bundle.getString("selectedItem");

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(halfCheckBox.isChecked()) {
                String quantity = halfBottle.getText().toString();
                String price = halfPrice.getText().toString();
                selected.put(quantity, price);
            }
                if(oneCheckBox.isChecked()){
                    String quantity = oneBottle.getText().toString();
                    String price = onePrice.getText().toString();
                    selected.put(quantity, price);
                }

                if(twoCheckBox.isChecked()){
                    String quantity = twoBottles.getText().toString();
                    String price = twoPrice.getText().toString();
                    selected.put(quantity, price);
                }
                if(threeCheckBox.isChecked()){
                    String quantity = threeBottles.getText().toString();
                    String price = threePrice.getText().toString();
                    selected.put(quantity, price);
                }

                //check if the list is empty
                if(selected.isEmpty()){
                    Toast.makeText(Selections.this, "Please your cart is empty", Toast.LENGTH_LONG).show();
                }else{
                    //looping via all the selected items and inserting them items cart
                    Map<String, String> map = selected;
                    for(Map.Entry<String, String> entry : map.entrySet()){
                     state = databaseHelper.insertItems(selectedDrink, entry.getKey(), entry.getValue());// database as shopping cart
                    }

                    if(state == true){
                        Toast.makeText(Selections.this, "Thanks ", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(Selections.this, "Error inserting", Toast.LENGTH_SHORT).show();
                    }
                }

                selected.clear();
                halfCheckBox.setChecked(false);
                oneCheckBox.setChecked(false);
                twoCheckBox.setChecked(false);
                threeCheckBox.setChecked(false);



//                Intent intent = new Intent();
//                intent.putExtra("From Reservation", "Checked");
//                overridePendingTransition(R.anim.slide_left_to_right, R.anim.slide_left_to_right);
//                setResult(REQUEST_CODE, intent);
//                finish();
            }
        });

        //called this action if user dont want any of the menu items
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                overridePendingTransition(R.anim.slide_left_to_right, R.anim.slide_left_to_right);
                setResult(REQUEST_CODE, intent);
                finish();
            }
        });
 if(getSupportActionBar() != null){
     getSupportActionBar().setTitle(bundle.getString("selectedItem"));
 }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id){
//            case R.id.setting:
//                break;
            case android.R.id.home:
                onBackPressed();
                overridePendingTransition(R.anim.slide_right_to_left, android.R.anim.fade_out);
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
