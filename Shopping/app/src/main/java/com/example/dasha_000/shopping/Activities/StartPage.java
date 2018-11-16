package com.example.dasha_000.shopping.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.dasha_000.shopping.CartInfo;
import com.example.dasha_000.shopping.R;
import com.example.dasha_000.shopping.WebParsing.ParseProducts;

public class StartPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);


        //////////////







        //////////////
        final Button button = findViewById(R.id.products_search_button);

        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent activityChangeIntent = new Intent(StartPage.this, ProductSearch.class);
                activityChangeIntent.putExtra("category_id", "0");
                StartPage.this.startActivity(activityChangeIntent);
            }
        });

        final Button categoriesButton = findViewById(R.id.product_categories_button);

        categoriesButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent activityChangeIntent = new Intent(StartPage.this, ProductCategories.class);
                StartPage.this.startActivity(activityChangeIntent);
            }
        });


        final Button cartButton = findViewById(R.id.cart_button);

        cartButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent activityChangeIntent = new Intent(StartPage.this, CartInfo.class);
                StartPage.this.startActivity(activityChangeIntent);
            }
        });


        final Button parseButton = findViewById(R.id.parseButton);

        parseButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent activityChangeIntent = new Intent(StartPage.this, ParseProducts.class);
                StartPage.this.startActivity(activityChangeIntent);
            }
        });
    }
}
