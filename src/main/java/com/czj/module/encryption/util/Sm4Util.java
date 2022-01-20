package com.czj.module.encryption.util;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;

/**
 * @Author:caizhijian
 * @Date:2022-01-05
 */
public class Sm4Util {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    private static final String ENCODING = "UTF-8";
    public static final String ALGORITHM_NAME = "SM4";
    //加密算法/分组加密模式/分组填充方式
    //PKCS5Padding-以8个字节为一组进行分组加密
    //定义分组加密模式使用:PKCS5Padding
    public static final String ALGORITHM_NAME_ECB_PADDING = "SM4/ECB/PKCS5Padding";
    //128-32位16进制﹔256-64位16进制
    public static final int DEFAULT_KEY_SIZE = 128;

    /**
     * 生成ECB暗号
     *
     * @param mode 模式
     * @param key
     * @return
     * @throws Exception
     * @explain ECB模式（电子密码本模式:Electronic codebook )*@param algorithmName
     * <p>
     * 算法名称
     */
    private static Cipher generateEcbCipher(String algorithmName, int mode, byte[] key) throws Exception {
        Cipher cipher = Cipher.getInstance(algorithmName,BouncyCastleProvider.PROVIDER_NAME);
        Key sm4Key = new SecretKeySpec(key,ALGORITHM_NAME);
        cipher.init(mode, sm4Key);
        return cipher;
    }

}