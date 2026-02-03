package nota.android.app.fragment.page;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import nota.android.R;
import nota.android.app.fragment.page.FragmentPageAnimator;

public interface IFragmentPage {
    int getContainerId();

    Fragment get();

    FragmentPageAnimator getPageAnimator();

    View onProvideCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);

    default void onBackToFragment() {
    }

    default void startFragmentPage(Fragment childFragment, String tag) {
        FragmentPageAnimator pageHelper = getPageAnimator();
        Fragment fragment = get();

        FragmentManager parentFragmentManager = fragment.getParentFragmentManager();
        parentFragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                if (fragment.isVisible()) {
                    Fragment fragmentById = parentFragmentManager.findFragmentById(getContainerId());
                    if (fragmentById != fragment) {
                        return;
                    }
                    parentFragmentManager.removeOnBackStackChangedListener(this);
                    pageHelper.startPageEnterAnim(fragment);
                    onBackToFragment();
                }
            }
        });
        getPageAnimator().startPageExitAnim();
        FragmentTransaction fragmentTransaction = parentFragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.mz_activity_to_next_open_enter, R.anim.mz_activity_to_next_open_exit, R.anim.mz_activity_to_next_close_enter, R.anim.mz_activity_to_next_close_exit)
                .add(getContainerId(), childFragment, tag)
                .hide(fragment)
                .addToBackStack(null)
                .commit();
    }

    default void startFragmentPage(Fragment childFragment) {
        startFragmentPage(childFragment, null);
    }


    default FragmentActivity getFragmentActivity() {
        return get().getActivity();
    }
}
