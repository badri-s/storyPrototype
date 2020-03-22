package com.example.prototypevol3;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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

import jp.shts.android.storiesprogressview.StoriesProgressView;

public class MyAdapter extends PagerAdapter implements StoriesProgressView.OnClickListener, StoriesProgressView.StoriesListener {
    private StoriesProgressView storiesProgressView;
    List<Model> modelList;
    private static final int PROGRES_COUNT=6;
    private int pos = 0;
    private ImageView imageView;
    private int counter=0;
    private int[] images = new int[]{
            modelList.get(pos).getImage1(),
            modelList.get(pos).getImage2(),
            modelList.get(pos).getImage3(),
            modelList.get(pos).getImage4(),
            modelList.get(pos).getImage5(),
            modelList.get(pos).getImage6(),
            modelList.get(pos).getImage7(),
            modelList.get(pos).getImage8(),
            modelList.get(pos).getImage9(),
            modelList.get(pos).getImage10()
    };
    Long limit = 500L;
    Long prestime = 0L;
    private View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    prestime = System.currentTimeMillis();
                    storiesProgressView.pause();
                    return false;
                case MotionEvent.ACTION_UP:
                    long now = System.currentTimeMillis();
                    storiesProgressView.resume();
                    return limit < now - prestime;
            }
            return false;
        }
    };
    Context context;

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
        final ImageView image = (ImageView) view.findViewById(R.id.image);

        image.setImageResource(images[counter]);
        final StoriesProgressView storiesProgressView = (StoriesProgressView)view.findViewById(R.id.story_image);
        storiesProgressView.setStoriesCount(PROGRES_COUNT);
        storiesProgressView.setStoryDuration(10000L);
        storiesProgressView.setStoriesListener(this);
        counter = 1;
        storiesProgressView.startStories(images[counter]);
        final View reverse = view.findViewById(R.id.reverse);
        final View skip = view.findViewById(R.id.skip);
        reverse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storiesProgressView.reverse();
            }
        });
        reverse.setOnTouchListener(onTouchListener);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            storiesProgressView.skip();
            }
        });
        skip.setOnTouchListener(onTouchListener);
        container.addView(view);
        return view;
    }



    @Override
    public void onNext() {
        imageView.setImageResource(images[++counter]);

    }

    @Override
    public void onPrev() {
        if ((counter-1)<0)return;
        imageView.setImageResource(images[--counter]);

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onClick(View v) {

    }
}
