package xyz.bhent.production.Activities;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;

import xyz.bhent.production.Connectivity.DatabaseHelper;
import xyz.bhent.production.Connectivity.InfosRetrieving;
import xyz.bhent.production.Model.ItemModel;
import xyz.bhent.production.R;
import xyz.bhent.production.adapters.AvailableAdapter;

public class Availables extends AppCompatActivity {
    private ListView availsListView;
    private AvailableAdapter availableAdapter;
    private ArrayList<ItemModel> itemModels;
    private boolean state;
    private ItemModel tempValues;
    private DatabaseHelper databaseHelper;
    private InfosRetrieving retrieving;
    private static int  REQUEST_CODE = 0001;
    private ItemModel model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.availables);
        availsListView = (ListView)findViewById(R.id.available);
        databaseHelper = new DatabaseHelper(this); //getting an instant of the database helper
        itemModels = new ArrayList<>();
        model = new ItemModel();

        retrieving = new InfosRetrieving(this); //instant of where we post, retrieve information
        // using volley library

        model.setTitle("MALTA");
        model.setPrice("2500");
        itemModels.add(model);


        setListData();


        Resources resources = getResources();

        availableAdapter = new AvailableAdapter(Availables.this, itemModels, resources);

        availsListView.setAdapter(availableAdapter);


        if(getSupportActionBar() != null){

                getSupportActionBar().setDisplayShowHomeEnabled(true);
                getSupportActionBar().setDisplayUseLogoEnabled(true);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setTitle("Availables");

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
            case android.R.id.home:
                onBackPressed();
                overridePendingTransition(R.anim.slide_right_to_left, android.R.anim.fade_out);
                finish();
                break;
            case R.id.active:
               Map<String, String> map = databaseHelper.getAllItems();
                //check if there is any item from the database
                if(map.isEmpty()){
                    Toast.makeText(Availables.this, "Oops! Cart Is Empty", Toast.LENGTH_LONG).show();
                }else {
                    for (Map.Entry<String, String> entry : map.entrySet()) {
                        Toast.makeText(Availables.this, "The Items is " + entry.getKey() + " / the price is " + entry.getValue(), Toast.LENGTH_SHORT).show();
                    }
                }

        }

        return super.onOptionsItemSelected(item);
    }

    //method that populate the list adapter
    public void setListData(){



        for (int i = 1; i <= 4; i++){
            model = new ItemModel();
            model.setTitle("Volka " + i);
            model.setPrice("25000" + i);
            model.setImage("image" + i);
            model.setUrl("http://www." + i + ".com");
            itemModels.add(model);
        }
    }

    //method call upon clicking a specific item on the list
    public void onItemClick(int mPosition){
        tempValues = itemModels.get(mPosition);//get the reference of the selected item on the list

        Intent intent = new Intent(Availables.this, Selections.class);
        Bundle bundle = new Bundle();
        bundle.putString("selectedItem", tempValues.getTitle());
        intent.putExtras(bundle);
        startActivityForResult(intent, REQUEST_CODE);
        overridePendingTransition(R.anim.activity_open, R.anim.slide_left_to_right);

   }

    //getting the return result
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            //first checking if the request code  is same as what was sent
            if (requestCode == REQUEST_CODE) {
                String confirmation = data.getStringExtra("From Reservation");
                if(confirmation.isEmpty()){

                }else{
                Toast.makeText(Availables.this, confirmation, Toast.LENGTH_LONG).show();
                }
            } else {

            }
        }catch (Exception e){}
    }
}
