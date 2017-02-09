package demo.com.databasedemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 10-Jan-17.
 */

public class CustomAdapter extends BaseAdapter {

    Context context;
    ArrayList<User> arr;
    int size;

    public CustomAdapter(Context context, List<User> list) {
        this.context = context;
        this.arr = (ArrayList<User>) list;
        size = list.size();
    }

    @Override
    public int getCount() {
        return size;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        User user = arr.get(i);
        LayoutInflater layinf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = layinf.inflate(R.layout.custom, null);
        TextView tv1 = (TextView) v.findViewById(R.id.tv1);
        TextView tv2 = (TextView) v.findViewById(R.id.tv2);
        TextView tv3 = (TextView) v.findViewById(R.id.tv3s);
        tv1.setText(user.getId() + "");
        tv2.setText(user.getName());
        tv3.setText(user.getAddress());

        return v;
    }
}
