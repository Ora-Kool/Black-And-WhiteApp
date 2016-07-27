package xyz.bhent.production.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.load.engine.Resource;

import org.w3c.dom.Text;

import java.util.ArrayList;

import xyz.bhent.production.Activities.Availables;
import xyz.bhent.production.Model.ItemModel;
import xyz.bhent.production.R;

/**
 * Created by root on 7/22/16.
 */
public class AvailableAdapter extends BaseAdapter implements View.OnClickListener{
    private final Activity context;
    private final ArrayList<ItemModel> modelArrayList;
    private static  LayoutInflater inflater = null;
    public Resources resources;
    ItemModel tempModel = null;
    boolean[] itemChecked;

    public AvailableAdapter(Activity context, ArrayList<ItemModel> itemModels, Resources res){
        this.context = context;
        this.modelArrayList = itemModels;
        this.resources = res;

        this.itemChecked = new boolean[itemModels.size()];

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
    public View getView(final int position, View contentView, ViewGroup parent){
       View view = contentView;
        final ViewHolder holder;
        if(contentView == null){
            view = inflater.inflate(R.layout.availablecustomizes, null);

            holder = new ViewHolder();
            holder.drinkName = (TextView) view.findViewById(R.id.item_title);
            holder.drinkPrice = (TextView)view.findViewById(R.id.price_lable);
//            holder.selectedItem = (CheckBox)view.findViewById(R.id.order);
            holder.drinkImage = (ImageView)view.findViewById(R.id.item_image);
//            holder.state = (TextView)view.findViewById(R.id.statechecked);


            view.setTag(holder);
        }else
             holder = (ViewHolder)view.getTag();
        if(modelArrayList.size() <= 0){
            holder.drinkName.setText("No data");
        }else{
            tempModel = null;
            tempModel = (ItemModel)modelArrayList.get(position);

            //set model values in holder elements
            holder.drinkName.setText(modelArrayList.get(position).getTitle());
            holder.drinkPrice.setText(modelArrayList.get(position).getPrice());
            holder.drinkImage.setImageResource(R.drawable.wine);

//            if(modelArrayList.get(position).isChecked()){
//                holder.selectedItem.setChecked(true);
//            }else {
//                holder.selectedItem.setChecked(false);
//            }

//            holder.selectedItem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                    Toast.makeText(context, "checked "+isChecked, Toast.LENGTH_SHORT).show();
//                    if(isChecked){
//                        modelArrayList.get(position).setIsChecked(true);
//                    }else {
//                        modelArrayList.get(position).setIsChecked(false);
//                    }
//
//                }
//            });

            view.setOnClickListener(new OnItemClickListener(position));

        }


        return view;
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(context, "---Row clicked====", Toast.LENGTH_SHORT).show();
    }

    public static class ViewHolder{
        public TextView drinkName;
        public TextView drinkPrice;
//        public TextView state;
//        public CheckBox selectedItem;
        private ImageView drinkImage;

    }
    private class OnItemClickListener implements View.OnClickListener{
        private int mPosition;
        OnItemClickListener(int position){
            mPosition = position;

        }
        @Override
        public void onClick(View arg){
            Availables availables = (Availables)context;
            availables.onItemClick(mPosition);
        }
    }
}
