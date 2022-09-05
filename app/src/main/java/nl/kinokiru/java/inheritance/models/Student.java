package nl.kinokiru.java.inheritance.models;

public class Student extends Person {

    public boolean forcedToLearn() {
        return this.getAge() < 18;
    }

    public Student(String ssn) {
        super(ssn);
    }

    public Student(String ssn, int age, String name) {
        super(ssn, age, name);
    }

    @Override
    public void sayGreetings() {
        System.out.println("Yo Fakka JE MOEDER");
    }

}
