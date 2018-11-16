package com.example.dasha_000.shopping.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dasha_000.shopping.CartList;
import com.example.dasha_000.shopping.DBHelper;
import com.example.dasha_000.shopping.ListAdapters.CustomPricesListAdapter;
import com.example.dasha_000.shopping.ProductInfo;
import com.example.dasha_000.shopping.ProductItemInfo;
import com.example.dasha_000.shopping.R;

import java.net.URL;
import java.util.Objects;

public class ProductReview extends AppCompatActivity {

    private SQLiteDatabase Db;
    private String product_id;
    private String category_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_review);

        //для кнопочки назад
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        com.example.dasha_000.shopping.DBHelper DBHelper = new DBHelper(this);


        Db = DBHelper.getWritableDatabase();

        product_id = getIntent().getStringExtra("product_id");
        category_id = getIntent().getStringExtra("category_id");


        final ProductInfo productInfo = getItemInfoFromDB(product_id);



        ImageView imageView = findViewById(R.id.imageView);
      //  imageView.setImageResource(R.drawable.shoping_im);
        try {
            URL url = new URL(productInfo.getImage());
            // URL url = new URL("https://img3.zakaz.ua/20180322.1521729846.ad72436478c_2018-03-22_Alexey/20180322.1521729846.SNCPSG10.obj.0.1.jpg.oe.jpg.pf.jpg.350nowm.jpg.350x.jpg");
            Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            imageView.setImageBitmap(bmp);
            //BitmapDrawable bd = ResourcesCompat.getResources().getDrawable(R.drawable.shoping_im);
            //holder.imageView.setImageResource(R.drawable.shoping_im);
        }catch(Exception e){
            URL url;
            try {
                url = new URL("http://image10.bizrate-images.com/resize?sq=60&uid=2216744464");
                Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                imageView.setImageBitmap(bmp);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }



        TextView textView = findViewById(R.id.textView);
        textView.setText(productInfo.getName());

        final ListView lvPrices = findViewById(R.id.lvPriceView);

        TextView tvEnergy = findViewById(R.id.tvEnergy);
        tvEnergy.setText(productInfo.getEnergy());

        TextView tvProtein = findViewById(R.id.tvProtein);
        tvProtein.setText(productInfo.getProtein());

        TextView tvFat = findViewById(R.id.tvFat);
        tvFat.setText(productInfo.getFat());

        TextView tvCarbohydrates = findViewById(R.id.tvCarbohydrates);
        tvCarbohydrates.setText(productInfo.getCarbohydrates());

        final CustomPricesListAdapter adapter = new CustomPricesListAdapter(this, productInfo.getProductItemInfo());
        lvPrices.setAdapter(adapter);

        ImageButton cartButton = findViewById(R.id.imageButton);
        cartButton.setImageResource(R.drawable.list);


        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartList.addElement(productInfo);
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Товар додано до списку вибраних", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    private ProductInfo getItemInfoFromDB(String product_id) {
        ProductInfo currentItem = new ProductInfo();
        String[] arguments = new String[]{ product_id };


        String sqlQuery = "SELECT ProductName, ProductImage, ProductEnergy, ProductProtein, ProductFat, ProductCarbohydrates \n" +
                "FROM ProductInfo\n" +
                "WHERE ProductInfo.id = ?;";
        Cursor cursor1 = Db.rawQuery(sqlQuery, arguments);
        cursor1.moveToFirst();
        do {
            currentItem.setName(cursor1.getString(cursor1.getColumnIndex("ProductName")));
            currentItem.setEnergy(cursor1.getString(cursor1.getColumnIndex("ProductEnergy")));
            currentItem.setProtein(cursor1.getString(cursor1.getColumnIndex("ProductProtein")));
            currentItem.setFat(cursor1.getString(cursor1.getColumnIndex("ProductFat")));
            currentItem.setCarbohydrates(cursor1.getString(cursor1.getColumnIndex("ProductCarbohydrates")));
            currentItem.setImage(cursor1.getString(cursor1.getColumnIndex("ProductImage")));
        } while (cursor1.moveToNext());
        cursor1.close();

        String sqlQuery2 = "SELECT ProductPrice, ProductLink, Name\n" +
                "FROM ProductItem\n" +
                "INNER JOIN ShopName ON ProductItem.ShopNameId = ShopName.id\n" +
                "WHERE ProductItem.ProductInfoId = ?;";
        cursor1 = Db.rawQuery(sqlQuery2, arguments);
        cursor1.moveToFirst();
        do {
            currentItem.upgradeProductItemInfo(new ProductItemInfo(cursor1.getDouble(cursor1.getColumnIndex("ProductPrice")), cursor1.getString(cursor1.getColumnIndex("ProductLink")), cursor1.getString(cursor1.getColumnIndex("Name"))));
        } while (cursor1.moveToNext());
        cursor1.close();


        return currentItem;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                Intent activityChangeIntent = new Intent(ProductReview.this, ProductSearch.class);
                activityChangeIntent.putExtra("product_id", product_id);
                activityChangeIntent.putExtra("category_id", category_id);
                ProductReview.this.startActivity(activityChangeIntent);
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
