package xyz.bhent.production.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

import xyz.bhent.production.Activities.AdvancedCocktail;
import xyz.bhent.production.Activities.Drinks;
import xyz.bhent.production.Model.ItemModel;
import xyz.bhent.production.R;

/**
 * Created by root on 7/22/16.
 */
public class CockTailAdapter extends BaseAdapter implements View.OnClickListener{
    private final Activity context;
    private final ArrayList<ItemModel> modelArrayList;
    private static  LayoutInflater inflater = null;
    public Resources resources;
    ItemModel tempModel = null;
    int i = 0;

    public CockTailAdapter(Activity context, ArrayList<ItemModel> itemModels, Resources res){
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
            view = inflater.inflate(R.layout.advanceview, null);

            holder = new ViewHolder();
            holder.drinkName = (TextView) view.findViewById(R.id.item_title);
            holder.drinkImage = (ImageView)view.findViewById(R.id.item_image);
            holder.inclusiveItems = (TextView)view.findViewById(R.id.inclusive);
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.slide_right_to_left);
            Animation animateDrinkName = AnimationUtils.loadAnimation(context, R.anim.slide_left_to_right);
            animateDrinkName.setDuration(3000);
            animation.setDuration(3000);
            holder.inclusiveItems.setAnimation(animation);
            holder.drinkName.setAnimation(animateDrinkName);

            view.setTag(holder);
        }else
             holder = (ViewHolder)view.getTag();
        if(modelArrayList.size() <= 0){
            holder.drinkName.setText("No data");
        }else{
            tempModel = null;
            tempModel = (ItemModel)modelArrayList.get(position);

            //set model values in holder elements
            holder.drinkName.setText(tempModel.getTitle());
            holder.drinkImage.setImageResource(R.mipmap.ic_launcher);
            holder.inclusiveItems.setText(tempModel.getInclusivelabel());

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
        private ImageView drinkImage;
        private TextView inclusiveItems;

    }
    private class OnItemClickListener implements View.OnClickListener{
        private int mPosition;
        OnItemClickListener(int position){
            mPosition = position;
        }
        @Override
        public void onClick(View arg){
            AdvancedCocktail advancedCocktail = (AdvancedCocktail)context;
            advancedCocktail.onItemClick(mPosition);
        }
    }
}
