package com.example.dasha_000.shopping.WebParsing;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dasha_000.shopping.DBHelper;
import com.example.dasha_000.shopping.R;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



public class ParseProducts extends AppCompatActivity {

    private static final String TAG = "Parse";
    private String price;
    private double price_double;
    private String name;
    private String big_image;
    private String energy;
    private String protein;
    private String fat;
    private String carbohydrates;
    private String category;
    private ArrayList<ParseHelper> listLinks;
    private ArrayList<String> allStringLinks;
    private String currentFLink;
    private ArrayList<ParseFItemInfo> parseFItemInfos;

    private SQLiteDatabase Db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parse_products);

        com.example.dasha_000.shopping.DBHelper DBHelper = new DBHelper(this);
        Db = DBHelper.getWritableDatabase();

//КАТЕГОРІЇ
        listLinks = new ArrayList<>();
     //   listLinks.add(new ParseHelper("http://lviv.efurshet.com/categories/sladosti", 1, 1));
      // listLinks.add(new ParseHelper("http://lviv.efurshet.com/categories/morozilka", 2, 4));//++++
      //  listLinks.add(new ParseHelper("http://lviv.efurshet.com/categories/napitki", 3, 1));
       // listLinks.add(new ParseHelper("http://lviv.efurshet.com/categories/fish", 4, 7));//++++
     //   listLinks.add(new ParseHelper("http://lviv.efurshet.com/categories/dairy-eggs", 5, 1));
      //  listLinks.add(new ParseHelper("http://lviv.efurshet.com/categories/fruits-and-vegetables", 6, 4));//+++

        TextView result = findViewById(R.id.result);
        Button getLinksButton = findViewById(R.id.GetLinksButton);
        getLinksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    GetWebsite();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Button bntSendHttpRequest = findViewById(R.id.btnSendRequest);
        EditText etJsonResponse = findViewById(R.id.etJson);

        //В ДРУГУ ЧЕРГУ ПРАЦЮЄ
        bntSendHttpRequest.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (parseFItemInfos != null)
                {
                    for (ParseFItemInfo item : parseFItemInfos)
                    {
                        String tmp_currentBarcode = item.getBarCode();
                        if (tmp_currentBarcode.length() < 14){
                            StringBuilder sb = new StringBuilder(tmp_currentBarcode);
                            for (int i = 0; i < (14 - tmp_currentBarcode.length()); i++) {
                                sb.insert(0,"0");
                            }
                            tmp_currentBarcode = sb.toString();
                        }
                        final String currentBarcode = tmp_currentBarcode;
                        final Double furshetPrice = item.getPriceF();
                        final String furshetLink = item.getLinkFitem();

         //   final String currentBarcode = "auchan02000000536996";
                        final OkHttpClient client = new OkHttpClient();
                        MediaType mediaType = MediaType.parse("application/octet-stream");
                        RequestBody body = RequestBody.create(mediaType, "{\"meta\":{},\"request\":[{\"args\":{\"store_id\":\"48246409\",\"eans\":[\"" + currentBarcode + "\"]},\"v\":\"0.1\",\"type\":\"product.details\",\"id\":\"product_" + currentBarcode + "_full\"}]}");
                        Request request = new Request.Builder()
                                .url("https://auchan.zakaz.ua/api/query.json")
                                .post(body)
                                .header("Cookie", "lang=uk;")
                                .build();
//31842549
                        client.newCall(request).enqueue(new Callback() {
                            @Override
                            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                Log.i (TAG, e.getMessage());

                            }

                            @Override
                            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                String body = Objects.requireNonNull(response.body()).string();
                                Log.i (TAG, body);

                                JsonParser parser = new JsonParser();
                                JsonObject obj = parser.parse(body.trim()).getAsJsonObject();
                                JsonArray responses = obj.getAsJsonArray("responses");
                                JsonObject data = responses.get(0).getAsJsonObject();
                                JsonObject data1 = data.getAsJsonObject("data");
                                JsonArray item_tmp = data1.getAsJsonArray("items");
                               if (item_tmp.size() != 0){
                                JsonObject item = data1.getAsJsonArray("items").get(0).getAsJsonObject();
                                price = item.get("price").toString();
                                price_double = (Double.parseDouble(price)/100);
                                name = item.get("name").toString();
                                    if (name != null && name.length() > 0) {
                                        name = name.substring(1, name.length());
                                        name = name.substring(0, name.length() - 1);
                                    }
                                category = item.get("base_group_id").toString();
                                    Log.i ("CATEGORY_STRING", category);
                                Integer category_int = 0;
                                if (category.equals("\"grocery-and-sweets-auchan\"")) category_int = 1;
                                if (category.equals("\"frozen\"")) category_int = 2;
                                if (category.equals("\"drinks\"")) category_int = 3;
                                if (category.equals("\"meat-fish-poultry\"")) category_int = 4;
                                if (category.equals("\"dairy-and-eggs\"")) category_int = 5;
                                if (category.equals("\"fruits-and-vegetables\"")) category_int = 6;
                                Log.i ("CATEGORY", category_int.toString());
                                JsonObject image = item.getAsJsonObject("image");
                                big_image = image.get("s200x200").toString();
                                    if (big_image != null && big_image.length() > 0) {
                                        big_image = big_image.substring(1, big_image.length());
                                        big_image = big_image.substring(0, big_image.length() - 1);
                                    }
                                JsonObject extended_info = item.getAsJsonObject("extended_info");
                                  if (extended_info.has("ingredient_energy")) {
                                        energy = extended_info.get("ingredient_energy").toString();
                                    }
                                  else {
                                      energy = "\"0\"";
                                  }

                                   if (energy != null && energy.length() > 0) {
                                       energy = energy.substring(1, energy.length());
                                       energy = energy.substring(0, energy.length() - 1);
                                   }

                                   if (extended_info.has("ingredient_protein")) {
                                       protein = extended_info.get("ingredient_protein").toString();
                                   }
                                   else {
                                       protein = "\"0\"";
                                   }

                                   if (protein != null && protein.length() > 0) {
                                       protein = protein.substring(1, protein.length());
                                       protein = protein.substring(0, protein.length() - 1);
                                   }

                                   if (extended_info.has("ingredient_fat")) {
                                       fat = extended_info.get("ingredient_fat").toString();
                                   }
                                   else {
                                       fat = "\"0\"";
                                   }


                                   if (fat != null && fat.length() > 0) {
                                       fat = fat.substring(1, fat.length());
                                       fat = fat.substring(0, fat.length() - 1);
                                   }

                                   if (extended_info.has("ingredient_carbohydrates")) {
                                       carbohydrates = extended_info.get("ingredient_carbohydrates").toString();
                                   }
                                   else {
                                       carbohydrates = "\"0\"";
                                   }


                                   if (carbohydrates != null && carbohydrates.length() > 0) {
                                       carbohydrates = carbohydrates.substring(1, carbohydrates.length());
                                       carbohydrates = carbohydrates.substring(0, carbohydrates.length() - 1);
                                   }
                             Log.i (TAG, price_double + energy + protein + fat + carbohydrates + category);
                                if (category_int != 0) {
                                    ContentValues ProductInfoValues = new ContentValues();
                                    ProductInfoValues.put("BarCode", currentBarcode);
                                    ProductInfoValues.put("ProductName", name);
                                    ProductInfoValues.put("ProductEnergy", energy);
                                    ProductInfoValues.put("ProductProtein", protein);
                                    ProductInfoValues.put("ProductFat", fat);
                                    ProductInfoValues.put("ProductCarbohydrates", carbohydrates);
                                    ProductInfoValues.put("ProductImage", big_image);
                                    ProductInfoValues.put("ProductSectionId", category_int);
                                    long product_id = Db.insert("ProductInfo", null, ProductInfoValues);

                                    ContentValues ProductAuchanItemValues = new ContentValues();
                                    ProductAuchanItemValues.put("ProductPrice", price_double);
                                    ProductAuchanItemValues.put("ProductLink", "https://auchan.zakaz.ua/uk/" + currentBarcode);
                                    ProductAuchanItemValues.put("ProductInfoId", product_id);
                                    ProductAuchanItemValues.put("ShopNameId", 1);
                                    Db.insert("ProductItem", null, ProductAuchanItemValues);

                                    Log.i ("BDDD", "++");

                                   /* ContentValues ProductFurshetItemValues = new ContentValues();
                                    ProductFurshetItemValues.put("ProductPrice", furshetPrice);
                                    ProductFurshetItemValues.put("ProductLink", furshetLink);
                                    ProductFurshetItemValues.put("ProductInfoId", product_id);
                                    ProductFurshetItemValues.put("ShopNameId", 2);
                                    Db.insert("ProductItem", null, ProductFurshetItemValues);*/
                                }
                                }

                                //НЕПОТРІБНИЙ КОД
                      /*  ContentValues initialValues = new ContentValues();
                        initialValues.put("ProductPrice", 20.13); // the execution is different if _id is 2
                        initialValues.put("ProductLink", "http://lviv.efurshet.com/product/kruasan_mini_kakao_7_Days_200g");
                        initialValues.put("ProductInfoId", 1);
                        initialValues.put("ShopInfoId", 1);
                        long newRowId = mDb.insert("ProductItem", null, initialValues);*/

                                /*new Thread() {
                                    public void run() {
                                        try {
                                            runOnUiThread(new Runnable() {

                                                @Override
                                                public void run() {
                                                    //ВИДАЛИТИ
                                                    String str = "name: ";
                                                    etJsonResponse.setText(str + big_image);
                                                }
                                            });
                                            Thread.sleep(300);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                }.start();*/


                            }
                        });
                    }
                }
            }

        });
    }

    private void GetWebsite() throws InterruptedException {
        Toast toast = Toast.makeText(getApplicationContext(),
                "START FUNCTION", Toast.LENGTH_SHORT);
        toast.show();

        //ЗАКОМЕНТОВАНИЙ ДЛЯ ПОТОКІВ, ЗНИЗУ ТОЙ Що ЗАРАЗ ЗАПУСКАЄТЬСЯ
     /*   List<Thread> threadsToJoin = new ArrayList<>(6);
        int i;
        for (i = 0; i < 1; i++) {
            final ParseHelper link_group = listLinks.get(i);
            Thread t = new Thread(new Runnable() {

                @Override
                public void run() {

                        for (int i = 0; i < link_group.getPages_count(); i++)
                        {
                            currentFLink = link_group.getFlink() + "/p-" + (i+1);
                            try {
                                Document doc = Jsoup.connect(currentFLink).get();
                                Elements links = doc.select("a[href]");
                                for (Element link : links) {
                                    if (link.attr("href").contains("efurshet.com/product")) {
                                        String currentLink = link.attr("href");
                                        if (!(allStringLinks.contains(currentLink)))
                                            allStringLinks.add(currentLink);
                                        Log.d("LINK", currentLink);
                                        Toast toast = Toast.makeText(getApplicationContext(),
                                                currentLink, Toast.LENGTH_SHORT);
                                        toast.show();
                                    }
                                }
                            } catch (IOException e) {
                            }
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    //      result.setText(builder.toString());

                                }
                            });
                        }

                    parseFItemInfos = new ArrayList<>();
                    for (String link : allStringLinks)
                    {
                        ParseFItemInfo item;
                        Document doc;
                        try {
                            doc = Jsoup.connect(link).get();
                            Elements content = doc.select(".sal");
                            Elements barCode = doc.select(".artcl");
                            String barCodeString = barCode.text();
                            if (barCodeString != null && barCodeString.length() > 0) {
                                barCodeString = barCodeString.substring(11, barCodeString.length());
                            }
                            Log.d("BARCODE", barCodeString);
                            Toast toast = Toast.makeText(getApplicationContext(),
                                    barCodeString, Toast.LENGTH_SHORT);
                            toast.show();
                            String currentString = content.text();
                            if (currentString != null && currentString.length() > 0) {
                                currentString = currentString.substring(0, currentString.length() - 4);
                            }
                            Double currentDoublePrice = Double.parseDouble(currentString);
                            Log.d("PRICE", currentDoublePrice.toString());

                            item = new ParseFItemInfo(barCodeString, link, currentDoublePrice);
                            parseFItemInfos.add(item);
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }
            });
            threadsToJoin.add(t);
            t.start();
        }*/

     //ТИПУ СТВОРЕННЯ ПОТОКУ ЯКИЙ ОЧІКУЄ ІНШІ
      /*  MyThread t = new MyThread();
        t.setThreadsToJoin(threadsToJoin);
        t.start();
        toast = Toast.makeText(getApplicationContext(),
                "START JOIN THREAD", Toast.LENGTH_SHORT);
        toast.show();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        toast = Toast.makeText(getApplicationContext(),
                "FINISH JOIN THREAD", Toast.LENGTH_SHORT);
        toast.show();*/


        /////////////////////



        allStringLinks= new ArrayList<>();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (ParseHelper link_group : listLinks)
                {
                    for (int i = 0; i < link_group.getPages_count(); i++)
                    {
                        currentFLink = link_group.getFlink() + "/p-" + (i+1);
                        try {
                            Document doc = Jsoup.connect(currentFLink).get();
                            Elements links = doc.select("a[href]");
                            for (Element link : links) {
                                if (link.attr("href").contains("efurshet.com/product")) {
                                    String currentLink = link.attr("href");
                                    if (!(allStringLinks.contains(currentLink)))
                                        allStringLinks.add(currentLink);
                                    Log.i("LINK", currentLink);
                                }
                            }
                        } catch (IOException e) {
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //      result.setText(builder.toString());

                            }
                        });
                    }
                }
                parseFItemInfos = new ArrayList<>();
                for (String link : allStringLinks)
                {
                    ParseFItemInfo item;
                    Document doc;
                    try {
                        doc = Jsoup.connect(link).get();
                        Elements content = doc.select(".sal");
                        Elements barCode = doc.select(".artcl");
                        String barCodeString = barCode.text();
                        if (barCodeString != null && barCodeString.length() > 0) {
                            barCodeString = barCodeString.substring(11, barCodeString.length());
                        }
                        Log.i("BARCODE", barCodeString);
                        String currentString = content.text();
                        if (currentString != null && currentString.length() > 0) {
                            currentString = currentString.substring(0, currentString.length() - 4);
                        }
                        Double currentDoublePrice = Double.parseDouble(currentString);
                        Log.i("PRICE", currentDoublePrice.toString());
                        item = new ParseFItemInfo(barCodeString, link, currentDoublePrice);
                        parseFItemInfos.add(item);
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
