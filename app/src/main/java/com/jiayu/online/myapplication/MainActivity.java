package com.jiayu.online.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/***
 * commonbase 库项目
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.btn_test);
        btn .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i    = new Intent(MainActivity.this,TestActivity.class);
                startActivity(i);

            }
        });
    }
}
