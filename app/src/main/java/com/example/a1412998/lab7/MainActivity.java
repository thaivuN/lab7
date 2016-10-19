package com.example.a1412998.lab7;

import android.content.Intent;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imagesID = new int[]{R.drawable.avaceratops, R.drawable.brachiosaurusdrawing,
                R.drawable.cartoondinosaur, R.drawable.deinonychus, R.drawable.gorgosaurus,
                R.drawable.irritator, R.drawable.megalosaurus, R.drawable.nipponosaurus,
                R.drawable.pentaceratops, R.drawable.saltasaurus};

        imagesIconIds = new int []{
                R.drawable.avaceratops_icon, R.drawable.brachiosaurusdrawing_icon,
                R.drawable.cartoondinosaur_icon, R.drawable.deinonychus_icon, R.drawable.gorgosaurus_icon,
                R.drawable.irritator_icon, R.drawable.megalosaurus_icon, R.drawable.nipponosaurus_icon,
                R.drawable.pentaceratops_icon, R.drawable.saltasaurus_icon
        };

        dinonames = getResources().getStringArray(R.array.dino_names);
        dinodescs = getResources().getStringArray(R.array.dino_desc);

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
