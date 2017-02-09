package demo.com.databasedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateData extends AppCompatActivity {

    EditText txt1,txt2;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);
        txt1=(EditText)findViewById(R.id.editText3);
        txt2=(EditText)findViewById(R.id.editText4);
        btn=(Button)findViewById(R.id.button2);
        final Intent intent=getIntent();
        final int id=intent.getIntExtra("key1",0);
        String name=intent.getStringExtra("key2");
        String address=intent.getStringExtra("key3");
        txt1.setText(name);
        txt2.setText(address);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name=txt1.getText().toString();
                String address=txt2.getText().toString();
                User user=new User();
                user.setId(id);
                user.setName(name);
                user.setAddress(address);
                DBHelper dbHelper=new DBHelper(UpdateData.this);
                dbHelper.Updatedata(user);
                Intent intent1=new Intent(UpdateData.this,ShowData.class);
                startActivity(intent1);
            }
        });
    }
}
