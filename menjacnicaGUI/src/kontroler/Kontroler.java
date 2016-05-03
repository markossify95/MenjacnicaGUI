package kontroler;

import java.awt.EventQueue;

import menjacnica.Kurs;
import menjacnica.gui.MenjacnicaGUI;

public class Kontroler {
	private static MenjacnicaGUI gui;

	public static void upisiKursUStatusBar(Kurs k) {
		gui.upisiDodatiKursUStatusBar(k.toString());
	}

	public static void dodajKursUListu(Kurs k) {
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

	public static void upisiIzmenu(String odabran, int iznos, String vrstaTransakcije) {
		gui.upisiIzmenu(odabran, iznos, vrstaTransakcije);
	}
}
