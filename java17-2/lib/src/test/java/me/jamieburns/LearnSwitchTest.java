package me.jamieburns;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class LearnSwitchTest {
    @Test
    void testReturnDessertComment() {
        LearnSwitch ls = new LearnSwitch();
        assertTrue(ls.returnDessertComment(Dessert.CHOCOLATE) == "yum", "CHOCOLATE comment should return 'yum'");
    }

    @Test
    void testDessertDemand() {
        LearnSwitch ls = new LearnSwitch();
        assertTrue(ls.dessertDemand(Dessert.CHOCOLATE) == "high", "CHOCOLATE demand should return 'high'");
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testDessertDemandThrowsExceptionForNullValue() {
        LearnSwitch ls = new LearnSwitch();
        ls.dessertDemand(null);
    }

}
