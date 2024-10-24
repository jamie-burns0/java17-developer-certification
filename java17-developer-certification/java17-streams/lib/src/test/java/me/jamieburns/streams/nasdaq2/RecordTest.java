package me.jamieburns.streams.nasdaq2;

class RecordTest {
    
    static record ExplicitlyStaticMember() {
        static record NestedExplicitlyStatic_ExplicitlyStaticMember() {}
        record NestedImplicitlyStatic_ExplicitlyStaticMember() {}
    }

    record ImplicitlyStaticMember() {
        static record NestedExplicitlyStatic_ImplicitlyStaticMember() {}
        record NestedImplicitlyStatic_ImplicitlyStaticMember() {}
    }

    public static void main(String[] args) {

    }

    public static void staticMethod() {
        record ImplicitlyStatic_Local() {}
        // static record ExplicitlyStatic_Local() {}
        Lambda fn = () -> {
            record LambdaRecord() {}
            new LambdaRecord();
        };
    }

    public void nonStaticMethod() {
        record ImplicitlyStatic_Local() {}
        //static record ExplicitlyStatic_Local() {}

    }

    public void lambdaMethod() {
        Lambda fn = () -> {
            record LambdaRecord() {};
            new LambdaRecord();
        };

        new TopLevelLambdaRecord(() -> {
            record LambdaRecord() {};
            new LambdaRecord();
        });
    }
}

record TopLevel(String a) {
    static record NestedExplicitlyStatic_TopLevel() {}
    record NestedImplicitlyStatic_TopLevel() {}
    
}

@FunctionalInterface
interface Lambda {
    void fn();
}

record TopLevelLambdaRecord(Lambda fn) {}

class TopLevelLambdaClass {
    Lambda fn;
    TopLevelLambdaClass(Lambda fn) {this.fn = fn;}
}