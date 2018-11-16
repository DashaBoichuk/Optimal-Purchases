package com.example.dasha_000.shopping.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.dasha_000.shopping.R;

import java.util.Objects;

public class ProductCategories extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_categories);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        final Button sweetsCategoryButton = findViewById(R.id.sweetsCategoryButton);
        final Button freezeCategoryButton = findViewById(R.id.freezeCategoryButton);
        final Button drinksCategoryButton = findViewById(R.id.drinksCategoryButton);
        final Button meatCategoryButton = findViewById(R.id.meatCategoryButton);
        final Button milkCategoryButton = findViewById(R.id.milkCategoryButton);
        final Button fructsCategoryButton = findViewById(R.id.fructsCategoryButton);

        sweetsCategoryButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent activityChangeIntent = new Intent(ProductCategories.this, ProductSearch.class);
                activityChangeIntent.putExtra("category_id", "1");
                ProductCategories.this.startActivity(activityChangeIntent);
            }
        });
        freezeCategoryButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent activityChangeIntent = new Intent(ProductCategories.this, ProductSearch.class);
                activityChangeIntent.putExtra("category_id", "2");
                ProductCategories.this.startActivity(activityChangeIntent);
            }
        });
        drinksCategoryButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent activityChangeIntent = new Intent(ProductCategories.this, ProductSearch.class);
                activityChangeIntent.putExtra("category_id", "3");
                ProductCategories.this.startActivity(activityChangeIntent);
            }
        });
        meatCategoryButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent activityChangeIntent = new Intent(ProductCategories.this, ProductSearch.class);
                activityChangeIntent.putExtra("category_id", "4");
                ProductCategories.this.startActivity(activityChangeIntent);
            }
        });
        milkCategoryButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent activityChangeIntent = new Intent(ProductCategories.this, ProductSearch.class);
                activityChangeIntent.putExtra("category_id", "5");
                ProductCategories.this.startActivity(activityChangeIntent);
            }
        });
        fructsCategoryButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent activityChangeIntent = new Intent(ProductCategories.this, ProductSearch.class);
                activityChangeIntent.putExtra("category_id", "6");
                ProductCategories.this.startActivity(activityChangeIntent);
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                Intent activityChangeIntent;
                activityChangeIntent = new Intent(ProductCategories.this, StartPage.class);
                ProductCategories.this.startActivity(activityChangeIntent);
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
