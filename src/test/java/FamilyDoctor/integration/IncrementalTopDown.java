package FamilyDoctor.integration;

import FamilyDoctor.exceptions.PatientException;
import FamilyDoctor.model.Consultation;
import FamilyDoctor.model.Patient;
import FamilyDoctor.repository.Repository;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by vladnegrea on 08/05/2018.
 */
public class IncrementalTopDown {
    private Repository repository;

    @Before
    public void setUp() throws Exception {
        repository = new Repository();
    }

    @Test
    public void unitTestA() throws PatientException {
        assertEquals(0, repository.getPatientList().size());
        repository.addPatient(new Patient("2960809125790", "Numele", "Frunzisului 5"));
        assertEquals(1, repository.getPatientList().size());
    }

    @Test
    public void integrationTestAB() throws PatientException {
        assertEquals(0, repository.getPatientList().size());
        repository.addPatient(new Patient("2960809125790", "Numele", "Frunzisului 5"));
        assertEquals(1, repository.getPatientList().size());
        List<String> meds = new ArrayList<String>();
        meds.add("nurofen");
        repository.addConsultation(new Consultation(1, "2960809125790", "ill", meds, "23.03.2018"));
    }

    @Test
    public void integrationTestABC() throws PatientException {
        assertEquals(0, repository.getPatientList().size());
        repository.addPatient(new Patient("2960809125790", "Numele", "Frunzisului 5"));
        assertEquals(1, repository.getPatientList().size());
        List<String> meds = new ArrayList<String>();
        meds.add("nurofen");
        repository.addConsultation(new Consultation(1, "2960809125790", "ill", meds, "23.03.2018"));
        assertEquals(1, repository.getPatientsWithDisease("ill").size());
    }
}
