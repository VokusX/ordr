package net.ordrapp.ramen.ui.menu

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import net.ordrapp.ramen.data.MenuItem

class MenuPager(fragmentManager: FragmentManager, val categories: Map<String, List<MenuItem>>) : FragmentPagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
        val fragment = MenuFragment()
        fragment.arguments = Bundle().apply { putString("category", categories.entries.toTypedArray()[position].key) }

        return fragment
    }

    override fun getCount(): Int = categories.size

    override fun getPageTitle(position: Int): CharSequence? {
        return categories.entries.toTypedArray()[position].key
    }
}