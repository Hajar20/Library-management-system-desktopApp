package biblio;

import java.io.Serializable;
/**
 * 
 * @author hajar
 *
 */
public class Locataire implements Serializable{

	private static final long serialVersionUID = 1L;
	/**
	 * @param idLoc
	 */
	private int idLoc;
	/**
	 * @param nomPrenom
	 */
	private String nomPrenom;
	/**
	 * @param niveau
	 */
	private String niveau;
	/**
	 * @param filiere
	 */
	private String filiere;
	/**
	 * @param livre
	 */
    private String livre;
	/**
	 * 
	 * @param idLoc
	 * @param nomPrenom
	 * @param niveau
	 * @param filiere
	 * @param livre
	 */
	public Locataire(int idLoc, String nomPrenom, String niveau, String filiere,String livre) {
		this.idLoc = idLoc;
		this.nomPrenom = nomPrenom;
		this.niveau = niveau;
		this.filiere = filiere;
		this.livre = livre;
	}
	
	@Override
	public String toString() {
		return "ID locataire : "+idLoc+" ,Le nom complet : "+nomPrenom+" ,Niveau : "+niveau+" ,filiere : "+filiere+" ,Livre loué : "+livre;
	}
/**
 * 
 * @return
 */
	public int getId() {
		return this.idLoc;
	}

	public String getNomPrenom() {
		return nomPrenom;
	}

	public void setNomPrenom(String nomPrenom) {
		this.nomPrenom = nomPrenom;
	}

	public String getNiveau() {
		return niveau;
	}

	public void setNiveau(String niveau) {
		this.niveau = niveau;
	}

	public String getFiliere() {
		return filiere;
	}

	public void setFiliere(String filiere) {
		this.filiere = filiere;
	}

	public String getLivre() {
		return livre;
	}

	public void setLivre(String livre) {
		this.livre = livre;
	}
	
}
