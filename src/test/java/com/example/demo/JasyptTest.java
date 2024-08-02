package com.example.demo;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JasyptTest {

  private static final Logger log = LoggerFactory.getLogger(JasyptTest.class);

  @Test
  public void testEncrypt() {
    String value = "Y2kim2A!";
    String result = jasyptEncrypt(value);
    log.info("----- {} -----", result);
  }

  public String jasyptEncrypt(String value) {
    String key="52C965FBA64471979863B3A53ED1B";
    StandardPBEStringEncryptor pbeEnc = new StandardPBEStringEncryptor();
    pbeEnc.setAlgorithm("PBEWithMD5AndDES");
    pbeEnc.setPassword(key);
    return pbeEnc.encrypt(value);
  }

}
