package com.modu.ModuForm.app.web.config.auth.jwt.encrypt;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;

class RSATest {
    final String plainText = "And for that reason no regime has ever loved great writers,only minor ones.";

    @DisplayName("암호화 -> 복호화 테스트")
    @Test
    void cryptography() throws NoSuchPaddingException, IllegalBlockSizeException, UnsupportedEncodingException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, InvalidKeySpecException {
        //given
        final String encrypted;
        final String decrypted;

        // RSA 키쌍을 생성
        KeyPair keyPair = RSA.genRSAKeyPair();

        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        long before = System.currentTimeMillis();

        encrypted = RSA.encryptRSA(plainText, publicKey);;

        for (int i = 0; i < 100000; i++) {
            RSA.encryptRSA(plainText, publicKey);
            RSA.decryptRSA(encrypted, privateKey);
        }

        decrypted = RSA.decryptRSA(encrypted, privateKey);

        long after = System.currentTimeMillis();
        System.out.print("AES256 Algorithm : ");
        System.out.println(after - before);

        Assert.assertEquals(plainText, decrypted);
    }
}