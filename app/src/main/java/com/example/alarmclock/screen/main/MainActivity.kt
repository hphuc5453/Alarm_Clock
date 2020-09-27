package com.example.alarmclock.screen.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.SparseArray
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.alarmclock.R
import com.example.alarmclock.screen.TabViewModel
import com.example.alarmclock.core.CoreActivity
import com.example.alarmclock.screen.home.HomeFragment
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity(), MainContract.View {

    private lateinit var tlTabs: TabLayout
    private lateinit var vpTabs: ViewPager

    companion object {
        val tabData = mutableListOf<TabViewModel>()
    }

    override fun showData(list: MutableList<TabViewModel>) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        CoreActivity.setTransparentStatusBar(this)

        tlTabs = this.findViewById(R.id.tlTabs)
        vpTabs = this.findViewById(R.id.vpPager)
        getData()
        initTabData()
    }

    private fun getData(){
        tabData.add(
            TabViewModel(
                id = 1,
                name = "Báo thức"
            )
        )
        tabData.add(
            TabViewModel(
                id = 2,
                name = "Đông hồ"
            )
        )
        tabData.add(
            TabViewModel(
                id = 3,
                name = "Bấm giờ"
            )
        )
        tabData.add(
            TabViewModel(
                id = 4,
                name = "Hẹn giờ"
            )
        )
    }

    private fun initTabData(){
        val adapter =
            Adapter(this.supportFragmentManager)
        vpTabs.offscreenPageLimit = 1
        vpTabs.adapter = adapter
        tlTabs.setupWithViewPager(vpTabs)
        tabData.forEachIndexed { index, tabViewModel ->
            tlTabs.getTabAt(index)?.text = tabViewModel.name
        }
    }

    private val onActionClick = View.OnClickListener {
        when (it.id) {
        }
    }

    internal class Adapter(fm: FragmentManager) :
        FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        private var mAvailableFragments: SparseArray<Fragment> = SparseArray()
        override fun getItem(position: Int): Fragment {
            var f: Fragment? = null
            val data = tabData[position]
            when (data.id) {
                1 -> {
                    f = HomeFragment.newInstance()
                }
                2 -> {
                    f = HomeFragment.newInstance()
                }
                3 -> {
                    f = HomeFragment.newInstance()
                }
                4 -> {
                    f = HomeFragment.newInstance()
                }
            }
            return f!!
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val fragment = super.instantiateItem(container, position)
            mAvailableFragments.put(position, fragment as Fragment)
            return fragment
        }

        override fun destroyItem(container: ViewGroup, position: Int, any: Any) {
            super.destroyItem(container, position, any)
            mAvailableFragments.remove(position)
        }

        override fun getCount(): Int = tabData.size
    }
}