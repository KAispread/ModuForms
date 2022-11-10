package com.modu.ModuForm.app.web.config.auth.jwt.encrypt;

public interface Cryptography {
    String encrypt(String text) throws Exception;
    String decrypt(String cipherText) throws Exception;
}
