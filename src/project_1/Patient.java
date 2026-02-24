package project_1;

import java.time.LocalDate;

public class Patient extends Person {

    public enum UrgencyLevel {
        URGENT,
        HIGH,
        MEDIUM,
        LOW,
        TBD
    };

    private String symptoms;
    private UrgencyLevel urgency;
    private LocalDate arrival;
    private boolean isTreated;
    private String assignedDoctor;

    public Patient(String name, int age, String symptoms, UrgencyLevel urgency, LocalDate arrival) {
        super(name, age);
        this.symptoms = symptoms;
        this.urgency = urgency;
        this.arrival = arrival;
    }

    public Patient(String name, int age, String symptoms) {
        this(name, age, symptoms, UrgencyLevel.TBD, LocalDate.now());
    }

    public Patient compareTo(Patient other) {
        // Filter by urgency, then by arrival
        return (this.urgency.compareTo(other.getUrgency()) < 0) ? this : other;
    }

    public void setIsTreated(boolean state) {
        this.isTreated = state;
    }

    @Override
    public String toString() {
        return String.format("Patient: %s, Urgency: %s, Symptoms: %s", this.getName(), this.urgency.name(),
                this.symptoms);
    }

    public UrgencyLevel getUrgency() {
        return this.urgency;
    }
}
