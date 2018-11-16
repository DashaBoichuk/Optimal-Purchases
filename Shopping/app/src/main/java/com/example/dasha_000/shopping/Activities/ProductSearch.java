package com.example.dasha_000.shopping.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SearchView;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.dasha_000.shopping.DBHelper;
import com.example.dasha_000.shopping.ListAdapters.CustomListAdapter;
import com.example.dasha_000.shopping.ProductInfo;
import com.example.dasha_000.shopping.ProductItemInfo;
import com.example.dasha_000.shopping.R;

import java.util.ArrayList;
import java.util.Objects;

public class ProductSearch extends AppCompatActivity {


    private String category_id;
    private SQLiteDatabase Db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_search);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        category_id = getIntent().getStringExtra("category_id");


        com.example.dasha_000.shopping.DBHelper DBHelper = new DBHelper(this);

        Db = DBHelper.getWritableDatabase();

     /*   String sqlQuery = "SELECT * from ProductSection";
        Cursor tmp_cursor = Db.rawQuery(sqlQuery, null);
        tmp_cursor.moveToFirst();
        do {
            for (String cn : tmp_cursor.getColumnNames()) {
                Log.d(cn, tmp_cursor.getString(tmp_cursor.getColumnIndex(cn)));
            }

        } while (tmp_cursor.moveToNext());
        tmp_cursor.close();*/

      /*  Db.execSQL("DROP TABLE IF EXISTS ShopInfo");

        Db.execSQL("CREATE TABLE ShopInfo (\n" +
                "                id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "                Address TEXT NOT NULL,\n" +
                "                HoursOfWork TEXT NOT NULL,\n" +
                "                PhoneNumber TEXT NOT NULL,\n" +
                "                CoordinateX REAL NOT NULL,\n" +
                "                CoordinateY REAL NOT NULL,\n" +
                "                ShopNameId INTEGER NOT NULL,\n" +
                "                FOREIGN KEY (ShopNameId) REFERENCES ShopName(id)\n" +
                "        );" );

        ContentValues ShopInfoValues = new ContentValues();
        ShopInfoValues.put("Address", "вул. Стрийська, 30");
        ShopInfoValues.put("HoursOfWork", "8:30 - 23:00");
        ShopInfoValues.put("PhoneNumber", "0322420530");
        ShopInfoValues.put("CoordinateX", 49.773606);
        ShopInfoValues.put("CoordinateY", 24.010384);
        ShopInfoValues.put("ShopNameId", 1);
        Db.insert("ShopInfo", null, ShopInfoValues);

        ShopInfoValues = new ContentValues();
        ShopInfoValues.put("Address", "вул. Володимира Великого, 58");
        ShopInfoValues.put("HoursOfWork", "8:00 - 23:00");
        ShopInfoValues.put("PhoneNumber", "0322427135");
        ShopInfoValues.put("CoordinateX", 49.812393);
        ShopInfoValues.put("CoordinateY", 23.986463);
        ShopInfoValues.put("ShopNameId", 1);
        Db.insert("ShopInfo", null, ShopInfoValues);

        ShopInfoValues = new ContentValues();
        ShopInfoValues.put("Address", "вул. Академіка Андрія Сахарова, 45");
        ShopInfoValues.put("HoursOfWork", "7:30 - 22:30");
        ShopInfoValues.put("PhoneNumber", "0445831563");
        ShopInfoValues.put("CoordinateX", 49.820882);
        ShopInfoValues.put("CoordinateY", 24.017967);
        ShopInfoValues.put("ShopNameId", 2);
        Db.insert("ShopInfo", null, ShopInfoValues);

        ShopInfoValues = new ContentValues();
        ShopInfoValues.put("Address", "вул. Зубрівська 36А");
        ShopInfoValues.put("HoursOfWork", "8:00 - 23:00");
        ShopInfoValues.put("PhoneNumber", "0445831563");
        ShopInfoValues.put("CoordinateX", 49.795268);
        ShopInfoValues.put("CoordinateY", 24.059314);
        ShopInfoValues.put("ShopNameId", 2);
        Db.insert("ShopInfo", null, ShopInfoValues);


        ShopInfoValues = new ContentValues();
        ShopInfoValues.put("Address", "вул. Богдана Хмельницького, 176");
        ShopInfoValues.put("HoursOfWork", "8:00 - 22:30");
        ShopInfoValues.put("PhoneNumber", "0445831563");
        ShopInfoValues.put("CoordinateX", 49.861543);
        ShopInfoValues.put("CoordinateY", 24.051312);
        ShopInfoValues.put("ShopNameId", 2);
        Db.insert("ShopInfo", null, ShopInfoValues);*/

       /*  String sqlQuery = "SELECT * from ShopInfo";
        Cursor tmp_cursor = Db.rawQuery(sqlQuery, null);
        tmp_cursor.moveToFirst();
        do {
            for (String cn : tmp_cursor.getColumnNames()) {
                Log.d(cn, tmp_cursor.getString(tmp_cursor.getColumnIndex(cn)));
            }

        } while (tmp_cursor.moveToNext());
        tmp_cursor.close();*/

       /* sqlQuery = "SELECT * from ProductInfo";
        tmp_cursor = Db.rawQuery(sqlQuery, null);
        tmp_cursor.moveToFirst();
        do {
            for (String cn : tmp_cursor.getColumnNames()) {
                Log.d(cn, tmp_cursor.getString(tmp_cursor.getColumnIndex(cn)));
            }

        } while (tmp_cursor.moveToNext());
        tmp_cursor.close();

        sqlQuery = "SELECT * from ProductItem";
        tmp_cursor = Db.rawQuery(sqlQuery, null);
        tmp_cursor.moveToFirst();
        do {
            for (String cn : tmp_cursor.getColumnNames()) {
                Log.d(cn, tmp_cursor.getString(tmp_cursor.getColumnIndex(cn)));
            }

        } while (tmp_cursor.moveToNext());
        tmp_cursor.close();*/


        final ListView productsListView = findViewById(R.id.products_list_view);
        final ArrayList<ProductInfo> currentCategoryList;
        final ArrayList<ProductInfo> findedList = new ArrayList<>();


        currentCategoryList = getProductsFromDB(category_id);

        final CustomListAdapter adapter = new CustomListAdapter(this, currentCategoryList);
        productsListView.setAdapter(adapter);

        SearchView searchView = findViewById(R.id.products_search_view);

        productsListView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object o = productsListView.getItemAtPosition(position);
                ProductInfo product = (ProductInfo) o;
                Intent activityChangeIntent = new Intent(ProductSearch.this, ProductReview.class);
                activityChangeIntent.putExtra("product_id", String.valueOf(product.getId()));
                activityChangeIntent.putExtra("category_id", category_id);
                ProductSearch.this.startActivity(activityChangeIntent);
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                callSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
//              if (searchView.isExpanded() && TextUtils.isEmpty(newText)) {
                //callSearch(newText);
//              }
                return true;
            }
            // ПРИ ПОШУКУ ПРОДУКТІВ
            void callSearch(String query) {
                findedList.clear();

                for (ProductInfo product : currentCategoryList) {
                    if (product.getName().toLowerCase().contains(query.toLowerCase())){
                        findedList.add(product);
                    }
                }
                adapter.updateResults(findedList);

            }


        });

    }

    private ArrayList<ProductInfo> getProductsFromDB(String category) {

        final ArrayList<ProductInfo> productsList = new ArrayList<>();
        final ArrayList<ProductInfo> currentCategoryList = new ArrayList<>();
        String sqlQuery = "SELECT ProductName, ProductImage, ProductPrice, Name, PItem.ProductInfoId as product_id, ProductSectionId\n" +
                "FROM ProductItem as PItem\n" +
                "INNER JOIN ProductInfo ON ProductInfo.id = PItem.ProductInfoId\n" +
                "INNER JOIN ShopName ON PItem.ShopNameId = ShopName.id;";
        Cursor cursor1 = Db.rawQuery(sqlQuery, null);
        cursor1.moveToFirst();
        ProductInfo temp_productInfo;
        do {
            for (String cn : cursor1.getColumnNames()) {
                Log.d(cn, cursor1.getString(cursor1.getColumnIndex(cn)));
            }
            int tmp_exist = 0;
            for (ProductInfo productInfo : productsList)
            {
                if (productInfo.getId() == cursor1.getInt(cursor1.getColumnIndex("product_id"))) {
                    productInfo.upgradeProductItemInfo(new ProductItemInfo(cursor1.getDouble(cursor1.getColumnIndex("ProductPrice")), "link", cursor1.getString(cursor1.getColumnIndex("Name"))));
                    tmp_exist = 1;
                }
            }
            if (tmp_exist == 0) {
                temp_productInfo = new ProductInfo();
                temp_productInfo.setId(cursor1.getInt(cursor1.getColumnIndex("product_id")));
                temp_productInfo.setName(cursor1.getString(cursor1.getColumnIndex("ProductName")));
                temp_productInfo.setCategoryId(cursor1.getInt(cursor1.getColumnIndex("ProductSectionId")));
                temp_productInfo.setImage(cursor1.getString(cursor1.getColumnIndex("ProductImage")));
                temp_productInfo.upgradeProductItemInfo(new ProductItemInfo(cursor1.getDouble(cursor1.getColumnIndex("ProductPrice")), "link", cursor1.getString(cursor1.getColumnIndex("Name"))));
                productsList.add(temp_productInfo);
            }
        } while (cursor1.moveToNext());
        cursor1.close();

        for (ProductInfo product_item : productsList){
            if ((product_item.getCategoryId() == Integer.parseInt(category)) || (Integer.parseInt(category) == 0) ){
                currentCategoryList.add(product_item);
            }
        }
        return currentCategoryList;
    }

   @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                Intent activityChangeIntent;
                if (Objects.equals(category_id, "0"))
                    activityChangeIntent = new Intent(ProductSearch.this, StartPage.class);
                else
                    activityChangeIntent = new Intent(ProductSearch.this, ProductCategories.class);
                ProductSearch.this.startActivity(activityChangeIntent);
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}