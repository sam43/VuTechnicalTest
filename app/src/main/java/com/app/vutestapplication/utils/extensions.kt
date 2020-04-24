package com.app.vutestapplication.utils

import android.content.Context
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

inline fun <reified T : ViewModel> Fragment.getViewModel(noinline creator: (() -> T)? = null): T {
    return if (creator == null)
        ViewModelProvider(this).get(T::class.java)
    else
        ViewModelProvider(this, ViewModelFactory(creator)).get(T::class.java)
}

inline fun <reified T : ViewModel> FragmentActivity.getViewModel(noinline creator: (() -> T)? = null): T {
    return if (creator == null)
        ViewModelProvider(this).get(T::class.java)
    else
        ViewModelProvider(this, ViewModelFactory(creator)).get(T::class.java)
}

fun Context.loadUserAvatar(url: String?, holder: ImageView) {
    Glide.with(this)
        .load(url)
        .transition(DrawableTransitionOptions.withCrossFade(200))
        .apply(
            RequestOptions.placeholderOf(0)
                .error(android.R.drawable.ic_menu_gallery)
                .dontAnimate().diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
        )
        .into(holder)

}