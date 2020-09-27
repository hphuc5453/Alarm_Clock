package com.example.alarmclock.screen.main

import com.example.alarmclock.screen.TabViewModel

interface MainContract {

    interface View{
        fun showData(list: MutableList<TabViewModel>)
    }

    abstract class MainPresenter(){
        abstract fun getData()
    }
}