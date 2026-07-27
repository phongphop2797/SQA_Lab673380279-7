//นายปองภพ ศรีรักษ์ 673380279-7 sec.1
package Test.Lab5_2;

import model_Competition.CompetitionScore;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


public class CompetitionScoreTest {

    private final CompetitionScore comp = new CompetitionScore();

    //// ----- findMaxScore(int, int, int) -----
    @ParameterizedTest
    @MethodSource("threeParamsTestData")
    void testFindMaxScoreThreeParams(int score1, int score2, int score3,
                                     int expected, Class<? extends Throwable> expectedException) {
        if (expectedException != null) {
            assertThrows(expectedException, () -> comp.findMaxScore(score1, score2, score3));
        } else {
            assertEquals(expected, comp.findMaxScore(score1, score2, score3));
        }
    }

    static Stream<Arguments> threeParamsTestData() {
        // 27 combinations for Strong Robust:
        // Each parameter can be: valid (250), below range (-1), above range (501)
        return Stream.of(
                // 1) score1 = valid (250)
                Arguments.of(250, 250, 250, 250, null),   // TC001
                Arguments.of(250, 250, -1, 0, IllegalArgumentException.class),   // TC002
                Arguments.of(250, 250, 501, 0, IllegalArgumentException.class),   // TC003
                Arguments.of(250, -1, 250, 0, IllegalArgumentException.class),    // TC004
                Arguments.of(250, -1, -1, 0, IllegalArgumentException.class),     // TC005
                Arguments.of(250, -1, 501, 0, IllegalArgumentException.class),    // TC006
                Arguments.of(250, 501, 250, 0, IllegalArgumentException.class),   // TC007
                Arguments.of(250, 501, -1, 0, IllegalArgumentException.class),    // TC008
                Arguments.of(250, 501, 501, 0, IllegalArgumentException.class),   // TC009

                // 2) score1 = below range (-1)
                Arguments.of(-1, 250, 250, 0, IllegalArgumentException.class),    // TC010
                Arguments.of(-1, 250, -1, 0, IllegalArgumentException.class),     // TC011
                Arguments.of(-1, 250, 501, 0, IllegalArgumentException.class),    // TC012
                Arguments.of(-1, -1, 250, 0, IllegalArgumentException.class),     // TC013
                Arguments.of(-1, -1, -1, 0, IllegalArgumentException.class),      // TC014
                Arguments.of(-1, -1, 501, 0, IllegalArgumentException.class),     // TC015
                Arguments.of(-1, 501, 250, 0, IllegalArgumentException.class),    // TC016
                Arguments.of(-1, 501, -1, 0, IllegalArgumentException.class),     // TC017
                Arguments.of(-1, 501, 501, 0, IllegalArgumentException.class),    // TC018 (corrected EC ID, input stays -1,501,501)

                // 3) score1 = above range (501)
                Arguments.of(501, 250, 250, 0, IllegalArgumentException.class),   // TC019
                Arguments.of(501, 250, -1, 0, IllegalArgumentException.class),    // TC020
                Arguments.of(501, 250, 501, 0, IllegalArgumentException.class),   // TC021
                Arguments.of(501, -1, 250, 0, IllegalArgumentException.class),    // TC022
                Arguments.of(501, -1, -1, 0, IllegalArgumentException.class),     // TC023
                Arguments.of(501, -1, 501, 0, IllegalArgumentException.class),    // TC024
                Arguments.of(501, 501, 250, 0, IllegalArgumentException.class),   // TC025
                Arguments.of(501, 501, -1, 0, IllegalArgumentException.class),    // TC026
                Arguments.of(501, 501, 501, 0, IllegalArgumentException.class)    // TC027 (corrected to 501,501,501)
        );
    }

    // ----- findMaxScore(int[]) -----
    @ParameterizedTest
    @MethodSource("arrayTestData")
    void testFindMaxScoreArray(int[] scores, int expected, Class<? extends Throwable> expectedException) {
        if (expectedException != null) {
            assertThrows(expectedException, () -> comp.findMaxScore(scores));
        } else {
            assertEquals(expected, comp.findMaxScore(scores));
        }
    }

    static Stream<Arguments> arrayTestData() {
        return Stream.of(
                // Valid
                Arguments.of(new int[]{100, 200, 300}, 300, null),          // TC028
                Arguments.of(new int[]{0, 0, 0}, 0, null),                 // TC034
                Arguments.of(new int[]{500, 500, 500}, 500, null),         // TC035
                Arguments.of(new int[]{0, 250, 500}, 500, null),           // TC036

                // Invalid array cases
                Arguments.of(null, 0, IllegalArgumentException.class),               // TC029
                Arguments.of(new int[]{100, 200}, 0, IllegalArgumentException.class), // TC030
                Arguments.of(new int[]{100, 200, 300, 400}, 0, IllegalArgumentException.class), // TC031
                Arguments.of(new int[]{-1, 100, 200}, 0, IllegalArgumentException.class), // TC032
                Arguments.of(new int[]{100, 501, 200}, 0, IllegalArgumentException.class)  // TC033
        );
    }
}