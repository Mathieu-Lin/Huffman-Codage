package modele;
import java.math.BigInteger;
// Importation
import java.util.ArrayList;
import java.util.LinkedHashMap; // Il permet obtenir un dictionnaire non auto-triée (HashMap est auto-triée)
import java.util.Map;
import java.util.Map.Entry;
/*
 * Ce programme permet de faire une codage Huffman 
 */
public class HuffmanCodage {
	/*********
	 * Attributs
	 */
	private ArrayList<String>list_uncompressed_data;
	private Map<String, Integer>list_compressed_data = new LinkedHashMap<>();
	private ArrayList<Node>tree = new ArrayList<Node>();;
	private String brutText;
	private String compressedText;
	private int taille;
	/*********
	 * Constructeur
	 * 
	 */
	public HuffmanCodage(ArrayList<String> list_uncompressed_data, String textBrut) {
		this.list_uncompressed_data = list_uncompressed_data;
		this.taille = 0;	
		this.brutText = textBrut;
	}
	/*********
	 * Méthodes
	 */
	
	
	/***************************************************************************
	 * Partie 1 : Du fichier .txt vers du type dictionnaire
	 * 
	 */
	
	/**
	 * moveKeyToLast permet de mettre un élément du dictionnaire en dernier
	 * @param
	 * @return
	 */
	public void moveKeyToLast(String key) {
	    if (this.list_compressed_data.containsKey(key)) {
	        int valeur = this.list_compressed_data.remove(key); // Supprimer l'élément de sa position actuelle
	        this.list_compressed_data.put(key, valeur); // Ajouter l'élément à la fin de la carte
	    }
	}
	/**
	 * transformData permet de compléter list_compressed_data
	 * @param
	 * @return
	 */
	public Map<String, Integer> transformData (){
		this.taille = 0;
		int premier = 0;
		for (String ligne : list_uncompressed_data) {
			// Saut de ligne
			if (premier!=0) {
				if (!this.list_compressed_data.containsKey(String.valueOf("Saut"))) {
		             this.list_compressed_data.put(String.valueOf("Saut"), 1); // Initialiser la fréquence à 1 pour chaque nouvelle lettre
					this.taille++;
				} else {
		            // Si la lettre existe déjà, incrémenter sa fréquence
		            int frequence = list_compressed_data.get(String.valueOf("Saut"));
		            this.list_compressed_data.put(String.valueOf("Saut"), frequence + 1);
		            this.moveKeyToLast(String.valueOf("Saut"));
		         }

			}
			// Autres
			for (int i = 0; i < ligne.length(); i++) {
				 char lettre = ligne.charAt(i); // Lire la lettre à l'indice i dans la chaîne ligne
		         // Vérifier si la lettre existe déjà dans la carte
				 	// Si la lettre n'existe pas, ajouter une lettre et sa fréquence qui est égale à 1
		         if (!this.list_compressed_data.containsKey(String.valueOf(lettre))) {
		             this.list_compressed_data.put(String.valueOf(lettre), 1); // Initialiser la fréquence à 1 pour chaque nouvelle lettre
		             this.taille = this.taille + 1; // Mise à jour de la taille 
		         } else {
		            // Si la lettre existe déjà, incrémenter sa fréquence
		            int frequence = list_compressed_data.get(String.valueOf(lettre));
		            this.list_compressed_data.put(String.valueOf(lettre), frequence + 1);
		            this.moveKeyToLast(String.valueOf(lettre));
		         }
				 
			}
			premier++;
		}
		// Quand il y a un saut de ligne après la fin
		if (this.list_compressed_data.containsKey(String.valueOf("Saut"))) {
			if (list_uncompressed_data.size() != this.list_compressed_data.get("Saut")) {
				if (!this.list_compressed_data.containsKey(String.valueOf("Saut"))) {
					this.list_compressed_data.put(String.valueOf("Saut"), 1); // Initialiser la fréquence à 1 pour chaque nouvelle lettre
				} else {
					// Si la lettre existe déjà, incrémenter sa fréquence
					int frequence = list_compressed_data.get(String.valueOf("Saut"));
					this.list_compressed_data.put(String.valueOf("Saut"), frequence + 1);
					this.moveKeyToLast(String.valueOf("Saut"));
				}
				 this.taille = this.taille + 1; // Mise à jour de la taille 
			}
		}
		return this.list_compressed_data;
	}
	
	/**
	 * displayListCompressedData permet d'afficher la liste compressé 
	 * @param
	 * @return
	 */
	public void displayListCompressedData() {
		System.out.println("Le nombre des caractères :");
		System.out.println(this.getTaille());
	    System.out.println("Contenu de la liste compressée :");
	    for (Map.Entry<String, Integer> entry : this.list_compressed_data.entrySet()) {
	        System.out.println(entry.getKey() + " : " + entry.getValue());
	    }
	}
	
	
	/***************************************************************************
	 * Partie 2 : Construction de l'arbre
	 * 
	 */
	

	/**
	 * Transforme de type Map vers Array / Liste
	 */
	public void mapToArray () {
        // Étape 1: Créer un arbre (feuille) pour chaque caractère
    	
    	// boucle for dont Entry prend l'élément du dictionnaire 
        for (Entry<String, Integer> entry : this.list_compressed_data.entrySet()) {
        	// Ajout vers une nouvelle liste 
            this.tree.add(new Node(entry.getKey(), entry.getValue()));
        }
	}
	/**
	 * Tri à bulle des noeuds
	 */
	public void triArray () {
		for (int i = 0; i<this.tree.size()-1; i++ ) {
			for (int j =0; j<this.tree.size() - i - 1; j++) {
				if (this.tree.get(j).getFrequency() > this.tree.get(j+1).getFrequency()) {
					Node temp = this.tree.get(j);
					this.tree.set(j, this.tree.get(j+1));
					this.tree.set(j+1, temp);
				}
			}
		}
	}
	
	/**
	 * buildTree construit un arbre de façon itératif 
	 * @param
	 * @return
	 */
	public Node buildTree () {
		this.mapToArray(); // Transformation de dictionnaire en liste des noeuds
        while (this.tree.size() > 1) {
        	// poll() permet de prendre le petit élément de la liste et le supprimer de la liste 
        	// "un peu comme liste.pop() en python" 
        	this.triArray();
            Node left = this.tree.get(0); // t1
            Node right = this.tree.get(1); // t2
            Node parent = new Node(left, right); // Nouveau nœud parent t
            this.tree.remove(1); // On retire les deux premiers
            this.tree.remove(0);
            this.tree.add(parent); // On ajoute un parent 

        }
        return this.tree.get(0); // L'arbre final restant
	}
 
    
	/***************************************************************************
	 * Partie 3 : Codage du texte
	 * 
	 */
	/**
	 * pour encoder le texte en utilisant les codes générés
	 * @param codes
	 * @return
	 */
    public String encodeText(Map<String, String> codes) {
    	String text = this.brutText;
        StringBuilder encodedText = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            String character = text.substring(i, i + 1); // Récupérer chaque caractère sous forme de chaîne
            String code = codes.get(character); // Récupérer le code binaire correspondant à ce caractère
            if (code != null) {
                encodedText.append(code); // Concaténer le code binaire
            } else {
                // Si le code n'est pas trouvé pour ce caractère,
                // envoie d'un message d'erreur
                System.err.println("Code introuvable pour le caractère : " + character);
            }
        }
        return encodedText.toString();
    }
    
    /**
     * Conversion d'une chaîne de bits en un tableau d'octets Necessite appel à une biblio de math 
     * @param bits comme "01010111110101010010101"
     * @return bytes (format octets)
     */
    public static byte[] bitsToBytes(String bits) {
        // Convertit la chaîne de bits en un BigInteger
        BigInteger bInteger = new BigInteger(bits, 2);
        
        // Convertit le BigInteger en un tableau d'octets
        byte[] bytes = bInteger.toByteArray();
        
        // Retourne le tableau d'octets
        return bytes;
    }
    
	/***************************************************************************
	 * Partie 4 : Taux de compression 
	 * 
	 */
    
    /**
     *  countBytes pour calculer le nombre d'octets dans une chaîne de caractères
     * @param text
     * @return
     */
    public int countBytes() {
        return this.brutText.getBytes().length;
    }
    /**
     * pour calculer le taux de compression
     * @param initialVolume
     * @param compressedVolume
     * @return
     */
    public static double compressionRatio(int initialVolume, int compressedVolume) {
        double gainVolume = initialVolume - compressedVolume;
        return 1.0 - ((double) compressedVolume / (double) initialVolume);
    }
    
    
	/***************************************************************************
	 * Partie 5 : Détermination du nombre moyen de bits de stockage d’un caractère du texte compressé
	 * 
	 */
    
    /**
     *  pour calculer le nombre total de bits dans une chaîne de codes compressés
     * @return
     */
    public int countBits() {
        return this.compressedText.length();
    }

    /**
     * pour calculer le nombre moyen de bits de stockage par caractère dans le texte compressé
     * @param nombreCaractères
     * @return
     */
    public double averageBitsPerCharacter( int nombreCaractères) {
        int nombreBits = countBits();
        return (double) nombreBits / (double) nombreCaractères;
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




	public String getTextBrut() {
		return brutText;
	}


	public void setTextBrut(String textBrut) {
		this.brutText = textBrut;
	}


	public String getBrutText() {
		return brutText;
	}


	public void setBrutText(String brutText) {
		this.brutText = brutText;
	}


	public String getCompressedText() {
		return compressedText;
	}


	public void setCompressedText(String compressedText) {
		this.compressedText = compressedText;
	}
	
	
	
	
	
	
	
}
