package com.lqn.dictionary.callback.translate

interface OnItemListener {
    fun onItemClick(position: Int)

    fun onItemSound(position: Int)

    fun onItemStar(position: Int)
}