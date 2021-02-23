package ru.vincetti.test.cashpointssample.ui.main

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.vincetti.test.cashpointssample.ui.list.ListFragment
import ru.vincetti.test.cashpointssample.ui.map.MapFragment

class MapListAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return NUM_PAGES
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MapFragment()
            1 -> ListFragment()
            else -> error("Must be ONLY $NUM_PAGES")
        }
    }

    companion object {

        private const val NUM_PAGES = 2
    }
}
