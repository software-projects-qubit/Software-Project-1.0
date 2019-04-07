package com.wits.witssrcconnect.managers;



import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityManager {

    public static String SHA1(String text){
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            byte[] textBytes = text.getBytes(Charset.forName("iso-8859-1"));
            messageDigest.update(textBytes, 0, textBytes.length);
            byte[] sha1hash = messageDigest.digest();
            return convertToHex(sha1hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static String convertToHex(byte[] data) {
        StringBuilder buf = new StringBuilder();
        for (byte b : data){
            int halfByte = (b >>> 4) & 0x0F;
            int two_halves = 0;
            do {
                buf.append(halfByte <= 9 ? (char) ('0' + halfByte)
                        : (char) ('a' + halfByte - 10));
                halfByte = b & 0x0F;
            }while (two_halves++ < 1);
        }
        return buf.toString();
    }
}
