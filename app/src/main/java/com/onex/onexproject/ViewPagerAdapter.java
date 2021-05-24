package com.onex.onexproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import org.jetbrains.annotations.NotNull;

public class ViewPagerAdapter extends PagerAdapter {

    private LayoutInflater inflater;
    private final int[] images = {
            R.drawable.exhibit1,
            R.drawable.exhibit2,
            R.drawable.exhibit3,
            R.drawable.exhibit4,
            R.drawable.exhibit5};
    private Context mContext;

    public ViewPagerAdapter(Context context) {
        mContext = context;
    }


    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull @NotNull View view, @NonNull @NotNull Object object) {
        return view ==((View)object);
    }
    public Object instantiateItem(ViewGroup container, int position)
    {
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view =inflater.inflate(R.layout.ex1,container,false);
        ImageView imageView = view.findViewById(R.id.ex1);
        imageView.setImageResource(images[position]);
        container.addView(view);
        return view;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object)
    {
        container.invalidate();
    }
}
