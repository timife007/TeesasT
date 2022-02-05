package com.timife.teesast.main.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.timife.teesast.R
import com.timife.teesast.databinding.FragmentDashboardBinding


class DashboardFragment : Fragment() {
    private lateinit var dashboardBinding: FragmentDashboardBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dashboardBinding = FragmentDashboardBinding.inflate(inflater)
        return dashboardBinding.root
    }

}