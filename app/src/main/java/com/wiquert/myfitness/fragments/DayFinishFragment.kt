package com.wiquert.myfitness.fragments

import android.app.Activity
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.wiquert.myfitness.databinding.DayFinishFragmentBinding
import com.wiquert.myfitness.databinding.WaitingFragmentBinding
import com.wiquert.myfitness.utils.FragmentManager
import com.wiquert.myfitness.utils.TimeUtils
import pl.droidsonroids.gif.GifDrawable


class DayFinishFragment : Fragment() {
    private lateinit var binding: DayFinishFragmentBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DayFinishFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imMain.setImageDrawable(GifDrawable((activity as AppCompatActivity).assets, "congrats-congratulations.gif"))
        binding.bDone.setOnClickListener {
            FragmentManager.setFragment(DaysFragment.newInstance(), activity as AppCompatActivity)
        }


    }



    companion object {

        @JvmStatic
        fun newInstance() = DayFinishFragment()

    }


}