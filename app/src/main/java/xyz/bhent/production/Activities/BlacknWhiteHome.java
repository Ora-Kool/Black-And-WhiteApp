package xyz.bhent.production.Activities;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import xyz.bhent.production.Model.ItemModel;
import xyz.bhent.production.R;
import xyz.bhent.production.adapters.HomeMenuAdapter;

public class BlacknWhiteHome extends AppCompatActivity{
    HomeMenuAdapter adapter;
    ListView listView;
    private ArrayList<ItemModel> itemModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blacknwhite);
        listView = (ListView)findViewById(R.id.listView);

        setMenuData();

        Resources resources = getResources();
        adapter = new HomeMenuAdapter(BlacknWhiteHome.this, itemModels, resources);
        listView.setAdapter(adapter);

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                Object object = listView.getItemAtPosition(position);
//                ItemModel item = (ItemModel) object;
//                TextView title = (TextView) view.findViewById(R.id.item_title);
//                title.setBackgroundColor(Color.WHITE);
//
//                Intent makereservations = new Intent(BlacknWhiteHome.this, Availables.class);
//                startActivity(makereservations);
//                //creating an animation
//                overridePendingTransition(R.anim.slide_left_to_right, android.R.anim.fade_out);
//            }
//        });

        if(getSupportActionBar() != null){

            getSupportActionBar().setDisplayUseLogoEnabled(true);
            getSupportActionBar().isHideOnContentScrollEnabled();
            getSupportActionBar().setTitle("Black & White");
        }



    }
    public void setMenuData(){
        for (int i = 0; i < 11; i++){
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
        Intent makereservations = new Intent(BlacknWhiteHome.this, Availables.class);
        startActivity(makereservations);
                //creating an animation
        overridePendingTransition(R.anim.slide_left_to_right, android.R.anim.fade_out);

    }



}
