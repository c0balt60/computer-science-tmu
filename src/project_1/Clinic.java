package project_1;

import java.util.ArrayList;

public class Clinic {
    private String clinicName;
    private ArrayList<Patient> waitingRoom;
    private ArrayList<Doctor> onDutyDoctors;
    private static ArrayList<Patient> fullDayLog;

    public Clinic(String name) {
        this.clinicName = name;
    }

    public void addPatient(Patient p) {
        this.waitingRoom.add(p);
    }

    public void callNextPatient() {
        // STUB - Should call the next patient in line, maybe return it?
    }

    public void getAvailableDoctor() {
        // STUB - Should return an available doctor
    }

    public void printWaitingRoom() {
        // STUB - Print the waiting room
    }
}
