package xyz.bhent.production.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import xyz.bhent.production.Activities.Drinks;
import xyz.bhent.production.Activities.Food;
import xyz.bhent.production.Model.ItemModel;
import xyz.bhent.production.R;

/**
 * Created by root on 7/22/16.
 */
public class FoodAdapter extends BaseAdapter implements View.OnClickListener{
    private final Activity context;
    private final ArrayList<ItemModel> modelArrayList;
    private static  LayoutInflater inflater = null;
    public Resources resources;
    ItemModel tempModel = null;
    int i = 0;

    public FoodAdapter(Activity context, ArrayList<ItemModel> itemModels, Resources res){
        this.context = context;
        this.modelArrayList = itemModels;
        this.resources = res;

        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    //the size of the passed array
    @Override
    public int getCount() {
        if(modelArrayList.size() <= 0)
            return 1;
        return modelArrayList.size();
    }


    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View contentView, ViewGroup parent){
       View view = contentView;
        ViewHolder holder;
        if(contentView == null){
            view = inflater.inflate(R.layout.black_and_white_items, null);

            holder = new ViewHolder();
            holder.foodName = (TextView) view.findViewById(R.id.item_title);
            holder.foodImage = (ImageView)view.findViewById(R.id.item_image);

            view.setTag(holder);
        }else
             holder = (ViewHolder)view.getTag();
        if(modelArrayList.size() <= 0){
            holder.foodName.setText("No data");
        }else{
            tempModel = null;
            tempModel = (ItemModel)modelArrayList.get(position);

            //set model values in holder elements
            holder.foodName.setText(tempModel.getTitle());
            holder.foodImage.setImageResource(R.mipmap.ic_launcher);

            view.setOnClickListener(new OnItemClickListener(position));

        }


        return view;
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(context, "---Row clicked====", Toast.LENGTH_SHORT).show();
    }

    public static class ViewHolder{
        public TextView foodName;
        private ImageView foodImage;

    }
    private class OnItemClickListener implements View.OnClickListener{
        private int mPosition;
        OnItemClickListener(int position){
            mPosition = position;
        }
        @Override
        public void onClick(View arg){
            Food food = (Food)context;
            food.onItemClick(mPosition);
        }
    }
}
