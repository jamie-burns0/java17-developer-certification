package me.jamieburns.mo09may;

public class ThisAndSuperClass {
    public int a = 1;
    public class NestedA {
        public int a = 2;
        public int aThis() {return this.a;}
        public int aOuter() {return ThisAndSuperClass.this.a;}
    }
    public class NestedB extends NestedA{
        public int a = 3;
        public class NestedC {
            public int a = 4;
            public int aThis() {return a;}
            public int aOuter1() {return NestedB.this.a;}
            public int aOuter2() {return ThisAndSuperClass.this.a;}
            public int methodOuter1() {return NestedB.this.aSuper();}
        }
        public int aThis() {return a;}
        public int aSuper() {return super.a;}
        public int aOuter() {return ThisAndSuperClass.this.a;}
    }

    public static void main(String[] args) {
        var c = new ThisAndSuperClass().new NestedA();
        System.out.println(c.aThis());
        System.out.println(c.aOuter());

        var d = new ThisAndSuperClass().new NestedB();
        System.out.println(d.aThis());
        System.out.println(d.aSuper());
        System.out.println(d.aOuter());
        
        System.out.println("...");

        var f = new ThisAndSuperClass().new NestedB().new NestedC();
        System.out.println(f.a);
        System.out.println(f.aThis());
        System.out.println(f.aOuter1());
        System.out.println(f.aOuter2());
        System.out.println(f.methodOuter1());

        System.out.println("xxx");

        var e = new C();
        System.out.println(e.a);
        System.out.println(((B)e).a);
        System.out.println(((A)e).a);
        System.out.println("---");
        System.out.println(e.b());
        System.out.println(e.c());
        //System.out.println(((B)e).b());
    }
}

class A {
    int a = 1;
    public int b() {return a;}
}
class B extends A {
    int a = 2;
    public int b() {return super.b();}
}
class C extends B {
    int a = 3;
    public int b() {return super.b();}
    public int c() {return ((A)this).a;}
}

