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

public class ViewClients extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_clients);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Add new client", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    public static void LoadFromDatabase(BizwizOpenHelper dbHelper){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] clientsColumns = {
                Clients.COLUMN_CLIENT_FIRSTNAME,
                Clients.COLUMN_CLIENT_SECOND_NAME,
                Clients.COLUMN_CLIENT_EMAIL,
                Clients.COLUMN_CLIENT_DEBT,
                Clients.COLUMN_CLIENT_PHONE

        };
        final Cursor clientCursor = db.query(Clients.TABLE_NAME, clientsColumns, null, null, null, null, Clients.COLUMN_CLIENT_FIRSTNAME + " DESC");
        loadClientsFromDatabase(clientCursor);
    }

    private static void loadClientsFromDatabase(Cursor cursor) {
        int firstNamePos = cursor.getColumnIndex(Clients.COLUMN_CLIENT_FIRSTNAME);
        int secondNamePos = cursor.getColumnIndex(Clients.COLUMN_CLIENT_SECOND_NAME);
        int clientEmailPos = cursor.getColumnIndex(Clients.COLUMN_CLIENT_EMAIL);
        int debtPos = cursor.getColumnIndex(Clients.COLUMN_CLIENT_DEBT);
        int phonePos = cursor.getColumnIndex(Clients.COLUMN_CLIENT_PHONE);

        while (cursor.moveToNext()){
            String firstName = cursor.getString(firstNamePos);
            String secondName = cursor.getString(secondNamePos);
            String clientEmail = cursor.getString(clientEmailPos);
            String debt = cursor.getString(debtPos);
            String phone = cursor.getString(phonePos);
        }
    }

}
