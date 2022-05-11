package me.jamieburns.we11may;

public sealed class SealedA implements SealedInterfaceA permits SealedB, SealedC {} // must have permits when the classes are in their own compilation units
