package com.bdev.final_project.first_page;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.bdev.final_project.first_page.Login_Fragment;
import com.bdev.final_project.first_page.Register_Fragment;

public class ViewPagerAdapter extends FragmentStateAdapter {


    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position==0) return new Register_Fragment();
        else return new Login_Fragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
