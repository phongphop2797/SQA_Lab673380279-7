//นายปองภพ ศรีรักษ์ 673380279-7 sec.1
package Test.Lab5_1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import model_ShiftCipher.ShiftCipher;

import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;


public class ShiftCipherTest {

    private final ShiftCipher cipher = new ShiftCipher();

    // -------------------- ENCRYPTION --------------------
    @ParameterizedTest
    @MethodSource("encryptTestData")
    void testEncrypt(String plainText, int key, String expected, Class<? extends Throwable> expectedException) {
        if (expectedException != null) {
            assertThrows(expectedException, () -> cipher.encrypt(plainText, key));
        } else {
            assertEquals(expected, cipher.encrypt(plainText, key));
        }
    }

    static Stream<Arguments> encryptTestData() {
        return Stream.of(
                // Valid cases (Weak Robust – at least one valid combination)
                Arguments.of("ATTACK", 17, "RKKRTB", null),   // TC001
                Arguments.of("ATTACK", -3, "XQQXZH", null),   // TC002
                Arguments.of("ATTACK", 0, "ATTACK", null),    // TC003
                Arguments.of("ATTACK", 30, "EXXEGO", null),   // TC004

                // Invalid cases (each invalid EC paired with valid key)
                Arguments.of(null, 17, null, IllegalArgumentException.class),          // TC005 
                Arguments.of("", 17, null, IllegalArgumentException.class),            // TC006 
                Arguments.of("ATT ACK", 17, null, IllegalArgumentException.class),      // TC007 
                Arguments.of("HELLO!", 17, null, IllegalArgumentException.class),       // TC008 
                Arguments.of("attack", 17, null, IllegalArgumentException.class)        // TC009 
        );
    }

    // -------------------- DECRYPTION --------------------
    @ParameterizedTest
    @MethodSource("decryptTestData")
    void testDecrypt(String cipherText, int key, String expected, Class<? extends Throwable> expectedException) {
        if (expectedException != null) {
            assertThrows(expectedException, () -> cipher.decrypt(cipherText, key));
        } else {
            assertEquals(expected, cipher.decrypt(cipherText, key));
        }
    }

    static Stream<Arguments> decryptTestData() {
        return Stream.of(
                // Valid cases
                Arguments.of("RKKRTB", 17, "ATTACK", null),   // TC001
                Arguments.of("XQQXZH", -3, "ATTACK", null),   // TC002
                Arguments.of("ATTACK", 0, "ATTACK", null),    // TC003
                Arguments.of("EXXEGO", 30, "ATTACK", null),   // TC004

                // Invalid cases
                Arguments.of(null, 17, null, IllegalArgumentException.class),          // TC005
                Arguments.of("", 17, null, IllegalArgumentException.class),            // TC006 (empty)
                Arguments.of("RKK RTB", 17, null, IllegalArgumentException.class),     // TC007
                Arguments.of("HELLO!", 17, null, IllegalArgumentException.class),      // TC008
                Arguments.of("attack", 17, null, IllegalArgumentException.class)       // TC009 
        );
    }
}