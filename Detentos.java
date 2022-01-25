package Trabalho_Final;

public class Detentos {
	private String nomedetento;
	private int registrodetento, idade, numcela;
	private long telefone;
	

	public Detentos(String nomedetento, int numcela, int idade, int registrodetento,long telefone) {
		this.nomedetento = nomedetento;
		this.numcela = numcela;
		this.idade = idade;
		this.registrodetento = registrodetento;
		this.telefone = telefone;
			
	}

	public String getNomedetento() {
		return nomedetento;
	}

	public void setNomedetento(String nomedetento) {
		this.nomedetento = nomedetento;
	}
	
	public int getNumcela() {
		return numcela;
	}
	
	public void setNumerocela(int numerocela) {
		this.numcela = numerocela;
	}
	
	public int getIdade() {
		return idade;
	}
	
	public void setIdade(int idade) {
		this.idade = idade;
	}

	public int getRegistrodetento() {
		return registrodetento;
	}

	public void setRegistrodetento(int registrodetento) {
		this.registrodetento = registrodetento;
	}

	public long getTelefone() {
		return telefone;
	}

	public void setTelefone(long telefone) {
		this.telefone = telefone;
	}


	public String toString() {
		return this.numcela+"\t"+this.registrodetento+"\t"+this.idade+"\t"+this.nomedetento+"\t"+this.telefone+"\n";
	}
}
