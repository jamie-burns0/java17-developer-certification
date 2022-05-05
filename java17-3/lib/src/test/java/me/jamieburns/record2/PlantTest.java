package me.jamieburns.record2;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

public class PlantTest {

    @Test 
    public void canCreateWithNonNullValues() {
        PlantRecord p = new PlantRecord("Gossamer Wattle", "Acacia floribunda", "3-8", "2-3", PlantType.TREE, Origin.LOCAL, LandscapeSuitability.G, LandscapeSuitability.OS);
        assertTrue("Gossamer Wattle".equals(p.commonName()));
        assertTrue("Acacia floribunda".equals(p.botanicalName()));
        assertTrue("3-8".equals(p.height()));
        assertTrue("2-3".equals(p.width()));
        assertTrue(PlantType.TREE.equals(p.plantType()));
        assertTrue(Origin.LOCAL.equals(p.origin()));
        assertTrue(p.landscapeSuitability().length == 2);
        assertTrue(LandscapeSuitability.G.equals(p.landscapeSuitability()[0]));
        assertTrue(LandscapeSuitability.OS.equals(p.landscapeSuitability()[1]));
    }

    @Test 
    public void canCreateWithOptionalValues() {
        PlantRecord p = new PlantRecord("Gossamer Wattle", "Acacia floribunda", "3-8", "2-3", null, null);
        assertTrue("Gossamer Wattle".equals(p.commonName()));
        assertTrue("Acacia floribunda".equals(p.botanicalName()));
        assertTrue("3-8".equals(p.height()));
        assertTrue("2-3".equals(p.width()));
        assertTrue(PlantType.UNKNOWN.equals(p.plantType()));
        assertTrue(Origin.UNKNOWN.equals(p.origin()));
        assertTrue(p.landscapeSuitability().length == 1);
        assertTrue(LandscapeSuitability.UNK.equals(p.landscapeSuitability()[0]));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void cannotCreateWithNoCommonName() {
        new PlantRecord(null, null, null, null, null, null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void cannotCreateWithNoBotanicalName() {
        new PlantRecord("commonName", null, null, null, null, null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void cannotCreateWithNoHeightName() {
        new PlantRecord("commonName", "botanicalName", null, null, null, null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void cannotCreateWithNoWidthName() {
        new PlantRecord("commonName", "botanicalName", "3-8", null, null, null);
    }

    @Test 
    public void canCreateWithOptionalValuesUsingDefaultPlantBuilder() {

        PlantBuilder pb = new DefaultPlantBuilder()
            .commonName("Gossamer Wattle")
            .botanicalName("Acacia floribunda")
            .height("3-8")
            .width("2-3")
            .plantType(PlantType.TREE)
            .origin(Origin.LOCAL)
            .landscapeSuitability(LandscapeSuitability.G, LandscapeSuitability.OS)
            .build();

        Plant p = pb.plant();

        assertTrue("Gossamer Wattle".equals(p.commonName()));
        assertTrue("Acacia floribunda".equals(p.botanicalName()));
        assertTrue("3-8".equals(p.height()));
        assertTrue("2-3".equals(p.width()));
        assertTrue(PlantType.TREE.equals(p.plantType()));
        assertTrue(Origin.LOCAL.equals(p.origin()));
        assertTrue(p.landscapeSuitability().length == 2);
        assertTrue(LandscapeSuitability.G.equals(p.landscapeSuitability()[0]));
        assertTrue(LandscapeSuitability.OS.equals(p.landscapeSuitability()[1]));
    }

    @Test 
    public void canCreateWithOptionalValuesUsingThreadsafePlantBuilder() {

        Thread t1 = new Thread(() -> {

            PlantBuilder pb = new ThreadsafePlantBuilder()
                .commonName("Gossamer Wattle")
                .botanicalName("Acacia floribunda")
                .height("3-8")
                .width("2-3")
                .plantType(PlantType.TREE)
                .origin(Origin.LOCAL)
                .landscapeSuitability(LandscapeSuitability.G, LandscapeSuitability.OS)
                .build();

            Plant p = pb.plant();
            System.out.println("t1: " + p.toString());
            assertTrue("Gossamer Wattle".equals(p.commonName()));
            assertTrue("Acacia floribunda".equals(p.botanicalName()));
            assertTrue("3-8".equals(p.height()));
            assertTrue("2-3".equals(p.width()));
            assertTrue(PlantType.TREE.equals(p.plantType()));
            assertTrue(Origin.LOCAL.equals(p.origin()));
            assertTrue(p.landscapeSuitability().length == 2);
            assertTrue(LandscapeSuitability.G.equals(p.landscapeSuitability()[0]));
            assertTrue(LandscapeSuitability.OS.equals(p.landscapeSuitability()[1]));
        });

        Thread t2 = new Thread(() -> {

            PlantBuilder pb = new ThreadsafePlantBuilder()
                .commonName("Two-Veined Hickory")
                .botanicalName("Acacia binervata")
                .height("10-15")
                .width("2-4")
                .plantType(PlantType.TREE)
                .origin(Origin.LOCAL)
                .landscapeSuitability(LandscapeSuitability.S, LandscapeSuitability.G, LandscapeSuitability.OS)
                .build();

            Plant p = pb.plant();
            System.out.println("t2: " + p.toString());
            assertTrue("Two-Veined Hickory".equals(p.commonName()));
            assertTrue("Acacia binervata".equals(p.botanicalName()));
            assertTrue("10-15".equals(p.height()));
            assertTrue("2-4".equals(p.width()));
            assertTrue(PlantType.TREE.equals(p.plantType()));
            assertTrue(Origin.LOCAL.equals(p.origin()));
            assertTrue(p.landscapeSuitability().length == 3);
            assertTrue(LandscapeSuitability.S.equals(p.landscapeSuitability()[0]));
            assertTrue(LandscapeSuitability.G.equals(p.landscapeSuitability()[1]));
            assertTrue(LandscapeSuitability.OS.equals(p.landscapeSuitability()[2]));
        });

        t1.start();
        t2.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
