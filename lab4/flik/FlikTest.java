package flik;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class FlikTest {
    @Test
    public void sameNumberTest() {
        int i = 0;
        for (int j = 0; j < 500; j++, i++) {
            assertTrue(Flik.isSameNumber(i, j));
        }
    }
}
