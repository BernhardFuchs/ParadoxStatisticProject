import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by fuchs on 25/08/2017.
 */
public class ParseCK2FileServiceTest {

    public static final String TEST_FILE_SIMPLE_FILENAME = "resources/testFileSimple.ck2";
    public static final String REAL_FILE_FILENAME = "resources/vonTyrol2017_8_14_21_4_37.ck2";

    ParseCK2FileService parseCK2FileService;

    @Before
    public void setUp(){
        parseCK2FileService = new ParseCK2FileService();
    }

    @Ignore
    @Test
    public void
    given_isValidSearchForDynasties_REAL_FILE_then_correctValuesAreReturned() throws IOException {
        parseCK2FileService.readNestedMultiLineValues(REAL_FILE_FILENAME, "dynasties");
    }

    @Test
    public void given_isValidSearchForPlayer_REAL_FILE_then_correctValueIsReturned() throws IOException {
        Map<String, Map<String, String>> playerValues = parseCK2FileService.readMultiLineValue(REAL_FILE_FILENAME, "player");
        assertThat(playerValues.get("player").get("id"), is("1613"));
        assertThat(playerValues.get("player").get("type"), is("66"));
    }

    @Test
    public void given_isValidSearchTerm_REAL_FILE_then_correctValueIsReturned() throws IOException {
        assertThat(parseCK2FileService.readOneLineValue(REAL_FILE_FILENAME, "date"), is("887.10.1"));
    }

    @Test
    public void given_isInvalidSearchTerm_REAL_FILE_then_nullIsReturned() throws IOException {
        assertThat(parseCK2FileService.readOneLineValue(REAL_FILE_FILENAME, "blabla"), nullValue());
    }

    @Test
    public void given_isValidSearchTerm_then_correctValueIsReturned() throws IOException {
        assertThat(parseCK2FileService.readOneLineValue(TEST_FILE_SIMPLE_FILENAME, "date"), is("887.10.1"));
    }

    @Test
    public void given_isInvalidSearchTerm_then_nullIsReturned() throws IOException {
        assertThat(parseCK2FileService.readOneLineValue(TEST_FILE_SIMPLE_FILENAME, "blabla"), nullValue());
    }

}