package br.com.softblue.bluebank.application;

import static org.apache.commons.codec.digest.DigestUtils.md5Hex;

public class MD5HashGenerator implements HashGenerator {

	@Override
	public String generate(String string) {
		return md5Hex(string);
	}
}
