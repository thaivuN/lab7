package com.example.a1412998.lab7;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.StrictMode;
import android.util.Log;

/**
 * Created by 1412998 on 10/26/2016.
 */
public class DBHipsterDino extends SQLiteOpenHelper {

    public static  final String TAG = "DBHipsterDino";

    public static final String TABLE_DINO = "Dino";
    public static final String COL_ID = "_id";
    public static final String COL_NAME = "Name";
    public static final String COL_INFO = "Info";
    public static final String COL_IMG_ID = "Image_id";
    public static final String COL_ICON_ID = "Icon_id";
    private static final String DB_Name = "dino.db";
    private static final int DATABASE_VERSION = 1;
    private static DBHipsterDino daoH = null;



    private static final String DATABASE_CREATE = "create table "
            + TABLE_DINO + " ( " + COL_ID
            + " integer primary key autoincrement, "
            + COL_NAME + " text not null, "
            + COL_INFO + " text not null, "
            + COL_IMG_ID + " integer not null, "
            + COL_ICON_ID + " integer not null );";
    private Context context;

    private DBHipsterDino(Context context){
        super(context, DB_Name, null, DATABASE_VERSION);
        this.context = context;

    }

    public static DBHipsterDino getDBHipsterDino(Context context){
        if (daoH == null){
            daoH = new DBHipsterDino(context.getApplicationContext());
            Log.i(TAG, "getDBHipsterDino, daoH == null");

        }

        return daoH;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        Log.i(TAG, "onCreate() executed");
        populateDB(sqLiteDatabase);

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        Log.i(TAG, "onOpen() executed");
    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVer, int newVer) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_DINO);
        Log.i(TAG, "Updating database");

        onCreate(sqLiteDatabase);
        Log.i(TAG, "onUpgrade() called");

    }

    public long insertDino(String name, String info, int img_id, int icon_id, SQLiteDatabase db){
        ContentValues cv = new ContentValues();

        cv.put(COL_NAME, name);
        cv.put(COL_INFO, info);
        cv.put(COL_IMG_ID, img_id);
        cv.put(COL_ICON_ID, icon_id);




        long code = db.insert(TABLE_DINO, null, cv);
        return code;
    }

    public Cursor getDinos(){
        return getReadableDatabase().query(TABLE_DINO, null, null, null,
                null, null, null);
    }

    private void populateDB(SQLiteDatabase db){
        db.execSQL(DATABASE_CREATE);
        int[]imagesID = new int[]{R.drawable.avaceratops, R.drawable.brachiosaurusdrawing,
                R.drawable.cartoondinosaur, R.drawable.deinonychus, R.drawable.gorgosaurus,
                R.drawable.irritator, R.drawable.megalosaurus, R.drawable.nipponosaurus,
                R.drawable.pentaceratops, R.drawable.saltasaurus};

        int[]imagesIconIds = new int []{
                R.drawable.avaceratops_icon, R.drawable.brachiosaurusdrawing_icon,
                R.drawable.cartoondinosaur_icon, R.drawable.deinonychus_icon, R.drawable.gorgosaurus_icon,
                R.drawable.irritator_icon, R.drawable.megalosaurus_icon, R.drawable.nipponosaurus_icon,
                R.drawable.pentaceratops_icon, R.drawable.saltasaurus_icon
        };

        String[]dinonames = context.getResources().getStringArray(R.array.dino_names);
        String[]dinodescs = context.getResources().getStringArray(R.array.dino_desc);


        for (int i = 0; i < dinonames.length; i++){
            insertDino(dinonames[i], dinodescs[i], imagesID[i], imagesIconIds[i], db);
        }
    }
}
