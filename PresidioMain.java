package Trabalho_Final;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class PresidioMain {

	static StringBuffer memoria = new StringBuffer();
	static Scanner scan = new Scanner(System.in);
	static int reg2=0;
	static int numcela=0;
	static int celera;
	public static void main(String[] args) {
		char resp = 'N';
		char opcao;

		System.out.println("---PENITENCIARIA FAESA---");
		System.out.println("");
		System.out.println(" Escolha uma opção abaixo");
		do {
			System.out.println("\n1 - Cadastrar o Celas.\n" 
					+ "2 - Cadastrar detentos.\n"
					+ "3 - Consulta geral Celas\n" 
					+ "4 - Consulta geral detentos.\n" 
					+ "5 - Consulta especifica.\n"
					+ "6 - Finalizar operações.\n");
			opcao = scan.next().charAt(0);

			switch (opcao) {
			case '1':
				inserirCelas();
				break;
			case '2':
				inserirDetento();
				break;
			case '3':
				consultarGeral1();
				break;
			case '4':
				consultarGeral2();
				break;
			case '5':
				System.out.println("Digite a cela a ser consultada, que será dito em qual bloco fica e a capacidade de detentos:");
				celera=scan.nextInt();
				consultaespecifica(celera);
				break;
			case '6':
				System.out.println("Deseja realmente sair do programa? S/N");
				resp = Character.toUpperCase(scan.next().charAt(0));
				System.out.println("Até a proxima.\n\nDESENVOLVIDO PELOS ALUNOS DO 2º PERÍODO DO \n      "
						+ "CENTRO UNIVERSITÁRIO FAESA - 2021/2\n\nBruno Barbosa Correira,\nPhellipe Santos Sarmento,\n"
						+ "Tiago de Lima Santos Abreu,\nWdenberg Modesto");
				break;
			default:
				System.out.println("Entrada inválida, por favor digite uma opção válida.");
				break;
			}
		} while (resp != 'S');

	}

	// INICIAR ARQUIVO: 
	static void iniciarArquivo() {
		try {
			BufferedReader arqEntrada;
			arqEntrada = new BufferedReader(new FileReader("Celas.txt"));
			String linha = "";
			memoria.delete(0, memoria.length());
			while ((linha = arqEntrada.readLine()) != null) {
				memoria.append(linha + "\n");
			}
			arqEntrada.close();
		} catch (FileNotFoundException erro) {
			System.out.println("\nArquivo nao encontrado");
		} catch (Exception e) {
			System.out.println("\nErro de Leitura !");
		}
	}

	// GRAVAR:
	public static void gravarDados() {
		try {
			BufferedWriter arqSaida;
			arqSaida = new BufferedWriter(new FileWriter("Celas.txt"));
			arqSaida.write(memoria.toString());
			arqSaida.flush();
			arqSaida.close();
		} catch (Exception e) {
			System.out.println("\nErro de gravação!");
		}
	}
	// 1 - INSERIR CELAS
	static void inserirCelas() {
		String nomebloco;
		int capacidade, numerocela;

		try {
			BufferedWriter saida;
			saida = new BufferedWriter(new FileWriter("Celas.txt", true));
			System.out.print("Digite o Bloco: ");
			nomebloco = scan.next();
			System.out.print("Digite a capacidade de detento: ");
			capacidade = scan.nextInt();
			System.out.print("Digite o número da cela:");
			numerocela = scan.nextInt();
			Celas regdetent = new Celas(nomebloco, capacidade, numerocela);
			saida.write(regdetent.toString());
			saida.flush();
			saida.close();
			System.out.println("Cela cadastrada com sucesso\n");
		} catch (Exception e) {
			System.out.println("Erro de gravação");
		}
	}

	// 3 - CONSULTA GERAL CELAS
	static private void consultarGeral1() {
		String numerocela, capacidade, nomebloco;
		String cad = "\nCelas cadastradas:\n\n";
		int inicio, fim, primeiro, ultimo;
		iniciarArquivo();
		inicio = 0;
		try {
			if (memoria.length() != 0) {
				while (inicio != memoria.length()) {
					ultimo = memoria.indexOf("\t", inicio);
					numerocela = memoria.substring(inicio, ultimo);
					primeiro = ultimo + 1;
					ultimo = memoria.indexOf("\t", primeiro);
					capacidade = memoria.substring(primeiro, ultimo);
					primeiro = ultimo + 1;
					fim = memoria.indexOf("\n", primeiro);
					nomebloco = memoria.substring(primeiro, fim);
					Celas regi = new Celas(nomebloco, Integer.parseInt(capacidade), Integer.parseInt(numerocela));
					cad += "Bloco: " + nomebloco + "\n" + "Capacidade da Cela: " + capacidade + "\n"
							+ "Número da Cela: " + numerocela + "\n\n";

					inicio = fim + 1;
				}
				System.out.println(cad);
			} else {
				System.out.println("Arquivo vazio.");
			}
		} catch (Exception erro2) {
			System.out.println("Erro de leitura.");
		}
	}

	// INICIAR ARQUIVO DETENTOS: 
	static void iniciarArquivo2() {
		try {
			BufferedReader arqEntrada;
			arqEntrada = new BufferedReader(new FileReader("Detentos.txt"));
			String linha = "";
			memoria.delete(0, memoria.length());
			while ((linha = arqEntrada.readLine()) != null) {
				memoria.append(linha + "\n");
			}
			arqEntrada.close();
		} catch (FileNotFoundException erro) {
			System.out.println("\nArquivo nao encontrado");
		} catch (Exception e) {
			System.out.println("\nErro de Leitura !");
		}
	}

	// GRAVAR DADOS DETENTOS:
	public static void gravarDados2() {
		try {
			BufferedWriter arqSaida;
			arqSaida = new BufferedWriter(new FileWriter("Detentos.txt"));
			arqSaida.write(memoria.toString());
			arqSaida.flush();
			arqSaida.close();
		} catch (Exception e) {
			System.out.println("\nErro de gravacao!");
		}
	}

	// 2 - INSERIR DADOS DE DETENTOS
	static void inserirDetento() {
		String nomedet;
		int regdet, idade;
		long teldet;
		try {
			BufferedWriter saida;
			saida = new BufferedWriter(new FileWriter("Detentos.txt", true));
			System.out.print("Digite o nome do detento: ");
			nomedet = scan.next();
			consultarcela();
			System.out.print("Número da cela: ");
			numcela = scan.nextInt();
			consultarcela2(numcela);
			System.out.print("Digite a idade do detento: ");
			idade = scan.nextInt();
			System.out.print("Digite o registro detento: ");
			regdet = scan.nextInt();
			System.out.print("Digite o contato do responsavel com ddd junto do número: ");
			teldet = scan.nextLong();
			Detentos regdetent = new Detentos(nomedet, numcela, idade, regdet, teldet);
			saida.write(regdetent.toString());
			saida.flush();
			saida.close();
			System.out.println("Detento cadastrado com sucesso\n");
		} catch (Exception e) {
			System.out.println("Erro de gravação");
		}
	}

	// 4 - CONSULTA GERAL DETENTOS
	static private void consultarGeral2() {
		String nomedetento, numcela, idade, registrodetento, telefone;
		String cadastro = "\nDetentos cadastrados:\n\n";
		int inicio, fim, primeiro, ultimo;
		iniciarArquivo2();
		inicio = 0;
		try {
			if (memoria.length() != 0) {
				while (inicio != memoria.length()) {
					ultimo = memoria.indexOf("\t", inicio);
					numcela = memoria.substring(inicio, ultimo);
					primeiro = ultimo + 1;

					ultimo = memoria.indexOf("\t", primeiro);
					registrodetento = memoria.substring(primeiro, ultimo);
					primeiro = ultimo + 1;

					ultimo = memoria.indexOf("\t", primeiro);
					idade = memoria.substring(primeiro, ultimo);
					primeiro = ultimo + 1;

					ultimo = memoria.indexOf("\t", primeiro);
					nomedetento = memoria.substring(primeiro, ultimo);
					primeiro = ultimo + 1;

					fim = memoria.indexOf("\n", primeiro);
					telefone = memoria.substring(primeiro, fim);
					Detentos reg = new Detentos(nomedetento, Integer.parseInt(numcela), Integer.parseInt(idade),
							Integer.parseInt(registrodetento), Long.parseLong(telefone));
					cadastro += "Nome do detento: " + nomedetento + "\n" + "Cela: " + numcela + "\n" + "Idade: " + idade
							+ "\n" + "Registro: " + registrodetento + "\n" + "Telefone: " + telefone + "\n\n";
					inicio = fim + 1;
				}
				System.out.println(cadastro);
			} else {
				System.out.println("Arquivo vazio.");
			}
		} catch (Exception erro2) {
			System.out.println("Erro de leitura.");
		}
	}
	//checar celas já escritas e mostrar para pessoa que vai cadastrar novos detentos
	static void consultarcela() {
		String numerocela, capacidade, nomebloco;
		String cad = "";
		int inicio, fim, primeiro, ultimo;
		iniciarArquivo();
		inicio = 0;
		try {
			if (memoria.length() != 0) {
				while (inicio != memoria.length()) {
					ultimo = memoria.indexOf("\t", inicio);
					numerocela = memoria.substring(inicio, ultimo);
					primeiro = ultimo + 1;
					ultimo = memoria.indexOf("\t", primeiro);
					capacidade = memoria.substring(primeiro, ultimo);
					primeiro = ultimo + 1;
					fim = memoria.indexOf("\n", primeiro);
					nomebloco = memoria.substring(primeiro, fim);
					Celas regi = new Celas(nomebloco, Integer.parseInt(capacidade), Integer.parseInt(numerocela));
					cad += numerocela + "\t";
					inicio = fim + 1;
				}
				String [] separacao = cad.split("\t");
				System.out.print("Celas disponiveis são: ");
				for (int i=0;i<separacao.length;i++) {
					System.out.print(separacao[i]+" ");
				}
				System.out.print("\n");
			} else {
				System.out.println("Arquivo vazio.");
			}
		} catch (Exception erro2) {
			System.out.println("Erro de leitura.");
		}
	}
	// método para condicionar a pessoa digitar somente celas já existentes.
	static void consultarcela2(int reg) {
		String numerocela, capacidade, nomebloco;
		String cad = "";
		reg2 = reg;
		boolean resposta = true;
		int inicio, fim, primeiro, ultimo;
		iniciarArquivo();
		inicio = 0;
		try {
			if (memoria.length() != 0) {
				while (inicio != memoria.length()) {
					ultimo = memoria.indexOf("\t", inicio);
					numerocela = memoria.substring(inicio, ultimo);
					primeiro = ultimo + 1;
					ultimo = memoria.indexOf("\t", primeiro);
					capacidade = memoria.substring(primeiro, ultimo);
					primeiro = ultimo + 1;
					fim = memoria.indexOf("\n", primeiro);
					nomebloco = memoria.substring(primeiro, fim);
					Celas regi = new Celas(nomebloco, Integer.parseInt(capacidade), Integer.parseInt(numerocela));
					cad += numerocela + "\t";
					inicio = fim + 1;
				}
				String [] separacao = cad.split("\t");
				int [] separacao2 = new int [separacao.length];
				for (int i=0;i<separacao2.length;i++) {
					separacao2[i]= Integer.parseInt(separacao[i]);
				}
				//				System.out.print("Celas disponiveis são: ");
				//				for (int i=0;i<separacao.length;i++) {
				//					System.out.print(separacao2[i]+" ");
				//				}
				//				System.out.print("\n");
				int m = 0;
				while(resposta == true) {
					if (m >= separacao2.length)
					{
						m = 0;
						System.out.println("A cela não esta cadastrada, por favor digite cela disponivel!");
						System.out.print("Digite o número da cela: ");
						reg2=scan.nextInt();
						for (int j = 0 ; j < separacao2.length; j++) {
							if(reg2 == separacao2[j]) {
								numcela = reg2;
								resposta = false;
							}
						}
					}
					else if(separacao2[m] == reg2) {
						resposta = false;
					}
					m += 1;
				} 
			}
			else {
				System.out.println("Arquivo vazio.");
			}
		} catch (Exception erro2) {
			System.out.println("Erro de leitura.");
		}


	}
	// 5 - Consulta Especifica
	static void consultaespecifica(int celalouca) {
		String numerocela, capacidade, nomebloco;
		String cad = "";//número cela
		String cad2 = "";//bloco
		String cad3 = "";//capacidade de detentos
		int inicio, fim, primeiro, ultimo;
		int achado = 0;
		int j = 0;
		boolean resposta = false;
		iniciarArquivo();
		inicio = 0;
		try {
			if (memoria.length() != 0) {
				while (inicio != memoria.length()) {
					ultimo = memoria.indexOf("\t", inicio);
					numerocela = memoria.substring(inicio, ultimo);
					primeiro = ultimo + 1;
					ultimo = memoria.indexOf("\t", primeiro);
					capacidade = memoria.substring(primeiro, ultimo);
					primeiro = ultimo + 1;
					fim = memoria.indexOf("\n", primeiro);
					nomebloco = memoria.substring(primeiro, fim);
					
					cad += numerocela +"\t";
					cad2 += nomebloco +"\t";
					cad3 += capacidade +"\t";
					inicio = fim + 1;
				}
				String [] separacao = cad.split("\t");
				int [] separara = new int [separacao.length];
				for (int i=0;i<separara.length;i++) {
					separara[i]= Integer.parseInt(separacao[i]);
				}
				String [] separacao2= cad2.split("\t");
				String [] separacao3= cad3.split("\t");
//				for (int i=0; i<separacao.length;i++) {
//					System.out.print("cela: "+separara[i]+"  "+separacao2[i]+" capacidade: "+separacao3[i]+"\n");
//				}
				while(j<separara.length) {
					if(celalouca == separara[j]) {
						achado = j;
						resposta = true;
					}
					j += 1;
				}
				if(resposta == true) {
					System.out.println("A cela nº "+celalouca+" esta no "+separacao2[achado]+" e cabe "+separacao3[achado]+" pessoas.");
				}
				else if(resposta == false) {
					System.out.println("A cela nº "+celalouca+" não tem na nossa prisão.");
				}
				
			} else {
				System.out.println("Arquivo vazio.");
			}
		} catch (Exception erro2) {
			System.out.println("Erro de leitura.");
		}
	}

}
