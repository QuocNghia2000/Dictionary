package com.lqn.dictionary.callback.recentword

interface OnItemListener {
    fun onItemClick(position: Int)

    fun onItemCheck(position: Int)

    fun onItemSound(position: Int)

    fun onItemStar(position: Int)
}