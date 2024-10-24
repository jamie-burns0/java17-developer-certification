package me.jamieburns.tu10may;

import java.util.Arrays;
import java.util.List;

public final class Immutable {
    public static void main(String[] args) {
        List<Object> ol = Arrays.asList(new Object[] {new Object(), new Object()});
        ImmutableList<Object> iol = new ImmutableList<>(ol);
        for (Object o : ol) {
            System.out.println(o);
        }
        for (Object o : iol.unmodifiableList()) {
            System.out.println(o);
        }
        List<Object> uol = iol.unmodifiableList();
        uol.add(new Object()); // throws UnsupportedOperationException
    }
}

final class ImmutableList<T> {
    private final List<T> idList;
    public ImmutableList(List<T> idList) {
        if (idList == null) {
            throw new IllegalArgumentException();
        }
        this.idList = List.copyOf(idList);
    }
    public List<T> unmodifiableList() {
        return idList;
    }
}
