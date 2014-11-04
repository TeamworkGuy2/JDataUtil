/** Author: Benjamin Vanluven
 * CIS 4361 - Secure Operating Systems
 * Spring 2014
 * Purpose: Integrity checker & secure file shredder
 */
package hash;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.EnumMap;

/** A factory for creating hashes of various input sources
 * @author TeamworkGuy2
 * @since 2014-4-2
 */
public final class HashValueFactory {
	private static final int BUFFER_SIZE = 24576;
	private static EnumMap<HashAlgorithm, HashValueFactory> algorithmHashFactories =
			new EnumMap<HashAlgorithm, HashValueFactory>(HashAlgorithm.class);

	private HashAlgorithm algorithm;
	private MessageDigest digest;


	/** Create a hash value factory using the hashing algorithm specified by the name
	 * @param algorithmName the name of the cryptographic hash function that this factory should use
	 */
	public HashValueFactory(String algorithmName) {
		try {
			digest = MessageDigest.getInstance(algorithmName);
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException(e);
		}
		algorithm = HashAlgorithm.getByName(algorithmName);
		if(algorithm == null) {
			throw new IllegalArgumentException("could not find an algorithm by the name '" + algorithmName + "'");
		}
	}


	/** Create a hash value factory using the specified {@link HashAlgorithm}
	 * @param algorithm the cryptographic hash algorithm that this factory should use
	 */
	public HashValueFactory(HashAlgorithm algorithm) {
		this(algorithm.getAlgorithmName());
	}


	/** Create a file hash value using the specified file and function
	 * @param file the file to read and hash
	 * @return the newly created hash value as a byte array
	 * @throws IOException if there is an error reading the file or calculating the hash
	 */
	public byte[] createFileHash(File file) throws IOException {
		final int len = BUFFER_SIZE;
		InputStream in = new FileInputStream(file);
		byte[] b = new byte[len];
		int readNum = -1;
		while((readNum = in.read(b, 0, len)) > 0) {
			digest.update(b, 0, readNum);
		}
		in.close();
		byte[] hash = digest.digest();
		return hash;
	}


	/** Create a string hash value using the specified string and function
	 * @param str the string to hash
	 * @return the newly created hash value as a byte array
	 */
	public byte[] createStringHash(String str, Charset charset) {
		byte[] b = str.getBytes(charset);
		digest.update(b, 0, b.length);
		byte[] hash = digest.digest();
		return hash;
	}


	/** Create a hash value using the specified byte array and function
	 * @param ary the byte array to hash
	 * @return the newly created hash value as a byte array
	 */
	public byte[] createByteArrayHash(byte[] ary, Charset charset) {
		digest.update(ary, 0, ary.length);
		byte[] hash = digest.digest();
		return hash;
	}


	/** Create a hash value using the specified byte array and function
	 * @param ary the byte array to hash
	 * @param offset the offset into the byte array at which to start reading bytes
	 * @param length the number of bytes to read and hash from the array
	 * @return the newly created hash value as a byte array
	 */
	public byte[] createByteArrayHash(byte[] ary, int offset, int length, Charset charset) {
		digest.update(ary, offset, length);
		byte[] hash = digest.digest();
		return hash;
	}


	/** Get the hash value factory for the specified algorithm type
	 * @param algorithm the cryptographic hash algorithm of the factory to retrieve
	 * @return the hash value of the factory matching the specified algorithm, or null
	 * if a factory for the algorithm could not be found or created.
	 */
	public static final HashValueFactory getInstance(HashAlgorithm algorithm) {
		HashValueFactory factory = null;
		synchronized(algorithmHashFactories) {
			factory = algorithmHashFactories.get(algorithm);
			if(factory == null) {
				factory = new HashValueFactory(algorithm);
				algorithmHashFactories.put(algorithm, factory);
			}
		}
		return factory;
	}

}
