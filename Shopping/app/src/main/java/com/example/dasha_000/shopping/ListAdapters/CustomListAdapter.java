package com.example.dasha_000.shopping.ListAdapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dasha_000.shopping.ProductInfo;
import com.example.dasha_000.shopping.ProductItemInfo;
import com.example.dasha_000.shopping.R;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by dasha_000 on 01.04.2018.
 */

public class CustomListAdapter extends BaseAdapter {

    private ArrayList<ProductInfo> listData;
    private final LayoutInflater layoutInflater;
    private final Context context;

    public CustomListAdapter(Context aContext,  ArrayList<ProductInfo> listData) {
        this.context = aContext;
        this.listData = listData == null ? new ArrayList<ProductInfo>() : listData;
        layoutInflater = LayoutInflater.from(aContext);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
    }

    public void updateResults(ArrayList<ProductInfo> results) {
        listData = results;
        //Triggers the list update
        notifyDataSetChanged();
    }

    public void deleteRecord(int position){
        this.listData.remove(position);
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_item_layout, null);
            holder = new ViewHolder();
            holder.imageView = convertView.findViewById(R.id.imageView_flag);
            holder.productNameView = convertView.findViewById(R.id.textView_productName);
            holder.priceView = convertView.findViewById(R.id.textView_price);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ProductInfo product = this.listData.get(position);
        holder.productNameView.setText(product.getName());
        String priceStr = "Ціна: від " + getMinPrice(product.getProductItemInfo()) + " до " + getMaxPrice(product.getProductItemInfo());
        holder.priceView.setText(priceStr);
        try {
            URL url = new URL(product.getImage());
           // URL url = new URL("https://img3.zakaz.ua/20180322.1521729846.ad72436478c_2018-03-22_Alexey/20180322.1521729846.SNCPSG10.obj.0.1.jpg.oe.jpg.pf.jpg.350nowm.jpg.350x.jpg");
            Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            holder.imageView.setImageBitmap(bmp);
            //BitmapDrawable bd = ResourcesCompat.getResources().getDrawable(R.drawable.shoping_im);
            //holder.imageView.setImageResource(R.drawable.shoping_im);
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

    // Find Image ID corresponding to the name of the image (in the directory mipmap).
    public int getMipmapResIdByName(String resName)  {
        String pkgName = context.getPackageName();
        // Return 0 if not found.
        int resID = context.getResources().getIdentifier(resName , "mipmap", pkgName);
        Log.i("CustomListView", "Res Name: "+ resName+"==> Res ID = "+ resID);
        return resID;
    }

    private double getMaxPrice(ArrayList<ProductItemInfo> productItemInfo) {
        double max_price = 0;
        for (ProductItemInfo product_item_info : productItemInfo)
        {
            if (product_item_info.getProductPrice() > max_price)
                max_price = product_item_info.getProductPrice();
        }
        return max_price;
    }

    private double getMinPrice(ArrayList<ProductItemInfo> productItemInfo) {
        double min_price = productItemInfo.get(0).getProductPrice();
        for (ProductItemInfo product_item_info : productItemInfo)
        {
            if (product_item_info.getProductPrice() < min_price)
                min_price = product_item_info.getProductPrice();
        }
        return min_price;
    }

    static class ViewHolder {
        ImageView imageView;
        TextView productNameView;
        TextView priceView;
    }

}
