package testdesclasses;

import static org.junit.Assert.*;
import org.junit.*;
import biblio.*;
/**
 * 
 * @author hajar
 *
 */
public class LivreTest {
    /**
     * @param lv1
     */
	private Livre lv1;
	/**
     * @param lv2
     */
	private Livre lv2;

	@Before
	/**
	 * instantiation the objects lv1 and lv2 before test
	 */
	public void setUp() {
		System.out.println("L'initialisation des deux livres :\n");
	lv1 = new Livre(1,"JAVA","Programmation","Bouaabid",66);
	lv2 = new Livre(2,"PHP","Programmation","Elhiaine",40);
	assertNotEquals(0,lv1.getQuantite());
    assertNotEquals(0,lv2.getQuantite());
	}

	@After
	/**
	 * make the object empty after test
	 */
	public void tearDown() {
		lv1 = null;
		lv2 = null;
		assertNull("vérifier si lv2 est null",lv2);
		assertNull("vérifier si lv1 est null",lv1);
		System.out.println("\n"+lv1+"\n"+lv2);
	}

	@Test
	/**
	 * testing the method toString()
	 */
	public void testToString() {
		assertEquals("vérifier l'égalité",1,lv1.getId());
		assertEquals(" vérifier l'égalité ","JAVA",lv1.getTitre());
		assertNotEquals("prog",lv1.getGenre());
		assertNotSame("vérifier que auteur de lv2 n'est égale à Mr Hiane ","Mr Hiane",lv2.getAuteur());
		assertNotNull(" ",lv2);
		assertNotNull(" ",lv1);
		System.out.println(lv1.toString()+"\n");
		System.out.println(lv2.toString()+"\n \n");
		lv1.setTitre("SQLServer");
		lv2.setAuteur("Hamid EL Hiaine");
		lv1.setQuantite(52);
		lv2.setGenre("BD");
		System.out.println(lv1.toString()+"\n");
		System.out.println(lv2.toString()+"\n \n");
	}
}
