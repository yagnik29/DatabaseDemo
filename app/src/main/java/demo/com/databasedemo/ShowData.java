package demo.com.databasedemo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ShowData extends AppCompatActivity {

    ListView lv;
    ArrayList<User> arr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);
        lv = (ListView) findViewById(R.id.lstview);
        arr = new ArrayList<>();
       refresh();
    }
    public void refresh() {
        DBHelper dbHelper = new DBHelper(ShowData.this);
        arr = (ArrayList<User>) dbHelper.show();
        CustomAdapter ad = new CustomAdapter(ShowData.this, arr);
        registerForContextMenu(lv);
        lv.setAdapter(ad);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.add(0, 0, 0, "Update");
        menu.add(0, 1, 0, "Delete");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int pos = acmi.position;

        User user = arr.get(pos);
        if (item.getItemId() == 0) {

            int id = user.getId();
            String name = user.getName();
            String address = user.getAddress();

            Intent intent = new Intent(ShowData.this, UpdateData.class);
            intent.putExtra("key1", id);
            intent.putExtra("key2", name);
            intent.putExtra("key3", address);
            startActivity(intent);
        }
        if (item.getItemId() == 1) {
            DBHelper dbHelper = new DBHelper(ShowData.this);
            dbHelper.deletedata(user.getId());
            Toast.makeText(ShowData.this, "Data Deleted", Toast.LENGTH_LONG).show();
            refresh();
        }
        return super.onContextItemSelected(item);
    }
}
