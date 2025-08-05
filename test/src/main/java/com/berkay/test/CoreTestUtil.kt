package com.berkay.test

import org.junit.jupiter.api.extension.RegisterExtension

open class CoreTestUtil(useUnconfinedDispatcher: Boolean = false) {

    @JvmField
    @RegisterExtension
    var mainCoroutineExtension = MainCoroutineExtension(useUnconfinedDispatcher)

}
