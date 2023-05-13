package com.modu.ModuForm.app.web.config.jwt.encrypt;

import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

@Component
public class DES3 implements Cryptography {
    private final Key key;

    public DES3() {
        try {
            key = setKey();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String encrypt(String text) throws Exception {
        StringBuffer sb = null;
        Cipher cipher = Cipher.getInstance("TripleDES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] plaintext = text.getBytes("UTF8");
        byte[] ciphertext = cipher.doFinal(plaintext);

        sb = new StringBuffer(ciphertext.length * 2);
        for (int i = 0; i < ciphertext.length; i++) {
            String hex = "0" + Integer.toHexString(0xff & ciphertext[i]);
            sb.append(hex.substring(hex.length() - 2));
        }
        return sb.toString();
    }

    @Override
    public String decrypt(String cipherText) throws Exception {
        String text = null;
        byte[] b = new byte[cipherText.length() / 2];
        Cipher cipher = Cipher.getInstance("TripleDES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key);
        for (int i = 0; i < b.length; i++) {
            b[i] = (byte) Integer.parseInt(cipherText.substring(2 * i, 2 * i + 2), 16);
        }
        byte[] decryptedText = cipher.doFinal(b);
        text = new String(decryptedText, "UTF8");

        return text;
    }

    private Key setKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("TripleDES");
        keyGenerator.init(168);
        return keyGenerator.generateKey();
    }
}