package org.wpattern.mutrack.service.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.codec.Hex;

public final class TokenUtils {

	private TokenUtils() { }

	public static String createToken(UserDetails userDetails, String magicKey, long interval) {
		long expires = System.currentTimeMillis() + interval;
		StringBuilder tokenBuilder = new StringBuilder();

		tokenBuilder.append(userDetails.getUsername());
		tokenBuilder.append(":");
		tokenBuilder.append(expires);
		tokenBuilder.append(":");
		tokenBuilder.append(TokenUtils.computeSignature(userDetails, magicKey, expires));

		return tokenBuilder.toString();
	}

	public static boolean validateToken(String authToken, UserDetails userDetails, String magicKey) {
		String[] parts = authToken.split(":");

		if (parts.length != 3) {
			return false;
		}

		long expires = Long.parseLong(parts[1]);
		String signature = parts[2];

		if (expires < System.currentTimeMillis()) {
			return false;
		}

		return signature.equals(TokenUtils.computeSignature(userDetails, magicKey, expires));
	}

	private static String computeSignature(UserDetails userDetails, String magicKey, long expires) {
		StringBuilder signatureBuilder = new StringBuilder();

		signatureBuilder.append(userDetails.getUsername());
		signatureBuilder.append(":");
		signatureBuilder.append(expires);
		signatureBuilder.append(":");
		signatureBuilder.append(userDetails.getPassword());
		signatureBuilder.append(":");
		signatureBuilder.append(magicKey);

		MessageDigest digest;

		try {
			digest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException("No MD5 algorithm available!");
		}

		return new String(Hex.encode(digest.digest(signatureBuilder.toString().getBytes())));
	}

}
