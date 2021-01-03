package com.sem.capstoneproject.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sem.capstoneproject.ui.camera.CameraFragment
import com.sem.capstoneproject.ui.friends.FriendsFragment
import com.sem.capstoneproject.ui.sneps.SnepsFragment

class SectionsPagerAdapter(fm: FragmentManager, lifecycle: Lifecycle, private var numberOfTabs: Int)
    : FragmentStateAdapter(fm, lifecycle) {

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> {
                // # Snaps Fragment
                val bundle = Bundle()
                bundle.putString("SnepsFragment", "Sneps Fragment")
                val snapsFragment = SnepsFragment()
                snapsFragment.arguments = bundle
                return snapsFragment
            }
            1 -> {
                // # Camera Fragment
                val bundle = Bundle()
                bundle.putString("CameraFragment", "Camera Fragment")
                val cameraFragment = CameraFragment()
                cameraFragment.arguments = bundle
                return cameraFragment
            }
            2 -> {
                // # Friends Fragment
                val bundle = Bundle()
                bundle.putString("FriendsFragment", "Friends Fragment")
                val friendsFragment = FriendsFragment()
                friendsFragment.arguments = bundle
                return friendsFragment
            }
            else -> return CameraFragment()
        }
    }

    override fun getItemCount(): Int {
        return numberOfTabs
    }
}