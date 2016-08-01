package com.droidsonroids.materialmusicfacts.views.behavior;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import com.droidsonroids.materialmusicfacts.R;

/**
 * Created by Radek on 2016-08-01.
 */
public class MyFloatingActionButton extends FloatingActionButton {

    String direction;
    private boolean hidden = false;

    public MyFloatingActionButton(Context context) {
        super(context);
    }

    public MyFloatingActionButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context,attrs);
    }

    private void initialize(Context context, AttributeSet attrs){
        TypedArray at = context.obtainStyledAttributes(attrs, R.styleable.FloatingActionButtonBehavior);
        direction = at.getString(R.styleable.FloatingActionButtonBehavior_direction);
        at.recycle();
    }

    public void hideFab(){
            if(direction.equals("right") ){
                moveToRight();
            }else if(direction.equals("center")){
                super.hide();
            }
    }

    public void showFab(){
            if(direction.equals("right")){
                moveToLeft();
            }else if(direction.equals("center")){
                super.show();
            }
    }

    private void moveToRight(){
        Animation hide = new TranslateAnimation(0, 300, 0, 0);
        hide.setDuration(200);
        this.startAnimation(hide);
    }

    private void moveToLeft(){
        Animation show = new TranslateAnimation(300, 0, 0, 0);
        show.setDuration(200);
        this.startAnimation(show);
    }
}
