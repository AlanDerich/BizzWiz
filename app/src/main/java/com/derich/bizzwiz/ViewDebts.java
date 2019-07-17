package com.derich.bizzwiz;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.derich.bizzwiz.BizwizDatabaseContract.Clients;

public class ViewDebts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_debts);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Add new debt", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public static void LoadFromDatabase(BizwizOpenHelper dbHelper) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] debtColumns = {Clients.COLUMN_CLIENT_FIRSTNAME,
                Clients.COLUMN_CLIENT_SECOND_NAME,
                Clients.COLUMN_CLIENT_DEBT};
        final Cursor debtCursor = db.query(Clients.TABLE_NAME, debtColumns, null, null, null, null, Clients.COLUMN_CLIENT_DEBT + " DESC");
        loadDebtsFromDatabase(debtCursor);
    }

    private static void loadDebtsFromDatabase(Cursor Cursor) {
        int firstNamePos = Cursor.getColumnIndex(Clients.COLUMN_CLIENT_FIRSTNAME);
        int secondNamePos = Cursor.getColumnIndex(Clients.COLUMN_CLIENT_SECOND_NAME);
        int debtPos = Cursor.getColumnIndex(Clients.COLUMN_CLIENT_DEBT);

        while (Cursor.moveToNext()){
            String firstName = Cursor.getString(firstNamePos);
            String secondName = Cursor.getString(secondNamePos);
            String debt = Cursor.getString(debtPos);
        }

    }

}
