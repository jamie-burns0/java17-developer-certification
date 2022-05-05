package me.jamieburns.record;

public record ParentRecord(String name, int age) implements ParentInterface {
    public ParentRecord {
        if (age <= 0 || age > 150) {
            throw new IllegalArgumentException("please provide a reasonable age");
        }
        if (name == null || name.length() == 0 || name.length() > 150) {
            throw new IllegalArgumentException("please provide a name");
        }
        name = name.toLowerCase();
    }

    @Override
    public String listRules() {
        return "no TV";
    }
}
