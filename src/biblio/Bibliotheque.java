package biblio;

import java.io.Serializable;
import java.sql.*;
import java.util.*;
/**
 * 
 * @author hajar
 *
 */
public class Bibliotheque implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 * @param id
	 * @param titre
	 * @param genre
	 * @param auteur
	 * @param qnt
	 * @return
	 */
    public boolean addLivre(int idLivre,String titre,String genre,String auteur,int quantite){
    	final String requet = "insert into livre values('"+idLivre+"','"+titre+"','"+genre+"','"+auteur+"','"+quantite+"')";
        final int number=Connect.getInstance().update(requet);
        return number!=-1;
    }
    /**
     * 
     * @param idLiv
     * @return
     */
	public boolean delLivre(int idLiv) {
		final String requet1 = "delete from locataire where idLiv='"+idLiv+"'";
		Connect.getInstance().update(requet1);
		final String requet2="delete from livre where idLiv='"+idLiv+"'";
		final int numero = Connect.getInstance().update(requet2);
		return numero !=-1;
	}
/**
 * 
 * @param idLiv
 * @param titre
 * @param genre
 * @param auteur
 * @param qnt
 * @return
 */
	public boolean updateLivre(int idLiv,String titre,String genre,String auteur,int qnt) {
		final String requet ="update livre set titre='"+titre+"',genre='"+genre+"',auteur='"+auteur+"',quantite='"+qnt+"' where idLiv='"+idLiv+"'";
		final int numero = Connect.getInstance().update(requet);
		return numero !=-1;
	}
	/**
	 * 
	 * @param idLv
	 * @return
	 */
    public List<Livre> getLivre(int idLv){
    	List<Livre> lvr=new ArrayList<>();
    	final String requet = "select * from livre where idLiv = '"+idLv+"'";
		final ResultSet result = Connect.getInstance().lire(requet);
		try {
			if(result.next()){
				lvr.add(new Livre(result.getInt(1),result.getString(2),result.getString(3),result.getString(4),result.getInt(5)));
			}
				return lvr;
		}catch(SQLException ex){
			lvr = null;
			return lvr;
		} finally {
			try {
				result.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
    }
    /**
     * 
     * @return
     */
	public List<Livre> getTousLesLivres() {
		final String rqt = "select * from livre";
		List<Livre> books=new ArrayList<>();
		final ResultSet result = Connect.getInstance().lire(rqt);
		try{
			while(result.next()){
				books.add(new Livre(result.getInt(1),result.getString(2),result.getString(3),result.getString(4),result.getInt(5)));
			}
			return books;
		}catch(SQLException e){
			books =null;
			return books;
		}finally {
			try {
				result.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 
	 * @param idLivre
	 * @return
	 */
	public boolean laDesponibiliteDeLivre(int idLivre){
		String requet1;
		String requet2;
		
        ResultSet result;
	    ResultSet result1;
		
		int i=0;
		int q=0;
		
		requet1 = "select * from livre where idLiv='"+idLivre+"'";
		requet2 = "select * from locataire where idLiv ='"+idLivre+"'";
		 
		result1 = Connect.getInstance().lire(requet1);
		result = Connect.getInstance().lire(requet2);
		
		try {
			if (result1.next()) {q = result1.getInt(5);} 
			 while (result.next())  {i++;}
		} catch (SQLException ex){ex.printStackTrace();
		}finally {
			try {
				result1.close();
				result.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
		return i<q;
	}
	/**
	 * 
	 * @param id
	 * @param np
	 * @param niveau
	 * @param filiere
	 * @param idLiv
	 * @return
	 */
	  public boolean addLocataire(int idLocataire,String nomprenom,String niveau,String filiere,int idLiv){
	    	final String requet = "insert into locataire values('"+idLocataire+"','"+nomprenom+"','"+niveau+"','"+filiere+"','"+idLiv+"')";
	        final int nbr=Connect.getInstance().update(requet);
	        return nbr !=-1;  
	    }
	    /**
	     * 
	     * @param idLoc
	     * @return
	     */
		public boolean delLocataire(int idLoc) {
			final String requet="delete from locataire where idLoc='"+idLoc+"'";
			final int numbr = Connect.getInstance().update(requet);
			return numbr !=-1;
		}
/**
 * 
 * @param idLoc
 * @param np
 * @param niveau
 * @param filiere
 * @param idLiv
 * @return
 */
		public boolean updateLocataire(int idLoc,String nomprenom,String niveau,String filiere,int idLiv) {
			final String requet="update locataire set nomprenom='"+nomprenom+"',niveau='"+niveau+"',filiere='"+filiere+"',idLiv='"+idLiv+"' where idLoc='"+idLoc+"'";
			final int nbre = Connect.getInstance().update(requet);
			return nbre !=-1;
		}
/**
 * 
 * @param idLoc
 * @return
 */
		public List<Locataire> getLocataire(int idLoc){
	    	List<Locataire> locataire=new ArrayList<>();
	    	final String requet = "select idLoc,nomprenom,niveau,filiere,titre from locataire lc inner join livre lv ON lc.idLiv=lv.idLiv where idLoc = '"+idLoc+"'";
			final ResultSet result = Connect.getInstance().lire(requet);
			try {
				if(result.next()){
					locataire.add(new Locataire(result.getInt(1),result.getString(2),result.getString(3),result.getString(4),result.getString(5)));
				}
					return locataire;
			}catch(SQLException ex){
				locataire = null;
				return locataire;
			} finally {
				try {
					result.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}	
	    }
		/**
		 * 
		 * @return
		 */
		public List<Locataire> getTousLesLocataire() {
			String rqt = "select idLoc,nomprenom,niveau,filiere,titre from locataire lc inner join livre lv on lc.idLiv=lv.idLiv";
			List<Locataire> loc=new ArrayList<>();
			final ResultSet results = Connect.getInstance().lire(rqt);
			try{
				while(results.next()){
					loc.add(new Locataire(results.getInt(1),results.getString(2),results.getString(3),results.getString(4),results.getString(5)));
				}
				return loc;
			}catch(SQLException e){
			loc=null;
			return loc;
			}finally {
				try {
					results.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
}
