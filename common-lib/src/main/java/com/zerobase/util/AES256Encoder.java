package com.zerobase.util;

import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES256Encoder {
    
    private static final String ALG = "AES/CBC/PKCS5Padding";
    private static final String KEY = "ZEROBASEPROJECT2";
    private static final String IV = KEY.substring(0, 16);

    public static String encrypt(String originText)
        throws  NoSuchPaddingException, NoSuchAlgorithmException,
                InvalidAlgorithmParameterException, InvalidKeyException,
                BadPaddingException, IllegalBlockSizeException {

        Cipher cipher = Cipher.getInstance(ALG);
        cipher.init(
            Cipher.ENCRYPT_MODE, 
            new SecretKeySpec(KEY.getBytes(), "AES"),
            new IvParameterSpec(IV.getBytes(StandardCharsets.UTF_8)));

        byte[] encrypted = cipher.doFinal(originText.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encrypted);            
    }

    public static String decrypt(String originText)
        throws  NoSuchPaddingException, NoSuchAlgorithmException,
                InvalidAlgorithmParameterException, InvalidKeyException,
                BadPaddingException, IllegalBlockSizeException {

        Cipher cipher = Cipher.getInstance(ALG);
        cipher.init(
            Cipher.DECRYPT_MODE, 
            new SecretKeySpec(KEY.getBytes(), "AES"),
            new IvParameterSpec(IV.getBytes(StandardCharsets.UTF_8)));

        byte[] base64Decrypted = Base64.getDecoder().decode(originText);
        byte[] decrypted = cipher.doFinal(base64Decrypted);
        return new String(decrypted);            
}
}