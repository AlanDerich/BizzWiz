package com.derich.bizzwiz;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.derich.bizzwiz.BizwizDatabaseContract.Products;

public class ViewProducts extends AppCompatActivity {
    BizwizDatabaseContract mBizwizDatabaseContract;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_products);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Add new product", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }

    public static void LoadFromDatabase(BizwizOpenHelper dbHelper){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        final String[] productColumns = {
                Products.COLUMN_PRODUCT_NAME,
                Products.COLUMN_PRODUCT_PRICE,
                Products.COLUMN_PRODUCT_QUANTITY};
        final Cursor productsCursor = db.query(Products.TABLE_NAME, productColumns, null, null, null, null, Products.COLUMN_PRODUCT_NAME + " DESC");
        loadProductsFromDatabase(productsCursor);

    }

    private static void loadProductsFromDatabase(Cursor cursor) {
        int productNamepos = cursor.getColumnIndex(Products.COLUMN_PRODUCT_NAME);
        int productPricepos = cursor.getColumnIndex(Products.COLUMN_PRODUCT_PRICE);
        int productQuantitypos = cursor.getColumnIndex(Products.COLUMN_PRODUCT_QUANTITY);

       while (cursor.moveToNext()) {
           String productName = cursor.getString(productNamepos);
           String productPrice = cursor.getString(productPricepos);
           String productQuantity = cursor.getString(productQuantitypos);
       }

    }

}
