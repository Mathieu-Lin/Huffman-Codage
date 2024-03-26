package modele;
// Importation
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Map;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;

public class Logger {
	// Attributs
    private PrintWriter sortieTxt;
    private BufferedOutputStream sortieBin;
	private String cheminBase = "../Data/Compressed_data/";

    // Constructeur pour enregistrer
    public Logger(String fichier, boolean texte) {
    	fichier = cheminBase + fichier;
        if (texte) { // Pour texte
            try {
                FileWriter f = new FileWriter(fichier);
                BufferedWriter b = new BufferedWriter(f);
                this.sortieTxt = new PrintWriter(b);
            } catch (Exception e) {
                System.err.println("Erreur lors de l'initialisation du Logger pour texte");
            }
        } else { // Pour binaire
            try {
                FileOutputStream f = new FileOutputStream(fichier);
                this.sortieBin = new BufferedOutputStream(f);
            } catch (Exception e) {
                System.err.println("Erreur lors de l'initialisation du Logger pour binaire");
            }
        }
    }

    // Méthode pour logger du texte
    public void logTxt(int taille,Map<String,Integer> message) {
        if (this.sortieTxt != null) {
        	this.sortieTxt.println(taille);
    	    for (Map.Entry<String, Integer> entry : message.entrySet()) {
    	        this.sortieTxt.println(entry.getKey() + " " + entry.getValue());
    	    }
        } else {
            System.err.println("Logger texte non initialisé");
        }
    }

    // Méthode pour logger du binaire
    public void logBin(byte[] data) {
        if (this.sortieBin != null) {
            try {
                this.sortieBin.write(data);
            } catch (Exception e) {
                System.err.println("Erreur lors de l'écriture binaire");
            }
        } else {
            System.err.println("Logger binaire non initialisé");
        }
    }

    // Méthode pour fermer les sorties proprement
    public void close() {
        try {
            if (this.sortieTxt != null) {
                this.sortieTxt.close();
            }
            if (this.sortieBin != null) {
                this.sortieBin.close();
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de la fermeture des sorties");
        }
    }


	/*********
	 * Getters/Setters
	 */
	
}
