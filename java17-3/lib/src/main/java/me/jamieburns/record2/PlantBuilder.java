package me.jamieburns.record2;

import java.util.List;

public interface PlantBuilder {
    Plant plant();
    PlantBuilder build();
    PlantBuilder commonName(String commonName);
    PlantBuilder botanicalName(String botanicalName);
    PlantBuilder height(String height);
    PlantBuilder width(String width);
    PlantBuilder plantType(PlantType plantType);
    PlantBuilder origin(Origin origin);
    PlantBuilder landscapeSuitability(List<LandscapeSuitability> landscapeSuitabilityList);
    PlantBuilder landscapeSuitability(LandscapeSuitability... landscapeSuitabilityArray);
}
