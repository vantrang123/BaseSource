package com.tom.template.extensions

import androidx.recyclerview.widget.RecyclerView
import com.tom.template.utils.EndlessRecyclerOnScrollListener

fun RecyclerView.onLoadMore(block: () -> Unit) {
    this.clearOnScrollListeners()
    this.addOnScrollListener(object: EndlessRecyclerOnScrollListener(){
        override fun onLoadMore() {
            block.invoke()
        }

    })
}