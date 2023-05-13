package com.modu.ModuForm.app.web.config.jwt.encrypt;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("DES 테스트")
class DES3Test {
    final String plainText = "A great writer is, so to speak, a second government in his country. And for that reason no regime has ever loved great writers,only minor ones.";
    DES3 des3 = new DES3();

    @DisplayName("암호화 테스트")
    @Test
    void encrypt() {
        //when
        try {
            des3.encrypt(plainText);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DisplayName("암호화 -> 복호화 테스트")
    @Test
    void cryptography() {
        //given
        final String encrypted;
        final String decrypted;

        //when
        try {
            long before = System.currentTimeMillis();
            encrypted = des3.encrypt(plainText);
            decrypted = des3.decrypt(encrypted);

            long after = System.currentTimeMillis();
            System.out.print("DES3 Algorithm : ");
            System.out.println(after - before);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //then
        Assertions.assertThat(decrypted).isEqualTo(plainText);
    }
}