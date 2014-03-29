package com.eagerlogic.cubee.shared.utils;

/**
 * This class contains some helper methods to interact with gravatar.
 *
 * @author dipacs
 */
public class GravatarUtil {

    /**
     * Returns the Gravatar url of the given email address.
     *
     * @param email The email of the user
     * @param size The size of the resulting image.
     *
     * @return The gravatar url of the given email.
     */
    public static String getGravatarUrl(String email, int size) {
        String res = "http://www.gravatar.com/avatar/";
        email = email.toLowerCase();
        String hash = MD5Util.hash(email);
        res += hash;
        res += "?s=" + size;
        return res;
    }

    private GravatarUtil() {
    }

}
