import kotlin.test.Test
import kotlin.test.assertEquals

class JsStringUtilTest {

    @Test
    @JsName("jsStringUtilTest")
    fun `Js String Util Test`() {
        val inputString = "Hello Kotlin"
        val outputString = inputString.removeChars("Hello ")
        assertEquals("Kotlin", outputString, "Modified string does not match expected")
    }
}
