package mn.edu.must.sqat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Хэсэг A: gcd()-ийн all-DU-pairs хамралт.
 * Мөрийн дугаар нь DataFlowTargets.java файлын бодит мөр:
 * 13 def x,y | 15 p-use y | 16 c-use x,y + def tmp | 17 c-use y + def x
 * 18 c-use tmp + def y | 20 c-use x.
 * Хос бүрийг аль тест хамарч буйг @DisplayName-д тэмдэглэв;
 * бүрэн хүснэгт README.md-ийн A хэсэгт.
 */
class GcdTest {

    @Test
    @DisplayName("G1 gcd(7,0): (x,13->20,c), (y,13->15,p,F) - loop 0 iterations")
    void g1_zeroY_coversEntryDefs() {
        assertEquals(7, DataFlowTargets.gcd(7, 0));
    }

    @Test
    @DisplayName("G2 gcd(0,5): (y,13->16,c), (y,13->17,c), (tmp,16->18,c), (x,17->20,c), (y,18->15,p,F) - loop 1 iteration")
    void g2_zeroX_coversFirstIterationDefs() {
        assertEquals(5, DataFlowTargets.gcd(0, 5));
    }

    @Test
    @DisplayName("G3 gcd(12,8): (x,13->16,c), (y,15->15,p,T), (x,17->16,c), (y,18->16,c), (y,18->17,c) - loop 2 iterations")
    void g3_twoIterations_coversLoopBackPairs() {
        assertEquals(4, DataFlowTargets.gcd(12, 8));
    }
}