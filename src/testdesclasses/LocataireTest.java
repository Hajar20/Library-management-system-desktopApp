package testdesclasses;

import static org.junit.Assert.*;
import org.junit.*;
import biblio.*;
/**
 * 
 * @author hajar
 *
 */
public class LocataireTest {
    /**
     * @param lc1
     */
	private Locataire lc1;
	 /**
     * @param lc2
     */
    private Locataire lc2;
	@Before
	/**
	 * instantiation lc1 and lc2 before test
	 */
	public void setUp(){
		System.out.println("L'intialization des deux locataire :\n");
		lc1 = new Locataire(1,"Hajar ait abdielmomin","2ème année","DSI","PHP");
		lc2 = new Locataire(2,"Salma ait youssef","2ème année","DSI","JAVA");
		assertNotEquals(lc1.getId(),lc2.getId());
	}

	@After
	/**
	 * make lc1 and lc2 null after test
	 */
	public void tearDown() {
		lc1 = null;
		lc2 = null;
	}

	@Test
	/**
	 * testing the method toString()
	 */
	public void testToString() {
		assertNotNull("Making sure if it's not null",lc1);
		assertNotNull("Making sure if it's not null  ",lc2);
		System.out.println(lc1.toString()+"\n"+lc2.toString());
		lc2.setLivre("Les mésirables");
		assertNotSame(" ","Java",lc2.getLivre());
		System.out.println(lc1.toString()+"\n"+lc2.toString());
	}
	

	@Test
	/**
	 * testing a setter
	 */
	public void testSetNomPrenom() {
		lc1.setNomPrenom("Zakaria jamal Eddine");
		assertNotEquals("Hajar ait abdielmomin",lc1.getNomPrenom());
		System.out.println(lc1.toString());
	}

	@Test
	/**
	 * testing a setter
	 */
	public void testSetNiveau() {
		lc1.setNiveau("1ère année");
		assertNotEquals("2ème",lc1.getNiveau());
		System.out.println(lc1.toString());
	}
	@Test
	/**
	 * testing a setter
	 */
	public void testSetFiliere() {
		lc1.setFiliere("PME/PMI");
		assertNotEquals("DSI",lc1.getFiliere());
		System.out.println(lc1.toString());
	}
}
