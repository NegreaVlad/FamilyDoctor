package FamilyDoctor.controller;

import FamilyDoctor.exceptions.ConsultationException;
import FamilyDoctor.exceptions.PatientException;
import FamilyDoctor.model.Patient;
import FamilyDoctor.repository.Repository;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DoctorControllerTest {
    Repository repository;
    DoctorController controller;
    String consultationsFile = "FileConsultations.txt";
    String patientsFile = "FilePatients.txt";

    @Before
    public void setUp() throws Exception {
        repository = new Repository(patientsFile, consultationsFile);
        controller = new DoctorController(repository);
    }

    @Test
    public void testAddConsultation() throws Exception {
        List<String> meds = new ArrayList<String>();
        meds.add("paracetamol");
        repository.addPatient(new Patient("1970809135797", "Numel", "Apoca"));
        assertEquals(0, controller.getConsultationList().size());
        controller.addConsultation(1, "1970809135797", "ill", meds, "23.03.2018");
        assertEquals(1, controller.getConsultationList().size());
    }

    @Test(expected = ConsultationException.class)
    public void testAddConsultation2() throws ConsultationException, PatientException {
        List<String> meds = new ArrayList<String>();
        repository.addPatient(new Patient("1970809135797", "Numel", "Apoca"));
//        assertEquals(0,controller.getConsultationList().size());
        controller.addConsultation(1, "1970809135797", "ill", meds, "23.03.2018");
//        assertEquals(1, controller.getConsultationList().size());
    }

    @Test(expected = ConsultationException.class)
    public void testAddConsultation3() throws ConsultationException, PatientException {
        List<String> meds = new ArrayList<String>();
        repository.addPatient(new Patient("1970809135797", "Numel", "Apoca"));
//        assertEquals(0,controller.getConsultationList().size());
        controller.addConsultation(1, "1970809135797", "ill", meds, "23.03.2018");
//        assertEquals(1, controller.getConsultationList().size());
    }

    // ConsID = 0
    @Test(expected = ConsultationException.class)
    public void testAddConsultation4() throws ConsultationException, PatientException {
        List<String> meds = new ArrayList<String>();
        meds.add("paracetamol");
        repository.addPatient(new Patient("1970809135797", "Numel", "Apoca"));
        controller.addConsultation(0, "1970809135797", "ill", meds, "23.03.2018");
    }

    // Null patient ssn
    @Test(expected = ConsultationException.class)
    public void testAddConsultation5() throws ConsultationException, PatientException {
        List<String> meds = new ArrayList<String>();
        meds.add("paracetamol");
        repository.addPatient(new Patient("1970809135797", "Numel", "Apoca"));
        controller.addConsultation(1, null, "ill", meds, "23.03.2018");
    }

    // Null patient diag
    @Test(expected = ConsultationException.class)
    public void testAddConsultation6() throws ConsultationException, PatientException {
        List<String> meds = new ArrayList<String>();
        meds.add("paracetamol");
        repository.addPatient(new Patient("1970809135797", "Numel", "Apoca"));
        controller.addConsultation(1, "1970809135797", null, meds, "23.03.2018");
    }

    // Empty meds
    @Test(expected = ConsultationException.class)
    public void testAddConsultation7() throws ConsultationException, PatientException {
        List<String> meds = new ArrayList<String>();
        repository.addPatient(new Patient("1970809135797", "Numel", "Apoca"));
        controller.addConsultation(1, "1970809135797", null, meds, "23.03.2018");
    }

    // Cons doesn't exist
    @Test(expected = ConsultationException.class)
    public void testAddConsultation8() throws ConsultationException, PatientException {
        List<String> meds = new ArrayList<String>();
        meds.add("paracetamol");
        repository.addPatient(new Patient("1970809135797", "Numel", "Apoca"));
        controller.addConsultation(142, "1970809135797", null, meds, "23.03.2018");
    }

    // Null meds
    @Test(expected = ConsultationException.class)
    public void testAddConsultation9() throws ConsultationException, PatientException {
        List<String> meds = null;
        repository.addPatient(new Patient("1970809135797", "Numel", "Apoca"));
        controller.addConsultation(142, "1970809135797", null, meds, "23.03.2018");
    }
}