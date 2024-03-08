package modele;
// Importation
import java.util.ArrayList;
import java.util.LinkedHashMap; // Il permet obtenir un dictionnaire non auto-triée (HashMap est auto-triée)
import java.util.Map;
/*
 * Ce programme permet de faire une codage Huffman 
 */
public class HuffmanCodage {
	/*********
	 * Attributs
	 */
	private ArrayList<String>list_uncompressed_data;
	private Map<String, Integer>list_compressed_data = new LinkedHashMap<>();
	private int taille;
	/*********
	 * Constructeur
	 */
	public HuffmanCodage(ArrayList<String> list_uncompressed_data) {
		this.list_uncompressed_data = list_uncompressed_data;
		this.taille = 0;
	}
	/*********
	 * Méthodes
	 */
	
	/*
	 * deplacerCleEnDernier permet de mettre un élément du dictionnaire en dernier
	 */
	public void deplacerCleEnDernier(String key) {
	    if (list_compressed_data.containsKey(key)) {
	        int valeur = list_compressed_data.remove(key); // Supprimer l'élément de sa position actuelle
	        list_compressed_data.put(key, valeur); // Ajouter l'élément à la fin de la carte
	    }
	}
	/*
	 * transformData permet de compléter list_compressed_data
	 */
	public Map<String, Integer> transformData (){
		this.taille = 0;
		int premier = 0;
		for (String ligne : list_uncompressed_data) {
			// Saut de ligne
			if (premier!=0 ) {
				if (!this.list_compressed_data.containsKey(String.valueOf("Saut"))) {
		             this.list_compressed_data.put(String.valueOf("Saut"), 1); // Initialiser la fréquence à 1 pour chaque nouvelle lettre
		         } else {
		            // Si la lettre existe déjà, incrémenter sa fréquence
		            int frequence = list_compressed_data.get(String.valueOf("Saut"));
		            this.list_compressed_data.put(String.valueOf("Saut"), frequence + 1);
		            this.deplacerCleEnDernier(String.valueOf("Saut"));
		         }
				this.taille++;
			}
			// Autres
			for (int i = 0; i < ligne.length(); i++) {
				 char lettre = ligne.charAt(i); // Lire la lettre à l'indice i dans la chaîne ligne
		         // Vérifier si la lettre existe déjà dans la carte
				 	// Si la lettre n'existe pas, ajouter une lettre et sa fréquence qui est égale à 1
		         if (!this.list_compressed_data.containsKey(String.valueOf(lettre))) {
		             this.list_compressed_data.put(String.valueOf(lettre), 1); // Initialiser la fréquence à 1 pour chaque nouvelle lettre
		         } else {
		            // Si la lettre existe déjà, incrémenter sa fréquence
		            int frequence = list_compressed_data.get(String.valueOf(lettre));
		            this.list_compressed_data.put(String.valueOf(lettre), frequence + 1);
		            this.deplacerCleEnDernier(String.valueOf(lettre));
		         }
				 this.taille = this.taille + 1; // Mise à jour de la taille 
			}
			premier++;
		}
		return this.list_compressed_data;
	}
	
	/*
	 * afficherListCompressedData permet d'afficher la liste compressé 
	 */
	public void afficherListCompressedData() {
		System.out.println("Le nombre des caractères :");
		System.out.println(this.getTaille());
	    System.out.println("Contenu de la liste compressée :");
	    for (Map.Entry<String, Integer> entry : list_compressed_data.entrySet()) {
	        System.out.println(entry.getKey() + " : " + entry.getValue());
	    }
	}
	
	/*********
	 * Getters/Setters
	 */
	public ArrayList<String> getList_uncompressed_data() {
		return list_uncompressed_data;
	}
	public void setList_uncompressed_data(ArrayList<String> list_uncompressed_data) {
		this.list_uncompressed_data = list_uncompressed_data;
	}

	public Map<String, Integer> getList_compressed_data() {
		return list_compressed_data;
	}

	public void setList_compressed_data(Map<String, Integer> list_compressed_data) {
		this.list_compressed_data = list_compressed_data;
	}

	public int getTaille() {
		return taille;
	}

	public void setTaille(int taille) {
		this.taille = taille;
	}
	
	
	
	
	
	
	
}
