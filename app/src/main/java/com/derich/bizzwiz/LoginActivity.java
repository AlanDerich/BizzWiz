package com.derich.bizzwiz;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.derich.bizzwiz.BizwizDatabaseContract.users;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private BizwizOpenHelper mBizwizOpenHelper;
    EditText username,password;
    private Cursor mUserCursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBizwizOpenHelper = new BizwizOpenHelper(this);
        setContentView(R.layout.activity_login);
        SQLiteDatabase db = mBizwizOpenHelper.getReadableDatabase();
        username = findViewById(R.id.editText_username);
        password = findViewById(R.id.editText_password);







    }

    private boolean loadUserData(String user_username, String user_password) {
        SQLiteDatabase db = mBizwizOpenHelper.getReadableDatabase();


        String selection = users.COLUMN_USERNAME + " = ? " + " AND " + users.COLUMN_PASSWORD + " = ? ";
        String selectionArgs[] = {user_username,user_password};

        String[] vColumns = {
                users.COLUMN_USERNAME,
                users.COLUMN_PASSWORD};
        mUserCursor = db.query(users.TABLE_NAME, vColumns, selection, selectionArgs, null, null, null);
        int cursorCount = mUserCursor.getCount();
        mUserCursor.close();
        db.close();

        if (cursorCount > 0){
            return true;
        }
        return false;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_login:
                final String user_username = username.getText().toString().trim();
                final String user_password = password.getText().toString().trim();
                if (loadUserData(user_username,user_password)){
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));

                }
                else {
                    Snackbar.make(v, "Wrong username or password", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                break;
            case R.id.signUp_link:
                Intent intentRegister = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intentRegister);
                break;

        }
    }
}
