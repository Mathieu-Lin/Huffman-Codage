package modele;

import java.util.Map;

public class Node{
	/*********
	 * Attributs
	 */
    private String key;
    private int frequency;
    private Node left, right;
	/*********
	 * Constructeur
	 * 
	 */
	public Node (String key, int frequency, Node left, Node right){
		this.key = key;
		this.frequency = frequency;
		this.left = left;
		this.right = right;
	}
	public Node (String key, int frequency, Node left){
		this.key = key;
		this.frequency = frequency;
		this.left = left;
		this.right = null;
	}
	public Node (String key, int frequency) {
		this.key = key;
		this.frequency = frequency;
		this.left = null;
		this.right = null;
	}
    public Node(Node left, Node right) {
        this.frequency = left.frequency + right.frequency;
        this.left = left;
        this.right = right;
    }
	/*********
	 * Méthodes
	 */
    


    
    
 
     /**
      * Complète "code" en chaine des caractères qui contient les nombres binaires
      * Utilisation du parcours en profondeur et récursif
      * @param node
      * @param code
      * @param codes
      */
    public static void depthCode(Node node, String code, Map<String, String> codes) {
        if (node != null) {
            if (node.getLeft() == null && node.getRight() == null) {
                // C'est une feuille, donc stocker le code dont une dictionnaire
                codes.put(node.getKey(), code);
            }
            // Parcourir l'arbre récursivement pour générer les codes
            depthCode(node.getLeft(), code + "0", codes);
            depthCode(node.getRight(), code + "1", codes);
        }
    }
    
    /**
     * Affiche de manière récursif l'arbre du noeud
     * @param prefix
     * @param isTail
     */
    public void printTree(String prefix, boolean isTail) {
        System.out.println(prefix + (isTail ? "└── " : "├── ") + key + " : " + frequency);
        if (left != null) {
            left.printTree(prefix + (isTail ? "    " : "│   "), right == null);
        }
        if (right != null) {
            right.printTree(prefix + (isTail ? "    " : "│   "), true);
        }
    }
    
	/*********
	 * Getters/Setters
	 */
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public int getFrequency() {
		return frequency;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	public Node getLeft() {
		return left;
	}
	public void setLeft(Node left) {
		this.left = left;
	}
	public Node getRight() {
		return right;
	}
	public void setRight(Node right) {
		this.right = right;
	}

}
