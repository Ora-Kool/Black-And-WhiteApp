package xyz.bhent.production.Activities;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import xyz.bhent.production.Methods.Data;
import xyz.bhent.production.Model.ItemModel;
import xyz.bhent.production.R;
import xyz.bhent.production.adapters.CockTailAdapter;

import static xyz.bhent.production.Methods.Data.cocktailAlco;
import static xyz.bhent.production.Methods.Data.cocktailEteki;
import static xyz.bhent.production.Methods.Data.cocktailNonAlc;

public class AdvancedCocktail extends AppCompatActivity {
    CockTailAdapter adapter;
    ListView listView;
    private ItemModel model;
    private ArrayList<ItemModel> itemModels = new ArrayList<>();
    private Data data;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blacknwhite);
        listView = (ListView)findViewById(R.id.listView);
        data = new Data();
        bundle = getIntent().getExtras();
        setMenuData(bundle.getString("cocktails"));

        Resources resources = getResources();
        adapter = new CockTailAdapter(AdvancedCocktail.this, itemModels, resources);
        listView.setAdapter(adapter);

        overridePendingTransition(R.anim.slide_left_to_right, R.anim.slide_right_to_left);

        if(getSupportActionBar() != null){

            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayUseLogoEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(bundle.getString("cocktails"));

        }


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.advance, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                overridePendingTransition(R.anim.slide_right_to_left, android.R.anim.fade_out);
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    //depending on the choosen item from the list, this will be render acc
    public void setMenuData(String category){
        switch (category){
            case "Cocktails (avec alcool)":
                Map<String, String> cockAlc = cocktailAlco();
                for(Map.Entry<String, String> entryAl : cockAlc.entrySet()){
                    model = new ItemModel();
                    model.setTitle(entryAl.getKey().trim());
                    model.setInclusivelabel(entryAl.getValue().trim());
                    itemModels.add(model);
                }
                break;
            case "Cocktails (sans Alcool)":
                HashMap<String, String> cockNonAlc = cocktailNonAlc();
                for(Map.Entry<String, String> nonAlc : cockNonAlc.entrySet()){
                    model = new ItemModel();
                    model.setTitle(nonAlc.getKey());
                    model.setInclusivelabel(nonAlc.getValue().trim());
                    itemModels.add(model);
                }
                break;
            case "Cocktails Eteki":
                HashMap<String, String> cockEtek = cocktailEteki();
                for(Map.Entry<String, String> cockE : cockEtek.entrySet()){
                    model = new ItemModel();
                    model.setTitle(cockE.getKey().trim());
                    model.setInclusivelabel(cockE.getValue().trim());
                    itemModels.add(model);
                }
                break;
        }
    }

    public void onItemClick(int mPosition){
        ItemModel tempValues = (ItemModel) itemModels.get(mPosition);
        Bundle bundle = new Bundle();
        bundle.putString("category", tempValues.getTitle());
        Intent subcategory = new Intent(AdvancedCocktail.this, Availables.class);
        subcategory.putExtras(bundle);
        overridePendingTransition(R.anim.slide_left_to_right, android.R.anim.fade_out);
        startActivity(subcategory);
                //creating an animation

    }



}
