import com.github.mvonrenteln.dsa.converter.internalMain
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class DsaConverterTest {

    @Test
    fun smokeTest() {
        runBlocking {
            internalMain(emptyArray())
        }
    }
}