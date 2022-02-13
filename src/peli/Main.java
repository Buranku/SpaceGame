package peli;

import java.awt.Dimension;
import java.awt.Toolkit;
/**
 * ohjelman main-luokka
 * alustaa uuden pelin
 */
public class Main {

	public static int leveys;
	public static int korkeus;
	public static Dimension dim;
	
	
	public static void main(String[] args) {
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		leveys = dim.width;
		korkeus = dim.height;
		new Peli();
	}

}
