package vue;

import javax.swing.*;

import controleur.LoggerButtonListener;
import controleur.ReaderButtonListener;

import java.awt.*;

import modele.HuffmanCodage;
import modele.Logger;
import modele.Reader;

/*
 * 
 */
public class HuffmanCodageJFrame extends JFrame {
	/*********
	 * Attributs
	 */
	/*
	 * Valeurs constantes : 
	 * - HAUTEUR de la fenetre
	 * - LARGEUR de la fenetre 
	*/
	private static final int HAUTEUR = 700, LARGEUR = 600;
	
	private Reader r;
	private Logger l;
	private HuffmanCodage hfc;
	
	/*********
	 * Constructeur
	 */
	public HuffmanCodageJFrame (Reader r, HuffmanCodage hfc) {
		this.r =r ;
		this.hfc = hfc;
		
	    // Création de la fenêtre
	    JFrame frame = new JFrame("Codage Huffman");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(HAUTEUR, LARGEUR);

	    // Création du panel principal
	    JPanel panel = new JPanel();
	    panel.setLayout(new BorderLayout());
	    
	    // Création des boutons
	    JButton readerButton = new JButton("Reader");
	    JButton loggerButton = new JButton("Logger");
	    
	    // Création du label pour afficher le texte avec défilement vertical
	    JTextArea textArea = new JTextArea();
	    textArea.setEditable(false);
	    JScrollPane scrollPane = new JScrollPane(textArea);
	    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    
	    // Création du label pour afficher le texte avec défilement vertical
	    JTextArea information = new JTextArea();
	    JScrollPane scrollPanel = new JScrollPane(information);
	    scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

	    // Ajout des composants au panel principal
	    panel.add(readerButton, BorderLayout.WEST);
	    panel.add(loggerButton, BorderLayout.EAST);
	    panel.add(scrollPane, BorderLayout.CENTER);
	    panel.add(scrollPanel, BorderLayout.SOUTH);
	    
	    // Ajouter du texte initial dans la JTextArea
        textArea.setText(r.getTexteBrut());
        information.setText("Inserez votre nom pour enregistrer sous codage Huffman");
        
        
        // Création de l'objet pour la gestion des événements
        
        ReaderButtonListener readerActionListener = new ReaderButtonListener(this.r,textArea,this.hfc);
        readerActionListener.setHfc(this.hfc);
        
        // Ajout du gestionnaire d'événements pour le bouton "Reader"
        readerButton.addActionListener(readerActionListener);
        
        // Création de l'objet pour la gestion des événements
        LoggerButtonListener loggerActionListener = new LoggerButtonListener(textArea,information,readerActionListener.getHfc());

        // Ajout du gestionnaire d'événements pour le bouton "Logger"
        loggerButton.addActionListener(loggerActionListener);
        
        
	    // Ajout du panel à la fenêtre
	    frame.getContentPane().add(panel);

	    // Affichage de la fenêtre
	    frame.setVisible(true);
		
	}
	
	/*********
	 * Méthodes
	 */
	

	/*********
	 * Getters/Setters
	 */
	
}
