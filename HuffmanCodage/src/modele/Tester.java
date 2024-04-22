package modele;

import java.util.HashMap;
import java.util.Map;

import vue.HuffmanCodageJFrame;

//import java.io.File;



public class Tester {

	public static void main(String[] args) {
    	/***************************************************************************
    	 * Partie 0 : Lecture
    	 * 
    	 */
		
		Reader r = new Reader("bonjour.txt");
		
		/***************************************************************************
		 * Partie 1 : Du fichier .txt vers du type dictionnaire
		 * 
		 */
		System.out.println(r.getContenu().get(0));
		HuffmanCodage hfc = new HuffmanCodage(r.getContenu(),r.getTexteBrut());
		Map<String, Integer> donnees = hfc.transformData();
		hfc.displayListCompressedData();
		
		/***************************************************************************
		 * Partie 2 : Construction de l'arbre
		 * 
		 */
		Node n = hfc.buildTree();
		
		
		/***************************************************************************
		 * Partie 3 : Codage du texte
		 * 
		 */
	    Map<String, String> codes = new HashMap<String, String>();
	    String code = "";
        n.depthCode(n, code, codes);
        n.printTree("",true);
        
        // Encodage du texte
        String encodedText = hfc.encodeText(codes);
        System.out.println("Texte encodé : " + encodedText);
        
        // Conversion en octets et stockage 
        byte[] encodedBytes = hfc.bitsToBytes(encodedText);
        
    	/***************************************************************************
    	 * Partie 4 : Taux de compression 
    	 * 
    	 */
        // Début de comparaison
        String texteInitial = hfc.getTextBrut();
        String texteCompressé = encodedBytes.toString();
        int volumeInitial = hfc.countBytes();
        int volumeFinal = encodedBytes.length;
        
        double tauxCompression = hfc.compressionRatio(volumeInitial, volumeFinal);
        
        System.out.println("Volume initial : " + volumeInitial + " octets");
        System.out.println("Volume final : " + volumeFinal + " octets");
        System.out.println("Taux de compression : " + (tauxCompression * 100) + "%");
       
    	/***************************************************************************
    	 * Partie 5 : Détermination du nombre moyen de bits de stockage d’un caractère du texte compressé
    	 * 
    	 */
        // Début de comparaison
        String texteCompresse = ""; // Initialisation de la chaîne compressée
        for (byte b : encodedBytes) {
            texteCompresse += String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
        }
        hfc.setCompressedText(texteCompresse);
        
        
        int nombreCaractères = texteInitial.length();
        double moyenneBitsParCaractère = hfc.averageBitsPerCharacter(nombreCaractères);
        
        System.out.println("Nombre moyen de bits de stockage par caractère dans le texte compressé : " + moyenneBitsParCaractère);
       
    	/***************************************************************************
    	 * Partie 6 : Enregistrement  
    	 * 
    	 */
        Logger loggerTexte = new Logger("bonjour_freq.txt", true);
        loggerTexte.logTxt(hfc.getTaille(),hfc.getList_compressed_data());
        loggerTexte.close();
        
        Logger loggerBinaire = new Logger("boujour_comp.bin", false);
        loggerBinaire.logBin(encodedBytes);
        loggerBinaire.close();
        
    	/***************************************************************************
    	 * Partie 7 : Interface  
    	 * 
    	 */
        HuffmanCodageJFrame hfcJF = new HuffmanCodageJFrame(r,hfc);
        
    	/***************************************************************************
    	 * Partie 8 : Encore encore... 
    	 * 
    	 */
	}

}
