package com.simple.creact.simple.app.presentation.bindingadapter;

import android.databinding.BindingAdapter;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.simple.creact.simple.app.SimpleApplication;


/**
 * @author:YJJ
 * @date:2016/3/14
 * @email:yangjianjun@117go.com
 */
public class ImageAdapter {

    @BindingAdapter(value = {"imgUrl", "placeHolder"})
    public void setImageUrl(ImageView imageView, String url, @DrawableRes int placeHolder) {
        SimpleApplication.getPicasso()
                .load(url)
                .placeholder(placeHolder)
                .into(imageView);
    }
    @BindingAdapter(value = {"imgUrl"})
    public void setImageUrl(ImageView imageView, String url) {
        SimpleApplication.getPicasso()
                .load(url)
                .into(imageView);
    }
}
