package com.example.m5lesson4_retrofitmvvm_rickandmortyapi.ui.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.m5lesson4_retrofitmvvm_rickandmortyapi.ui.fragments.onBoard.OnBoardPagingFragment
import com.example.m5lesson4_retrofitmvvm_rickandmortyapi.ui.fragments.onBoard.OnBoardPagingFragment.Companion.ARG_ONBOARD_POSITION

class OnBoardViewPagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int) = OnBoardPagingFragment().apply {
        arguments = Bundle().apply {
            putInt(ARG_ONBOARD_POSITION, position)
        }
    }
}