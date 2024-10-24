package me.jamieburns.we11may;

public sealed interface SealedInterfaceA permits SealedA, SealedInterfaceB {}
// a sealed interface can permit a sealed class but not the other way around.
// This is because an interface can't extend a class
//
// class A {}
// interface B extends A {} <-- we can't do this
