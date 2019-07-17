package com.derich.bizzwiz;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import static com.derich.bizzwiz.BizwizDatabaseContract.users;

public class SignUpActivity extends AppCompatActivity {

    EditText editText_user_name,editText_username,editText_password,editText_confirm_password, editText_email,editText_phone;
    Button signupBtn;
    BizwizOpenHelper dbHelper;
    Cursor mCursor;
    ConstraintLayout viewLayout;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String UserName = "nameKey" ;
    public static final String Email = "emailKey";
    public static SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new BizwizOpenHelper(this);
        setContentView(R.layout.activity_sign_up);
        editText_user_name = findViewById(R.id.signup_user_name);
        editText_username = findViewById(R.id.signup_username);
        editText_password = findViewById(R.id.signup_password);
        editText_confirm_password = findViewById(R.id.signup_confirm_password);
        editText_phone = findViewById(R.id.signUp_phone);
        editText_email = findViewById(R.id.signUp_email);
        signupBtn = findViewById(R.id.signup_button);
        viewLayout = findViewById(R.id.viewLayout);
        sharedPreferences = getSharedPreferences(getPackageName() + "_preferences", MODE_PRIVATE);
    signupBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String username = editText_username.getText().toString().trim();
            String phone = editText_phone.getText().toString().trim();
            String user_name = editText_user_name.getText().toString().trim();
           // String email = editText_email.getText().toString().trim();
            String password = editText_password.getText().toString().trim();
            String confirmPassword = editText_confirm_password.getText().toString().trim();
            checkUserDetails(username,phone);

            if (!user_name.isEmpty()){
                if(!phone.isEmpty()){
                    if (!username.isEmpty()){
                if (!password.isEmpty()){
                    if (!(password==confirmPassword)){
                if (!checkUserDetails(username,phone)){
                insertIntoDatabase();
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                PreferenceHelper.setUsername(username);
            }
            else{
                Snackbar.make(v, "User exists", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
            }
                    else{
                        Snackbar.make(v, "Passwords do not match", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                }



                else {
                    Snackbar.make(v, "Please enter preferred password", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                    }
                    else {
                        Snackbar.make(v, "Please enter your preferred username", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                }
                else {
                    Snackbar.make(v, "Please enter phone number", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }

            else {
                Snackbar.make(v, "Please Enter Your Names", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }

        }
    });
    }

    private boolean checkUserDetails(String username,String phone) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();


        String selection = BizwizDatabaseContract.users.COLUMN_USERNAME + " = ? " + " OR " + BizwizDatabaseContract.users.COLUMN_PHONE + " = ? ";
        String selectionArgs[] = {username,phone};

        String[] vColumns = {
                BizwizDatabaseContract.users.COLUMN_USERNAME,
                BizwizDatabaseContract.users.COLUMN_PHONE};
        mCursor = db.query(BizwizDatabaseContract.users.TABLE_NAME, vColumns, selection, selectionArgs, null, null, null);
        int cursorCount = mCursor.getCount();
        mCursor.close();
        db.close();

        if (cursorCount > 0){
            return true;
        }
        return false;
    }

    private void insertIntoDatabase() {
        String user_name = editText_user_name.getText().toString().trim();
        String username = editText_username.getText().toString().trim();
        String phone = editText_phone.getText().toString().trim();
        String email = editText_email.getText().toString().trim();
        String password = editText_password.getText().toString().trim();
        SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(users.COLUMN_USER_NAME, user_name);
            values.put(users.COLUMN_USERNAME, username);
            values.put(users.COLUMN_PHONE, phone);
            values.put(users.COLUMN_EMAIL, email);
            values.put(users.COLUMN_PASSWORD, password);

            db.insert(users.TABLE_NAME, null, values);
            db.close();

        /*else {
            Snackbar.make(viewLayout, "User exists", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }*/


    }

}
