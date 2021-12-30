package co.rendernetwork.cosmoscore.utils;

import java.math.BigInteger;

public class ByteArray {
    public static byte[] decodeUsingBigInteger(String hexString) {

        // Make sure it doesn't return NullPointerException when getting the byteArray
        if (hexString == null) return null;
        if (hexString.length() == 0) return null;

        byte[] byteArray = new BigInteger(hexString, 16).toByteArray();
        if (byteArray[0] == 0) {
            byte[] output = new byte[byteArray.length - 1];
            System.arraycopy(
                    byteArray, 1, output,
                    0, output.length);
            return output;
        }
        return byteArray;

    }
}
