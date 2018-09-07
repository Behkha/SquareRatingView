package com.ratintech.behkha.squareratingview;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class SquareRatingView extends LinearLayout implements View.OnClickListener{

    private ArrayList<CardView> mCards = new ArrayList<>();
    private int mCurrentRate = 0;
    private boolean mCanRate = true;
    private int mRatedSquareColor = R.color.rated_card_color;
    private int mUnRatedSquareColor = R.color.unrated_card_color;
    private float mCardRadius = 0;
    private int mCardElevation = 0;
    private OnRateChangeListener mOnRateChangeListener;

    public SquareRatingView(Context context) {
        this(context , null);
    }

    public SquareRatingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs , 0);
    }

    public SquareRatingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr );
        init();
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SquareRatingView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        inflate(getContext() , R.layout.square_rating_view_layout , this);

        CardView card1 = findViewById(R.id.card_1);
        CardView card2 = findViewById(R.id.card_2);
        CardView card3 = findViewById(R.id.card_3);
        CardView card4 = findViewById(R.id.card_4);
        CardView card5 = findViewById(R.id.card_5);

        card1.setOnClickListener(this);
        card2.setOnClickListener(this);
        card3.setOnClickListener(this);
        card4.setOnClickListener(this);
        card5.setOnClickListener(this);

        mCards.add(card1);
        mCards.add(card2);
        mCards.add(card3);
        mCards.add(card4);
        mCards.add(card5);

        changeRateUI(this.mCurrentRate);
        setRadius(0f);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setElevation(0f);
        }
    }

    public void setRateChangeListener(OnRateChangeListener rateChangeListener){
        this.mOnRateChangeListener = rateChangeListener;
    }
    public void setRateEnable(boolean canRate){
        this.mCanRate = canRate;
    }
    public void setRate(int rate){
        if (rate <= 0 || rate >= 6)
            return;
        mCurrentRate = rate;
        changeRateUI(rate);
        if (mOnRateChangeListener != null)
            mOnRateChangeListener.onRateChanged(rate);
    }
    public void setRatedSquareColor(@ColorRes int color){
        this.mRatedSquareColor = color;
    }
    public void setUnRatedSquareColor(@ColorRes int color){
        this.mUnRatedSquareColor = color;
    }
    public void setRadius(float radius){
        for (int i = 0; i < 5; i++) {
            mCards.get(i).setRadius(radius);
        }
    }
    public int getRate(){
        return this.mCurrentRate;
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setElevation(float elevation){
        for (int i = 0; i < 5 ; i++) {
            mCards.get(i).setElevation(elevation);
        }
    }


    private void changeRateUI(int rate){
        for (int i = 0; i < 5; i++) {
            if ( i <= rate - 1 )
                mCards.get(i).setCardBackgroundColor( getResources().getColor( mRatedSquareColor ) );
            else
                mCards.get(i).setCardBackgroundColor( getResources().getColor( mUnRatedSquareColor ) );
        }
    }
    @Override
    public void onClick(View v) {
        if (!mCanRate) return;
        int id = v.getId();
        if ( id == R.id.card_1 )
            mCurrentRate = 1;
        else if ( id == R.id.card_2 )
            mCurrentRate = 2;
        else if ( id == R.id.card_3 )
            mCurrentRate = 3;
        else if ( id == R.id.card_4 )
            mCurrentRate = 4;
        else if ( id == R.id.card_5 )
            mCurrentRate = 5;
        changeRateUI(mCurrentRate);
        if (mOnRateChangeListener != null)
            mOnRateChangeListener.onRateChanged(mCurrentRate);
    }

    public interface OnRateChangeListener{
        void onRateChanged(int rate);
    }
}
