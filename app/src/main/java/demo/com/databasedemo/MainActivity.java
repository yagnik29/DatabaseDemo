package demo.com.databasedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText txt1, txt2;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt1 = (EditText) findViewById(R.id.editText);
        txt2 = (EditText) findViewById(R.id.editText2);
        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User();
                String name = txt1.getText().toString();
                String address = txt2.getText().toString();
                user.setName(name);
                user.setAddress(address);
                DBHelper dbHelper=new DBHelper(MainActivity.this);
                dbHelper.insertData(user);
                Toast.makeText(MainActivity.this,"Data inserted",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(MainActivity.this,ShowData.class);
                startActivity(intent
                );
            }
        });
    }
}
