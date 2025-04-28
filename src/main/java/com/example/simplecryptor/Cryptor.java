package com.example.simplecryptor;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Cryptor {
	private final SecureRandom secureRandom;

	// コンストラクタ
	public Cryptor(String key){
		// keyをハッシュ化
		byte[] hash = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			hash = md.digest(key.getBytes(StandardCharsets.UTF_8));
		} catch (NoSuchAlgorithmException e) {
			System.out.println("SHA-256 algorithm not found.");
			System.exit(0);
		}

		SecureRandom sr = null;
		try {
			sr = SecureRandom.getInstance("SHA1PRNG");
		}catch (NoSuchAlgorithmException e){
			System.out.println("SHA1PRNG algorithm not found.");
			System.exit(0);
		}
		sr.setSeed(hash);
		secureRandom = sr;
	}

	// バイト列を暗号化(復号)
	public byte[] transformData(byte[] input){
		byte[] ans = new byte[input.length];

		for (int i = 0; i < ans.length; i++){
			ans[i] = (byte)(input[i] ^ getRandomByte());
		}

		return ans;
	}

	// ランダムな1バイトを取得
	private byte getRandomByte(){
		byte[] tmp = new byte[1];
		secureRandom.nextBytes(tmp);
		return tmp[0];
	}
}
