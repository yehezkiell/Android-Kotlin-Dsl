package com.example.home.ui

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import java.lang.RuntimeException

class HomeViewModel : ViewModel() {
    private val numberOne = MutableLiveData<Int>()
    private val numberTwo = MutableLiveData<Int>()

    val sumResult = MediatorLiveData<Int>().apply {
        postValue(0)
    }

    val sumData = MutableLiveData<String>()
    val sumData2 = MutableLiveData<String>()

    init {
        sumResult.addSource(numberOne) {
            sumResult.value = sumResult.value?.plus(it)
        }

        sumResult.addSource(numberTwo) {
            sumResult.value = sumResult.value?.plus(it)
        }
    }

    fun addNumberOne() {
        numberOne.value = 1
    }

    fun addNumberTwo() {
        numberOne.value = 2
    }

    fun getInfo(): String {
        return "Hai"
    }

    fun getProduct() {
        //SupervisorScope will not cancel children when on of them error
        viewModelScope.launch {
            supervisorScope {
                val asyncData2 = async {
                    data2()
                }

                val asyncData1 = async {
                    data1()
                }
                try {
                    sumData.postValue(asyncData1.await())
                } catch (e: Throwable) {
                    sumData.postValue("error")
                }
                sumData2.postValue(asyncData2.await())
            }
        }
    }

    //CoroutineScope will cancel children when on of them error, and we need special try catch outside the coroutinescope
    fun getProductCoroutineScope() {
        viewModelScope.launch {
            coroutineScope {
                val asyncData2 = async {
                    data2()
                }

                val asyncData1 = async {
                    data1()
                }
                try {
                    sumData.postValue(asyncData1.await())
                } catch (e: Throwable) {
                    sumData.postValue("Error")
                }

//                    sumData2.postValue(asyncData2.await())
            }
        }


    }


    fun data1(): String {
//        throw RuntimeException()
        return "1"
    }

    fun data2(): String {
        return "2"
    }
}