package com.filmster.application.view;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * StackOverFlow onTouchListener wrapper for the mediaCard in MainView.
 * @author Magnus
 */
public class GestureHelper implements View.OnTouchListener {

    private final GestureDetector mGestureDetector;

    public GestureHelper(Context context) {
        mGestureDetector = new GestureDetector(context, new GestureListener(this));
    }

    public void onSwipeRight() {
    };

    public void onSwipeLeft() {
    };

    public void onSwipeTop() {
    };

    public void onSwipeBottom() {
    };

    public void onDoubleTap() {
    };

    public void onClick() {
    };

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }

    private static final class GestureListener extends GestureDetector.SimpleOnGestureListener {

        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;
        private GestureHelper mHelper;

        public GestureListener(GestureHelper helper) {
            mHelper = helper;
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            mHelper.onClick();
            return true;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            mHelper.onDoubleTap();
            return true;
        }

        /**
         * Denna får nån kommentera(MAGNUS)
         * Uses METH to figure out whats happening??
         * @param e1
         * @param e2
         * @param velocityX
         * @param velocityY
         * @return
         */
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            boolean result = false;
            try {
                float diffY = e2.getY() - e1.getY();
                float diffX = e2.getX() - e1.getX();
                if (Math.abs(diffX) > Math.abs(diffY)) {
                    if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffX > 0) {
                            mHelper.onSwipeRight();
                        } else {
                            mHelper.onSwipeLeft();
                        }
                    }
                } else {
                    if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffY > 0) {
                            mHelper.onSwipeBottom();
                        } else {
                            mHelper.onSwipeTop();
                        }
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return result;
        }
    }
}