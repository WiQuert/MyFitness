package com.wiquert.myfitness.utils

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wiquert.myfitness.adapters.ExerciseModel

class MainViewModel : ViewModel() {
    val mutableListExercise = MutableLiveData<ArrayList<ExerciseModel>>()


}