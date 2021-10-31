package com.jakemay.cryptominingpuzzle;
import java.math.*;
import java.security.MessageDigest;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String args[]) {
        int s = 256;
        double t = Math.pow(2, 256)/20;
        int iter = 10000;

        System.out.println(puzzleFinder(s, t));
        System.out.println(avgPuzzleFinder(iter, s, t));
    }

    // run for specified amount of iterations
    public static double avgPuzzleFinder(int iter, int s, double t){
        double sum = 0.0;

        for (int i = 0; i < iter; i++) {
            sum += puzzleFinder(s + i, t);
        }

        // return average amount of attempts to find target
        // based on number of times iterated
        return sum/iter;
    }

    // use specified string and target to solve puzzle
    public static int puzzleFinder(int s, double t) {
        for (int r = 0; r < t; r++) {
            // create new hash for every value of r
            String h = getSha256(str(s) + str(r));

            // store value of h in BigInt as int is too small
            BigInteger bi = new BigInteger(h, 16);

            // use BigDec to compare t against h
            BigDecimal db = new BigDecimal(t);

            try {
                // compare t > h; break if true
                // return value of r
                if (db.compareTo(new BigDecimal(bi)) == 1) {
                    return r;
                }
            } catch (NumberFormatException ex) {
                System.out.println(ex);
                break;
            }
        }

        return 0;
    }

    public static String str(Integer n) {
        return n.toString();
    }

    public static String getSha256(String value) {
        try {
            byte[] messageDigest = null;
            MessageDigest digestCryptoprimitive = null;

            digestCryptoprimitive = MessageDigest.getInstance("SHA-256");
            digestCryptoprimitive.update(value.getBytes());
            messageDigest = digestCryptoprimitive.digest();

            return bytesToHex(messageDigest);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    final protected static char[] hexArray = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
            'e', 'f' };

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        int v;

        for (int j = bytes.length - 1, cp = (bytes.length - 1) * 2; j >= 0; j--, cp -= 2) {
            v = bytes[j] & 0xFF;
            hexChars[cp] = hexArray[v >>> 4]; // Most Significant (Upper) Nybble
            hexChars[cp + 1] = hexArray[v & 0x0F]; // Least Significant (Lower) Nybble
        }
        return new String(hexChars);
    }
}
