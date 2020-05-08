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
                MyCrewActivity mycrewactivity = new MyCrewActivity();
                return mycrewactivity;

            case 1:
                HomeActivity homeactivity = new HomeActivity();
            return homeactivity;

            case 2:
                UniversityActivity universityactivity = new UniversityActivity();
                return universityactivity;

            case 3:
                CategoryActivity categoryactivity = new CategoryActivity();
                return categoryactivity;

            case 4:
                UniteActivity uniteactivity = new UniteActivity();
                return uniteactivity;

            case 5:
                AllthingsActivity allthingsactivity = new AllthingsActivity();
                return allthingsactivity;


            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mtabCount;
    }
}
