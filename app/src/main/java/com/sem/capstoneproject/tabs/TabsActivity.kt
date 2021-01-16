package com.sem.capstoneproject.tabs

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.sem.capstoneproject.R
import kotlinx.android.synthetic.main.activity_tabs.*

class TabsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tabs)

        this.supportActionBar?.hide()
        tabs.bringToFront()

        // Tabs Customization
        tabs.setSelectedTabIndicatorColor(Color.WHITE)
        tabs.setBackgroundColor(
            ContextCompat.getColor(
                this,
                R.color.design_default_color_primary_variant
            )
        )

        // Number Of Tabs
        val numberOfTabs = 3

        // Show all Tabs in screen
        tabs.tabMode = TabLayout.MODE_FIXED

        tabs.isInlineLabel = false

        val adapter = SectionsPagerAdapter(supportFragmentManager, lifecycle, numberOfTabs)
        view_pager.adapter = adapter

        // Enable Swipe
        view_pager.isUserInputEnabled = true

        TabLayoutMediator(tabs, view_pager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Sneps"
                    tab.setIcon(R.drawable.favorite)
                }
                1 -> {
                    tab.text = "Create"
                    tab.setIcon(R.drawable.photo_camera)
                }
                2 -> {
                    tab.text = "Friends"
                    tab.setIcon(R.drawable.star_rate)
                }
            }
            // Change color of the icons
            tab.icon?.colorFilter =
                    BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                        Color.WHITE,
                        BlendModeCompat.SRC_ATOP
                    )
        }.attach()

        val tab = tabs.getTabAt(1)
        tab?.select()

        val parentLayout: View = findViewById(android.R.id.content)
        if(intent.getBooleanExtra("send", false)) {
            Snackbar.make(parentLayout, "Snep was sent!", Snackbar.LENGTH_LONG).show()
        }

    }

}