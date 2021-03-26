package io.czz.explorer.utils;

import io.czz.explorer.exception.MessageDecodingException;
import io.czz.explorer.exception.MessageEncodingException;

import java.math.BigInteger;

public class Numeric {


    public static String encodeQuantity(BigInteger value) {
        if (value.signum() != -1) {
            return "0x" + value.toString(16);
        } else {
            throw new MessageEncodingException("Negative values are not supported");
        }
    }

    public static BigInteger decodeQuantity(String value) {
        if (!isValidHexQuantity(value)) {
            throw new MessageDecodingException("Value must be in format 0x[1-9]+[0-9]* or 0x0");
        } else {
            try {
                return new BigInteger(value.substring(2), 16);
            } catch (NumberFormatException var2) {
                throw new MessageDecodingException("Negative ", var2);
            }
        }
    }

    private static boolean isValidHexQuantity(String value) {
        if (value == null) {
            return false;
        } else if (value.length() < 3) {
            return false;
        } else {
            return value.startsWith("0x");
        }
    }
}
