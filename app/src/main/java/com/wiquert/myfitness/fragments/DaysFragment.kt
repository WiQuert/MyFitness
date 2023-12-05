package com.wiquert.myfitness.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.wiquert.myfitness.R
import com.wiquert.myfitness.adapters.DayModel
import com.wiquert.myfitness.adapters.DaysAdapter
import com.wiquert.myfitness.adapters.ExerciseModel
import com.wiquert.myfitness.databinding.FragmentDaysBinding
import com.wiquert.myfitness.utils.FragmentManager
import com.wiquert.myfitness.utils.MainViewModel


class DaysFragment : Fragment(), DaysAdapter.Listener {
    private lateinit var binding: FragmentDaysBinding
    private val model: MainViewModel by activityViewModels()
    private var ab: ActionBar? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDaysBinding.inflate(inflater, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRcView()
    }

    private fun initRcView() = with(binding) {
        val adapter = DaysAdapter(this@DaysFragment)
        ab = (activity as AppCompatActivity).supportActionBar
        ab?.title = getString(R.string.fitness_days)
        rcViewDays.layoutManager = LinearLayoutManager(activity as AppCompatActivity)
        rcViewDays.adapter = adapter
        adapter.submitList(fillDaysArray())
    }



     private fun fillDaysArray() : ArrayList<DayModel> {

        val tArray = ArrayList<DayModel>()
        resources.getStringArray(R.array.day_exercises).forEach {
            tArray.add(DayModel(it, 0, false))
        }
        return tArray
    }

    private fun fillExerciseList(day: DayModel) {
        val tempList = ArrayList<ExerciseModel>()
        day.exercises.split(",").forEach {
            val exerciseList = resources.getStringArray(R.array.exercises)
            val exercise = exerciseList[it.toInt()]
            val exerciseArray =  exercise.split("|")
            tempList.add(ExerciseModel(exerciseArray[0], exerciseArray[1], false, exerciseArray[2]))
        }
        model.mutableListExercise.value = tempList
    }

    companion object {

        @JvmStatic
        fun newInstance() = DaysFragment()

    }

    override fun onClick(day: DayModel) {
        fillExerciseList(day)
        model.currentDay = day.dayNumber
        FragmentManager.setFragment(ExercisesListFragment.newInstance(), activity as AppCompatActivity)
    }


}