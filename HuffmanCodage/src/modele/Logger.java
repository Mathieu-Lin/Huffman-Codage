package modele;
// Importation
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
/**
 * 
 * 
 * Ignorer ce programme
 * Import" via mon travail de TP de l'année 2022-2023
 *
 */
/*
 * Ce programme permet d'enregistrer une fichier compressée.
 */
public class Logger {
	public static int ALL=0,DEBUG=100,INFO=500,IMPORTANT=900,OFF=Integer.MAX_VALUE;
	// Attributs 
	private int level ;
	private PrintWriter sortie; //FileWriter(path) 
	
	// Constructeur 
	public Logger () {
		this.level = Logger.DEBUG;
		try{
			FileWriter f = new FileWriter("afficher.txt");
			BufferedWriter b = new BufferedWriter(f);
			this.sortie = new PrintWriter(b);
		} catch(Exception e ) {
			System.err.println("Logger morte");
		}
	}
	
	public Logger(String fich) {
		this.level = Logger.DEBUG;
		try{
			FileWriter f = new FileWriter(fich);
			BufferedWriter b = new BufferedWriter(f);
			this.sortie = new PrintWriter(b);
		} catch(Exception e ) {
			System.err.println("Logger morte");
		}
	}
	
	// Méthode logger
	public void log (int level, String message) {
		try {
		this.sortie.println(message);
		System.out.println(message);
	}catch(Exception e) {
		System.err.println("Logger Morte");
	}
	}

	/*********
	 * Getters/Setters
	 */
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public PrintWriter getSortie() {
		return sortie;
	}

	public void setSortie(PrintWriter sortie) {
		this.sortie = sortie;
	}
}
