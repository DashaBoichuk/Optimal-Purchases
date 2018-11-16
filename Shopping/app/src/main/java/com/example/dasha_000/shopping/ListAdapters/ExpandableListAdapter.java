package com.example.dasha_000.shopping.ListAdapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dasha_000.shopping.CartInformation.CartProductInfo;
import com.example.dasha_000.shopping.CartInformation.CartShopInfo;
import com.example.dasha_000.shopping.R;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * Created by reale on 21/11/2016.
 */

public class ExpandableListAdapter extends BaseExpandableListAdapter {
    private final Context context;
    private final List<String> listDataHeader;
    private final HashMap<String,ArrayList<CartProductInfo>> listHashMap;
    private final LayoutInflater layoutInflater;
    private final ArrayList<CartShopInfo> shopNames;

    public ExpandableListAdapter(Context context, List<String> listDataHeader, HashMap<String, ArrayList<CartProductInfo>> listHashMap, ArrayList<CartShopInfo> shopNames) {
        this.context = context;
        this.listDataHeader = listDataHeader;
        this.listHashMap = listHashMap;
        this.shopNames = shopNames;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getGroupCount() {
        return listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return listHashMap.get(listDataHeader.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return shopNames.get(i).getCartProductInfos();
    }

    @Override
    public Object getChild(int i, int i1) {
        return listHashMap.get(listDataHeader.get(i)).get(i1); // i = Group Item , i1 = ChildItem
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
     //   String headerTitle = (String)getGroup(i);
        ExpandableListAdapter.ViewHolder holder;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.list_group, null);
            holder = new ExpandableListAdapter.ViewHolder();
            holder.ivShopName = view.findViewById(R.id.ivShopName);
            holder.tvProductsAvailable = view.findViewById(R.id.tvProductsAvailable);
            holder.tvPrice = view.findViewById(R.id.tvPrice);
            holder.tvAddress = view.findViewById(R.id.tvAddress);
            holder.tvTime = view.findViewById(R.id.tvTime);
            holder.tvNumber = view.findViewById(R.id.tvNumber);
            view.setTag(holder);
        } else {
            holder = (ExpandableListAdapter.ViewHolder) view.getTag();
        }
/*
       if(view == null)
        {
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_group,null);
        }*/
      /*  TextView lblListHeader = (TextView)view.findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);*/
        CartShopInfo cartShopInfo = this.shopNames.get(i);

        if (cartShopInfo.getShop_name().equals("Ашан"))
            holder.ivShopName.setImageResource(R.drawable.ashan);
        else
            holder.ivShopName.setImageResource(R.drawable.furshet);
        int availableCount = cartShopInfo.getCartProductInfos().size();
        for (int count = 0; count < cartShopInfo.getCartProductInfos().size(); count++) {
            if (cartShopInfo.getCartProductInfos().get(count).getPrice() == 0.0){
                availableCount--;
            }
        }
        String productAvailableString = "Продуктів в наявності: " + availableCount + "/" + cartShopInfo.getCartProductInfos().size();
        holder.tvProductsAvailable.setText(productAvailableString);
        String allPriceStr = cartShopInfo.getShop_summ().toString() + " грн";
        holder.tvPrice.setTextColor(Color.YELLOW);
        if (availableCount/cartShopInfo.getCartProductInfos().size() == 1)
        {
            holder.tvPrice.setTextColor(Color.GREEN);
        }
        if (cartShopInfo.getCartProductInfos().size()/availableCount > 2) {
            holder.tvPrice.setTextColor(Color.RED);
        }
        holder.tvPrice.setText(allPriceStr);
        String addressStr = cartShopInfo.getNearestShop().getAddress() + " (" + cartShopInfo.getNearestShop().getCurrent_distance() + " км)";
        holder.tvAddress.setText(addressStr);
        holder.tvTime.setText(cartShopInfo.getNearestShop().getHours_of_work());
        holder.tvNumber.setText(cartShopInfo.getNearestShop().getNumber());
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {

        if(view == null)
        {
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = Objects.requireNonNull(inflater).inflate(R.layout.list_item,null);
        }
       //  lvPrices.setAdapter(adapter);
        //final ArrayList<CartProductInfo> childText = (ArrayList<CartProductInfo>)getGroup(i);
     //   CartProductInfo childText = this.shopNames.get(i).getCartProductInfos().get(il);
       // final CustomCartListAdapter adapter = new CustomCartListAdapter(context, childText);
        final CartProductInfo childText = (CartProductInfo)getChild(i,i1);
        TextView txtListChild = view.findViewById(R.id.lblListItem);
        txtListChild.setText(childText.getName());
        TextView textView2 = view.findViewById(R.id.textView2);
        String priceString;
        if (childText.getPrice() == 0.0)
        {
            textView2.setTextColor(Color.RED);
            priceString = "Немає в наявності";
            textView2.setText(priceString);
        }
        else {
            textView2.setTextColor(Color.BLACK);
            priceString = childText.getPrice().toString() + " грн";
            textView2.setText(priceString);
        }

        ImageView cartProductImage = view.findViewById(R.id.cartProductImage);
        try {
            URL url = new URL(childText.getImage());
            Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            cartProductImage.setImageBitmap(bmp);
        }catch(Exception e){
            URL url;
            try {
                url = new URL("http://image10.bizrate-images.com/resize?sq=60&uid=2216744464");
                Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                cartProductImage.setImageBitmap(bmp);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
      // TextView txtListChild = view.findViewById(R.id.lblListItem);
      //  txtListChild.setText(childText.getName());
      //  ListView lblResultListView = view.findViewById(R.id.lblResultListView);
      //  lblResultListView.setAdapter(adapter);



        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    static class ViewHolder {
        ImageView ivShopName;
        TextView tvProductsAvailable;
        TextView tvPrice;
        TextView tvAddress;
        TextView tvTime;
        TextView tvNumber;
    }
}