import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

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

    @Test
    public void given_isValidSearchTerm_REAL_FILE_then_correctValueIsReturned() throws IOException {
        assertThat(parseCK2FileService.readFile(REAL_FILE_FILENAME, "date"), is("887.10.1"));
    }

    @Test
    public void given_isInvalidSearchTerm_REAL_FILE_then_nullIsReturned() throws IOException {
        assertThat(parseCK2FileService.readFile(REAL_FILE_FILENAME, "blabla"), nullValue());
    }

    @Test
    public void given_isValidSearchTerm_then_correctValueIsReturned() throws IOException {
        assertThat(parseCK2FileService.readFile(TEST_FILE_SIMPLE_FILENAME, "date"), is("887.10.1"));
    }

    @Test
    public void given_isInvalidSearchTerm_then_nullIsReturned() throws IOException {
        assertThat(parseCK2FileService.readFile(TEST_FILE_SIMPLE_FILENAME, "blabla"), nullValue());
    }

}