package com.ramavathubalaji.lost_found;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by RAMAVATHU BALAJI on 14-05-2018.
 */

public class ContactsDB {
    public static final String KEY_ROWID="_id";
    public static final String KEY_EMAIL="email";
    public static final String KEY_PASSWORD="password";

    private final String DATABASE_NAME="LFDB";
    private final String DATABASE_TABLE="SIGNUP";
    private final int DATABASE_VERSION=1;

    private DBHelper ourHelper;
    private final Context ourContext;
    private SQLiteDatabase ourDatabase;

    public ContactsDB(Context context)
    {
        ourContext=context;
    }
    private class DBHelper extends SQLiteOpenHelper
    {
        public DBHelper(Context context)
        {
            super(context,DATABASE_NAME,null,DATABASE_VERSION);
        }

        @Override
        public  void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion)
        {
            db.execSQL("drop table of exists "+DATABASE_TABLE);
            onCreate(db);
        }
        @Override
        public void onCreate(SQLiteDatabase db)
        {
            String sql="create table "+DATABASE_TABLE+" ("+KEY_ROWID+" integer primary key autoincrement, "
                    +KEY_EMAIL+" text not null, "
                    +KEY_PASSWORD+" text not null);";
            db.execSQL(sql);

        }
        public DBHelper open() throws SQLException
        {
            ourHelper=new DBHelper(ourContext);
            ourDatabase=ourHelper.getWritableDatabase();
            return this;
        }
        public void close()
        {
            ourHelper.close();
        }
        public long createEntry(String email,String password)
        {
            ContentValues cv=new ContentValues();
            cv.put(KEY_EMAIL,email);
            cv.put(KEY_PASSWORD,password);
            return ourDatabase.insert(DATABASE_TABLE,null,cv);
        }

    }

}
