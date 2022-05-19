package me.jamieburns.tu17may;

import static me.jamieburns.TestSupport.tests;

import java.util.*;

import me.jamieburns.Test;

public class Chapter9Test {
    
    public static void main(String[] args) {
        new Chapter9Test().testsToRun();
    }
    @org.testng.annotations.Test
    private void testsToRun() {
        tests(
            new Test(() -> {
                // List - ORDERED collection where duplicate items ARE allowed
                // Set - collection where duplicate items are NOT allowed
                // Queue - specifically ORDERED collection
                // Map - KEY-VALUE pair collection where duplicate keys are NOT allowed

                // collection.add(E):boolean
                // collection.remove(Object):boolean
                // collection.isEmpty():boolean
                // collection.size():int
                // collection.clear():void
                // collection.contains(Object):boolean
                // collection.removeIf(Predicate<E>):boolean
                // collection.forEach(Consumer<E>):void
                // collection.equals(Collection<E>):boolean
                //   - list.equals(list2) -> true if same elements in the same order
                //   - set.equals(set2) = true if same elements
                //   - set.equals(list) -> false
                //   - list.equals(set) -> false

                Collection<Integer> c = new ArrayList<>();
                
                c.add(789); // returns boolean
                c.add(456);
                c.add(123);

                c.removeIf(i -> i % 2 == 0);

                System.out.println(c.contains(456));

                Collection<Integer> c2 = new ArrayList<>();

                c.forEach(i -> c2.add((i + i) * i - i));
                c.addAll(c2);

                System.out.println(c.size() == 4);

                // *** be aware of this trick on the exam

                c.remove(2); // remove the element at index 2
                c.remove(Integer.valueOf(2)); // remove the value 2

                return 0;}, 0),
            new Test(() -> {
                // Arrays.asList(...):List<Object>    - almost immutable - can replace
                // List.of(...):List<Object>          - immuatble
                // List.copyOf(Collection<E>):List<E> - immutable

                List<Integer> l = Arrays.asList(1, 2, 3, 3);
                List<Integer> l2 = List.of(1, 1, 2, 3, 5, 8);
                List<Number> l3 = List.copyOf(l2);

                l.set(3, 5); // zero-based index - replace 4th element with the value 5

                System.out.println(l);

                return 0;}, 0),
            new Test(() -> {
                // Constructors - using ArrayList as an example
                // new ArrayList<T>() - empty, 
                // new ArrayList<T>(list) - copy of the elements in list
                // new ArrayList<T>(size) - empty list of size elements

                return 0;}, 0),
            new Test(() -> {
                // list.replaceAll(UnaryOperator<E>):void
                // list.toArray(new E[0]):E[]

                List<Integer> li = Arrays.asList(1, 1, 2, 3, 5, 8, 13);

                li.replaceAll(i -> i % 2 == 0 ? --i : ++i);

                System.out.println(li);

                Integer[] ia = li.toArray(new Integer[0]);

                return 0;}, 0),
            new Test(() -> {
                // Queue interface
                //         | throws    |  value   | 
                // --------+-----------+----------+
                // insert  | add(e)    | offer(e) | offer returns false if e cant be added
                // remove  | remove()  | poll()   | poll returns null if our queue is empty
                // examine | element() | peek()   | peek returns null if our queue is empty
                //
                // see https://docs.oracle.com/en/java/javase/18/docs/api/java.base/java/util/Queue.html

                // Deque interface
                //         | throws    |  value   | 
                // --------+-----------+----------+
                // insert  | add(e)    | offer(e) | offer returns false if e cant be added
                // remove  | remove()  | poll()   | poll returns null if our queue is empty
                // examine | element() | peek()   | peek returns null if our queue is empty

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
}
