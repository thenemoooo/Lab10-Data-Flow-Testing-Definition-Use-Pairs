package mn.edu.must.sqat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Хэсэг B: bonus()-ийн тестүүд.
 * Шат 1 (all-defs): b1, b2 — хамгийн цөөн 2 тест (үндэслэл README B4).
 * Шат 2 (all-DU-pairs): b3-b9 нэмэгдэнэ.
 * Мөрийн дугаар нь DataFlowTargets.java файлын бодит мөр (31-56).
 */
class BonusTest {

    @Test
    @DisplayName("B1 all-defs: bonus([],0)=5 - defs 35,36,37,49,51 -> uses 50T,53F,56; attendance p-use 38F")
    void b1_allDefs_emptyArray() {
        assertEquals(5, DataFlowTargets.bonus(new int[]{}, 0));
    }

    @Test
    @DisplayName("B2 all-defs: bonus([1x6],5)=8 - defs 38a,40,41,43,51,54 -> uses 39,40,41,42,43,50,53,54,56")
    void b2_allDefs_sixStreak() {
        assertEquals(8, DataFlowTargets.bonus(new int[]{1, 1, 1, 1, 1, 1}, 5));
    }

    @Test
    @DisplayName("B5 boundary: bonus(null,0) throws IllegalArgumentException - 32T short-circuit")
    void b5_nullInput_throws() {
        assertThrows(IllegalArgumentException.class, () -> DataFlowTargets.bonus(null, 0));
    }

    @Test
    @DisplayName("B6 boundary: bonus([1],-1) throws IllegalArgumentException - 32F||T")
    void b6_negativeThreshold_throws() {
        assertThrows(IllegalArgumentException.class, () -> DataFlowTargets.bonus(new int[]{1}, -1));
    }

    @Test
    @DisplayName("B3 all-DU-pairs: bonus([],7)=0 - (present,35->50,F), (threshold,31->50,F), (score,49->56,c)")
    void b3_emptyArray_belowThreshold() {
        assertEquals(0, DataFlowTargets.bonus(new int[]{}, 7));
    }

    @Test
    @DisplayName("B4 all-DU-pairs: bonus([0,0,0],1)=0 - (a,38->39,F), streak def 46, 39F branch")
    void b4_allAbsent() {
        assertEquals(0, DataFlowTargets.bonus(new int[]{0, 0, 0}, 1));
    }

    @Test
    @DisplayName("B7 all-DU-pairs: bonus([1,1,1],3)=5 - (present,40->50,T), (best,43->53,F), (score,51->56,c), (threshold,31->50,T)")
    void b7_exactThreshold_shortStreak() {
        assertEquals(5, DataFlowTargets.bonus(new int[]{1, 1, 1}, 3));
    }

    @Test
    @DisplayName("B8 all-DU-pairs: bonus([1,1,1,1,1,1,0,1,1,1,1,1,1],6)=8 - (streak,46->41,c), (streak,46->42,T) after kill at 46")
    void b8_longStreakBreak() {
        assertEquals(8, DataFlowTargets.bonus(new int[]{1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1}, 6));
    }

    @Test
    @DisplayName("B9 all-DU-pairs: bonus([1],0)=5 - threshold=0 boundary, (threshold,31->32,F), (present,40->50,T)")
    void b9_thresholdZero() {
        assertEquals(5, DataFlowTargets.bonus(new int[]{1}, 0));
    }
}