package com.jamesvanhallen.movierater.extensions

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

fun FragmentManager.replaceFragment(fragment: Fragment): Transaction {
    return Transaction(
        this,
        fragment,
        Transaction.Type.REPLACE
    )
}