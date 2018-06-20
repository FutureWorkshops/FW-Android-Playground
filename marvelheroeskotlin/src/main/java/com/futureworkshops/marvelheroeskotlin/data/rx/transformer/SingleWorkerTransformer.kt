/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroeskotlin.data.rx.transformer

import com.futureworkshops.marvelheroeskotlin.data.rx.SchedulersProvider
import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.SingleTransformer

/**
 * Created by dimitrios on 20/06/2018.
 * Copyright © 2018 - Future Workshops.
 * All rights reserved.
 */

class SingleWorkerTransformer<T>(private val schedulersProvider: SchedulersProvider) : SingleTransformer<T, T> {
    
    override fun apply(upstream: Single<T>): SingleSource<T> {
        // subscribeOn will cause all upstream calls to run on an io thread.
        // observeOn will cause all the downstream calls to run on the main thread.
        return upstream.subscribeOn(schedulersProvider.io())
                .observeOn(schedulersProvider.ui())
    }
}