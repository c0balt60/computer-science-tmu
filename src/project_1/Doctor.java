package project_1;

import java.util.ArrayList;

public class Doctor extends Person {
    private String sepcialty;
    private boolean isAvailable = true;
    private int patientsSeenToday = 0;
    private ArrayList<Patient> treatedPatients = new ArrayList<>();

    public Doctor(String name, int age, String specialty) {
        super(name, age);
        this.sepcialty = specialty;
    }

    public void Treat(Patient patient) {
        // TODO - Make this async
        // STUB - Fill in
    }

    public void getPatientHistory() {
        // NOTE - May change this to have an actual return type
    }

    @Override
    public String toString() {
        return String.format("Dr.%s, Specialty: %s, Available: %s", this.getName(), this.sepcialty,
                this.isAvailable ? "Yes" : "No");
    }
}
