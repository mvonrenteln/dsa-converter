import com.github.mvonrenteln.dsa.converter.internalMain
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class DsaConverterTest {

    @Test
    fun smokeTest() {
        runBlocking {
            internalMain(emptyArray())
        }
    }

    /**
     * Test in IDE starten mit VM-Options "-DinputFile=..."
     */
    @Test
    @Disabled
    fun manuellerTest() {
        val inputFile = System.getProperty("inputFile")
        runBlocking {
            internalMain(arrayOf(inputFile))
        }
    }
}