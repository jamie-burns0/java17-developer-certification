package me.jamieburns.record;

import org.testng.annotations.*;
import static org.testng.Assert.*;

import java.util.Arrays;

public class ParentRecordTest {
    @Test 
    public void testParentRecordGetters() {
        ParentRecord pr = new ParentRecord("Harold", 55);
        assertTrue("harold".equals(pr.name()), "pr.name() should return 'Harold'");
        assertTrue(pr.age() == 55, "pr.age() should return '55'");
    }

    @Test(expectedExceptions = IllegalArgumentException.class) 
    public void testParentRecordNameGuard() {
        new ParentRecord(null, 55);
    }

    @Test(expectedExceptions = IllegalArgumentException.class) 
    public void testParentRecordNameGuard2() {
        new ParentRecord("", 55);
    }

    @Test(expectedExceptions = IllegalArgumentException.class) 
    public void testParentRecordNameGuard3() {
        char[] ca = new char[160];
        Arrays.fill(ca, 'a');
        StringBuilder sb = new StringBuilder(ca.length);
        sb.append(ca);        
        new ParentRecord(sb.toString(), 55);
    }

    @Test(expectedExceptions = IllegalArgumentException.class) 
    public void testParentRecordAgeGuard() {
        new ParentRecord("Harold", 0);
    }

    @Test(expectedExceptions = IllegalArgumentException.class) 
    public void testParentRecordAgeGuard2() {
        new ParentRecord("Harold", 151);
    }

    @Test public void testParentRecordListRulesMethod() {
        ParentRecord pr = new ParentRecord("Harold", 55);
        assertTrue(pr.listRules() == "no TV");
    }
}
