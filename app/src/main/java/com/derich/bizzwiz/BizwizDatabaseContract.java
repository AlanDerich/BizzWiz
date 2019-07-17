package com.derich.bizzwiz;

import android.preference.PreferenceScreen;
import android.provider.BaseColumns;


public class BizwizDatabaseContract {
   private BizwizDatabaseContract(){}


   public static final class users implements BaseColumns{
       public static final String TABLE_NAME = "users";
       public static final String COLUMN_USER_NAME = "user_name";
       public static final String COLUMN_USERNAME = "username";
       public static final String COLUMN_EMAIL = "user_email";
       public static final String COLUMN_PHONE = "user_phone";
       public static final String COLUMN_PASSWORD = "user_password";

       public static final String SQL_CREATE_DATABASE = "CREATE TABLE " + TABLE_NAME + " ( " + _ID + " INTEGER PRIMARY KEY, " + COLUMN_USER_NAME + " TEXT NOT NULL UNIQUE, " + COLUMN_USERNAME + " TEXT NOT NULL, " +
               COLUMN_EMAIL + " TEXT, "+ COLUMN_PHONE + " INTEGER NOT NULL,  " + COLUMN_PASSWORD + " TEXT NOT NULL )";

   }

    public static final class Clients implements BaseColumns {

        public static final String TABLE_NAME = "clients";
        public static final String COLUMN_CLIENT_FIRSTNAME = "client_first_name";
        public static final String COLUMN_CLIENT_SECOND_NAME = "client_second_name";
        public static final String COLUMN_CLIENT_EMAIL = "client_email";
        public static final String COLUMN_CLIENT_DEBT = "client_debt";
        public static final String COLUMN_CLIENT_PHONE = "client_phone";

        public static final String SQL_CREATE_DATABASE = "CREATE TABLE " + TABLE_NAME + " ( " + _ID + " INTEGER PRIMARY KEY, " + COLUMN_CLIENT_FIRSTNAME + " TEXT NOT NULL , " + COLUMN_CLIENT_SECOND_NAME +
                " TEXT NOT NULL, " + COLUMN_CLIENT_EMAIL + " TEXT, " + COLUMN_CLIENT_DEBT + " INTEGER, " + COLUMN_CLIENT_PHONE + " INTEGER NOT NULL UNIQUE )";

    }

        public static final class Products implements BaseColumns {
            public static final String TABLE_NAME = "products";
            public static final String COLUMN_PRODUCT_NAME = "product_name";
            public static final String COLUMN_PRODUCT_PRICE = "product_price";
            public static final String COLUMN_PRODUCT_QUANTITY = "product_quantity";



            public static final String SQL_CREATE_DATABASE = "CREATE TABLE " + TABLE_NAME + " ( " + _ID + " INTEGER PRIMARY KEY, " + COLUMN_PRODUCT_NAME + " TEXT NOT NULL, " +
                    COLUMN_PRODUCT_PRICE + " INTEGER NOT NULL," + COLUMN_PRODUCT_QUANTITY +" INTEGER ) ";

        }


}
