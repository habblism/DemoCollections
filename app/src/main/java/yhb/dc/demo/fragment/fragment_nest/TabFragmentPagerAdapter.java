package yhb.dc.demo.fragment.fragment_nest;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yhb on 18-4-21.
 */

public class TabFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<NestedFragment> mFragments = new ArrayList<>();

    public TabFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        addFragments();
    }

    private void addFragments() {
        for (int i = 1; i <= 4; i++) {
            mFragments.add(NestedFragment.newInstance("第 " + i + " 个 Fragment"));
        }
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragments.get(position).getName();
    }
}
