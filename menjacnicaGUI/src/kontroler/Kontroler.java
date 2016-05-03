package kontroler;

import java.awt.EventQueue;

import menjacnica.Kurs;
import menjacnica.gui.MenjacnicaGUI;

public class Kontroler {
	private static MenjacnicaGUI gui;
	public static void upisiKursUStatusBar(Kurs k) {
		gui.upisiUStatusBar(k.toString());
	}

	public static void dodajKursUListu(Kurs k){
		gui.dodajUListu(k);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gui = new MenjacnicaGUI();
					gui.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
