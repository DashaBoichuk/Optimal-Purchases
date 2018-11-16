package com.example.dasha_000.shopping;


import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ExpandableListView;

import com.example.dasha_000.shopping.CartInformation.CartProductInfo;
import com.example.dasha_000.shopping.CartInformation.CartShopInfo;
import com.example.dasha_000.shopping.CartInformation.CartShopItems;
import com.example.dasha_000.shopping.ListAdapters.ExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class CartInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_info);

        com.example.dasha_000.shopping.DBHelper DBHelper = new DBHelper(this);

        SQLiteDatabase db;

        db = DBHelper.getWritableDatabase();

        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        if (Objects.requireNonNull(locationManager).isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, new LocationListener() {

                @Override
                public void onLocationChanged(Location location) {
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            });
        }else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();

                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            });
        }

       /* float distance_result[] = new float[10];
        Location.distanceBetween(49.812393, 23.986463, 49.773606, 24.010384, distance_result);
        int distance_int = (int) distance_result[0];
        float distance = (float) distance_int/1000;*/

        List<String> listDataHeader = new ArrayList<>();
        HashMap<String, ArrayList<CartProductInfo>> listHash = new HashMap<>();

        ArrayList<ProductInfo> cartList;
        ArrayList<CartShopInfo> shopNames;

        ExpandableListView listView = findViewById(R.id.lvExp);
        if(CartList.getData()!=null) {
            int i = 0;
            cartList = new ArrayList<>(CartList.getData());
            shopNames = new ArrayList<>();
            //запис назв магазинів в яких є продукти з корзини
            for (ProductInfo productInfo : cartList) {
                for (ProductItemInfo productItemInfo : productInfo.getProductItemInfo()) {
                    if (shopNames.size() == 0){
                        shopNames.add(new CartShopInfo(productItemInfo.getShopName(), ++i));

                    //    listDataHeader.add(productItemInfo.getShopName());
                        continue;
                    }
                    int isAvalible = 0;
                    for (CartShopInfo shopName : shopNames) {
                        if (shopName.getShop_name().equals(productItemInfo.getShopName()))
                            isAvalible = 1;

                        //listDataHeader.add(productItemInfo.getShopName());continue;
                    }
                    if(isAvalible == 0 ) {
                        shopNames.add(new CartShopInfo(productItemInfo.getShopName(), ++i));
                        //listDataHeader.add(productItemInfo.getShopName());
                    }
                }
            }
            //запис списку продуктів до кожного магазину (закінчити з перевіркою чи є продукт)
            for (CartShopInfo shopInfo : shopNames) {
                double price = 0;
                for (ProductInfo productInfo : cartList) {
                    CartProductInfo cartProductInfo = new CartProductInfo(productInfo.getId(), productInfo.getName(), productInfo.getImage(), 0.0);
                    int done = 0;
                    // shopInfo.upgradeCartProductInfo(cartProductInfo);
                    for (ProductItemInfo productItemInfo : productInfo.getProductItemInfo()) {
                       // for (CartShopInfo shopName : shopNames) {

                            if (shopInfo.getShop_name().equals(productItemInfo.getShopName())) {
                                cartProductInfo.setPrice(productItemInfo.getProductPrice());
                                price += productItemInfo.getProductPrice();

                                shopInfo.upgradeCartProductInfo(cartProductInfo);
                                 //  listHash.put(listDataHeader.get(shopInfo.getId()),cartProducts);
                                done = 1;
                                break;
                                //listDataHeader.add(productItemInfo.getShopName());
                            }/* else {
                                shopInfo.upgradeCartProductInfo(cartProductInfo);
                                //break;
                            }*/
                       // }
                    }
                    if (done == 0) {
                        shopInfo.upgradeCartProductInfo(cartProductInfo);

                    }
                }
                shopInfo.setShop_summ(price);
            }
            //пошук деталей про кожен магазин
            for (CartShopInfo cartShopInfo : shopNames) {

                CartShopItems cartShopItems;
                String[] arguments = new String[] {cartShopInfo.getId().toString()};
                String sqlQuery = "SELECT Address, HoursOfWork, PhoneNumber, CoordinateX, CoordinateY, ShopNameId\n" +
                        "FROM ShopInfo\n" +
                        "WHERE ShopNameId = ?;";
                Cursor cursor1 = db.rawQuery(sqlQuery, arguments);
                cursor1.moveToFirst();
                do {
                    cartShopItems = new CartShopItems();
                    cartShopItems.setAddress(cursor1.getString(cursor1.getColumnIndex("Address")));
                    cartShopItems.setHours_of_work(cursor1.getString(cursor1.getColumnIndex("HoursOfWork")));
                    cartShopItems.setNumber(cursor1.getString(cursor1.getColumnIndex("PhoneNumber")));
                    cartShopItems.setCoordinateX(cursor1.getFloat(cursor1.getColumnIndex("CoordinateX")));
                    cartShopItems.setCoordinateY(cursor1.getFloat(cursor1.getColumnIndex("CoordinateY")));
                    float distance_result[] = new float[10];
                    Location.distanceBetween(cartShopItems.getCoordinateX(), cartShopItems.getCoordinateY(), locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER).getLatitude(), locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER).getLongitude(), distance_result);
                    int distance_int = (int) distance_result[0];
                    float distance = (float) distance_int/1000;
                    cartShopItems.setCurrent_distance(distance);
                    cartShopInfo.upgradeCartShopItems(cartShopItems);
                    for (String cn : cursor1.getColumnNames()) {
                        Log.d(cn, cursor1.getString(cursor1.getColumnIndex(cn)));
                    }
                } while (cursor1.moveToNext());
                cursor1.close();
            }

            //пошук найближчого магазину
            for (CartShopInfo cartShopInfo : shopNames) {
                CartShopItems nearestShop;
                nearestShop = cartShopInfo.getCartShopItemses().get(0);
                float minDistance = cartShopInfo.getCartShopItemses().get(0).getCurrent_distance();
                for (CartShopItems cartShopItems : cartShopInfo.getCartShopItemses()){
                    if (cartShopItems.getCurrent_distance() < minDistance){
                        minDistance = cartShopItems.getCurrent_distance();
                        nearestShop = cartShopItems;
                    }
                }
                cartShopInfo.setNearestShop(nearestShop);
            }

            //для передачі в адаптер
            int num = -1;
            ArrayList<CartProductInfo> cartProducts;
            for (CartShopInfo cartShopInfo : shopNames) {
                num ++;
                listDataHeader.add(cartShopInfo.getShop_name() + "Найближчий магазин: вул. Володимира Великого, 58 (" + /*distance + */" км");
                cartProducts = new ArrayList<>();
                for (CartProductInfo cartProductInfo : cartShopInfo.getCartProductInfos()){
                 cartProducts.add(cartProductInfo);
                    ///    cartProducts.add(cartProductInfo.getName() + "   " + cartProductInfo.getPrice() + " грн");


                }
                listHash.put(listDataHeader.get(num), cartProducts);

            }


            ExpandableListAdapter listAdapter = new ExpandableListAdapter(this, listDataHeader, listHash, shopNames);
            listView.setAdapter(listAdapter);
        }
//        initData();



        }


}



