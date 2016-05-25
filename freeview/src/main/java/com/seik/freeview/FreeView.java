package com.seik.freeview;


import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

public class FreeView extends FrameLayout {

    float initialX, initialY= 0;

    boolean rotate = false;

    public FreeView(Context context) {
        super(context);
    }

    public FreeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FreeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.FreeView,
                0, 0);

        try {
            rotate = typedArray.getBoolean(R.styleable.FreeView_rotable, false);
        } finally {
            typedArray.recycle();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return handleTouch(event);
    }

    public void setRotate(boolean rotate) {
        this.rotate = rotate;
    }

    boolean handleTouch(MotionEvent event) {
        init(event);
        final int action = MotionEventCompat.getActionMasked(event);

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                //Pass
                break;
            case MotionEvent.ACTION_UP:
                //Pass
                break;
            case MotionEvent.ACTION_MOVE: {
                float newX = event.getRawX();
                float newY = event.getRawY();

                float finalX = newX - initialX;
                float finalY = newY  - initialY;

                ViewCompat.setTranslationX(this, finalX);

                ViewCompat.setTranslationY(this, finalY);

                break;
            }
        }
        return true;
    }

    public boolean isInit() {
        return initialX == 0 && initialY == 0;
    }

    public void init(MotionEvent event){
        if(isInit()) {
            initialX = event.getRawX();
            initialY = event.getRawY();
        }
    }
}