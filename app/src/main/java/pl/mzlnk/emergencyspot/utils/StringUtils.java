package pl.mzlnk.emergencyspot.utils;

public class StringUtils {

    public static String repeat(String str, int times) {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < times; i++) {
            sb.append(str);
        }

        return sb.toString();
    }

}
