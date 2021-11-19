package com.sample.pokedex

import android.widget.AbsListView.OnScrollListener.SCROLL_STATE_IDLE
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class RecyclerViewPaginator() : RecyclerView.OnScrollListener() {

    var currentPage: Long = 0L

    /*
     * This variable is used to set
     * the threshold to start loading the new
     * items before reaching the last element.
     * */
    private val threshold = 2

    /*
     * This variable is used to ensure
     * that the app is notified only once
     * since we use the scrollListener.
     * */
    private var endWithAuto = false

    abstract val isLastPage: Boolean

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        if (newState == SCROLL_STATE_IDLE) {
            recyclerView.layoutManager?.let { layoutManager ->

                var firstVisibleItemPosition = 0
                if (layoutManager is LinearLayoutManager) {
                    firstVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                } else if (layoutManager is GridLayoutManager) {
                    firstVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                }

                //if(isLoading()) return
                if (isLastPage) return

                if (layoutManager.childCount + firstVisibleItemPosition + threshold >= layoutManager.itemCount) {
                    loadMore()
                } else {
                    endWithAuto = false
                }
            }
        }
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
    }

    abstract fun loadMore()
}