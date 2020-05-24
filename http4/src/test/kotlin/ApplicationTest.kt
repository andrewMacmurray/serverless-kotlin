import com.natpryce.hamkrest.Matcher
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.http4k.core.Body
import org.http4k.core.HttpMessage
import org.http4k.core.Method.GET
import org.http4k.core.Method.POST
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.format.Jackson
import org.http4k.format.Jackson.auto
import org.http4k.hamkrest.hasBody
import org.http4k.routing.RoutingHttpHandler
import org.junit.jupiter.api.Test

class ApplicationTest {

    private val app = Application.handler

    @Test
    fun `handles hello`() {
        assertThat(app.get("/hello"), hasBody("Hello World!"))
    }

    @Test
    fun `handles bread`() {
        assertThat(app.get("/bread"), hasJsonBody(Bread("I LOVE BREAD!")))
    }

    @Test
    fun `handles posted bread`() {
        val echoedBody = Bread("HELLO!")

        assertThat(app.post("/body", echoedBody), hasJsonBody(echoedBody))
    }

    @Test
    fun `handles fancy bread`() {
        val requestBody = FancyRequestBody("Bread")

        val response = app.post("/fancy-bread", requestBody)

        assertThat(response, hasJsonBody(Bread("Hello Fancy! Bread")))
    }
}

private fun RoutingHttpHandler.get(name: String): Response =
    this(Request(GET, name))

private fun <T : Any> RoutingHttpHandler.post(path: String, b: T): Response =
    this(Request(POST, path).body(Jackson.asJsonString(b)))

private inline fun <reified T : Any> hasJsonBody(body: T): Matcher<HttpMessage> =
    hasBody(Body.auto<T>().toLens(), equalTo(body))

