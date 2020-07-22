package tiiehenry.android.fragment;

import android.os.Parcelable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 动态删改，不重建
 *
 * @param <T>
 */

public abstract class DynamicFragmentStateAdapter<T> extends FragmentNoStatePagerAdapter {

    private List<T> dataList = new ArrayList<>();


    public DynamicFragmentStateAdapter(FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    public DynamicFragmentStateAdapter(FragmentManager fm, List<T> items) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        setDataList(items);
    }

    public DynamicFragmentStateAdapter(FragmentManager fm, T[] items) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        setDataList(Arrays.asList(items));
    }

    public List<T> getDataList() {
        return dataList;
    }

    public DynamicFragmentStateAdapter<T> setDataList(List<T> items) {
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

    public boolean removeAll(@NonNull List<T> item) {
        boolean b = dataList.removeAll(item);
        notifyDataSetChanged();
        return b;
    }

    public boolean contains(@NonNull T item) {
        return dataList.contains(item);
    }

    public boolean contains(Fragment item) {
        for (int i = 0; i < dataList.size(); i++) {
            if (getItem(i) == item)
                return true;
        }
        return false;
    }

    public int getPosition(@NonNull T item) {
        return dataList.indexOf(item);
    }

    public int getPosition(@NonNull Fragment item) {
        for (int i = 0; i < dataList.size(); i++) {
            if (getItem(i) == item)
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

    @Override
    public int getItemPosition(@NonNull Object object) {
        if (dataList != null && dataList.size()==0) {
            return POSITION_NONE;
        }
        return POSITION_NONE;
    }

//    @Override
//    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
//        return view==object;
//    }
}
