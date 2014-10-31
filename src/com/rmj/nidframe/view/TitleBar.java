package com.rmj.nidframe.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.rmj.nidframe.R;

/**
 * 通用TitleBar
 * Created by G11 on 2014/9/15.
 */
public class TitleBar extends RelativeLayout {

    Button mLeftButton;
    ImageView mLeftImageView;
    TextView mLeftTextView;
    TextView mTitleTV;
    ImageView mTitleImageView;
    Button mRightButton;
    RelativeLayout mRlTitleBar;

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.view_title_bar, this, true);

        mRlTitleBar = (RelativeLayout) findViewById(R.id.rl_titlebar);
        mLeftButton = (Button) findViewById(R.id.button_title_bar_left);
        mLeftImageView=(ImageView) findViewById(R.id.imageview_title_bar_left);
        mLeftTextView=(TextView) findViewById(R.id.textview_title_bar_left);
        mRightButton = (Button) findViewById(R.id.button_title_bar_right);
        mTitleTV = (TextView) findViewById(R.id.tv_title_bar_title);
        mTitleImageView = (ImageView) findViewById(R.id.iv_title_bar_title);
    }


    public void setTitleTextColor(int color){
        mTitleTV.setTextColor(color);
    }

    /**
     * 设置titlebar背景颜色
     *
     * @param color
     */
    public void setTitlbarBcakgroundColor(int color) {
        mRlTitleBar.setBackgroundColor(color);
    }

    /**
     * 显示文本title
     *
     * @param title
     */
    public void setTitleText(String title) {
        mTitleTV.setText(title);
    }

    /**
     * 显示文本title
     *
     * @param titleId
     */
    public void setTitleText(int titleId) {
        mTitleTV.setText(titleId);
    }

    /**
     * 显示或隐藏文本Title
     */
    public void showTitleText(boolean show){
        if (show) {
            mTitleTV.setVisibility(View.VISIBLE);
        } else {
            mTitleTV.setVisibility(View.GONE);
        }
    }
    /**
     * 显示图片title
     *
     * @param resId
     */
    public void setTitleImage(int resId) {
        mTitleImageView.setImageResource(resId);
    }

    /**
     * 显示或隐藏文本Title
     */
    public void showTitleImage(boolean show){
        if (show) {
            mTitleImageView.setVisibility(View.VISIBLE);
        } else {
            mTitleImageView.setVisibility(View.GONE);
        }
    }

    public void setLeftImageViewBackground(int resId){
        mLeftImageView.setImageResource(resId);
    }

    /**
     * 设置左边按钮的图片
     *
     * @param resId
     */
    public void setLeftBackground(int resId) {
        mLeftButton.setBackgroundResource(resId);
    }

    /**
     * 设置左边按钮的Text
     *
     * @param resId
     */
    public void setLeftText(int resId) {
        mLeftButton.setText(resId);
    }

    public void setLeftTextViewText(String text){
        mLeftTextView.setText(text);
    }


    public void setLeftImageViewVisibility(int visibility) {
        mLeftImageView.setVisibility(visibility);
    }

    /**
     * 设置左边按钮的显示状态
     *
     * @param visibility
     */
    public void setLeftButtonVisibility(int visibility) {
        mLeftButton.setVisibility(visibility);
    }

    public void setLeftTextViewVisibility(int visibility){
        mLeftTextView.setVisibility(visibility);
    }


    public void setOnLeftImageViewClickListener(OnClickListener listener) {
        mLeftImageView.setVisibility(View.VISIBLE);
        mLeftImageView.setOnClickListener(listener);
    }

    /**
     * 设置左边按钮的点击事件
     *
     * @param listener
     */
    public void setOnLeftClickListener(OnClickListener listener) {
        mLeftButton.setVisibility(View.VISIBLE);
        mLeftButton.setOnClickListener(listener);
    }

    /**
     * 设置右边按钮的图片
     *
     * @param resId
     */
    public void setRightBackground(int resId) {
        mRightButton.setBackgroundResource(resId);
    }


    /**
     * 设置右边按钮的Text
     *
     * @param resId
     */
    public void setRightText(int resId) {
        mRightButton.setText(resId);
    }

    public void setRightTextSize(int size){
        mRightButton.setTextSize(size);
    }

    /**
     * 设置右边按钮的显示状态
     *
     * @param visibility
     */
    public void setRightButtonVisibility(int visibility) {
        mRightButton.setVisibility(visibility);
    }

    public void setRightTextColor(int resId){
        mRightButton.setTextColor(resId);
    }

    /**
     * 设置右边按钮的点击事件
     *
     * @param listener
     */
    public void setOnRightClickListener(OnClickListener listener) {
        mRightButton.setVisibility(View.VISIBLE);
        mRightButton.setOnClickListener(listener);
    }
}
