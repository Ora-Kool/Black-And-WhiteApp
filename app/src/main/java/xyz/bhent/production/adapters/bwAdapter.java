package xyz.bhent.production.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import xyz.bhent.production.Model.ItemModel;
import xyz.bhent.production.R;

/**
 * Created by root on 7/22/16.
 */
public class bwAdapter  extends ArrayAdapter<ItemModel>{
    private final Context context;
    private final ArrayList<ItemModel> modelArrayList;

    public bwAdapter(Context context, ArrayList<ItemModel> itemModels){
        super(context, R.layout.black_and_white_items, itemModels);
        this.context = context;
        this.modelArrayList = itemModels;
    }

    @Override
    public View getView(int position, View contentView, ViewGroup parent){
        //1. creating inflater
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //2. get rowView from inflater
        View rowView = contentView;
        if(contentView == null){
            rowView = inflater.inflate(R.layout.black_and_white_items, parent, false);

            //3. get icon, titl & counter views from the rowview
            ImageView imageView = (ImageView)rowView.findViewById(R.id.item_image);
            TextView titleView = (TextView)rowView.findViewById(R.id.item_title);
//            TextView counterView = (TextView)rowView.findViewById(R.id.item_counter);

            //4. set the text for textview
            imageView.setImageResource(modelArrayList.get(position).getIcon());
            titleView.setText(modelArrayList.get(position).getTitle());
//            counterView.setText(modelArrayList.get(position).getCounter());
        }else{


        }
        //5. return rowView
        return rowView;
    }
}
