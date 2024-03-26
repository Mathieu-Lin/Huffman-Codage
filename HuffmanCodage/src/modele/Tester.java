package modele;

import java.util.HashMap;
import java.util.Map;

//import java.io.File;



public class Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Coucou");
		/*
        String chemin = "../Data/Uncompressed_data/textesimple.txt";
        System.out.println(chemin.toString());
        File fichier = new File(chemin);
        System.out.println(fichier.toString());
        // Vérifier si le fichier existe
        if (fichier.exists()) {
            // Fichier existe, faire ce que vous voulez avec
            System.out.println("Le fichier existe !");
        } else {
            // Fichier n'existe pas
            System.out.println("Le fichier n'existe pas !");
        }
        */
		Reader r = new Reader("bonjour.txt");
		
		System.out.println(r.getContenu().get(0));
		HuffmanCodage hfc = new HuffmanCodage(r.getContenu(),r.getTexteBrut());
		Map<String, Integer> donnees = hfc.transformData();
		hfc.displayListCompressedData();
		Node n = hfc.buildTree();
		
		/**
		 * Codage Huffman
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
        
        // Début de comparaison
        String texteInitial = hfc.getTextBrut();
        String texteCompressé = encodedBytes.toString();

        // Taux de compression
        int volumeInitial = hfc.countBytes();
        int volumeFinal = encodedBytes.length;
        
        double tauxCompression = hfc.compressionRatio(volumeInitial, volumeFinal);
        
        System.out.println("Volume initial : " + volumeInitial + " octets");
        System.out.println("Volume final : " + volumeFinal + " octets");
        System.out.println("Taux de compression : " + (tauxCompression * 100) + "%");
       
        // Moyenne 
        // Début de comparaison
        String texteCompresse = ""; // Initialisation de la chaîne compressée
        for (byte b : encodedBytes) {
            texteCompresse += String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
        }
        hfc.setCompressedText(texteCompresse);
        
        
        int nombreCaractères = texteInitial.length();
        double moyenneBitsParCaractère = hfc.averageBitsPerCharacter(nombreCaractères);
        
        System.out.println("Nombre moyen de bits de stockage par caractère dans le texte compressé : " + moyenneBitsParCaractère);
       
        // Logger
        Logger loggerTexte = new Logger("bonjour.txt", true);
        loggerTexte.logTxt(hfc.getTaille(),hfc.getList_compressed_data());
        loggerTexte.close();
        
        Logger loggerBinaire = new Logger("bonjour.bin", false);
        loggerBinaire.logBin(encodedBytes);
        loggerBinaire.close();
        
	}

}
