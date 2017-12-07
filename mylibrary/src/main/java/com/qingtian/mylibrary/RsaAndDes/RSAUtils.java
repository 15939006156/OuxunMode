package com.qingtian.mylibrary.RsaAndDes;

import android.util.Base64;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

//import Decoder.BASE64Decoder;

/**
 * @author Huang
 * @date 2016年10月22日 下午1:44:23
 */

public final class RSAUtils {
	public static String PUCLIC_KEY="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDBKUCikS86SHpAtUwchedgKS9uROQVEsFnv7QHQJd+n/ugPHEyZUCm+xKiDOt13HzfDdPXO6KbmBcjHRm1hmqj6VzAJLLYCFsZo/GxLpCimRR4QFGRPzuQFYEldXYdZxAh3XHxwTpvpZsRIhE1ohSKUSV6EcV9eGwoDiIvjk5ZQwIDAQAB";

	public static String PRIVATE_KEY="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMbS4RQiee2HyBcE\n" +
			"7UfDpGuiMguxc/Fdr8FYcFHHJYHn1PBnKzjUQDKW6BsLjpg5AtfDuCqQcWQUtz2K\n" +
			"75wQymMnPnENfXavRQyVJBkMfLHzDn4gHkWug+gJq0iLPdI99coTwwCRQxzA9zsK\n" +
			"F5QcwdpnmF7XnpRF38H0TbRG4nvXAgMBAAECgYAznEH47AoicoCqGQlO+6YOmofL\n" +
			"KUEjgmJBGift0ngWBIvt65n4ooAXobyWaKWXNhVa1N9VjTEh/Y6jVxFjvagLJv4P\n" +
			"Y1Pi8VyRfKgyS1xfv4lZaQJCNIALGxhynS+tgYNNDE4PtRgyPBdUSXGF0aDGewCI\n" +
			"fWC16Te6N6BxlC2ZQQJBAP8PCHzwqrL/KN+MMbjnpTYjG9lC/IwDo8VKzoLWBs0E\n" +
			"PlZXPvVAhQoGis2DhL3J+JEURqnOpDqvXU952xqaGXkCQQDHjrfRVA+flVx/dGfY\n" +
			"RGehZRRaECWUWhxAeJaZjlZ3+VUXPfMKWFE5eY+pI5IfXZs8MkzRo5YWLf5yObyS\n" +
			"cTvPAkBWssB637/Wal91ZY09Qhhph5OezWNVIKOZm60I0kGrjMa3yJ5Q9WaDmuex\n" +
			"pgQIE1BX2GGQtPokLJn2OwCcUUdZAkBkHw3Iv6UrCvQimlOzpPZdempQmYNRQDim\n" +
			"fBoLJufpP7Zad+FlklQGQyA52zfn9r344L+7+phFvoPH6YHb82XvAkEA9K5mj9II\n" +
			"Mo+3iQufEQaG2vXNO0OalbvQvLct10kWkM/G582qXB4xoXOY6Vc4T8P7/MhiWyQ7\n" +
			"7+aSbqUMFmmLHA==";


	private static String RSA = "RSA";
	private static String RSA_ANDROID = "RSA/ECB/PKCS1Padding";  //android平台加密和解密都需要用到这个，才可以和后台完美进行加解密
	private static String RSA_JAVA = "RSA/None/PKCS1Padding";

	/**
	 * 随机生成RSA密钥对(默认密钥长度为1024)
	 * 
	 * @return
	 */
	public static KeyPair generateRSAKeyPair() {
		return generateRSAKeyPair(1024);
	}

	/**
	 * 随机生成RSA密钥对
	 * 
	 * @param keyLength
	 *            密钥长度，范围：512～2048<br>
	 *            一般1024
	 * @return
	 */
	public static KeyPair generateRSAKeyPair(int keyLength) {
		try {
			KeyPairGenerator kpg = KeyPairGenerator.getInstance(RSA);
			kpg.initialize(keyLength);
			return kpg.genKeyPair();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 用公钥加密 <br>
	 * 每次加密的字节数，不能超过密钥的长度值减去11
	 * 
	 * @param data
	 *            需加密数据的byte数据
	 *            公钥
	 * @return 加密后的byte型数据
	 */
	public static byte[] encryptData(byte[] data, PublicKey publicKey) {
		try {
			Cipher cipher = Cipher.getInstance(RSA_JAVA);
			// 编码前设定编码方式及密钥
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			// 传入编码数据并返回编码结果
			return cipher.doFinal(data);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 用私钥解密
	 * 
	 * @param encryptedData
	 *            经过encryptedData()加密返回的byte数据
	 * @param privateKey
	 *            私钥
	 * @return
	 */
	public static byte[] decryptData(byte[] encryptedData, PrivateKey privateKey) {
		try {
			Cipher cipher = Cipher.getInstance(RSA_ANDROID);
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			return cipher.doFinal(encryptedData);
		} catch (Exception e) {
			return null;
		}
	}



	/**
	 * 从字符串中加载公钥
	 * @param publicKeyStr
	 *            公钥数据字符串
	 * @throws Exception
	 *             加载公钥时产生的异常
	 */
	public static PublicKey loadPublicKey(String publicKeyStr) throws Exception {
		try {
			//BASE64Decoder decoder = new BASE64Decoder();
			byte[] buffer = Base64.decode(publicKeyStr,Base64.NO_WRAP);
			KeyFactory keyFactory = KeyFactory.getInstance(RSA);
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
			return (RSAPublicKey) keyFactory.generatePublic(keySpec);
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此算法");
		} catch (InvalidKeySpecException e) {
			throw new Exception("公钥非法");
		} catch (NullPointerException e) {
			throw new Exception("公钥数据为空");
		}
	}

	/**
	 * 从字符串中加载私钥<br>
	 * 加载时使用的是PKCS8EncodedKeySpec（PKCS#8编码的Key指令）。
	 * 
	 * @param privateKeyStr
	 * @return
	 * @throws Exception
	 */
	public static PrivateKey loadPrivateKey(String privateKeyStr)
			throws Exception {
		try {
			//BASE64Decoder decoder = new BASE64Decoder();
			byte[] buffer = Base64.decode(privateKeyStr,Base64.NO_WRAP);
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
			KeyFactory keyFactory = KeyFactory.getInstance(RSA);
			return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此算法");
		} catch (InvalidKeySpecException e) {
			//throw new Exception("私钥非法");
			throw new Exception(e.toString());
		} catch (NullPointerException e) {
			throw new Exception("私钥数据为空");
		}
	}


	/**
	 * 读取密钥信息
	 *
	 * @param in
	 * @return
	 * @throws IOException
	 */
	private static String readKey(InputStream in) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String readLine = null;
		StringBuilder sb = new StringBuilder();
		while ((readLine = br.readLine()) != null) {
			if (readLine.charAt(0) == '-') {
				continue;
			} else {
				sb.append(readLine);
				sb.append('\r');
			}
		}
		return sb.toString();
	}


	/**
	 * 从文件中输入流中加载公钥
	 *  公钥输入流
	 * 加载公钥时产生的异常
	 */

	public static PublicKey loadPublicKey(InputStream in) throws Exception {
		try {
			return loadPublicKey(readKey(in));
		} catch (IOException e) {
			throw new Exception("公钥数据流读取错误");
		} catch (NullPointerException e) {
			throw new Exception("公钥输入流为空");
		}
	}

	/**
	 * 从文件中加载私钥
	 * 
	 *   私钥文件名
	 * @return 是否成功
	 * @throws Exception
	 */
	public static PrivateKey loadPrivateKey(InputStream in) throws Exception {
		try {
			return loadPrivateKey(readKey(in));
		} catch (IOException e) {
			throw new Exception("私钥数据读取错误");
		} catch (NullPointerException e) {
			throw new Exception("私钥输入流为空");
		}
	}


	/**
	 * 通过公钥byte[](publicKey.getEncoded())将公钥还原，适用于RSA算法
	 *
	 * @param keyBytes
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public static PublicKey getPublicKey(byte[] keyBytes)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(RSA);
		PublicKey publicKey = keyFactory.generatePublic(keySpec);
		return publicKey;
	}

	/**
	 * 通过私钥byte[]将公钥还原，适用于RSA算法
	 *
	 * @param keyBytes
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public static PrivateKey getPrivateKey(byte[] keyBytes)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(RSA);
		PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
		return privateKey;
	}

	/**
	 * 使用N、e值还原公钥
	 *
	 * @param modulus
	 * @param publicExponent
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public static PublicKey getPublicKey(String modulus, String publicExponent)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		BigInteger bigIntModulus = new BigInteger(modulus);
		BigInteger bigIntPrivateExponent = new BigInteger(publicExponent);
		RSAPublicKeySpec keySpec = new RSAPublicKeySpec(bigIntModulus,
				bigIntPrivateExponent);
		KeyFactory keyFactory = KeyFactory.getInstance(RSA);
		PublicKey publicKey = keyFactory.generatePublic(keySpec);
		return publicKey;
	}

	/**
	 * 使用N、d值还原私钥
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public static PrivateKey getPrivateKey(String modulus,
										   String privateExponent) throws NoSuchAlgorithmException,
			InvalidKeySpecException {
		BigInteger bigIntModulus = new BigInteger(modulus);
		BigInteger bigIntPrivateExponent = new BigInteger(privateExponent);
		RSAPublicKeySpec keySpec = new RSAPublicKeySpec(bigIntModulus,
				bigIntPrivateExponent);
		KeyFactory keyFactory = KeyFactory.getInstance(RSA);
		PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
		return privateKey;
	}


	/**
	 * 打印公钥信息
	 * 
	 * @param publicKey
	 */
	public static void printPublicKeyInfo(PublicKey publicKey) {
		RSAPublicKey rsaPublicKey = (RSAPublicKey) publicKey;
		System.out.println("----------RSAPublicKey----------");
		System.out.println("Modulus.length="
				+ rsaPublicKey.getModulus().bitLength());
		System.out.println("Modulus=" + rsaPublicKey.getModulus().toString());
		System.out.println("PublicExponent.length="
				+ rsaPublicKey.getPublicExponent().bitLength());
		System.out.println("PublicExponent="
				+ rsaPublicKey.getPublicExponent().toString());
	}


	public static void printPrivateKeyInfo(PrivateKey privateKey) {
		RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) privateKey;
		System.out.println("----------RSAPrivateKey ----------");
		System.out.println("Modulus.length="
				+ rsaPrivateKey.getModulus().bitLength());
		System.out.println("Modulus=" + rsaPrivateKey.getModulus().toString());
		System.out.println("PrivateExponent.length="
				+ rsaPrivateKey.getPrivateExponent().bitLength());
		System.out.println("PrivatecExponent="
				+ rsaPrivateKey.getPrivateExponent().toString());

	}

}
