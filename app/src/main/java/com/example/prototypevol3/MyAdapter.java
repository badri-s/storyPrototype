package com.example.prototypevol3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MyAdapter extends PagerAdapter {
    ProgressBar pb1,pb2,pb3,pb4,pb5;
    int counter = 0;
    int pos = 0;
    Context context;
    List<Model> modelList;

    public MyAdapter(Context context, List<Model> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    @Override
    public int getCount() {
        return modelList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull final ViewGroup container, final int position) {
        final View view = LayoutInflater.from(context).inflate(R.layout.card_item, container, false);
        final ImageView movie_image = (ImageView) view.findViewById(R.id.story_image);
        TextView movie_title = (TextView) view.findViewById(R.id.heshtega);
        TextView movie_description = (TextView) view.findViewById(R.id.descreba);
        Button btn1 = (Button) view.findViewById(R.id.btn1);
        Button btn2 = (Button) view.findViewById(R.id.btn2);
        movie_image.setImageResource(modelList.get(position).getImage());
        movie_title.setText(modelList.get(position).getTitle());
        movie_description.setText(modelList.get(position).getDescription());
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pos == 1) {
                    movie_image.setImageResource(modelList.get(position).getImage());
                    pos = 0;
                }else if (pos == 2) {
                    movie_image.setImageResource(modelList.get(position).getImage2());
                    pos=1;

                }else if (pos == 3) {
                    movie_image.setImageResource(modelList.get(position).getImage3());
                    pos= 2;

                }else if (pos == 4) {
                    movie_image.setImageResource(modelList.get(position).getImage4());
                    pos=3;

                }


            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pos == 0) {
                    setPb1(container);
                    movie_image.setImageResource(modelList.get(position).getImage2());
                    pos=1;
                }else if (pos == 1) {
                    movie_image.setImageResource(modelList.get(position).getImage3());
                    pos=2;
                }else if (pos == 2) {
                    movie_image.setImageResource(modelList.get(position).getImage4());
                    pos=3;
                }else if (pos == 3) {
                    movie_image.setImageResource(modelList.get(position).getImage5());
                    pos=4;
                }
            }
        });

        container.addView(view);
        return view;
    }
    private void setPb1(@NonNull ViewGroup container){
        View view = LayoutInflater.from(context).inflate(R.layout.card_item,container,false);
        pb1 = (ProgressBar) view.findViewById(R.id.progressBar1);
        final Timer t = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                counter ++;
                pb1.setProgress(counter);
                if (counter==100){
                    t.cancel();
                }
            }
        };
        t.schedule(tt,0,100);
    }
}
