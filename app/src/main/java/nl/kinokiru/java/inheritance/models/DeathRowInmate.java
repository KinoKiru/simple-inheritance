package nl.kinokiru.java.inheritance.models;

public class DeathRowInmate extends Person {

    private int daysLeftToLive;

    public DeathRowInmate(String ssn, int daysLeftToLive) {
        super(ssn);
        this.daysLeftToLive = daysLeftToLive;
    }

    public DeathRowInmate(String ssn, int age, String name, int daysLeftToLive) {
        super(ssn, age, name);
        this.daysLeftToLive = daysLeftToLive;
    }

    @Override
    public void sayGreetings() {
        System.out.printf("Klote kut zooi ik ga dood over %d dagen%n", daysLeftToLive);
    }

}
