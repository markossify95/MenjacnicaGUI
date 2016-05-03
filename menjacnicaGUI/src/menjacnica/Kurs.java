package menjacnica;

public class Kurs {
	private int sifra;
	private String skraceniNaziv;
	private int prodajni;
	private int srednji;
	private int kupovni;
	private String naziv;

	public Kurs(int sifra, String skraceniNaziv, int prodajni, int srednji, int kupovni, String naziv) {
		this.sifra = sifra;
		this.skraceniNaziv = skraceniNaziv;
		this.prodajni = prodajni;
		this.srednji = srednji;
		this.kupovni = kupovni;
		this.naziv = naziv;
	}

	public int getSifra() {
		return sifra;
	}

	public void setSifra(int sifra) {
		this.sifra = sifra;
	}

	public String getSkraceniNaziv() {
		return skraceniNaziv;
	}

	public void setSkraceniNaziv(String skraceniNaziv) {
		this.skraceniNaziv = skraceniNaziv;
	}

	public int getProdajni() {
		return prodajni;
	}

	public void setProdajni(int prodajni) {
		this.prodajni = prodajni;
	}

	public int getSrednji() {
		return srednji;
	}

	public void setSrednji(int srednji) {
		this.srednji = srednji;
	}

	public int getKupovni() {
		return kupovni;
	}

	public void setKupovni(int kupovni) {
		this.kupovni = kupovni;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	@Override
	public String toString() {
		return sifra + "\t" + skraceniNaziv + "\t" + prodajni + "\t" + srednji + "\t" + kupovni + "\t" + naziv;
	}

}
