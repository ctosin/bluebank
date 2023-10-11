package br.com.softblue.bluebank.application;

import static org.apache.commons.codec.digest.DigestUtils.sha256Hex;

public class SHAHashGenerator implements HashGenerator {

	@Override
	public String generate(String string) {
		return sha256Hex(string);
	}
}
