package amir.demo.openhtmltopdfdemo;

import java.util.Date;

public class Utils {
    public static String getString(Long counter) {
        return new Date().toString().substring(4, 10).replaceAll("\\s", "").trim() + "_" + counter;
    }
}
