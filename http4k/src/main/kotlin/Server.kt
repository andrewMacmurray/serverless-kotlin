import org.http4k.core.HttpHandler
import org.http4k.server.SunHttp
import org.http4k.server.asServer
import org.http4k.serverless.AppLoader

fun main() {
    DevServer.start()
}

object LambdaServer : AppLoader {
    override fun invoke(p1: Map<String, String>): HttpHandler = Application.handler
}

object DevServer {
    fun start() {
        println("starting local server on 8080")
        SunHttp(8080)
            .pipe(Application.handler::asServer)
            .start()
    }
}
