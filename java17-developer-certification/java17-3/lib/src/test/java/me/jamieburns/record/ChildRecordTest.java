package me.jamieburns.record;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

public class ChildRecordTest {
    @Test 
    public void testChildRecordGetters() {
        ChildRecord cr = new ChildRecord("Harold", 5);
        assertTrue("Harold".equals(cr.name()), "pr.name() should return 'Harold'");
        assertTrue(cr.age() == 5, "pr.age() should return '55'");
    }

    @Test
    public void testChildRecordListDemandsMethod() {
        ChildRecord cr = new ChildRecord("Harold", 2);
        assertTrue(cr.listDemands() == "PEPPA PIG!");

        cr = new ChildRecord("Harold", 5);
        assertTrue(cr.listDemands() == "ELANOR OF AVALAR!");

        cr = new ChildRecord("Harold", 9);
        assertTrue(cr.listDemands() == "TV! TV!");
    }
}
