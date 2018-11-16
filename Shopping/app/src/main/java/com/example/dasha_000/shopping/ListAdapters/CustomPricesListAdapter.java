package com.example.dasha_000.shopping.ListAdapters;

import android.content.Context;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dasha_000.shopping.ProductItemInfo;
import com.example.dasha_000.shopping.R;

import java.util.ArrayList;

/**
 * Created by dasha_000 on 22.05.2018.
 */

public class CustomPricesListAdapter extends BaseAdapter {

    private final ArrayList<ProductItemInfo> listData;
    private final LayoutInflater layoutInflater;

    public CustomPricesListAdapter(Context aContext, ArrayList<ProductItemInfo> listData) {
        this.listData = listData == null ? new ArrayList<ProductItemInfo>() : listData;
        layoutInflater = LayoutInflater.from(aContext);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CustomPricesListAdapter.ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.price_list_layout, null);
            holder = new CustomPricesListAdapter.ViewHolder();
            holder.shopNameView = convertView.findViewById(R.id.txShop);
            holder.priceView = convertView.findViewById(R.id.txPrice);
            convertView.setTag(holder);
        } else {
            holder = (CustomPricesListAdapter.ViewHolder) convertView.getTag();
        }

        ProductItemInfo product = this.listData.get(position);
        holder.shopNameView.setText(product.getShopName());
        String priceStr = product.getProductPrice().toString() + " грн.";
        holder.priceView.setText(priceStr);

        return convertView;
    }


    static class ViewHolder {
        TextView shopNameView;
        TextView priceView;
    }
}
