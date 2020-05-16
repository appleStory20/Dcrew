package g.sns_test;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class TabPagerAdapter extends FragmentStatePagerAdapter {

    private int mtabCount;


    public TabPagerAdapter(@NonNull FragmentManager fm, int tabCount) {
        super(fm);
        this.mtabCount =tabCount;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                MyCrewFragment mycrewfragment= new MyCrewFragment();
                return mycrewfragment;

            case 1:
                HomeFragment homefragment = new HomeFragment();
                return homefragment;

            case 2:
                UniversityFragment universitfragment = new UniversityFragment();
                return universitfragment;

            case 3:
                CategoryFragment categoryfragment = new CategoryFragment();
                return categoryfragment;

            case 4:
                UniteFragment unitefragmnet = new UniteFragment();
                return unitefragmnet;

            case 5:
                AllthingsFragment allthingsfragment = new AllthingsFragment();
                return allthingsfragment;


            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mtabCount;
    }
}
