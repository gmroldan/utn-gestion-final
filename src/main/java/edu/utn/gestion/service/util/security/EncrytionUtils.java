package edu.utn.gestion.service.util.security;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by martin on 08/08/16.
 */
public class EncrytionUtils {
    private static final String ALGORITHM = "MD5";

    /**
     * Return an encrypted string using md5 algorithm.
     *
     * @param input
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String encrypt(final String input) throws NoSuchAlgorithmException {
        String md5 = null;

        if (null == input) return null;

        //Create MessageDigest object for MD5
        MessageDigest digest = MessageDigest.getInstance(ALGORITHM);

        //Update input string in message digest
        digest.update(input.getBytes(), 0, input.length());

        //Converts message digest value in base 16 (hex)
        md5 = new BigInteger(1, digest.digest()).toString(16);

        return md5;
    }
}
