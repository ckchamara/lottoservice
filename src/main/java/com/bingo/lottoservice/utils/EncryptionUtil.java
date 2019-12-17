package com.bingo.lottoservice.utils;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class EncryptionUtil {

    public static String getDecryptedString(String encryptedValue) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword("bingo321"); //password that used to encrypt the text.
        return encryptor.decrypt(encryptedValue);
    }
}
