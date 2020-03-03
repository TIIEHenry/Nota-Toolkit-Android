package tiiehenry.android.fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class BaseFragmentAdapter<T> extends FragmentPagerAdapter {

    private List<T> dataList = new ArrayList<>();


    public BaseFragmentAdapter(FragmentManager fm) {
        super(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    public BaseFragmentAdapter(FragmentManager fm, List<T> items) {
        super(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        setDataList(items);
    }

    public BaseFragmentAdapter(FragmentManager fm, T[] items) {
        super(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        setDataList(Arrays.asList(items));
    }

    public List<T> getDataList() {
        return dataList;
    }

    public BaseFragmentAdapter<T> setDataList(List<T> items) {
        if (items != null && items.size() > 0) {
            dataList.clear();
            dataList.addAll(items);
            notifyDataSetChanged();
        }
        return this;
    }

    public void clear() {
        dataList.clear();
        notifyDataSetChanged();
    }

    public void add(@NonNull T item) {
        dataList.add(item);
        notifyDataSetChanged();
    }

    public void addAll(@NonNull List<T> items) {
        if (items.size() > 0) {
            dataList.addAll(items);
            notifyDataSetChanged();
        }
    }

    public boolean remove(@NonNull T item) {
        boolean b = dataList.remove(item);
        notifyDataSetChanged();
        return b;
    }

    public boolean contains(@NonNull T item) {
        return dataList.contains(item);
    }

    public int getPosition(@NonNull T item) {
        for (int i = 0; i < dataList.size(); i++) {
            if (dataList.get(i) == item)
                return i;
        }
        return -1;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @NonNull
    abstract public Fragment getItem(int position);

    @NonNull
    abstract public CharSequence getPageTitle(int position);
}
