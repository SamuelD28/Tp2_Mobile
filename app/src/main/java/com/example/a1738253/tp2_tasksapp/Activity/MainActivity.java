package com.example.a1738253.tp2_tasksapp.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.a1738253.tp2_tasksapp.Fragment.HomeFragment;
import com.example.a1738253.tp2_tasksapp.Fragment.TaskCreateFragment;
import com.example.a1738253.tp2_tasksapp.Fragment.TaskDetailFragment;
import com.example.a1738253.tp2_tasksapp.R;

/** Main activity that gets launched when the application loads.
 * This is where we initialise the view pager that will hold all our main fragment.
 */
public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;               //Viewpager used to navigate trough the pages
    private PagerAdapter mViewPagerAdapter;     //Used in combination with the view pager. The adapter handled the viewpager behavior

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instantiate a ViewPager and a PagerAdapter.
        mViewPager = findViewById(R.id.activity_view_pager);
        mViewPagerAdapter = new MainActivityPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mViewPagerAdapter);
    }

    /** Anonymous Child class that implement the fragment state adapter.
     * Since we dont need to access this outside the class
     * we declare it here
     */
    private class MainActivityPagerAdapter extends FragmentStatePagerAdapter {
        private static final int NUM_PAGES = 3;     //Specifies the number of page we want in the view pager
        /** Consturctor method
         * @param fm Fragment manager that the class needs
         */
        public MainActivityPagerAdapter(FragmentManager fm) {
            super(fm);
        }


        /** Method that return the appropriate Fragment in the view pager navigation.
         * @param position Position of the view pager.
         * @return Fragment to be returned
         */
        @Override
        public Fragment getItem(int position) {
            Toast.makeText(MainActivity.this, String.valueOf(position), Toast.LENGTH_SHORT).show();
            switch (position) {
                case 0:
                    return new HomeFragment();
                case 1:
                    return new TaskDetailFragment();
                case 2:
                    return new TaskCreateFragment();
                default:
                    return null;
            }
        }

        /** Method that return the number of pages hold inside the view pager
         * @return
         */
        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}

