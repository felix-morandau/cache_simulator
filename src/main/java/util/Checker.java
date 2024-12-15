package util;

import entity.SystemParameters;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Checker {

    public static boolean validateCommand(String command) {
        Pattern pattern = Pattern.compile("^(read|write)\\b\\s*([0-9A-Fa-f]{2,4})(\\s+([0-9A-Fa-f]{2}))?$");
        Matcher matcher = pattern.matcher(command);

        return matcher.matches();
    }

    public static boolean isValidAddress(String hexAddress) {
        int decAddress = Integer.parseInt(hexAddress, 16);

        return decAddress < SystemParameters.getInstance().getMainMemorySize();
    }

    public static boolean isByte(String newByte) {
        Pattern pattern = Pattern.compile("^[0-9A-Fa-f]{2}$");
        Matcher matcher = pattern.matcher(newByte);

        return matcher.matches();
    }

    public static boolean isCacheSizeValid() {
        return SystemParameters.getInstance().getCacheSize() < SystemParameters.getInstance().getMainMemorySize();
    }
}
