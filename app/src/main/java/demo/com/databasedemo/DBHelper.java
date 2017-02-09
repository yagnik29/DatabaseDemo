package demo.com.databasedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 07-Jan-17.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "mydb";
    public static final int VERSION = 1;
    public static final String ID = "id";
    public static final String NAME = "uname";
    public static final String ADDRESS = "address";
    public static final String TBLNAME = "mytable";

    public DBHelper(Context context) {
        super(context, DBNAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //String sql = "create table mytable(id integer primary key autoincrement,name text,address text)";
        String sql = "create table " + TBLNAME + "(" + ID + " integer primary key autoincrement,"
                + NAME + " text," + ADDRESS + " text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertData(User user) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NAME, user.getName());
        cv.put(ADDRESS, user.getAddress());
        db.insert(TBLNAME, ID, cv);
        db.close();
    }

    List<User> show() {
        ArrayList<User> arr = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String column[] = {ID, NAME, ADDRESS};
        Cursor c = db.query(TBLNAME, column, null, null, null, null, null);
        while (c.moveToNext()) {
            int id = c.getInt(0);
            String name = c.getString(1);
            String address = c.getString(2);
            User user = new User();
            user.setId(id);
            user.setName(name);
            user.setAddress(address);
            arr.add(user);

        }
        return arr;
    }

    public void Updatedata(User user) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NAME, user.getName());
        cv.put(ADDRESS, user.getAddress());
        String where = ID + "=" + user.getId();
        db.update(TBLNAME, cv, where, null);
        db.close();
    }

    public void deletedata(int id) {
        SQLiteDatabase db = getWritableDatabase();
        String where = ID + "=" + id;
        db.delete(TBLNAME, where, null);
    }
}
