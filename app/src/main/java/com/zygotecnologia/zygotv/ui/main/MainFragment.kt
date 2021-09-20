package com.zygotecnologia.zygotv.ui.main

import SectionsPagerAdapter
import TAB_TITLES
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.zygotecnologia.zygotv.databinding.FragmentMainBinding

class MainFragment: Fragment() {

    private var _binding: FragmentMainBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val root = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TabLayout
        val tabLayout = binding.tabLayout
        // ViewPager2
        val viewPager = binding.viewPager


        /*
             Set Adapter for ViewPager inside this fragment using this Fragment,
            more specifically childFragmentManager as param
         */
        viewPager.adapter = SectionsPagerAdapter(this)

        // Bind tabs and viewpager
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = requireContext().getText(TAB_TITLES[position])
        }.attach()

        viewPager.isUserInputEnabled = false

    }
}