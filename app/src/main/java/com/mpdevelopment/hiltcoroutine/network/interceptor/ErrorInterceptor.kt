package com.mpdevelopment.hiltcoroutine.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

//TODO WE CAN HAVE A GLOBAL INTERCEPTOR TO HANDLE 400 ETC CASES
//WE CAN USE AN RX PUBLISH SUBJECT TO HAVE A STREAM OF ERRORS THAT IS INJECTED IF MULTIPLE PLACES  WANT TO LISTEN FOR ERRORS
//We can also have an Authenticator : Interceptor which adds needed headers
//AccessTokenAuthenticator : Authenticator can be used to oAuth / refresh tokens
class ErrorInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request())
    }
}