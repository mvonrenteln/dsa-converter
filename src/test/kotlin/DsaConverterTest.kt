import com.github.mvonrenteln.dsa.converter.internalMain
import jdk.nashorn.internal.ir.annotations.Ignore
import kotlinx.coroutines.runBlocking
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
    @Ignore
    fun manuellerTest() {
        val inputFile = System.getProperty("inputFile")
        runBlocking {
            internalMain(arrayOf(inputFile))
        }
    }
}