package me.jamieburns.record2;

public record Plant(String commonName, String botanicalName, String height, String width, PlantType plantType, Origin origin, LandscapeSuitability... landscapeSuitability) {}