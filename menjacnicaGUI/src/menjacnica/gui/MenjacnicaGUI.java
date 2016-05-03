package menjacnica.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Toolkit;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.border.TitledBorder;
import javax.swing.JTextArea;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

import menjacnica.Kurs;
import model.MenjacnicaTableModel;

import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.LinkedList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenjacnicaGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu jmnFile;
	private JMenu jmnHelp;
	private JScrollPane scrollPane;
	private JTable table;
	private JPanel panel_east;
	private JButton jbtnDodaj;
	private JButton jbtnIzbrisi;
	private JButton jbtnIzvrsiZamenu;
	private JPanel panel_south;
	private JScrollPane scrollPane_1;
	private JTextArea textArea;
	private JMenuItem mntmOpen;
	private JMenuItem mntmSave;
	private JMenuItem mntmExit;
	private JMenuItem mntmAbout;
	private JPopupMenu popupMenu;
	private JMenuItem mntmDodajKurs;
	private JMenuItem mntmIzbriiKurs;
	private JMenuItem mntmIzvriZamenu;
	private LinkedList<Kurs> kursevi = new LinkedList<Kurs>();

	/**
	 * Create the frame.
	 */
	public MenjacnicaGUI() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenjacnicaGUI.class.getResource("/res/img/dolar.png")));
		setTitle("Menjacnica");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 861, 415);
		setJMenuBar(getMenuBar_1());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getScrollPane(), BorderLayout.CENTER);
		contentPane.add(getPanel_east(), BorderLayout.EAST);
		contentPane.add(getPanel_south(), BorderLayout.SOUTH);
	}

	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getJmnFile());
			menuBar.add(getJmnHelp());
		}
		return menuBar;
	}

	private JMenu getJmnFile() {
		if (jmnFile == null) {
			jmnFile = new JMenu("File");
			jmnFile.add(getMntmOpen());
			jmnFile.add(getMntmSave());
			jmnFile.add(getMntmExit());
		}
		return jmnFile;
	}

	private JMenu getJmnHelp() {
		if (jmnHelp == null) {
			jmnHelp = new JMenu("Help");
			jmnHelp.add(getMntmAbout());
		}
		return jmnHelp;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTable());
		}
		return scrollPane;
	}

	private JTable getTable() {
		if (table == null) {
			table = new JTable();
			table.setModel(new MenjacnicaTableModel(kursevi));
			addPopup(table, getPopupMenu());
		}
		return table;
	}

	private JPanel getPanel_east() {
		if (panel_east == null) {
			panel_east = new JPanel();
			panel_east.setPreferredSize(new Dimension(130, 10));
			panel_east.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			panel_east.add(getJbtnDodaj());
			panel_east.add(getJbtnIzbrisi());
			panel_east.add(getJbtnIzvrsiZamenu());
		}
		return panel_east;
	}

	private JButton getJbtnDodaj() {
		if (jbtnDodaj == null) {
			jbtnDodaj = new JButton("Dodaj kurs");
			jbtnDodaj.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					DodajKursGUI dodaj = new DodajKursGUI();
					dodaj.setVisible(true);
				}
			});
			jbtnDodaj.setPreferredSize(new Dimension(112, 23));
		}
		return jbtnDodaj;
	}

	private JButton getJbtnIzbrisi() {
		if (jbtnIzbrisi == null) {
			jbtnIzbrisi = new JButton("Izbri\u0161i kurs");
			jbtnIzbrisi.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int index = table.getSelectedRow();
					if (index == -1) {
						JOptionPane.showMessageDialog(getContentPane(), "Morate izabrati zeljeni kurs u tabeli",
								"Greska", JOptionPane.ERROR_MESSAGE);
					} else {
						int odabir = JOptionPane.showConfirmDialog(getContentPane(),
								"Da li ste sigurni da zelite da izbrisete selektovani kurs?", "Potvrda brisanja",
								JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
						if (odabir == 0) {
							if (kursevi.get(index) != null) {
								JOptionPane.showMessageDialog(getContentPane(),
										"Kurs za valutu: " + kursevi.get(index).getSkraceniNaziv()
												+ " uspesno izbrisan",
										"Uspesno brisanje", JOptionPane.INFORMATION_MESSAGE);
								kursevi.remove(index);
								osveziTabelu();
								textArea.append("Izbrisan je red sa indexom: " + index + "\n");
							} else {
								JOptionPane.showMessageDialog(getContentPane(), "Greska pri brisanju kursa",
										"Neuspesno brisanje", JOptionPane.ERROR_MESSAGE);
							}
						}
					}
				}
			});
			jbtnIzbrisi.setPreferredSize(new Dimension(112, 23));
		}
		return jbtnIzbrisi;
	}

	private JButton getJbtnIzvrsiZamenu() {
		if (jbtnIzvrsiZamenu == null) {
			jbtnIzvrsiZamenu = new JButton("Izvr\u0161i zamenu");
			jbtnIzvrsiZamenu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					IzvrsiZamenuGUI zamena = new IzvrsiZamenuGUI();
					zamena.setVisible(true);
				}
			});
		}
		return jbtnIzvrsiZamenu;
	}

	private JPanel getPanel_south() {
		if (panel_south == null) {
			panel_south = new JPanel();
			panel_south.setBorder(new TitledBorder(null, "STATUS", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_south.setPreferredSize(new Dimension(10, 90));
			panel_south.setLayout(null);
			panel_south.add(getScrollPane_1());
		}
		return panel_south;
	}

	private JScrollPane getScrollPane_1() {
		if (scrollPane_1 == null) {
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setBounds(12, 23, 811, 63);
			scrollPane_1.setViewportView(getTextArea());
		}
		return scrollPane_1;
	}

	private JTextArea getTextArea() {
		if (textArea == null) {
			textArea = new JTextArea();
			textArea.setSize(new Dimension(100, 35));
		}
		return textArea;
	}

	public void upisiUStatusBar(String text) {
		textArea.append(text + "\n");
	}

	private JMenuItem getMntmOpen() {
		if (mntmOpen == null) {
			mntmOpen = new JMenuItem("Open");
			mntmOpen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JFileChooser fc = new JFileChooser();
					int returnVal = fc.showOpenDialog(getContentPane());
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						File file = fc.getSelectedFile();
						textArea.append("Ucitan fajl: " + file.getAbsolutePath() + "\n");
					}
				}
			});
			mntmOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
			mntmOpen.setIcon(new ImageIcon(MenjacnicaGUI.class.getResource("/res/img/folder_icon_small.gif")));
		}
		return mntmOpen;
	}

	private JMenuItem getMntmSave() {
		if (mntmSave == null) {
			mntmSave = new JMenuItem("Save");
			mntmSave.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFileChooser fc = new JFileChooser();
					int returnVal = fc.showSaveDialog(getContentPane());
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						File file = fc.getSelectedFile();
						textArea.append("Sacuvan fajl: " + file.getAbsolutePath() + "\n");
					}
				}
			});
			mntmSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
			mntmSave.setIcon(new ImageIcon(MenjacnicaGUI.class.getResource("/res/img/folder_icon_small.gif")));
		}
		return mntmSave;
	}

	private JMenuItem getMntmExit() {
		if (mntmExit == null) {
			mntmExit = new JMenuItem("Exit");
			mntmExit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int opcija = JOptionPane.showConfirmDialog(getContentPane(), "Da li zelite da izadjete iz programa",
							"Exit", JOptionPane.YES_NO_CANCEL_OPTION);
					if (opcija == 0) {
						System.exit(0);
					}
				}
			});
			mntmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.ALT_MASK));
		}
		return mntmExit;
	}

	private JMenuItem getMntmAbout() {
		if (mntmAbout == null) {
			mntmAbout = new JMenuItem("About");
			mntmAbout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(getContentPane(), "Kreirao: Marko Kostadinovic\n\nFON 2016\n",
							"About", JOptionPane.INFORMATION_MESSAGE);
				}
			});
		}
		return mntmAbout;
	}

	private JPopupMenu getPopupMenu() {
		if (popupMenu == null) {
			popupMenu = new JPopupMenu();
			popupMenu.add(getMntmDodajKurs());
			popupMenu.add(getMntmIzbriiKurs());
			popupMenu.add(getMntmIzvriZamenu());
		}
		return popupMenu;
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}

	private JMenuItem getMntmDodajKurs() {
		if (mntmDodajKurs == null) {
			mntmDodajKurs = new JMenuItem("Dodaj kurs");
			mntmDodajKurs.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					DodajKursGUI dodaj = new DodajKursGUI();
					dodaj.setVisible(true);
				}
			});
		}
		return mntmDodajKurs;
	}

	private JMenuItem getMntmIzbriiKurs() {
		if (mntmIzbriiKurs == null) {
			mntmIzbriiKurs = new JMenuItem("Izbri\u0161i kurs");
			mntmIzbriiKurs.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int index = table.getSelectedRow();
					if (index == -1) {
						JOptionPane.showMessageDialog(getContentPane(), "Morate izabrati zeljeni kurs u tabeli",
								"Greska", JOptionPane.ERROR_MESSAGE);
					} else {
						int odabir = JOptionPane.showConfirmDialog(getContentPane(),
								"Da li ste sigurni da zelite da izbrisete selektovani kurs?", "Potvrda brisanja",
								JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
						if (odabir == 0) {
							if (kursevi.get(index) != null) {
								JOptionPane.showMessageDialog(getContentPane(),
										"Kurs za valutu: " + kursevi.get(index).getSkraceniNaziv()
												+ " uspesno izbrisan",
										"Uspesno brisanje", JOptionPane.INFORMATION_MESSAGE);
								kursevi.remove(index);
								osveziTabelu();
								textArea.append("Izbrisan je red sa indexom: " + index + "\n");
							} else {
								JOptionPane.showMessageDialog(getContentPane(), "Greska pri brisanju kursa",
										"Neuspesno brisanje", JOptionPane.ERROR_MESSAGE);
							}
						}
					}
				}
			});
		}
		return mntmIzbriiKurs;
	}

	private JMenuItem getMntmIzvriZamenu() {
		if (mntmIzvriZamenu == null) {
			mntmIzvriZamenu = new JMenuItem("Izvr\u0161i zamenu");
			mntmIzvriZamenu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					IzvrsiZamenuGUI zamena = new IzvrsiZamenuGUI();
					zamena.setVisible(true);
				}
			});
		}
		return mntmIzvriZamenu;
	}

	public void osveziTabelu() {
		MenjacnicaTableModel m = (MenjacnicaTableModel) table.getModel();
		m.osvezi(kursevi);
		table.setModel(m);
	}

	public void dodajUListu(Kurs k) {
		kursevi.add(k);
		osveziTabelu();
	}
}
