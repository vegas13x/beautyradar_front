package com.nick_sib.beauty_radar.view_model.interactor.core

interface BottomSheetInteractor<S> : Interactor {
    suspend fun getService(): S
}