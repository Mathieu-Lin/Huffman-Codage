package modele;

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
		Reader r = new Reader("alice.txt");
		
		System.out.println(r.getContenu().get(0));
		HuffmanCodage hfc = new HuffmanCodage(r.getContenu());
		Map<String, Integer> donnees = hfc.transformData();
		hfc.afficherListCompressedData();
		
	}

}
