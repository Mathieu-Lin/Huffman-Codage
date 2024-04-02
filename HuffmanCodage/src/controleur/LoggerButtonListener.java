package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import modele.HuffmanCodage;
import modele.Logger;
import modele.Node;

public class LoggerButtonListener implements ActionListener {
	// Attributs 
	private JTextArea t;
	private JTextArea response;
	private HuffmanCodage hfc;
	private ReaderButtonListener r;
    // Constructeur
    public LoggerButtonListener(JTextArea t, JTextArea response, HuffmanCodage hfc,ReaderButtonListener r) {
    	this.t = t;
    	this.response = response;
    	this.hfc = hfc;
    	this.r = r;
    }

    // Méthode appelée lorsque le bouton "Logger" est cliqué
    @Override
    public void actionPerformed(ActionEvent e) {
    	// Voir tester.java
    	this.hfc = this.r.getHfc();
		Node n = hfc.buildTree();
	    Map<String, String> codes = new HashMap<String, String>();
	    String code = "";
        n.depthCode(n, code, codes);
        String encodedText = hfc.encodeText(codes);
        byte[] encodedBytes = hfc.bitsToBytes(encodedText);
        String texteInitial = hfc.getTextBrut();
        String texteCompressé = encodedBytes.toString();
        int volumeInitial = hfc.countBytes();
        int volumeFinal = encodedBytes.length;
        double tauxCompression = hfc.compressionRatio(volumeInitial, volumeFinal);
        
        /**
         * Enregistrement message
         */
        String message = "";
        message = message + "Volume initial : " + volumeInitial + " octets\n";
        message = message + "Volume final : " + volumeFinal + " octets\n";
        message = message + "Taux de compression : " + (tauxCompression * 100) + "%\n";
        
        // Voir tester.java
        String texteCompresse = ""; // Initialisation de la chaîne compressée
        for (byte b : encodedBytes) {
            texteCompresse += String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
        }
        hfc.setCompressedText(texteCompresse);        
        int nombreCaractères = texteInitial.length();
        double moyenneBitsParCaractère = hfc.averageBitsPerCharacter(nombreCaractères);
        
        /**
         * Enregistrement message
         */
        message = message + "Nombre moyen de bits de stockage par caractère dans le texte compressé : " + moyenneBitsParCaractère;
        
        // Affichage d'une boîte de dialogue d'alerte avec le texte "ok"
        JOptionPane.showMessageDialog(null, message);
        
        // Enregistrement fichier
        
        Logger loggerTexte = new Logger(response.getText()+"_freq.txt", true);
        loggerTexte.logTxt(hfc.getTaille(),hfc.getList_compressed_data());
        loggerTexte.close();
        
        Logger loggerBinaire = new Logger(response.getText()+"_comp.bin", false);
        loggerBinaire.logBin(encodedBytes);
        loggerBinaire.close();
    }
}
