package me.jamieburns.we11may;

import me.jamieburns.Test;

import static me.jamieburns.TestSupport.tests;

public class Chapter7Test {
    
    public static void main(String[] args) {
        new Chapter7Test().testsToRun();
    }

    @org.testng.annotations.Test
    public void testsToRun() {

        tests(
            new Test(() -> {
                var a = new InterfaceA() {
                    @Override
                    public void a() {}
                    @Override
                    public void c() {System.out.println(C);}
                    @Override
                    public Number d() {return 1F;}
                }; 

                var b = new InterfaceB() {
                    @Override
                    public void b() {}
                    @Override
                    public void c() {System.out.println(C);} // hides InterfaceA.c()
                    @Override
                    public Integer d() {return 2;}
                };

                var c = new InterfaceC() {
                    @Override
                    public void a() {} // implements InterfaceA.a()
                    @Override
                    public void b() {} // implements InterfaceB.b()
                    @Override
                    public void c() {System.out.println(InterfaceA.C + "-" + InterfaceB.C);}
                    @Override
                    public Integer d() {return 3;}
                };

                a.c();
                b.c();
                c.c();

                InterfaceA d = c;
                d.c(); // still prints "11-22" because the actual object c is referencing is an InterfaceC
                       // where, in this case, InterfaceC.c() hides both InterfaceA.c() and InterfaceB.c()

                //InterfaceA e = (InterfaceA)c; // compile-time OK
                //e.c(); // runtime NOT OK

                System.out.println(a.d());
                System.out.println(b.d());
                System.out.println(c.d());

                System.out.println(a.e());
                System.out.println(b.e());
                System.out.println(c.e());

                System.out.println(InterfaceA.f());
                System.out.println(InterfaceB.f());
                System.out.println(InterfaceC.f());

                return 0;}, 0),
            new Test(() -> {
                for (EnumA e : EnumA.values()) {
                    var s = switch(e) {
                        case APPLE -> EnumA.APPLE + "tree";
                        case AFTER -> EnumA.AFTER + "dark";
                        case ARK -> EnumA.ARK + "royal";
                    };
                    System.out.println(s);
                }

                for (EnumB e : EnumB.values()) {
                    var s = switch(e) {
                        case BRAN -> {yield EnumB.BRAN.use();}
                        case BOT -> {yield EnumB.BOT.use();}
                        case BUBBLE -> {yield EnumB.BUBBLE.use();}
                    };
                    System.out.println(s);
                }

                for (EnumC e : EnumC.values()) {
                    var s = switch(e) {
                        case CACOPHNY -> EnumC.CACOPHNY + "-" + EnumC.CACOPHNY.association();
                        case COPPER -> EnumC.COPPER + "-" + EnumC.COPPER.association();
                        case CATCH -> EnumC.CATCH + "-" + EnumC.CATCH.association();
                    };
                    System.out.println(s);
                }

                for (EnumD e : EnumD.values()) {
                    Reverser r = switch(e) {
                        case DESSERT -> e; // EnumD implements Reverser
                        case DREADNOUGHT -> e;
                        case DROUGHT -> e;
                    };
                    System.out.println(r.reverse());
                }
                
                return 0;}, 0),
            new Test(() -> {
                RecordA a = new RecordA("A");
                System.out.println(a.a());

                RecordA a2 = new RecordA("A");
                System.out.println(a.equals(a2));

                RecordB b = new RecordB("bbBBbbBBbBbB");
                System.out.println(b.b());

                Reverser c = new RecordC("GobbledeGook");
                System.out.println(c.reverse());

                var d = new RecordD("jamie", "burns");
                System.out.println(d.d());

                return 0;}, 0),
            new Test(() -> {return 0;}, 0),
            new Test(() -> {return 0;}, 0),
            new Test(() -> {return 0;}, 0),
            new Test(() -> {return 0;}, 0),
            new Test(() -> {return 0;}, 0),
            new Test(() -> {return 0;}, 0),
            new Test(() -> {return 0;}, 0),
            new Test(() -> {return 0;}, 0),
            new Test(() -> {return 0;}, 0),
            new Test(() -> {return 0;}, 0),
            new Test(() -> {return 0;}, 0),
            new Test(() -> {return 0;}, 0)
        );
    }

    public void a() {
        var a = switch(EnumA.AFTER) {
            case APPLE -> "green";
            case AFTER -> "dark";
            case ARK -> "shark";
        };
    }
}
