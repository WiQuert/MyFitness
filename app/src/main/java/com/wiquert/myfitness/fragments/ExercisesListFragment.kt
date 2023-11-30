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
import com.wiquert.myfitness.adapters.ExerciseAdapter
import com.wiquert.myfitness.databinding.ExercisesListFragmentBinding
import com.wiquert.myfitness.utils.FragmentManager
import com.wiquert.myfitness.utils.MainViewModel


class ExercisesListFragment : Fragment() {
    private lateinit var binding: ExercisesListFragmentBinding
    private lateinit var adapter: ExerciseAdapter
    private val model: MainViewModel by activityViewModels()
    private var ab: ActionBar? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ExercisesListFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        model.mutableListExercise.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

    }


    private fun init() = with(binding) {
        ab = (activity as AppCompatActivity).supportActionBar
        ab?.title = getString(R.string.exercises)
        adapter = ExerciseAdapter()
        rcView.layoutManager = LinearLayoutManager(activity)
        rcView.adapter = adapter
        bStart.setOnClickListener {
            FragmentManager.setFragment(WaitingFragment.newInstance(),activity as AppCompatActivity)
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() = ExercisesListFragment()

    }


}