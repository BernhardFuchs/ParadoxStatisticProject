import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by fuchs on 25/08/2017.
 */
public class ParseCK2FileService {
    public String readFile(String filename) throws IOException {
        return readFile(filename, null);
    }

    public String readFile(String filename, String searchTerm) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
        String line = bufferedReader.readLine();
        while(line != null) {
            line = bufferedReader.readLine();
            if (StringUtils.startsWith(line, "\t" + searchTerm)){
                break;
            }
        }
        bufferedReader.close();

        if (line != null){
            String value = extractValue(line);
            return value;
        }

        return null;
    }

    private String extractValue(String line) {
        String[] splitLine = StringUtils.split(line, "=");

        return StringUtils.strip(splitLine[1], "\"\"");
    }
}
