package xyz.bhent.production.Activities;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import static xyz.bhent.production.Methods.Data.*;
import xyz.bhent.production.Model.ItemModel;
import xyz.bhent.production.R;
import xyz.bhent.production.adapters.DrinksAdapter;

public class Drinks extends Activity {
    DrinksAdapter adapter;
    ListView listView;
    private ItemModel model;
    private ArrayList<ItemModel> itemModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blacknwhite);
        listView = (ListView)findViewById(R.id.listView);
        overridePendingTransition(R.anim.slide_left_to_right, R.anim.slide_right_to_left);
        setMenuData();

        Resources resources = getResources();
        adapter = new DrinksAdapter(Drinks.this, itemModels, resources);
        listView.setAdapter(adapter);



    }
    public void setMenuData(){
        for (int i = 0; i < Categories.length; i++){
             model = new ItemModel();
             model.setTitle(Categories[i]);
            itemModels.add(model);
        }
    }

    public void onItemClick(int mPosition){
        ItemModel tempValues = (ItemModel) itemModels.get(mPosition);
        Bundle bundle = new Bundle();

        String selectedItem = tempValues.getTitle();
        if(selectedItem.equalsIgnoreCase("Cocktails (avec alcool)") ||
                selectedItem.equalsIgnoreCase("Cocktails (sans Alcool)") ||
                selectedItem.equalsIgnoreCase("Cocktails Eteki")){
            bundle.putString("cocktails", selectedItem);
            Intent advanceIntent = new Intent(Drinks.this, AdvancedCocktail.class);
            advanceIntent.putExtras(bundle);
            overridePendingTransition(R.anim.slide_left_to_right, R.anim.slide_right_to_left);
            startActivity(advanceIntent);

        }else {
            bundle.putString("category", tempValues.getTitle());
            Intent subcategory = new Intent(Drinks.this, Availables.class);
            subcategory.putExtras(bundle);
            overridePendingTransition(R.anim.slide_left_to_right, R.anim.slide_right_to_left);
            startActivity(subcategory);
            //creating an animation
        }

    }



}
