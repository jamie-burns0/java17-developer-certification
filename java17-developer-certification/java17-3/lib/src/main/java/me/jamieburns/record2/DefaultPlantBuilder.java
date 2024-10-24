package me.jamieburns.record2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DefaultPlantBuilder implements PlantBuilder {
    
    private class MyPlant {
        public String commonName;
        public String botanicalName;
        public String height;
        public String width;
        public PlantType plantType;
        public Origin origin;
        public List<LandscapeSuitability> landscapeSuitabilityList;
    };

    private MyPlant myPlant;

    public DefaultPlantBuilder() {

        myPlant = new MyPlant();

        myPlant.commonName = "UNKNOWN";
        myPlant.botanicalName = "UNKNOWN";
        myPlant.height = "UNKNOWN";
        myPlant.width = "UNKNOWN";
        myPlant.plantType = PlantType.UNKNOWN;
        myPlant.origin = Origin.UNKNOWN;
        myPlant.landscapeSuitabilityList = new ArrayList<>(1);
        myPlant.landscapeSuitabilityList.add(LandscapeSuitability.UNK);
    }

    @Override
    public Plant plant() {
        return new Plant(
            myPlant.commonName, 
            myPlant.botanicalName, 
            myPlant.height, 
            myPlant.width, 
            myPlant.plantType, 
            myPlant.origin, 
            myPlant.landscapeSuitabilityList.toArray(new LandscapeSuitability[2])
        );
    }

    @Override
    public PlantBuilder build() {
        return this;
    }

    @Override
    public PlantBuilder commonName(String commonName) {
        if (StringHelper.hasValue(commonName)) {
            myPlant.commonName = commonName;
        }        
        return this;
    }

    @Override
    public PlantBuilder botanicalName(String botanicalName) {
        if (StringHelper.hasValue(botanicalName)) {
            myPlant.botanicalName = botanicalName;
        }
        return this;
    }

    @Override
    public PlantBuilder height(String height) {
        if (StringHelper.hasValue(height)) {
            myPlant.height = height;
        }
        return this;
    }

    @Override
    public PlantBuilder width(String width) {
        if (StringHelper.hasValue(width)) {
            myPlant.width = width;
        }
        return this;
    }

    @Override
    public PlantBuilder plantType(PlantType plantType) {
        if (plantType != null) {
            myPlant.plantType = plantType;
        }
        return this;
    }

    @Override
    public PlantBuilder origin(Origin origin) {
        if (origin != null) {
            myPlant.origin = origin;
        }
        return this;
    }

    @Override
    public PlantBuilder landscapeSuitability(List<LandscapeSuitability> landscapeSuitabilityList) {
        if (landscapeSuitabilityList != null) {
            myPlant.landscapeSuitabilityList = landscapeSuitabilityList;
        }
        return this;
    }

    @Override
    public PlantBuilder landscapeSuitability(LandscapeSuitability... landscapeSuitabilityArray) {
        if (landscapeSuitabilityArray != null && landscapeSuitabilityArray.length > 0) {
            myPlant.landscapeSuitabilityList = Arrays.asList(landscapeSuitabilityArray);
        }
        return this;
    }
}
