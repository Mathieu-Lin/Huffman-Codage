package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JTextArea;

import modele.HuffmanCodage;
import modele.Reader;

public class ReaderButtonListener implements ActionListener {
	private Reader r;
	private JTextArea t;
	private HuffmanCodage hfc;
	public ReaderButtonListener (Reader r, JTextArea t,HuffmanCodage hfc) {
		this.r = r;
		this.t = t;
		this.hfc = hfc;
	}
	/**
	 * Méthode appelée lorsque le bouton est cliqué
	 */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Ouvrir une boîte de dialogue pour choisir un fichier
        JFileChooser fileChooser = new JFileChooser("../Data/Uncompressed_data/");
        int result = fileChooser.showOpenDialog(null);
        // Si un fichier est choisi, extraire le nom du fichier et l'afficher dans la zone de texte
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            this.r = new Reader(selectedFile.getName()); 
            this.t.setText(this.r.getTexteBrut());
            this.hfc = new HuffmanCodage(r.getContenu(),r.getTexteBrut());
//            
        }
    }
	public Reader getR() {
		return r;
	}
	public void setR(Reader r) {
		this.r = r;
	}
	public JTextArea getT() {
		return t;
	}
	public void setT(JTextArea t) {
		this.t = t;
	}
	public HuffmanCodage getHfc() {
		return hfc;
	}
	public void setHfc(HuffmanCodage hfc) {
		this.hfc = hfc;
	}
}
