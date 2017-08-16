package com.example.leixiang.demoapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.demolibrary.LibraryActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RealNameConfig.init(RealNameConfig.App_Type.FF_APP);
        setContentView(R.layout.activity_main);
        DemoAPI demoAPI=new DemoManager().create(DemoAPI.class,this);
        demoAPI.demo();
        findViewById(R.id.name).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,LibraryActivity.class));
            }
        });
    }
}
