package com.wiquert.myfitness.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wiquert.myfitness.R
import com.wiquert.myfitness.adapters.DayModel
import com.wiquert.myfitness.databinding.FragmentDaysBinding


class DaysFragment : Fragment() {
    private lateinit var binding: FragmentDaysBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDaysBinding.inflate(inflater, container, false)

        return binding.root
    }


    private fun fillDaysArray() : ArrayList<DayModel> {

        val tArray = ArrayList<DayModel>()
        resources.getStringArray(R.array.day_execrises).forEach {
            tArray.add(DayModel(it, false))
        }
        return tArray
    }

    companion object {

        @JvmStatic
        fun newInstance() = DaysFragment()

    }


}