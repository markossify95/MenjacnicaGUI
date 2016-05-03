package menjacnica.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.security.acl.Group;
import java.util.Enumeration;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;

import kontroler.Kontroler;

import javax.swing.event.ChangeEvent;

public class IzvrsiZamenuGUI extends JFrame {

	private JPanel contentPane;
	private JLabel lblKupovniKurs;
	private JTextField txtKupovni;
	private JLabel lblProdajniKurs;
	private JTextField txtProdajni;
	private JComboBox comboBox;
	private JLabel lblIznos;
	private JTextField txtIznos;
	private JLabel lblNewLabel;
	private JRadioButton rdbtnKupovina;
	private JRadioButton rdbtnProdaja;
	private JSlider slider;
	private JButton btnIzvrsiZamenu;
	private JButton btnOdustani;
	private ButtonGroup bg = new ButtonGroup();

	/**
	 * Create the frame.
	 */
	public IzvrsiZamenuGUI() {
		setResizable(false);
		setTitle("Izvrsi zamenu");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblKupovniKurs());
		contentPane.add(getTxtKupovni());
		contentPane.add(getLblProdajniKurs());
		contentPane.add(getTxtProdajni());
		contentPane.add(getComboBox());
		contentPane.add(getLblIznos());
		contentPane.add(getTxtIznos());
		contentPane.add(getLblNewLabel());
		contentPane.add(getRdbtnKupovina());
		contentPane.add(getRdbtnProdaja());
		contentPane.add(getSlider());
		contentPane.add(getBtnIzvrsiZamenu());
		contentPane.add(getBtnOdustani());
	}

	private JLabel getLblKupovniKurs() {
		if (lblKupovniKurs == null) {
			lblKupovniKurs = new JLabel("Kupovni kurs");
			lblKupovniKurs.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblKupovniKurs.setBounds(10, 11, 132, 14);
		}
		return lblKupovniKurs;
	}

	private JTextField getTxtKupovni() {
		if (txtKupovni == null) {
			txtKupovni = new JTextField();
			txtKupovni.setEditable(false);
			txtKupovni.setBounds(10, 36, 132, 20);
			txtKupovni.setColumns(10);
		}
		return txtKupovni;
	}

	private JLabel getLblProdajniKurs() {
		if (lblProdajniKurs == null) {
			lblProdajniKurs = new JLabel("Prodajni kurs");
			lblProdajniKurs.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblProdajniKurs.setBounds(249, 11, 132, 14);
		}
		return lblProdajniKurs;
	}

	private JTextField getTxtProdajni() {
		if (txtProdajni == null) {
			txtProdajni = new JTextField();
			txtProdajni.setEditable(false);
			txtProdajni.setBounds(249, 36, 132, 20);
			txtProdajni.setColumns(10);
		}
		return txtProdajni;
	}

	private JComboBox getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox();
			comboBox.setBounds(154, 36, 81, 20);
			comboBox.addItem("EUR");
			comboBox.addItem("USD");
			comboBox.addItem("CHF");
		}
		return comboBox;
	}

	private JLabel getLblIznos() {
		if (lblIznos == null) {
			lblIznos = new JLabel("Iznos");
			lblIznos.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblIznos.setBounds(48, 95, 94, 14);
		}
		return lblIznos;
	}

	private JTextField getTxtIznos() {
		if (txtIznos == null) {
			txtIznos = new JTextField();
			txtIznos.setBounds(48, 119, 132, 20);
			txtIznos.setColumns(10);
		}
		return txtIznos;
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Vrsta transakcije");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblNewLabel.setBounds(249, 95, 132, 14);
		}
		return lblNewLabel;
	}

	private JRadioButton getRdbtnKupovina() {
		if (rdbtnKupovina == null) {
			rdbtnKupovina = new JRadioButton("Kupovina");
			rdbtnKupovina.setBounds(249, 118, 109, 23);
			bg.add(rdbtnKupovina);
		}
		return rdbtnKupovina;
	}

	private JRadioButton getRdbtnProdaja() {
		if (rdbtnProdaja == null) {
			rdbtnProdaja = new JRadioButton("Prodaja");
			rdbtnProdaja.setBounds(249, 144, 109, 23);
			bg.add(rdbtnProdaja);
		}
		return rdbtnProdaja;
	}

	private JSlider getSlider() {
		if (slider == null) {
			slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
			slider.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent arg0) {
					txtIznos.setText(String.valueOf(slider.getValue()));
				}
			});
			slider.setPaintLabels(true);
			slider.setPaintTicks(true);
			slider.setMajorTickSpacing(10);
			slider.setMinorTickSpacing(5);
			slider.setBounds(22, 186, 351, 36);
		}
		return slider;
	}

	private JButton getBtnIzvrsiZamenu() {
		if (btnIzvrsiZamenu == null) {
			btnIzvrsiZamenu = new JButton("Izvrsi zamenu");
			btnIzvrsiZamenu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String odabran = comboBox.getSelectedItem().toString().trim();
					if (txtIznos.getText().isEmpty()) {
						JOptionPane.showMessageDialog(getContentPane(), "Greska! Niste odabrali iznos", "Greska",
								JOptionPane.ERROR_MESSAGE);
					} else {
						int iznos = Integer.parseInt(txtIznos.getText());
						String vrstaTransakcije = odrediSelektovanRB();
						if (vrstaTransakcije.equals("Nema odabira")) {
							JOptionPane.showMessageDialog(getContentPane(), "Greska! Niste odabrali vrstu transakcije",
									"Greska", JOptionPane.ERROR_MESSAGE);
						} else {
							Kontroler.upisiIzmenu(odabran, iznos, vrstaTransakcije);
							dispose();
						}
					}
				}
			});
			btnIzvrsiZamenu.setBounds(70, 235, 120, 26);
		}
		return btnIzvrsiZamenu;
	}

	private String odrediSelektovanRB() {
		for (Enumeration<AbstractButton> buttons = bg.getElements(); buttons.hasMoreElements();) {
			AbstractButton button = buttons.nextElement();
			if (button.isSelected()) {
				return button.getText();
			}
		}
		return "Nema odabira";
	}

	private JButton getBtnOdustani() {
		if (btnOdustani == null) {
			btnOdustani = new JButton("Odustani");
			btnOdustani.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnOdustani.setBounds(202, 235, 120, 26);
		}
		return btnOdustani;
	}
}
