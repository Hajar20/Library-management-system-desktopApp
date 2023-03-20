package testdesclasses;

import static org.junit.Assert.*;

import java.sql.*;

import org.junit.*;

import biblio.*;
/**
 * 
 * @author hajar
 *
 */
public class ConnectTest {
    /**
     * @param connexion1
     */
	private Connect connexion1;
	@Before
	/**
	 * instantiation the object of connection before test
	 */
	public void setUp() {
		connexion1 = new Connect();
	}
	@After
	/**
	 * 
	 * @throws SQLException
	 */
	public void tearDown() throws SQLException {
		connexion1.getCn().close();
	}
	@Test
	/**
	 * testing the method lire()
	 */
	public void testLire() {
		    final ResultSet result = connexion1.lire("select ;;;* from livre where idLiv =1");
			assertNull(result);
			final ResultSet result1 = connexion1.lire("select * from livre where idLiv =1");
			assertNotNull(result1);			
	}
	@Test
	/**
	 * testing the method update()
	 */
	public void testUpdate() {
		final int num=connexion1.update("Update livre set quantite = 20 where idLiv =4");
	    assertNotEquals("",-1,num); 
	    final int num1 = connexion1.update("Update ;;;livre set quantite = 20 where idLiv =4");
	    assertEquals("",-1,num1);
	}
}
