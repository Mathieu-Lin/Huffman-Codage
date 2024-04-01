package modele;
// Importation
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/*
 * Ce programme permet de lire une fichier sélectionnée.
 */
public class Reader {
	/*********
	 * Attributs
	 */
	private String nomFichier; // Nom du fichier
	private ArrayList<String>contenu = new ArrayList<String>();
	private String texteBrut ;
	/*********
	 * Constructeur
	 */
	/**
	 * 
	 * @param nomFichier
	 */
	public Reader(String nomFichier) {
		this.nomFichier = "../Data/Uncompressed_data/" + nomFichier; // Enregistre le nom du fichier
		// Début de la récupération des données du fichier
		// Essaie
        try (BufferedReader br = new BufferedReader(new FileReader(this.nomFichier))) {
            String ligne; // Une ligne du fichier
            StringBuilder sb = new StringBuilder();
            this.texteBrut = br.toString();
            // Répéter jusqu'à la fin de la ligne du fichier
            while ((ligne = br.readLine()) != null) { 
                contenu.add(ligne); // Ajoute la ligne au contenu
                sb.append(ligne).append("\n"); // Ajoute la ligne au texte brut
            }
            this.texteBrut = sb.toString(); // Convertit le StringBuilder en String
        // En cas d'erreur
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	/*********
	 * Méthodes
	 */
	
	/*********
	 * Getters/Setters
	 */
	public String getNomFichier() {
		return nomFichier;
	}

	public void setNomFichier(String nomFichier) {
		this.nomFichier = nomFichier;
	}

	public ArrayList<String> getContenu() {
		return contenu;
	}

	public void setContenu(ArrayList<String> contenu) {
		this.contenu = contenu;
	}

	public String getTexteBrut() {
		return texteBrut;
	}

	public void setTexteBrut(String texteBrut) {
		this.texteBrut = texteBrut;
	}

	
}
