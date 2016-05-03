package model;

import java.util.LinkedList;

import javax.swing.table.AbstractTableModel;

import menjacnica.Kurs;

public class MenjacnicaTableModel extends AbstractTableModel {
	private LinkedList<Kurs> kursevi;
	private String[] kolone = new String[] { "Sifra", "Skraceni naziv", "Prodajni", "Srednji", "Kupovni", "Naziv" };

	public MenjacnicaTableModel(LinkedList<Kurs> kursevi) {
		if (kursevi == null)
			this.kursevi = new LinkedList<Kurs>();
		this.kursevi = kursevi;
	}

	public MenjacnicaTableModel() {
		this.kursevi = new LinkedList<Kurs>();
	}

	@Override
	public int getColumnCount() {
		return kolone.length;
	}

	@Override
	public int getRowCount() {
		return kursevi.size();
	}

	@Override
	public String getColumnName(int column) {
		return kolone[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Kurs k = kursevi.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return k.getSifra();
		case 1:
			return k.getSkraceniNaziv();
		case 2:
			return k.getProdajni();
		case 3:
			return k.getSrednji();
		case 4:
			return k.getKupovni();
		case 5:
			return k.getNaziv();
		default:
			return "not found";
		}
	}

	public void osvezi(LinkedList<Kurs> kursevi) {
		this.kursevi = kursevi;
		fireTableDataChanged();
	}

}
