/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eagerlogic.cubee.shared.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author dipacs
 */
public class MD5Util {

    public static String hash(String input) {
        byte[] data = null;
        try {
            data = input.getBytes("UTF-8");
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException("No such encoding: UTF-8.");
        }

        return hash(data);
    }

    public static String hash(byte[] input) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException("No such algorithm: MD5.");
        }

        byte[] output = md.digest(input);

        return bytesToStr(output);
    }

    public static String bytesToStr(byte[] input) {
        String res = "";
        for (byte b : input) {
            res += byteToStr(b);
        }
        return res;
    }

    private static String byteToStr(byte input) {
        String res = Integer.toHexString(input & 0xff);
        if (res.length() < 2) {
            res = "0" + res;
        }
        return res;
    }

}
