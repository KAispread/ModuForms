package com.modu.ModuForm.app.web.security.authentication.jwt.encrypt;

public interface Cryptography {
    String encrypt(String text) throws Exception;
    String decrypt(String cipherText) throws Exception;
}
