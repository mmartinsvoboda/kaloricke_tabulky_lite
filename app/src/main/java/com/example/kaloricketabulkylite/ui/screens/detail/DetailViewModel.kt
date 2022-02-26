package com.example.kaloricketabulkylite.ui.screens.detail

import androidx.lifecycle.ViewModel
import com.example.kaloricketabulkylite.data.repository.potravina.PotravinaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    val potravinaRepository: PotravinaRepository
) : ViewModel() {

}