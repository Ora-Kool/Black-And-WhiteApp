package xyz.bhent.production.Activities;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import xyz.bhent.production.Model.ItemModel;
import xyz.bhent.production.R;
import xyz.bhent.production.adapters.DrinksAdapter;

public class Drinks extends AppCompatActivity{
    DrinksAdapter adapter;
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
        adapter = new DrinksAdapter(Drinks.this, itemModels, resources);
        listView.setAdapter(adapter);

//
        if(getSupportActionBar() != null){

            getSupportActionBar().setDisplayUseLogoEnabled(true);
            getSupportActionBar().isHideOnContentScrollEnabled();
            getSupportActionBar().setTitle("Black & White Drinks");
        }



    }
    public void setMenuData(){
        for (int i = 1; i < 4; i++){
            final ItemModel model = new ItemModel();
            model.setTitle("Volka "+i);
            model.setPrice("25000" + i);
            model.setImage("image" + i);
            model.setUrl("http://www." + i + ".com");

            itemModels.add(model);
        }
    }

    public void onItemClick(int mPosition){
        ItemModel tempValues = (ItemModel) itemModels.get(mPosition);
        Bundle bundle = new Bundle();
        bundle.putString("category", tempValues.getTitle());
        Intent subcategory = new Intent(Drinks.this, Availables.class);
        subcategory.putExtras(bundle);
        startActivity(subcategory);
                //creating an animation
        overridePendingTransition(R.anim.slide_left_to_right, android.R.anim.fade_out);

    }



}
