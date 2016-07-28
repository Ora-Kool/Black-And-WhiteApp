package xyz.bhent.production.Activities;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import xyz.bhent.production.Model.ItemModel;
import xyz.bhent.production.R;
import xyz.bhent.production.adapters.DrinksAdapter;
import xyz.bhent.production.adapters.FoodAdapter;

public class Food extends AppCompatActivity {
    FoodAdapter adapter;
    ListView listView;
    private ItemModel model;
    private ArrayList<ItemModel> itemModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blacknwhite);
        listView = (ListView)findViewById(R.id.listView);

        overridePendingTransition(R.anim.slide_left_to_right, android.R.anim.fade_out);
        setMenuData();

        Resources resources = getResources();
        adapter = new FoodAdapter(Food.this, itemModels, resources);
        listView.setAdapter(adapter);

//
        if(getSupportActionBar() != null){

            getSupportActionBar().setDisplayUseLogoEnabled(true);
            getSupportActionBar().isHideOnContentScrollEnabled();
            getSupportActionBar().setTitle("Black & White Food");
        }



    }
    public void setMenuData(){
        for (int i = 1; i <= 9; i++){
            final ItemModel model = new ItemModel();
            model.setTitle("Food  " + i);
            model.setPrice("2500" + i);


            itemModels.add(model);
        }
    }

    public void onItemClick(int mPosition){
        Toast.makeText(Food.this, "Thanks for choosen Black & White Food", Toast.LENGTH_SHORT).show();

    }
}
