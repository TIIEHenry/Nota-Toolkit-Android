package tiiehenry.android.fragment;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class BaseFragmentAdapter<T> extends FragmentPagerAdapter {

    private final FragmentManager fragmentManager;
    private List<T> dataList = new ArrayList<>();


    public BaseFragmentAdapter(FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.fragmentManager=fm;
    }

    public BaseFragmentAdapter(FragmentManager fm, List<T> items) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.fragmentManager=fm;
        setDataList(items);
    }

    public BaseFragmentAdapter(FragmentManager fm, T[] items) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.fragmentManager=fm;
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
            if (getItem(i)==item)
                return true;
        }
        return false;
    }

    public int getPosition(@NonNull T item) {
        return dataList.indexOf(item);
    }
    public int getPosition(@NonNull Fragment item) {
        for (int i = 0; i < dataList.size(); i++) {
            if (getItem(i)==item)
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

//
//
//    @Override
//    public int getItemPosition(@NonNull Object object) {
//        if (!((Fragment) object).isAdded() || !contains((Fragment) object)) {
//            return POSITION_NONE;
//        }
//        return getPosition((Fragment)object);
//    }
//
//    @NonNull
//    @Override
//    public Object instantiateItem(@NonNull ViewGroup container, int position) {
//        Fragment instantiateItem = ((Fragment) super.instantiateItem(container, position));
//        Fragment item = getItem(position);
//        if (instantiateItem == item) {
//            return instantiateItem;
//        } else {
//            //如果集合中对应下标的fragment和fragmentManager中的对应下标的fragment对象不一致，那么就是新添加的，所以自己add进入；这里为什么不直接调用super方法呢，因为fragment的mIndex搞的鬼，以后有机会再补一补。
//            fragmentManager.beginTransaction().add(container.getId(), item).commitNowAllowingStateLoss();
//            return item;
//        }
//    }
//
//    @Override
//    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        Fragment fragment = (Fragment) object;
//        //如果getItemPosition中的值为PagerAdapter.POSITION_NONE，就执行该方法。
//        if (contains(fragment)) {
//            super.destroyItem(container, position, fragment);
//            return;
//        }
//        //自己执行移除。因为mFragments在删除的时候就把某个fragment对象移除了，所以一般都得自己移除在fragmentManager中的该对象。
//        fragmentManager.beginTransaction().remove(fragment).commitNowAllowingStateLoss();
//
//    }

}
