package aoq2022.util;

import java.util.concurrent.TimeUnit;

public class Util {
	public static String getHumanReadableTime(long nanos) {
        TimeUnit unitToPrint = null;
        String result = "";
        long rest = nanos;
        for(TimeUnit t: TimeUnit.values()) {
            if (unitToPrint == null) {
                unitToPrint = t;
                continue;
            }
            // convert 1 of "t" to "unitToPrint", to get the conversion factor
            long factor = unitToPrint.convert(1, t);
            long value = rest % factor;
            rest /= factor;

            result = value + " " + unitToPrint + " " + result;

            unitToPrint = t;
            if (rest == 0) {
                break;
            }
        }
        if (rest != 0) {
            result = rest + " " + unitToPrint + " " + result;
        }

        return result.trim();
    }

}
