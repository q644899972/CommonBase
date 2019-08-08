package com.jiayu.online.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.wb.commonbase.manager.TaotutuManager;
import com.wb.commonbase.util.GsonUtils;

import java.util.HashMap;

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
//                Intent i    = new Intent(MainActivity.this,TestActivity.class);
//                startActivity(i);

                TaotutuManager.setAccessKey("7b51de35ba4f465a81c48af594df38e1");
                TaotutuManager.setAccessSecret("56cbaf232dbd48d9b6b7b88760b5784e");


                HashMap<String,String> map = new HashMap<>();
                map.put("pageNo","1");
                map.put("pageSize","10");

                HashMap<String, String> param = TaotutuManager. getParam(map);
                Log.d("test", GsonUtils.toJson(param));
            }
        });
    }
}
