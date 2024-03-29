package com.jamesvanhallen.movierater.extensions

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.jamesvanhallen.movierater.R

class Transaction(
    private val fm: FragmentManager,
    private val fragment: Fragment,
    private val type: Type
) {
    var addToBackStack: Boolean = false
    var enter: Int = 0
    var exit: Int = 0
    var popEnter: Int = 0
    var popExit: Int = 0

    fun commit() {
        val tr = fm.beginTransaction()
        if (enter != 0 && exit != 0) {
            if (popEnter != 0 && popExit != 0) {
                tr.setCustomAnimations(enter, exit, popEnter, popExit)
            } else {
                tr.setCustomAnimations(enter, exit)
            }
        }

        if (type == Type.REPLACE) {
            tr.replace(R.id.flContainer, fragment, fragment.javaClass.name)
        } else {
            tr.add(R.id.flContainer, fragment, fragment.javaClass.name)
        }

        if (addToBackStack) {
            tr.addToBackStack(fragment.javaClass.name)
        }
        tr.commit()
    }

    enum class Type {
        ADD, REPLACE
    }
}