package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Checker {

    public static boolean validateCommand(String command) {
        Pattern pattern = Pattern.compile("^(read|write)\\b\\s*([0-9A-Fa-f]{2,4})(\\s+([0-9A-Fa-f]{2}))?$");
        Matcher matcher = pattern.matcher(command);

        return matcher.matches();
    }
}
