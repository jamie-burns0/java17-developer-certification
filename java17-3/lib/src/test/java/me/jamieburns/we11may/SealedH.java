package me.jamieburns.we11may;

public sealed class SealedH { // we can omit permits when the classes are all nested
    final class SealedI extends SealedH {}
    non-sealed class SealedJ extends SealedH {}
}
