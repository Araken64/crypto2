import com.sun.istack.internal.Nullable;

public class Program02 {
    public static void main(String[] args) {
		java.security.KeyPair pair = generateKeys();
		Peer anna = new Peer("anna", pair);
		Peer bob  = new Peer("bob", pair);
		//anna.bind(bob);
		anna.bind(new Hacker(bob));

		anna.send("coucou");
		bob.send("hello");
    }

    private static @Nullable java.security.KeyPair generateKeys() {
    	try {
			java.security.SecureRandom random = java.security.SecureRandom.getInstance("SHA1PRNG");
			java.security.KeyPairGenerator keyGen = java.security.KeyPairGenerator.getInstance("RSA");
			int bitsize = 1024;
			keyGen.initialize(1024, random);
			return (keyGen.generateKeyPair());
		} catch (java.security.NoSuchAlgorithmException e) {
    		e.printStackTrace();
		}
    	return null;
	}
}
