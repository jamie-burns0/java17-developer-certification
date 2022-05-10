package me.jamieburns.mo09may;

public class TopLevelClass {
    // cam only have ONE top-level class in a *.java file

    public class NestedPublicClass {}
    class NestedPackageAccessClass {}
    protected class NestedProtectedAccessClass {}
    private class NestedPrivateAccessClass {}

    {
        new NestedPrivateAccessClass(); // make the compiler warning go away
    }
}

// cannot have this in the same file
//
// public class AnotherTopLevelClass {}
// protected class AnotherTopLevelClass {}
// private class AnotherTopLevelClass {}

// but can have package/default access classes in the same file
class SomePackageAccessClass {}
class AnotherPackageClass {}

