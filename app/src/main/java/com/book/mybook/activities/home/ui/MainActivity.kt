package com.book.mybook.activities.home.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.book.mybook.R
import com.book.mybook.R.anim.design_bottom_sheet_slide_in

class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFirstFragment(HomeFragment())
    }

    @SuppressLint("PrivateResource")
    fun replaceFragment(fragment: Fragment) {
        val name = fragment.javaClass.name
        val fragmentPopped = supportFragmentManager.popBackStackImmediate(name, 0)
        if(!fragmentPopped) {
            supportFragmentManager.beginTransaction()
                .setCustomAnimations(
                    design_bottom_sheet_slide_in,
                    R.anim.design_bottom_sheet_slide_out
                )
                .replace(R.id.fragment_container, fragment, fragment.javaClass.simpleName)
                .addToBackStack(name)
                .commit()
        }
    }

    @SuppressLint("PrivateResource")
    fun replaceFirstFragment(fragment: Fragment) {
        val name = fragment.javaClass.name
        val fragmentPopped = supportFragmentManager.popBackStackImmediate(name, 0)
        if(!fragmentPopped) {
            supportFragmentManager.beginTransaction()
                .setCustomAnimations(
                    design_bottom_sheet_slide_in,
                    R.anim.design_bottom_sheet_slide_out
                )
                .replace(R.id.fragment_container, fragment, fragment.javaClass.simpleName)
                .commit()
        }
    }
}
