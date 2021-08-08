import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;

public class RSA {
    public static BigInteger[] encrypt(String message, BigInteger E, BigInteger N) {
        int i;
        byte[] temp = new byte[1];
        byte[] digits = message.getBytes();
        BigInteger[] bigdigits = new BigInteger[digits.length];
        for (i = 0; i < bigdigits.length; i++) {
            temp[0] = digits[i];
            bigdigits[i] = new BigInteger(temp);
        }
        BigInteger[] encrypted = new BigInteger[bigdigits.length];
        for (i = 0; i < bigdigits.length; i++) {
            encrypted[i] = bigdigits[i].modPow(E, N);
        }
        return (encrypted);
    }

    public static String decrypt(BigInteger[] encrypted, BigInteger D, BigInteger N) {
        int i;
        BigInteger[] decrypted = new BigInteger[encrypted.length];
        for (i = 0; i < decrypted.length; i++) {
            decrypted[i] = encrypted[i].modPow(D, N);
        }
        char[] charArray = new char[decrypted.length];
        for (i = 0; i < charArray.length; i++) {
            charArray[i] = (char) (decrypted[i].intValue());
        }
        return (new String(charArray));
    }

    public static void main(String[] args) throws IOException {
        /* create a random number generator */
        Random rng = new Random();
        /* declare p and q as type BigInteger */
        BigInteger p, q;
        /* assign values to p and q as required */
        p = BigInteger.probablePrime(32, rng);
        q = BigInteger.probablePrime(32, rng);
        BigInteger n = p.multiply(q);
        BigInteger v = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
        BigInteger k = BigInteger.probablePrime(v.bitLength(), rng);

        if (!(v.gcd(k)).equals(BigInteger.ONE)) {
            k = BigInteger.probablePrime(v.bitLength(), rng);
        }

        BigInteger d = k.modInverse(v);

        /*• Public key is (k, n)
        • Private key is (d, n)*/

        String msg;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter message:");
        msg = br.readLine();

        BigInteger[] encryptedMsg = encrypt(msg, k, n);
        String decryptedMsg = decrypt(encryptedMsg, d, n);

        System.out.println("The encrypted msg is: " + Arrays.toString(encryptedMsg));
        System.out.println("The decrypted msg is: " + decryptedMsg);


/*        int num;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter number:");
        num = Integer.parseInt(br.readLine());
        // Message encryption
        BigInteger msg = BigInteger.valueOf(num);  // Any integer in the range [0, n)
        BigInteger enc = msg.modPow(k, n);
        System.out.println("The encrypted msg is: " + enc);

        // Message decryption
        BigInteger dec = enc.modPow(d, n);
        System.out.println();
        System.out.println("The decrypted msg is: " + dec);*/
    }
}
