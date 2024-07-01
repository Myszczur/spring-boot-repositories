package com.urbanik.authenticatedloginsystem.utils;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

/**
 * @author kurbanik
 */

public class KeyGeneratorUtility {

    private static final String INSTANCE_ALGORITHM = "RSA";
    private static final int KEY_SIZE = 2048;

    public static KeyPair generateRsaKey() {

        KeyPair keyPair;

        try {
            KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance(INSTANCE_ALGORITHM);
            keyGenerator.initialize(KEY_SIZE);
            keyPair = keyGenerator.generateKeyPair();

        } catch (Exception ex) {
            throw new IllegalStateException();
        }

        return keyPair;
    }
}
