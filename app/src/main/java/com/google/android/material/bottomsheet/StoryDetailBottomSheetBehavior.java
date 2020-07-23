package com.google.android.material.bottomsheet;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.viewpager.widget.ViewPager;

import java.lang.ref.WeakReference;

/**
 * Story 详情页处理 {@link ViewPager}和嵌套{@link androidx.core.view.NestedScrollingChild}的交互.
 * 默认的{@link com.google.android.material.bottomsheet.BottomSheetBehavior} 只支持一个{@link androidx.core.view.NestedScrollingChild}, 在
 * {@link ViewPager} 中，需要适配支持
 */
@SuppressWarnings("unused")
@Keep
public class StoryDetailBottomSheetBehavior<V extends View> extends BottomSheetBehavior<V> {

  public StoryDetailBottomSheetBehavior() {}

  public StoryDetailBottomSheetBehavior(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @SuppressLint("VisibleForTests")
  @Override
  View findScrollingChild(final View view) {
    if (view == null || ViewCompat.isNestedScrollingEnabled(view)) {
      return view;
    }
    if (view instanceof ViewPager) {
      final ViewPager pager = (ViewPager) view;
      final View child = pager.getChildAt(pager.getCurrentItem());
      return findScrollingChild(child);
    } else {
      return super.findScrollingChild(view);
    }
  }

  @UiThread
  public void updateScrollingChild() {
    final View child = findScrollingChild(viewRef.get());
    nestedScrollingChildRef = new WeakReference<>(child);
  }

  @Override
  public boolean onInterceptTouchEvent(androidx.coordinatorlayout.widget.CoordinatorLayout parent, V child, MotionEvent event) {
    try {
      boolean b = super.onInterceptTouchEvent(parent, child, event);
      if (!b) {
        return shouldConsumeEvent(parent, child, event);
      }
    } catch (Throwable e) {
      return true;
    }
    return true;
  }

  @Override
  public boolean onTouchEvent(androidx.coordinatorlayout.widget.CoordinatorLayout parent, V child, MotionEvent event) {
    boolean b = super.onTouchEvent(parent, child, event);
    if (!b) {
      return shouldConsumeEvent(parent, child, event);
    }
    return true;
  }

  private boolean shouldConsumeEvent(androidx.coordinatorlayout.widget.CoordinatorLayout parent, V child, MotionEvent event) {
    final int y = (int) event.getRawY();
    // child显示时，吃掉child上面的事件
    if (getState() != STATE_HIDDEN && y < (child.getTop() + parent.getTop())) {
      if (event.getActionMasked() == MotionEvent.ACTION_UP) {
        setState(STATE_HIDDEN);
      }
      return true;
    }
    return false;
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static <V extends View> StoryDetailBottomSheetBehavior<V> from(@NonNull V view) {
    ViewGroup.LayoutParams params = view.getLayoutParams();
    if (!(params instanceof CoordinatorLayout.LayoutParams)) {
      throw new IllegalArgumentException("The view is not a child of CoordinatorLayout");
    }
    androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior behavior = ((androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams) params)
        .getBehavior();
    if (!(behavior instanceof StoryDetailBottomSheetBehavior)) {
      throw new IllegalArgumentException(
          "The view is not associated with BottomSheetBehavior");
    }
    return (StoryDetailBottomSheetBehavior<V>) behavior;
  }
}
