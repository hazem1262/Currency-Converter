package com.hazem.currency_converter.utils.log

import timber.log.Timber

class ReleaseTree : Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        // todo send events to analytics, crashyltics or any other tool
    }
}