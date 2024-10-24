package me.jamieburns.tu10may;

public class Chimpanzee extends Ape {
   public Chimpanzee() {
       super(2);
   }
}

class Primate {}

class Ape extends Primate {
    public Ape(int i) {}
    // public Ape() {}
}
