package com.wiquert.myfitness.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.wiquert.myfitness.adapters.ExerciseAdapter
import com.wiquert.myfitness.adapters.ExerciseModel
import com.wiquert.myfitness.databinding.ExerciseFragmentBinding
import androidx.fragment.app.activityViewModels
import com.wiquert.myfitness.databinding.ExercisesListFragmentBinding
import com.wiquert.myfitness.utils.FragmentManager
import com.wiquert.myfitness.utils.MainViewModel
import pl.droidsonroids.gif.GifDrawable


class ExerciseFragment : Fragment() {
    private lateinit var binding: ExerciseFragmentBinding
    private var exerciseCounter = 0
    private var exList: ArrayList<ExerciseModel>? = null
    private val model: MainViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ExerciseFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        model.mutableListExercise.observe(viewLifecycleOwner) {

            exList = it
            nextExercise()
        }
        binding.bNext.setOnClickListener {
            nextExercise()
        }

    }

    private fun nextExercise() {

        if (exerciseCounter < exList?.size!!) {
            val ex = exList?.get(exerciseCounter++)
            showExercise(ex)
        } else {
            Toast.makeText(activity, "Done", Toast.LENGTH_LONG).show()
        }

    }

    private fun showExercise(exercise: ExerciseModel?) = with(binding) {
        if (exercise == null) return@with
        imMain.setImageDrawable(GifDrawable(root.context.assets, exercise.image))
        tvName.text = exercise.name
    }


    companion object {

        @JvmStatic
        fun newInstance() = ExerciseFragment()

    }

}
