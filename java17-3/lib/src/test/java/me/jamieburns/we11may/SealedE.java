package me.jamieburns.we11may;

public sealed class SealedE {} // we can omit permits when our classes are in the same compilation unit

final class SealedF extends SealedE {}

non-sealed class SealedG extends SealedE {}
