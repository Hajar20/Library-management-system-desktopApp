package biblio;

import java.sql.*;
/**
 * 
 * @author hajar
 *
 */
public class Connect {
	/**
	 * Implement the class
	 */
 private Connection connexion;
 /**
  * @param cn
  */
 private static Connect cnxn = null;
 /**
  * @param c 
  */
 
 /**
  * initialize the object of connection
  */
 public Connect (){
	 try{
		 Class.forName("com.mysql.jdbc.Driver");
		 connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotheque", "root", "");
	 
	 } catch (ClassNotFoundException ex) {ex.printStackTrace();}
	   catch (SQLException e) {e.printStackTrace();} 
 }
 /**
  * 
  * @return
  */
 public static Connect getInstance(){
	 if (cnxn == null) {cnxn=new Connect();}
	 return cnxn;
 }
 /**
  * 
  * @return
  */
 public Connection getCn(){
	 return connexion;
 }
 /**
  * 
  * @param rqt
  * @return
  */
 public ResultSet lire(String rqt){
	 Statement stm;
	 try {
		 stm = connexion.createStatement();
		 return stm.executeQuery(rqt);
	 } catch (Exception ex) {return null;}
	
 }
 /**
  * 
  * @param req
  * @return
  */
 public int update(String req){
	 Statement state=null;
	 try {
		  state = connexion.createStatement();
		 return state.executeUpdate(req);
	 } catch(SQLException e) {return -1;}
	 finally {
			try {
				if (state != null){state.close();}
			} catch (SQLException e) {e.printStackTrace();}
		}
	 }
 }
 
 
 

