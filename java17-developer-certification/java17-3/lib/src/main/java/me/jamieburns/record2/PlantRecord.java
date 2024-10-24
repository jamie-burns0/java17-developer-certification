package me.jamieburns.record2;

public record PlantRecord(String commonName, String botanicalName, String height, String width, PlantType plantType, Origin origin, LandscapeSuitability... landscapeSuitability) {
    
    public PlantRecord {

        if (StringHelper.hasNoValue(commonName)) {
            throw new IllegalArgumentException("commonName cannot be empty, blank or null");
        }
        
        if (StringHelper.hasNoValue(botanicalName)) {
            throw new IllegalArgumentException("botanicalName cannot be empty, blank or null");
        }

        if (StringHelper.hasNoValue(height)) {
            throw new IllegalArgumentException("height cannot be empty, blank or null");
        }

        if (StringHelper.hasNoValue(width)) {
            throw new IllegalArgumentException("width cannot be empty, blank or null");
        }

        if (plantType == null) {
            plantType = PlantType.UNKNOWN;
        }

        if (origin == null) {
            origin = Origin.UNKNOWN;
        }

        if (landscapeSuitability == null || landscapeSuitability.length == 0) {
            LandscapeSuitability[] landscapeSuitabilityArray = new LandscapeSuitability[1];
            landscapeSuitabilityArray[0] = LandscapeSuitability.UNK;
            landscapeSuitability = landscapeSuitabilityArray;
        }
    }
}
