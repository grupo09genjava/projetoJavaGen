package com.grupo09.generation.utils;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator;

import java.security.KeyPair;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class RSAKeyPairGenerator {
    public static RSAKey generateRSAKey() throws JOSEException{
        RSAKeyGenerator keyGenerator = new RSAKeyGenerator(2048);
        return keyGenerator.keyID("1").generate();
    }

    public static KeyPair getKeyPair(RSAKey rsaKey) throws JOSEException{
        RSAPublicKey publicKey = rsaKey.toRSAPublicKey();
        RSAPrivateKey privateKey = rsaKey.toRSAPrivateKey();
        return new KeyPair(publicKey, privateKey);
    }
}
