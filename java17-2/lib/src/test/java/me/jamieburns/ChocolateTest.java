package me.jamieburns;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

public class ChocolateTest {

    @Test public void testHowGoodIsChocolateMethod() {
        Chocolate c = new Chocolate();
        assertTrue(c.howGoodIsChocolate() == "really good", "howGoodIsChocolate should return 'really good'");
    }
}
