package Student;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class FragmentAdapter extends FragmentPagerAdapter {
    public FragmentAdapter(FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new homeFragment();
            case 1:
                return new CourseFragment();
            case 2:
                return new ProfileFragment();
            default:
                return new homeFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

//    @Override
//    public CharSequence getPageTitle(int position) {
//        String title = null;
//        switch (position) {
//            case 0:
//                title = "Home";
//                break;
//            case 1:
//                title = "Courses";
//                break;
//            case 2:
//                title = "Profile";
//                break;
//        }
//        return title;
//    }
}