package com.example.sony.mylogin;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class signup extends AppCompatActivity {
    EditText name,mail,pass1,pass2;
    Button reg,dev;
    ListView tv;
    DataHelper dh=new DataHelper(this);

    public void onClick(View v){

        name=(EditText)findViewById(R.id.name);
        mail=(EditText)findViewById(R.id.mail);
        pass1=(EditText)findViewById(R.id.pass1);
        pass2=(EditText)findViewById(R.id.pass2);
        String n=name.getText().toString();
        String m=mail.getText().toString();
        String p=pass1.getText().toString();
        String p1=pass2.getText().toString();
        reg=(Button)findViewById(R.id.reg);

        if(v.getId()==R.id.reg) {
            if (p.equals(p1)) {
                contacts c = new contacts();
                c.setName(n);
                c.setEmail(m);
                c.setPass(p1);
                dh.insertContact(c);
                Toast.makeText(getApplicationContext(), "id is created successfully", Toast.LENGTH_LONG).show();
                Intent i = new Intent(signup.this, MainActivity.class);
                startActivity(i);
            } else {
                Toast.makeText(getApplicationContext(), "password doesnt match", Toast.LENGTH_SHORT).show();
            }
        }
        if(v.getId()==R.id.delete){
            dh.deleteAll();
            Toast.makeText(getApplicationContext(),"all records deleted",Toast.LENGTH_SHORT).show();
            ArrayList<String> aa=new ArrayList();
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(signup.this, android.R.layout.simple_list_item_1, aa);
            tv.setAdapter(adapter);
        }
        if(v.getId()==R.id.back){
            startActivity(new Intent(signup.this,MainActivity.class));
        }
        }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_signup);
        tv=(ListView)findViewById(R.id.tv);
        dev=(Button)findViewById(R.id.dev);
        dev.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int i = 0;
                ArrayList<String> aa = dh.fetchData();
                Toast.makeText(getApplicationContext(),"dev working",Toast.LENGTH_SHORT).show();
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(signup.this, android.R.layout.simple_list_item_1, aa);
                    tv.setAdapter(adapter);
                }
        });

    }
}