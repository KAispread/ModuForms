package com.modu.ModuForm.app.web.config.jwt.encrypt;

import com.modu.ModuForm.app.web.security.authentication.jwt.encrypt.AES256;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("AES256 테스트")
class AES256Test {
    final String plainText = "A great writer is, so to speak, a second government in his country. And for that reason no regime has ever loved great writers,only minor ones.";
    AES256 aes256 = new AES256();

    @DisplayName("암호화 테스트")
    @Test
    void encrypt() {
        //when
        try {
            aes256.encrypt(plainText);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DisplayName("복호화 테스트")
    @Test
    void decrypt() {
        //given
        final String AES_Encrypted = "yrz+ggVAMfD/xO5t720A8DziYfcVsDCp+/eqik1PR5mhWXV5ETGurCmuh6T03klCz/yabvY8tMwvKQUA/gHp+fjoWnx148ecvmaNG+HkEs2Yfp8bos9N/mC0sFtuJ513dzukkAxAclwnbvONPRjM702OehHBETIR/pdrtCxdxG6jnN+AIA6agi7Qb8xh/6g7";

        //when
        String decrypted;
        try {
            decrypted = aes256.decrypt(AES_Encrypted);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //then
        Assertions.assertThat(decrypted).isEqualTo(plainText);
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

            encrypted = aes256.encrypt(plainText);
            decrypted = aes256.decrypt(encrypted);

            long after = System.currentTimeMillis();
            System.out.print("AES256 Algorithm : ");
            System.out.println(after - before);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //then
        Assertions.assertThat(decrypted).isEqualTo(plainText);
    }
}