package com.nick_sib.beauty_radar.view_model.interactor.core

interface MasterClientInteractor<S> : Interactor {
    fun getData(): S
}