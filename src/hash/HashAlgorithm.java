package hash;

/** Common cryptographic hashing algorithms
 * @author TeamworkGuy2
 * @since 2014-4-2
 */
public enum HashAlgorithm {
	MD5("MD5"),
	DES("DES"),
	RSA("RSA"),
	SHA_1("SHA"),
	SHA_256("SHA-256"),
	SHA_512("SHA-512");

	private final String name;


	/** Create a {@link HashAlgorithm} enum instance
	 * @param name the name of the hash algorithm
	 */
	private HashAlgorithm(String name) {
		this.name = name;
	}


	/** The Java recognized name of this cryptographic hash function
	 * @return the name of this hash function
	 */
	public String getAlgorithmName() {
		return name;
	}


	/** Get the hash algorithm matching the name specified
	 * @param name the name of the algorithm to retrieve
	 * @return the hashing algorithm with the name specified, or null if the algorithm could not be found
	 */
	public static final HashAlgorithm getByName(String name) {
		for(HashAlgorithm algorithm : HashAlgorithm.values()) {
			if(algorithm.getAlgorithmName().equals(name)) {
				return algorithm;
			}
		}
		return null;
	}

}
