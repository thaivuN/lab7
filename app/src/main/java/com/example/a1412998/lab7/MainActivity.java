package com.example.a1412998.lab7;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ListView lview;
    private int [] imagesID;
    private String[] dinonames;
    private String [] dinodescs;
    private int[] imagesIconIds;

    private DBHipsterDino dbHD;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dbHD = DBHipsterDino.getDBHipsterDino(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Cursor cursor = dbHD.getDinos();
        int ctr= 0;

        int cursorSize = cursor.getCount();

        dinonames = new String[cursorSize];
        dinodescs = new String[cursorSize];;
        imagesID = new int[cursorSize];
        imagesIconIds = new int [cursorSize];



        while(cursor.moveToNext()){
            dinonames[ctr] = cursor.getString(cursor.getColumnIndex(dbHD.COL_NAME));
            dinodescs[ctr] = cursor.getString(cursor.getColumnIndex(dbHD.COL_INFO));
            imagesID[ctr] = cursor.getInt(cursor.getColumnIndex(dbHD.COL_IMG_ID));
            imagesIconIds[ctr] = cursor.getInt(cursor.getColumnIndex(dbHD.COL_ICON_ID));
        }

        cursor.close();


        this.lview = (ListView) findViewById(R.id.lView);
        lview.setAdapter(new DinoAdaptor(this, imagesID, imagesIconIds, dinonames, dinodescs));


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        Intent intent;

        switch (item.getItemId())
        {
            case R.id.web:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.sciencekids.co.nz/pictures/dinosaurs.html"));
                startActivity(intent);
                return true;
            case R.id.about:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(getString(R.string.about));
                builder.create().show();

                return true;
            default:
                return false;
        }
    }



}
