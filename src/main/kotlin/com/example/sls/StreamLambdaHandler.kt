package com.example.sls

import com.amazonaws.serverless.exceptions.ContainerInitializationException
import com.amazonaws.serverless.proxy.internal.testutils.Timer
import com.amazonaws.serverless.proxy.model.AwsProxyRequest
import com.amazonaws.serverless.proxy.model.AwsProxyResponse
import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler
import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler.getAwsProxyHandler
import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestStreamHandler
import java.io.InputStream
import java.io.OutputStream

class StreamLambdaHandler : RequestStreamHandler {

    private val handler: SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> =
        tryBuildHandler()

    override fun handleRequest(inputStream: InputStream?, outputStream: OutputStream?, context: Context?) {
        handler.proxyStream(inputStream, outputStream, context)
    }

    private fun tryBuildHandler(): SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> = try {
        getAwsProxyHandler(Application::class.java)
    } catch (e: ContainerInitializationException) {
        e.printStackTrace()
        throw RuntimeException("Could not initialize Spring Boot application", e)
    }

    init {
        // we enable the timer for debugging. This SHOULD NOT be enabled in production.
        Timer.enable()
    }
}