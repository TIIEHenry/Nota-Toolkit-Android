package nota.android.app.fragment.page;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;

import nota.android.R;

/**
 * 这个轻松实现activity动画般的Fragment切换
 */
public class FragmentPageAnimator {
    private final IFragmentPage fragmentPage;
    private View coverView;

    public FragmentPageAnimator(IFragmentPage fragmentPage) {
        this.fragmentPage = fragmentPage;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Fragment fragment = fragmentPage.get();
        Context context = fragment.requireContext();
        FrameLayout frameLayout = new FrameLayout(context);
        View view = fragmentPage.onProvideCreateView(inflater, frameLayout, savedInstanceState);
        coverView = new View(context);
        coverView.setVisibility(View.GONE);
        coverView.setBackgroundColor(0xff000000);
        frameLayout.addView(view, -1, -1);
        frameLayout.addView(coverView, -1, -1);
//        frameLayout.setBackgroundColor(context.getColor(R.color.fd_sys_color_surface_container_lowest_default));

        return frameLayout;
    }

    /**
     * 退回上一级，旧的fragment显示cover后隐藏
     * new fragment enter
     * show cover in old fragment
     *
     * @param fragment
     */
    public void startPageEnterAnim(Fragment fragment) {
        Context context = fragment.requireContext();
        Animation animationCover = AnimationUtils.loadAnimation(context, R.anim.exit_cover);
        animationCover.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                coverView.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        coverView.startAnimation(animationCover);
        coverView.setVisibility(View.VISIBLE);
    }

    /**
     * 进入下一级，旧的fragment逐渐显示cover
     * old fragment exit
     * slowly cover in old fragment and keep
     */
    public void startPageExitAnim() {
        Fragment fragment = fragmentPage.get();
        Context context = fragment.requireContext();
        Animation animationCover = AnimationUtils.loadAnimation(context, R.anim.enter_cover);
        animationCover.setFillAfter(true);
        coverView.startAnimation(animationCover);
        coverView.setVisibility(View.VISIBLE);
    }

}
