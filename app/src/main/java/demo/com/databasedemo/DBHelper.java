package demo.com.databasedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 07-Jan-17.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "myst";
    public static final int VERSION = 1;
    public static final String ID = "id";
    public static final String NAME = "uname";
    public static final String ADDRESS = "address";
    public static final String IMAGE= "image";
    public static final String TBLNAME = "mytable";

    public DBHelper(Context context) {
        super(context, DBNAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //String sql = "create table mytable(id integer primary key autoincrement,name text,address text)";
        String sql = "create table " + TBLNAME + "(" + ID + " integer primary key autoincrement,"
                + NAME + " text," + IMAGE+ " blob,"+ ADDRESS + " text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertData(User user) throws IOException {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NAME, user.getName());
        cv.put(ADDRESS, user.getAddress());
        cv.put(IMAGE, String.valueOf(user.getImage()));
        db.insert(TBLNAME, ID, cv);
        db.close();
    }

    List<User> show() {
        ArrayList<User> arr = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String column[] = {ID, NAME,IMAGE, ADDRESS};
        Cursor c = db.query(TBLNAME, column, null, null, null, null, null);
        while (c.moveToNext()) {

            byte[] image = c.getBlob(2);

            Bitmap bmp = BitmapFactory.decodeByteArray(image,0,image.length);
            int id = c.getInt(0);
            String name = c.getString(1);
            String address = c.getString(3);


            User user = new User();
            user.setId(id);
            user.setName(name);
            user.setImage(bmp);
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
