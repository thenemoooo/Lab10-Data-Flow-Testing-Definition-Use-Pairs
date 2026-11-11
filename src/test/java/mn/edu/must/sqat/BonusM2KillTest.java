package mn.edu.must.sqat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Хэсэг C: M2 (best = present) мутацийг унагах нэмэлт тест.
 * all-DU-pairs тестүүд M2-ийг барьж чадахгүй (бүх хос хамрагдсан ч
 * 43-р мөрөн дээр present == streak байсан) — README C хэсэг.
 */
class BonusM2KillTest {

    @Test
    @DisplayName("B10 M2-kill: bonus([1,1,0,1,1,1,1],7)=0 - present=6, best streak=4; M2 best=present reaches 5 and adds +3, must fail")
    void b10_m2kill_presentDiffersFromStreak() {
        assertEquals(0, DataFlowTargets.bonus(new int[]{1, 1, 0, 1, 1, 1, 1}, 7));
    }
}