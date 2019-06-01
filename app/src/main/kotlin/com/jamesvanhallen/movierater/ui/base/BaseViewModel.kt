package com.jamesvanhallen.movierater.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job

open class BaseViewModel : ViewModel() {
    private var jobs = mutableListOf<Job>()

    protected fun addCancellableJob(job: Job?) {
        if (job != null) {
            jobs.filter { it.isCompleted }
                .forEach { jobs.remove(it) }
            jobs.add(job)
        }
    }

    override fun onCleared() {
        super.onCleared()
        jobs.forEach { it.cancel() }
        jobs.clear()
    }
}