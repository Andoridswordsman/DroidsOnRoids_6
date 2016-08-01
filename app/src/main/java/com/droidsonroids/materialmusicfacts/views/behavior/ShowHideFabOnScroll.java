package com.droidsonroids.materialmusicfacts.views.behavior;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

import com.droidsonroids.materialmusicfacts.R;

/**
 * Created by Radek on 2016-08-01.
 */
public class ShowHideFabOnScroll extends CoordinatorLayout.Behavior<MyFloatingActionButton>{

    String direction;

    public ShowHideFabOnScroll() {
        super();
    }

    public ShowHideFabOnScroll(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, MyFloatingActionButton child, View directTargetChild, View target, int nestedScrollAxes) {
        child.hideFab();
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, MyFloatingActionButton child, View target) {
        super.onStopNestedScroll(coordinatorLayout, child, target);
        child.showFab();
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, MyFloatingActionButton child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
    }
}
