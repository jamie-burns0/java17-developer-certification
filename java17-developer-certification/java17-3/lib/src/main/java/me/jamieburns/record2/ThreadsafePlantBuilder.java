package me.jamieburns.record2;

import java.util.List;

public class ThreadsafePlantBuilder implements PlantBuilder {
    
    // put our instance of myPlant on ThreadLocal so that we are threadsafe and can use the one
    // instance of ThreadsafePlantBuilder on multiple threads. Otherwise, if we use multiple 
    // threads we have to guarantee that each thread will have its own DefaultPlantBuilder -
    // which is ok, but this is a good exercise in refreshing threading in Java

    private static ThreadLocal<PlantBuilder> tlpb = new ThreadLocal<>();

    public ThreadsafePlantBuilder() {
        tlpb.set(new DefaultPlantBuilder());        
    }

    @Override
    public Plant plant() {
        return tlpb.get().plant();
    }

    @Override
    public PlantBuilder build() {
        return tlpb.get().build();
    }

    @Override
    public PlantBuilder commonName(String commonName) {
        return tlpb.get().commonName(commonName);
    }

    @Override
    public PlantBuilder botanicalName(String botanicalName) {
        return tlpb.get().botanicalName(botanicalName);
    }

    @Override
    public PlantBuilder height(String height) {
        return tlpb.get().height(height);
    }

    @Override
    public PlantBuilder width(String width) {
        return tlpb.get().width(width);
    }

    @Override
    public PlantBuilder plantType(PlantType plantType) {
        return tlpb.get().plantType(plantType);
    }

    @Override
    public PlantBuilder origin(Origin origin) {
        return tlpb.get().origin(origin);
    }

    @Override
    public PlantBuilder landscapeSuitability(List<LandscapeSuitability> landscapeSuitabilityList) {
        return tlpb.get().landscapeSuitability(landscapeSuitabilityList);
    }

    @Override
    public PlantBuilder landscapeSuitability(LandscapeSuitability... landscapeSuitabilityArray) {
        return tlpb.get().landscapeSuitability(landscapeSuitabilityArray);
    }
}
