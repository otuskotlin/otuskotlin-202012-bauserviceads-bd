import kotlin.test.Test
import kotlin.test.assertEquals

class StringUtilTest {

    @Test
    fun shouldRemoveChars(){
        val inputString = "Hello Kotlin"
        val outputString = inputString.removeChars("Hello ")
        assertEquals("Kotlin", outputString, "Modified string does not match expected")
    }
}