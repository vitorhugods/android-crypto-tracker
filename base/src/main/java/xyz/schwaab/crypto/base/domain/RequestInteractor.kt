package xyz.schwaab.crypto.base.domain

import io.reactivex.rxjava3.core.Single

interface RequestInteractor<Params, Result> {
    operator fun invoke(params: Params): Single<Result>
}