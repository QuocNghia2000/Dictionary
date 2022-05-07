package com.lqn.dictionary.callback.search

interface OnItemListener {
    fun onItemClick(position: Int)

    fun onItemDelete(position: Int)

    fun onItemPick(position: Int)
}