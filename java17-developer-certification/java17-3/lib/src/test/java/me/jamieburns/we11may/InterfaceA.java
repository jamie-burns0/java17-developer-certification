package me.jamieburns.we11may;

public interface InterfaceA { // abstract
    int A = 1; // public static final
    int C = 11;
    void a(); // public abstract  
    void c();
    Number d();
    default Integer e() {return forE();} // public
    static Integer f() {return forF();} // public
    private Integer forE() {return 111;}
    private static Integer forF() {return 1111;} // can call as forF() or InterfaceA.forF() - both work
}

interface InterfaceB {
    int B = 2;
    int C = 22;
    void b();
    void c();
    Integer d(); // return type must be covariant with return type of InterfaceA.d()
    default Integer e() {return 222;} // interface can have default methods.
                                      // Classes implementing our interface don't have to override this method  
    static Integer f() {return 2222;} // interface an have static methods
}

interface InterfaceC extends InterfaceA, InterfaceB {
    default Integer e() {
        doNothing();
        return InterfaceA.super.e() + InterfaceB.super.e();} // compiler forces us to provide this to clear up ambiguity
                                                             // - both InterfaceA and InterfaceB have a default Integer e() method
    static Integer f() {return InterfaceA.f() + InterfaceB.f();}
    private void doNothing() {a(); b(); c();}; // we can call our abstract methods
}

