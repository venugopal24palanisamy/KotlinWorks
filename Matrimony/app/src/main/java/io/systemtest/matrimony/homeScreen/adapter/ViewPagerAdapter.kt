package io.systemtest.matrimony.homeScreen.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.gson.Gson
import io.systemtest.matrimony.appDatabase.tables.ProfileTables
import io.systemtest.matrimony.homeScreen.HomeFragmentItem
import io.systemtest.matrimony.homeScreen.MainActivity

class ViewPagerAdapter(private var fm: MainActivity, private var list: ArrayList<ProfileTables>) :
    FragmentStateAdapter(fm) {
    override fun getItemCount(): Int {
        return list.size
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = HomeFragmentItem()
        val args = Bundle()
        args.putString("data", Gson().toJson(list[position]))
        fragment.arguments = args
        return fragment

    }
}