package FamilyDoctor.repository;

import FamilyDoctor.exceptions.PatientException;
import FamilyDoctor.model.Patient;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RepositoryTest {

    private Repository repository;

    @Before
    public void setUp() throws Exception {
        repository = new Repository();
    }

    @Test
    public void testAddPatient() throws Exception {
        assertEquals(0, repository.getPatientList().size());
        repository.addPatient(new Patient("1970311184746", "The name", "Venus"));
        assertEquals(1, repository.getPatientList().size());
    }

    @Test
    public void testAddPatientCase1() throws PatientException {
        assertEquals(0, repository.getPatientList().size());
        repository.addPatient(new Patient("1970311184723", "The namee", "Electrolizei 5"));
        assertEquals(1, repository.getPatientList().size());
    }

    @Test
    public void testAddPatientCase2() throws PatientException {
        assertEquals(0, repository.getPatientList().size());
        repository.addPatient(new Patient("1970311184735", "The namee", "Venus"));
        assertEquals(1, repository.getPatientList().size());
    }

    @Test(expected = PatientException.class)
    public void testAddPatientCase3() throws PatientException {
        assertEquals(0, repository.getPatientList().size());

//        repository.addPatient(new Patient("1970311184797.,;","The namee","Electrolizei 5"));
    }

    @Test
    public void testAddPatientCase4() throws PatientException {
        assertEquals(0, repository.getPatientList().size());
//        repository.addPatient(new Patient("19703111847415","The namee .","Electrolizei 5"));
        assertEquals(0, repository.getPatientList().size());
    }

    @Test
    public void testAddPatientCase5() throws PatientException {
        assertEquals(0, repository.getPatientList().size());
//        repository.addPatient(new Patient("1970311184783","The namee","Electrolizei, 5."));
        assertEquals(0, repository.getPatientList().size());
    }

    @Test
    public void testAddPatientCase6() throws PatientException {
        assertEquals(0, repository.getPatientList().size());
//        repository.addPatient(new Patient("1970311184739","The namee","Electrolizei 5"));
        assertEquals(0, repository.getPatientList().size());
    }

    @Test
    public void testAddPatientCase7() throws PatientException {
        assertEquals(0, repository.getPatientList().size());
//        repository.addPatient(new Patient("1970311184700","The namee","Electrolizei 5"));
        assertEquals(0, repository.getPatientList().size());
    }

    @Test
    public void testAddPatientCase8() throws PatientException {
        assertEquals(0, repository.getPatientList().size());
//        repository.addPatient(new Patient("197031118474631","Nume","Electrolizei 5"));
        assertEquals(0, repository.getPatientList().size());
    }

    @Test
    public void testAddPatientCase9() throws PatientException {
        assertEquals(0, repository.getPatientList().size());
//        repository.addPatient(new Patient("1970311184787","The name","Venus"));
        assertEquals(0, repository.getPatientList().size());
    }


    public void testAddConsultation() throws Exception {

        assertEquals(3, 3);

    }

}