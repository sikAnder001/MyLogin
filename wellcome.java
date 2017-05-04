package com.example.sony.mylogin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class wellcome extends AppCompatActivity {
TextView pname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wellcome);
        String uname=getIntent().getStringExtra("name");
        pname=(TextView)findViewById(R.id.pname);
        pname.setText(uname);
    }
}
