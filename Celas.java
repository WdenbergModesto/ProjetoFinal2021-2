package Trabalho_Final;

public class Celas {
	private String nomebloco;
	private int capacidade;
	private int numerocela;

	public Celas(String blc, int cap, int numcel) {
		this.nomebloco=blc;
		this.capacidade=cap;
		this.numerocela=numcel;
	}

	public Celas(){
		nomebloco="";
		capacidade=0;
		numerocela=0;
	}
	public String getNomebloco() {
		return nomebloco;
	}

	public void setNomebloco(String blc) {
		this.nomebloco = blc;
	}
	
	public int getCapacidade() {
		return capacidade;
	}
	public void setCapacidade(int cap) {
		this.capacidade = cap;
	}

	public int getNumerocela(){
		return numerocela;
	}
	
	public void setNumerocela(int numcel) {
		this.numerocela = numcel;
	}
	
	public String toString() {
		return this.numerocela+"\t"+capacidade+"\t"+nomebloco+"\n";
	}
}