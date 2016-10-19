package com.example.a1412998.lab7;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by 1412998 on 10/19/2016.
 */
public class DinosaurActivity extends AppCompatActivity {

    private String dinoName;
    private String dinoDesc;
    private int imageId;

    TextView tv1;
    TextView tv2;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dinosaur);

        Intent intent = getIntent();

        dinoName = intent.getExtras().getString("dinoName", "Evil Dinosaur Hipster");
        dinoDesc = intent.getExtras().getString("dinoDesc", "This dinosaur is what happens when weird things happen. You get this");
        imageId = intent.getExtras().getInt("dinoImg", R.drawable.weirddino);

        tv1 = (TextView) findViewById(R.id.dinoInfoName);
        tv2 = (TextView) findViewById(R.id.dinoInfoDescription);
        iv = (ImageView) findViewById(R.id.dinoInfoImg);

        tv1.setText(dinoName);
        tv2.setText(dinoDesc);
        iv.setImageResource(imageId);
    }



}
