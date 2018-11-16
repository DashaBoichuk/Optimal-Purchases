package com.example.dasha_000.shopping;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper  extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ShoppingDB";
    private static final String TABLE_PRODUCT_SECTION = "ProductSection";
    private static final String TABLE_SHOP_NAME = "ShopName";
    private static final String TABLE_PRODUCT_INFO = "ProductInfo";
    private static final String TABLE_PRODUCT_ITEM = "ProductItem";
    private static final String TABLE_SHOP_INFO = "ShopInfo";

/*    public static final String KEY_ID = "_id";
    public static final String KEY_SECTION_NAME = "SectionName";
    public static final String KEY_NAME = "Name";
    public static final String KEY_BARCODE = "BarCode";
    public static final String KEY_PRODUCT_NAME = "ProductName";
    public static final String KEY_PRODUCT_ENERGY = "ProductEnergy";
    public static final String KEY_PRODUCT_PROTEIN = "ProductProtein";
    public static final String KEY_PRODUCT_FAT = "ProductFat";
    public static final String KEY_PRODUCT_CARBOHYDRATES = "ProductCarbohydrates";
    public static final String KEY_PRODUCT_IMAGE = "ProductImage";
    public static final String KEY_PRODUCT_SECTION_ID = "ProductSectionId";*/



    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE ProductSection (\n" +
                "                id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "                SectionName TEXT NOT NULL\n" +
                "        );" );
        db.execSQL("CREATE TABLE ShopName (\n" +
                "                id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "                Name TEXT NOT NULL\n" +
                "        );" );
        db.execSQL("CREATE TABLE ProductInfo (\n" +
                "                id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "                BarCode TEXT NOT NULL,\n" +
                "                ProductName TEXT NOT NULL,\n" +
                "                ProductEnergy TEXT NOT NULL,\n" +
                "                ProductProtein TEXT NOT NULL,\n" +
                "                ProductFat TEXT NOT NULL,\n" +
                "                ProductCarbohydrates TEXT NOT NULL,\n" +
                "                ProductImage TEXT NOT NULL,\n" +
                "                ProductSectionId INTEGER NOT NULL,\n" +
                "                FOREIGN KEY (ProductSectionId) REFERENCES ProductSection(id)\n" +
                "        )");
        db.execSQL("CREATE TABLE ProductItem (\n" +
                "                id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "                ProductPrice REAL NOT NULL,\n" +
                "                ProductLink TEXT NOT NULL,\n" +
                "                ProductInfoId INTEGER NOT NULL,\n" +
                "                ShopNameId INTEGER NOT NULL,\n" +
                "                FOREIGN KEY (ProductInfoId) REFERENCES ProductInfo(id),\n" +
                "                FOREIGN KEY (ShopNameId) REFERENCES ShopName(id)\n" +
                "        );" );
        db.execSQL("CREATE TABLE ShopInfo (\n" +
                "                id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "                Address TEXT NOT NULL,\n" +
                "                HoursOfWork TEXT NOT NULL,\n" +
                "                PhoneNumber TEXT NOT NULL,\n" +
                "                CoordinateX REAL NOT NULL,\n" +
                "                CoordinateY REAL NOT NULL,\n" +
                "                ShopNameId INTEGER NOT NULL,\n" +
                "                FOREIGN KEY (ShopNameId) REFERENCES ShopName(id)\n" +
                "        );" );
        ContentValues ProductSectionValues = new ContentValues();
        ProductSectionValues.put("SectionName", "Бакалія і солодощі");
        db.insert("ProductSection", null, ProductSectionValues);
        ProductSectionValues = new ContentValues();
        ProductSectionValues.put("SectionName", "Заморозка");
        db.insert("ProductSection", null, ProductSectionValues);
        ProductSectionValues = new ContentValues();
        ProductSectionValues.put("SectionName", "Напої");
        db.insert("ProductSection", null, ProductSectionValues);
        ProductSectionValues = new ContentValues();
        ProductSectionValues.put("SectionName", "Мясо, риба, ковбаси");
        db.insert("ProductSection", null, ProductSectionValues);
        ProductSectionValues = new ContentValues();
        ProductSectionValues.put("SectionName", "Молочне, яйця, сир");
        db.insert("ProductSection", null, ProductSectionValues);
        ProductSectionValues = new ContentValues();
        ProductSectionValues.put("SectionName", "Фрукти та овочі");
        db.insert("ProductSection", null, ProductSectionValues);

        ContentValues ShopNameValues = new ContentValues();
        ShopNameValues.put("Name", "Ашан");
        db.insert("ShopName", null, ShopNameValues);
        ShopNameValues = new ContentValues();
        ShopNameValues.put("Name", "Фуршет");
        db.insert("ShopName", null, ShopNameValues);

        ContentValues ShopInfoValues = new ContentValues();
        ShopInfoValues.put("Address", "вул. Стрийська, 30");
        ShopInfoValues.put("HoursOfWork", "8:30 - 23:00");
        ShopInfoValues.put("PhoneNumber", "0322420530");
        ShopInfoValues.put("CoordinateX", 49.773606);
        ShopInfoValues.put("CoordinateY", 24.010384);
        ShopInfoValues.put("ShopNameId", 1);
        db.insert("ShopInfo", null, ShopInfoValues);


        ShopInfoValues = new ContentValues();
        ShopInfoValues.put("Address", "вул. Володимира Великого, 58");
        ShopInfoValues.put("HoursOfWork", "8:00 - 23:00");
        ShopInfoValues.put("PhoneNumber", "0322427135");
        ShopInfoValues.put("CoordinateX", 49.812393);
        ShopInfoValues.put("CoordinateY", 23.986463);
        ShopInfoValues.put("ShopNameId", 1);
        db.insert("ShopInfo", null, ShopInfoValues);

        ShopInfoValues = new ContentValues();
        ShopInfoValues.put("Address", "вул. Академіка Андрія Сахарова, 45");
        ShopInfoValues.put("HoursOfWork", "7:30 - 22:30");
        ShopInfoValues.put("PhoneNumber", "0445831563");
        ShopInfoValues.put("CoordinateX", 49.820882);
        ShopInfoValues.put("CoordinateY", 24.017967);
        ShopInfoValues.put("ShopNameId", 2);
        db.insert("ShopInfo", null, ShopInfoValues);

        ShopInfoValues = new ContentValues();
        ShopInfoValues.put("Address", "вул. Зубрівська 36А");
        ShopInfoValues.put("HoursOfWork", "8:00 - 23:00");
        ShopInfoValues.put("PhoneNumber", "0445831563");
        ShopInfoValues.put("CoordinateX", 49.795268);
        ShopInfoValues.put("CoordinateY", 24.059314);
        ShopInfoValues.put("ShopNameId", 2);
        db.insert("ShopInfo", null, ShopInfoValues);

        ShopInfoValues = new ContentValues();
        ShopInfoValues.put("Address", "вул. Богдана Хмельницького, 176");
        ShopInfoValues.put("HoursOfWork", "8:00 - 22:30");
        ShopInfoValues.put("PhoneNumber", "0445831563");
        ShopInfoValues.put("CoordinateX", 49.861543);
        ShopInfoValues.put("CoordinateY", 24.051312);
        ShopInfoValues.put("ShopNameId", 2);
        db.insert("ShopInfo", null, ShopInfoValues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCT_SECTION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SHOP_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCT_INFO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCT_ITEM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SHOP_INFO);

        onCreate(db);

    }
}