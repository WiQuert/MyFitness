package com.wiquert.myfitness.fragments

import android.os.Bundle
import android.os.CountDownTimer
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
import com.wiquert.myfitness.utils.TimeUtils
import pl.droidsonroids.gif.GifDrawable


class ExerciseFragment : Fragment() {
    private lateinit var binding: ExerciseFragmentBinding
    private var exerciseCounter = 0
    private var exList: ArrayList<ExerciseModel>? = null
    private val model: MainViewModel by activityViewModels()
    private var timer: CountDownTimer? = null


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
            val ex = exList?.get(exerciseCounter++) ?: return
            showExercise(ex)
            setExerciseType(ex)
        } else {
            Toast.makeText(activity, "Done", Toast.LENGTH_LONG).show()
        }

    }

    private fun showExercise(exercise: ExerciseModel) = with(binding) {
        imMain.setImageDrawable(GifDrawable(root.context.assets, exercise.image))
        tvName.text = exercise.name
    }

    private fun setExerciseType(exercise: ExerciseModel) {
        if(exercise.time.startsWith("x")) {
            binding.tvTime.text = exercise.time
        } else {
            startTimer(exercise)
        }
    }

        private fun startTimer(exercise: ExerciseModel) = with(binding) {
            progressBar.max = exercise.time.toInt() * 1000
            timer?.cancel()
            timer = object : CountDownTimer(exercise.time.toLong() * 1000,1){
                override fun onTick(restTime: Long) {
                    tvTime.text = TimeUtils.getTime(restTime)
                    progressBar.progress = restTime.toInt()

                }


                override fun onFinish() {
                    nextExercise()
                }

            }
                .start()

        }

                override fun onDetach() {
                    super.onDetach()
                    timer?.cancel()

        }





    companion object {

        @JvmStatic
        fun newInstance() = ExerciseFragment()

    }

}
