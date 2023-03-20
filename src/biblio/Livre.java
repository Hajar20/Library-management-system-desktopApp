package biblio;

import java.io.Serializable;
/**
 * 
 * @author hajar
 *
 */
public class Livre implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * @param idLiv
	 */
	private int idLiv;
	/**
	 * @param titre
	 */
	private String titre;
	/**
	 * @param auteur
	 */
	private String auteur;
	/**
	 * @param genre
	 */
	private String genre;
	/**
	 * @param quantite
	 */
	private int quantite;
/**
 * 
 * @param idLivre
 * @param titre
 * @param genre
 * @param auteur
 * @param quantite
 */
	public Livre(int idLivre, String titre,String genre,String auteur, int quantite) {
		this.idLiv = idLivre;
		this.titre = titre;
		this.auteur = auteur;
		this.genre = genre;
		this.quantite = quantite;
	}
    
	@Override
	public String toString() {
		return "ID livre : "+idLiv+" ,Titre : "+titre+" ,Auteur : "+auteur+" ,Genre : "+genre+" ,Quantité : "+quantite;
	}
/**
 * 
 * @return
 */
	public int getId() {
		return this.idLiv;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
}
