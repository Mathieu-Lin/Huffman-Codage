package modele;
// Importation
import java.util.ArrayList;

public class HuffmanCodage {
	/*********
	 * Attributs
	 */
	private ArrayList<String>list_uncompressed_data;
	private ArrayList<String>list_compressed_data;
	/*********
	 * Constructeur
	 */
	public HuffmanCodage(ArrayList<String> list_uncompressed_data) {
		this.list_uncompressed_data = list_uncompressed_data;
	}

	
	/*********
	 * Méthodes
	 */
	/*********
	 * Getters/Setters
	 */
	public ArrayList<String> getList_uncompressed_data() {
		return list_uncompressed_data;
	}
	public void setList_uncompressed_data(ArrayList<String> list_uncompressed_data) {
		this.list_uncompressed_data = list_uncompressed_data;
	}
	public ArrayList<String> getList_compressed_data() {
		return list_compressed_data;
	}
	public void setList_compressed_data(ArrayList<String> list_compressed_data) {
		this.list_compressed_data = list_compressed_data;
	}
	
	
	
	
	
	
}
