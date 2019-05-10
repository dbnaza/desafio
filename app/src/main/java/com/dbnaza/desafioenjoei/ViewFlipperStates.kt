package com.dbnaza.desafioenjoei

import androidx.annotation.IntDef

object ViewFlipperStates {
    const val STATE_LOADING = 0
    const val STATE_SUCCESS = 1
    const val STATE_ERROR = 2

    @IntDef(STATE_LOADING, STATE_SUCCESS, STATE_ERROR)
    @Retention()
    annotation class States
}