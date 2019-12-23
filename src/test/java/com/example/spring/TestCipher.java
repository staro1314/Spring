package com.example.spring;

import org.junit.Test;

import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.util.Arrays;

/**
 * @author: Staro
 * @date: 2019/9/25 14:12
 * @Description:
 */
public class TestCipher {

    @Test
    public void symmetricEncryption() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        //生成一个秘钥
        SecretKey secretKey = KeyGenerator.getInstance("SM4").generateKey();
        //取一个加密器
        Cipher cipher = Cipher.getInstance("SM4/ECB/PKCS7Padding");
        cipher.init(Cipher.ENCRYPT_MODE,secretKey);
        byte[] bytes = cipher.doFinal("abcd".getBytes());
        System.out.println(Arrays.toString(bytes));
    }

    /**
     * 生成摘要
     */
    @Test
    public void getDigest() throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.update("abcd".getBytes());
        byte[] digest1 = digest.digest();
        System.out.println(Arrays.toString(digest1));


    }

    @Test
    public void asymmetricEncryption() throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.update("abcd".getBytes());
        byte[] digest1 = digest.digest();
        System.out.println(Arrays.toString(digest1));
        Signature signature = Signature.getInstance("SHA1WithRSA");
    }
}
