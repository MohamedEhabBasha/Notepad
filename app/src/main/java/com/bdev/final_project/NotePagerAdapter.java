package com.bdev.final_project;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;


public class NotePagerAdapter extends FragmentStateAdapter {


    public NotePagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position==0) return new NoteFragment();
        else return new TodoFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
