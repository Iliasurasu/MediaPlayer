package com.example.musicplayer;
import androidx.viewpager2.widget.ViewPager2;



import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ImageFragment extends Fragment {
    private int[] images = {R.drawable.image_one, R.drawable.image_two, R.drawable.image_three};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image, container, false);
        ViewPager2 viewPager = view.findViewById(R.id.viewPager);
        ImageAdapter adapter = new ImageAdapter(images);
        viewPager.setAdapter(adapter);
        return view;
    }
}
