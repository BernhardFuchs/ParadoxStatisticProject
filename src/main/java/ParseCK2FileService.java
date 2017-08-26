import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by fuchs on 25/08/2017.
 */
public class ParseCK2FileService {

    public static final String TAB_PREFIX = "\t";
    public static final String EQUAL_SUFFIX = "=";

    public Map<String, Map<String, String>> readMultiLineValue(String filename, String searchTerm) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
        Map<String, Map<String, String>> valueMap = new HashMap<>();
        String line;
        do {
             line = bufferedReader.readLine();
             if (StringUtils.startsWith(line, TAB_PREFIX + searchTerm + EQUAL_SUFFIX)){
                 valueMap = extractValueMap(line, bufferedReader);
                 break;
             }
        } while(line != null);

        return valueMap;
    }

    private Map<String, Map<String, String>> extractValueMap(String line, BufferedReader bufferedReader) throws IOException {
        Map<String, String> subMap = new HashMap<>();
        String subLine;
        do {
            subLine = bufferedReader.readLine();
            if (isNotOpenBracket(subLine) && isNotClosingBracket(subLine)){
                String subKey = extractKey(subLine);
                String subValue = extractValue(subLine);
                subMap.put(subKey, subValue);
            }
        } while (isNotClosingBracket(subLine));

        Map<String, Map<String, String>> rootMap = new HashMap<>();
        String rootKey = stripSpecialChars(StringUtils.remove(line, EQUAL_SUFFIX));
        rootMap.put(rootKey, subMap);

        return rootMap;
    }

    private boolean isNotClosingBracket(String subLine) {
        return !subLine.equals(TAB_PREFIX + "}");
    }

    private boolean isNotOpenBracket(String subLine) {
        return !subLine.equals(TAB_PREFIX + "{");
    }

    private String extractKey(String subLine) {
        String[] splitLine = StringUtils.split(subLine, "=");
        String key = splitLine[0];
        key = stripSpecialChars(key);
        return key;
    }

    private String stripSpecialChars(String string) {
        Matcher matcher = Pattern.compile("[^a-zA-Z0-9]").matcher(string);
        if (matcher.find()){
            string = string.replaceAll(matcher.group(), "");
        }
        return string;
    }

    public String readOneLineValue(String filename, String searchTerm) throws IOException {
        String line;
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
        do {
            line = bufferedReader.readLine();
            if (StringUtils.startsWith(line, TAB_PREFIX + searchTerm + EQUAL_SUFFIX)){
                break;
            }
        } while(line != null);
        bufferedReader.close();

        if (line != null){
            String value = extractValue(line);
            return value;
        }

        return null;
    }

    private String extractValue(String line) {
        String[] splitLine = StringUtils.split(line, EQUAL_SUFFIX);
        String value = splitLine[1];
        value = stripSpecialChars(value);
        return value;
    }

}
