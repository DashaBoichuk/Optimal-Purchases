package com.example.dasha_000.shopping.ListAdapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.dasha_000.shopping.CartInformation.CartProductInfo;
import com.example.dasha_000.shopping.ProductInfo;
import com.example.dasha_000.shopping.R;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by dasha_000 on 04.04.2018.
 */

class CustomCartListAdapter extends BaseAdapter {

    private CartProductInfo listData;
    private final LayoutInflater layoutInflater;

    public CustomCartListAdapter(Context aContext,  CartProductInfo listData) {
        //this.listData = listData == null ? new ArrayList<CartProductInfo>() : listData;
        layoutInflater = LayoutInflater.from(aContext);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
    }

    public void updateResults(CartProductInfo results) {
        listData = results;
        //Triggers the list update
        notifyDataSetChanged();
    }


    /*@Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }*/

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.cart_list_layout, null);
            holder = new ViewHolder();
            holder.imageView = convertView.findViewById(R.id.imageView3);
            holder.productNameView = convertView.findViewById(R.id.textView6);
            holder.priceView = convertView.findViewById(R.id.textView7);
            convertView.setTag(holder);
           /* ImageButton deleteButton = convertView.findViewById(R.id.ibMap);
            deleteButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                View parentRow = (View) v.getParent();
                android.widget.ListView listView = (android.widget.ListView) parentRow.getParent();
                final int position = listView.getPositionForView(parentRow);
                listData.remove(position);
                notifyDataSetChanged();
            }
        });*/
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        holder.productNameView.setText(this.listData.getName());
        holder.priceView.setText(listData.getPrice().toString());
        try {
            URL url = new URL(listData.getImage());
            Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            holder.imageView.setImageBitmap(bmp);
        }catch(Exception e){
            URL url;
            try {
                url = new URL("http://image10.bizrate-images.com/resize?sq=60&uid=2216744464");
                Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                holder.imageView.setImageBitmap(bmp);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }


        return convertView;
    }

     static class ViewHolder {
        ImageView imageView;
        TextView productNameView;
        TextView priceView;
    }
}
