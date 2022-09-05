package nl.kinokiru.java.inheritance.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.checkerframework.checker.index.qual.SubstringIndexBottom;

public abstract class Person {
    private String name;
    private int age;
    private final String ssn;
    private final Set<Person> friends;

    // #region Get/Setters
    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getSsn() {
        return ssn;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }
    // #endregion

    public Person(String ssn) {
        this.ssn = ssn;
        this.friends = new HashSet<>();
    }

    public Person(String ssn, int age, String name) {
        this.age = age;
        this.ssn = ssn;
        this.name = name;
        this.friends = new HashSet<>();
    }

    public void addFriend(Person friend) {
        if (!this.hasFriend(friend) && !friend.equals(this)) {
            this.friends.add(friend);
        }

        if (!friend.hasFriend(this) && !friend.equals(this)) {
            friend.friends.add(this);
        }
    }

    public boolean removeFriend(Person friend) {
        return this.friends.remove(friend);
    }

    public boolean hasFriend(Person friend) {
        return this.friends.contains(friend);
    }

    @Override
    public boolean equals(Object arg0) {
        return arg0 instanceof Person && ((Person) arg0).ssn == this.ssn;
    }

    @Override
    public String toString() {
        return "name:" + name;
    }

    public abstract void sayGreetings();

    /**
     * @param target to look for
     * @return path from user to target
     */
    public String getCorrelationPath(Person target) {

        ArrayList<Person> path = recursiveDFS(target, new HashSet<>());

        if (path.isEmpty()) {
            return "No correlation found between " + target.name + " and " + this.name;
        }

        StringBuilder str = new StringBuilder();
        for (Person person : path) {
            str.append(person.name).append(" -> ");
        }

        return str.toString().substring(0, str.length() - 4);
    }

    /**
     * @param target  target to look for
     * @param visited
     * @return path from user to target
     *         path starts at targets and backtracks to user!
     */
    private ArrayList<Person> recursiveDFS(Person target, HashSet<Person> visited) {
        visited.add(this);
        ArrayList<Person> path = new ArrayList<>();

        // if target is the same as the current person instance
        if (this.equals(target)) {
            path.add(0, this);
            return path;
        }

        for (Person friend : this.friends) {
            if (!visited.contains(friend)) {
                path = friend.recursiveDFS(target, visited);
                if (!path.isEmpty()) {
                    // if the target is found add person before to path
                    path.add(0, this);
                    return path;
                }
            }
        }
        return path;
    }
}
