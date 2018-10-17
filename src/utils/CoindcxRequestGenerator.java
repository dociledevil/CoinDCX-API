package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;


public class CoindcxRequestGenerator
{
	public static String secret;
	public static String key;
	static ObjectMapper mapper=new ObjectMapper();
	static
	{
		try {
			List<String> data=Files.readAllLines(Paths.get("res/key"));
			secret=data.get(1);
			key=data.get(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private CoindcxRequestGenerator() {}

	public static String signatureGenerator(String json) throws JsonProcessingException
	{
		HashFunction hash = Hashing.hmacSha256(secret.getBytes(Charsets.UTF_8));
		HashCode hashCode = hash.newHasher().putString(json, Charsets.UTF_8).hash();
		return hashCode.toString();
	}
	public static void main(String[] args) throws JsonProcessingException {

	}
}
