package com.example;

import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import java.io.*;

public class AESEncrypt {

    public static String asHex(byte buf[]) {
        StringBuffer strbuf = new StringBuffer(buf.length * 2);
        int i;
        for (i = 0; i < buf.length; i++) {
            if (((int) buf[i] & 0xff) < 0x10) {
                strbuf.append("0");
            }
            strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
        }
        return strbuf.toString();
    }

    public static void main(String[] args) throws Exception {
        String message = "Test text!";
        // Dividere la stringa in parole
        String[] words = message.split(" ");

        // Get the KeyGenerator
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128); // 192 e 256 bit possono non essere disponibili

        // Generate the secret key specs.
        SecretKey skey = kgen.generateKey();
        byte[] raw = skey.getEncoded();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");

        // Instantiate the cipher
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);

        System.out.println("Original string -> " + message);
        System.out.print("Encrypted string -> ");

        for (String word : words) {
            byte[] encrypted = cipher.doFinal(word.getBytes());
            System.out.print(asHex(encrypted) + " ");
        }
    }
}
