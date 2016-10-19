package com.example.a1412998.lab7;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by 1412998 on 10/19/2016.
 */
public class DinoAdaptor extends BaseAdapter {

    Context context;
    private int [] imageIds;
    private int[] imageIconIds;
    private String[] dino_names;
    private String [] dino_descs;
    private static LayoutInflater inflater = null;
    public DinoAdaptor(MainActivity mainActivity, int[]imageIds, int[] imageIconIds, String[] dino_names, String[] dino_descs){
        this.imageIds = imageIds;
        this.dino_names = dino_names;
        this.dino_descs = dino_descs;
        this.imageIconIds = imageIconIds;
        context = mainActivity;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount(){
        return imageIds.length;
    }

    @Override
    public Object getItem(int position){
        return position;
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        TextView tv;
        ImageView img;

        View rowView = inflater.inflate(R.layout.dino_list,null);
        tv = (TextView) rowView.findViewById(R.id.dino_name);
        img = (ImageView) rowView.findViewById(R.id.dino_image);
        tv.setText(dino_names[position]);
        img.setImageResource(imageIconIds[position]);

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DinosaurActivity.class);
                intent.putExtra("dinoName", dino_names[position]);
                intent.putExtra("dinoDesc", dino_descs[position]);
                intent.putExtra("dinoImg", imageIds[position]);

                context.startActivity(intent);
            }
        });


        return rowView;

    }

}
