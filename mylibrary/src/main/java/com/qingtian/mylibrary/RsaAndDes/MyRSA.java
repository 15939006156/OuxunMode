package com.qingtian.mylibrary.RsaAndDes;

import android.util.Base64;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * Created by abc on 2016/12/29.
 */

public class MyRSA {
    /**
     * RSA加密
     * 需要加密的数据
     * @param data
     * @return
     */
    public static String cryptData(String data) {
        String source = data;
        try {
            // 从字符串中得到公钥
            PublicKey publicKey = RSAUtils.loadPublicKey(RSAUtils.PUCLIC_KEY);
            // 从文件中得到公钥
            // InputStream inPublic = getResources().getAssets().open("rsa_public_key.pem");
            //  PublicKey publicKey = RSAUtils.loadPublicKey(inPublic);
            // 加密
            byte[] encryptByte = RSAUtils.encryptData(source.getBytes("UTF-8"), publicKey);
            // 为了方便观察吧加密后的数据用base64加密转一下，要不然看起来是乱码,所以解密是也是要用Base64先转换
            //BASE64Encoder encoder = new BASE64Encoder();

            String ss = Base64.encodeToString(encryptByte,Base64.NO_WRAP);
          //  Log.e("huangyf", ss);
            return ss;
        } catch (Exception e) {
            e.printStackTrace();
            return "加密错误";
        }
    }


    /**
     * RSA解密
     * 需要解密的数据
     * @param data
     * @return
     */
    public static String decryptData(String data) {
        try {
            // 从字符串中得到私钥
            PrivateKey privateKey = RSAUtils.loadPrivateKey(RSAUtils.PRIVATE_KEY);
            //
            // 从文件中得到私钥
            //InputStream inPrivate = getResources().getAssets().open("pkcs8_rsa_private_key.pem");
            //PrivateKey privateKey = RSAUtils.loadPrivateKey(inPrivate);
            // 因为RSA加密后的内容经Base64再加密转换了一下，所以先Base64解密回来再给RSA解密
            //BASE64Decoder decoder = new BASE64Decoder();
            byte[] b1 = Base64.decode(data,Base64.NO_WRAP);
            byte[] decryptByte = RSAUtils.decryptData(b1, privateKey);
            String decryptStr = new String(decryptByte);
            return decryptStr;
        } catch (Exception e) {
            e.printStackTrace();
            return "解密错误";
        }
    }

}
