package com.example.prototypevol3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    HorizontalInfiniteCycleViewPager viewPager;
    List<Model> modelList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        viewPager = (HorizontalInfiniteCycleViewPager) findViewById(R.id.view_pager);
        MyAdapter adapter = new MyAdapter(this, modelList);
        viewPager.setAdapter(adapter);
    }

    private void initData() {
        modelList.add(new Model(" A G G Y", getString(R.string.app_name), R.mipmap.download1,R.mipmap.images2,R.drawable.ic_favorite_black_24dp,R.mipmap.download1,R.drawable.ic_launcher_background));
        modelList.add(new Model(" A G G Y", getString(R.string.app_name), R.mipmap.images2,R.mipmap.download1,R.drawable.ic_favorite_black_24dp,R.mipmap.download1,R.drawable.ic_launcher_background));
        modelList.add(new Model(" A G G Y", getString(R.string.app_name), R.mipmap.images3,R.mipmap.download1,R.drawable.ic_favorite_black_24dp,R.mipmap.download1,R.drawable.ic_launcher_background));
    }
}
