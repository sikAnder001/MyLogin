package com.example.sony.mylogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DataHelper dh=new DataHelper(this);

    public void onClick(View v) {
        EditText name = (EditText) findViewById(R.id.name);
        EditText pass = (EditText) findViewById(R.id.pass);
        String n = name.getText().toString();
        String p = pass.getText().toString();

        if(v.getId()==R.id.login) {

            if (!n.equals("")&& !p.equals("")) {
                String passs = dh.searchPass(n);
                if (p.equals(passs)) {
                    Intent i = new Intent(MainActivity.this, wellcome.class);
//                    i.putExtra("name", n);
                    startActivity(i);
                    Toast.makeText(getApplicationContext(), "thumbs up", Toast.LENGTH_SHORT).show();
                } else {Toast.makeText(getApplicationContext(),"invalid id and password",Toast.LENGTH_SHORT).show();}
            } else {
                Toast.makeText(getApplicationContext(), "enter ur name and password", Toast.LENGTH_SHORT).show();
            }
        }
            if(v.getId()==R.id.sign){
            Intent i=new Intent(MainActivity.this,signup.class);
            startActivity(i);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
