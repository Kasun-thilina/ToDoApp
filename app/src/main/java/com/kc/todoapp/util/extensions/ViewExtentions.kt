package com.kc.todoapp.util.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

fun View.isVisibile(): Boolean = this.visibility == View.VISIBLE

fun View.isGone(): Boolean = this.visibility == View.GONE

fun View.isInvisible(): Boolean = this.visibility == View.INVISIBLE

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.makeInvisible() {
    visibility = View.INVISIBLE
}

fun View.makeGone() {
    visibility = View.GONE
}

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View =
    LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)

fun Button.disableButton() {
    isEnabled = false
    alpha = 0.3f
}

fun Button.enableButton() {
    isEnabled = true
    alpha = 1.0f
}

fun SwipeRefreshLayout.startRefresh() {
    this.isRefreshing = true
}

fun SwipeRefreshLayout.stopRefresh() {
    this.isRefreshing = false
}
