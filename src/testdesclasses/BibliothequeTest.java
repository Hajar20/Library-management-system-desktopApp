package testdesclasses;

import static org.junit.Assert.*;

import java.sql.*;
import java.util.*;

import org.junit.*;

import biblio.*;
/**
 * 
 * @author hajar
 *
 */
public class BibliothequeTest {
	/**
	 * @param numBook
	 */
    private static int numBook;
    /**
     * @param numLoc
     */
    private static int numLoc;
	/**
	 * @param biblio
	 */
    Bibliotheque biblio;	
	@BeforeClass
	/**
	 * Complete the code
	 */
	public static void setUpBeforeClass() {
		Connect.getInstance();
		String requet = "select max(idLiv) from livre";
		String requet1 = "select max(idLoc) from locataire";
		final ResultSet resultat = Connect.getInstance().lire(requet);
		final ResultSet resultat1 = Connect.getInstance().lire(requet1);
		try {
			if (resultat.next()) {
				numBook=(resultat.getInt(1) + 1);
			} else {
				numBook = 1;
			}
			if (resultat1.next()) {
				numLoc=(resultat1.getInt(1) + 1) ;
			} else {
				numLoc = 1;
			}
		} catch(SQLException ex){ex.printStackTrace();}
		finally {
			try {
				resultat.close();
				resultat1.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
	}
	@AfterClass
	/**
	 * 
	 * @throws SQLException
	 */
	public static void tearDownAfterClass() throws SQLException {
		Connect.getInstance().getCn().close();
	}
	@Before
	/**
	 * instantiation the object biblio
	 */
	public void setUp() {
		biblio = new Bibliotheque();	
	}
	@After
	/**
	 * 
	 */
	public void tearDown(){
		biblio = null;
	}
	@Test
	/**
	 * Add book
	 */
	public void testAddLivre() {
		assertTrue("add book",biblio.addLivre(numBook, "BootStrap", "Design", "EL Hiaine", 30));
		System.out.println("Nouveau livre est ajouté!"); 
	}

	@Test
	/**
	 * remove book
	 */
	public void testDelLivre() {
		assertTrue(" ",biblio.delLivre(5));
		System.out.println("Le livre est supprimé!");
	}
	@Test
	/**
	 * update book
	 */
	public void testSetLivre() {
		assertTrue("",biblio.updateLivre(2, "JAVA", "Programmation", "Bouaabid", 23));
	    System.out.println("Mise à jour effectué!!");
	}
	@Test
	/**
	 * get book
	 */
	public void testGetLivre(){
		final List<Livre> lvr = biblio.getLivre(2);
        assertNotNull("it's not null!",lvr);
        System.out.println(lvr+"\n");    
	}
	@Test
	/**
	 * get all the books
	 */
	public void testGetTousLesLivres(){
		final List<Livre> book = biblio.getTousLesLivres();
		assertNotNull("it's not null",book);
		System.out.println("\n"+book);
	}
	@Test
	/**
	 * availability of a book
	 */
	public void testLaDesponibiliteDeLivre() {
		assertTrue(" ",biblio.laDesponibiliteDeLivre(numBook));
		System.out.println("Livre est disponible");
	}
	@Test
	/**
	 * add adhering
	 */
	public void testAddLocataire() {
		assertTrue("",biblio.addLocataire(numLoc, "Adam jihad", "2ème année","MI", numBook));
		System.out.println("Nouveau locataire est ajouté!");
	}
	@Test
	/**
	 * remove adhering
	 */
	public void testDelLocataire() {
		assertTrue("",biblio.delLocataire(6));
		System.out.println("Locataire est supprimé!");
	}
	@Test
	/**
	 * update adhering
	 */
	public void testSetLocataire() {
		assertTrue("",biblio.updateLocataire(1, "Zackaria jamal Eddine", "2ème année", "PME/PMI", numBook));
		System.out.println("Mise à jour effectuée!");
	}
	@Test
	/**
	 * get adhering
	 */
	public void testGetLocataire(){
		final List<Locataire> lct = biblio.getLocataire(1);
		assertNotNull("it's not null",lct);
		System.out.println(lct.toString());
	}
	@Test
	/**
	 * get all adhering
	 */
	public void testGetTousLesLocataire(){
		final List<Locataire> loct = biblio.getTousLesLocataire();
		assertNotNull("it's not null",loct);
		System.out.println(loct.toString());		
	}
}
