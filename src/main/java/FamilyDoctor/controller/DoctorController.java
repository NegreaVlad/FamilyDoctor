package FamilyDoctor.controller;

import FamilyDoctor.exceptions.ConsultationException;
import FamilyDoctor.exceptions.PatientException;
import FamilyDoctor.model.Consultation;
import FamilyDoctor.model.Patient;
import FamilyDoctor.repository.Repository;
import FamilyDoctor.validator.ConsultationValidation;

import java.io.IOException;
import java.util.List;

public class DoctorController {

    private List<Patient> PatientList;
    private List<Consultation> ConsultationList;
    private Repository rep;

    /**
     * Constructors
     */

    public DoctorController(Repository rep) {
        this.rep = rep;
        this.PatientList = rep.getPatientList();
        this.ConsultationList = rep.getConsultationList();
        // Get list from file in order to avoid duplicates.
    }

    /**
     * Getters
     */
    public List<Patient> getPatientList() {
        return rep.getPatientList();
    }

    public List<Consultation> getConsultationList() {
        return rep.getConsultationList();
    }

    public void setConsulationList(List<Consultation> consultationList) {
        ConsultationList = consultationList;
    }

    private int getPatientBySSN(String SSN) {
        for (int i = 0; i < PatientList.size(); i++) {
            if (PatientList.get(i).getSSN().equals(SSN))
                return i;
        }

        return -1;
    }

    private int getConsByID(int ID) {
        for (int i = 0; i < ConsultationList.size(); i++) {
            if (ConsultationList.get(i).getConsID() == ID) {
                /*
				 * System.out.println("I proud to have found " + ID + " here: "
				 * + i); System.out.println("Proof : " +
				 * ConsultationList.get(i).toString());
				 */
                return i;
            }
        }

        return -1;
    }

    public Repository getRepository() {
        return rep;
    }

    /**
     * Others
     */
    public void addPatient(Patient p) throws PatientException {

        try {
            rep.addPatient(p);
            rep.savePatientToFile(p);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // adding of a new consultation for a patient (consultation date,
    // diagnostic, prescription drugs)

    public void addConsultation(int consID, String patientSSN, String diag,
                                List<String> meds, String date) throws ConsultationException, PatientException {
        if (meds == null)
            throw new ConsultationException("meds is null");

        if (consID != 0 && patientSSN != null && diag != null && meds.size() != 0 && this.getPatientBySSN(patientSSN) > -1
                && this.getConsByID(consID) == -1) {
            ConsultationValidation.ssnValidate(patientSSN);
            Consultation c = new Consultation(consID, patientSSN, diag, meds, date);
            try {
                rep.addConsultation(c);
                rep.saveConsultationToFile(c);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Patient p = this.getPatientList().get(this.getPatientBySSN(c.getPatientSSN()));
            p.setConsNum(p.getConsNum() + 1);
        } else {
            throw new ConsultationException("invalid arguments");
        }
    }

    public List<Patient> getPatientsWithDisease(String disease) throws PatientException {

        return rep.getPatientsWithDisease(disease);
    }
}
