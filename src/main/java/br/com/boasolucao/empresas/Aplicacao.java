package br.com.boasolucao.empresas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static br.com.boasolucao.empresas.Comparador.*;
import static br.com.boasolucao.empresas.Coluna.*;

/**
 * Fonte de dados:
 * http://receita.economia.gov.br/orientacao/tributaria/cadastros/cadastro-nacional-de-pessoas-juridicas-cnpj/dados-publicos-cnpj
 * Template dos arquivos:
 * http://200.152.38.155/CNPJ/LAYOUT_DADOS_ABERTOS_CNPJ.pdf
 * 
 * @author Lucas Israel
 *
 */
public class Aplicacao {

	public static void main(String[] args) throws Exception {
		montarColunas();
		
		lerArquivo = "/Users/lucasisrael/Downloads/K3241.K03200DV.D90607.L00001";
		escreverArquivo = "/Users/lucasisrael/Downloads/K3241.K03200DV.D90607.L00001-processado.csv";
		
		/**
		 * Caso não trasforme em CSV, o arquivo poderá ter novos filtros aplicados nesta aplicação
		 */
		converterEmCSV = true;

		filtros.add(Filtro.filtrar(CNAE, IGUAL, "5611203"));
		filtros.add(Filtro.filtrar(UF, IGUAL, "SP"));

		criarArquivoFiltrado();
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	static List<Coluna> colunas = new ArrayList<Coluna>();
	static List<Filtro> filtros = new ArrayList<Filtro>();
	private static String lerArquivo;
	private static String escreverArquivo;
	private static boolean converterEmCSV = false;

	static void criarArquivoFiltrado() throws Exception {
		long inicio = System.currentTimeMillis();
		System.out.println("Lendo arquivo " + lerArquivo);
		BufferedReader reader = new BufferedReader(new FileReader(lerArquivo));
		final BufferedWriter writer = new BufferedWriter(new FileWriter(new File(escreverArquivo)));
		colunas.forEach(c -> {
			try {
				writer.write(c.getNomeColuna() + ";");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		writer.newLine();
		String line;
		int quantidadeColunas = colunas.size();
		int quantidadeFiltrada = 0;
		while ((line = reader.readLine()) != null) {
			final String linha = line;
			if (colunas.parallelStream().filter(c -> aplicarFiltros(c, linha)).count() == quantidadeColunas) {
				if (converterEmCSV) {
					colunas.forEach(c -> {
						try {
							writer.write(c.extrairValor(linha) + ";");
						} catch (IOException e) {
							e.printStackTrace();
						}
					});
				} else {
					writer.write(linha);
				}
				writer.newLine();
				quantidadeFiltrada++;
			}
		}
		writer.flush();
		writer.close();
		reader.close();

		System.out.println(quantidadeFiltrada + " itens filtrado no arquivo " + escreverArquivo);
		long fim = System.currentTimeMillis();
		System.out.println("Tempo gasto em segundos: " + ((fim - inicio) / 1000));
	}

	static boolean aplicarFiltros(final Coluna coluna, final String linha) {
		if (filtros == null || filtros.isEmpty()) {
			return true;
		}
		final String valor = coluna.extrairValor(linha);
		Stream<Filtro> stream;
		if (filtros.size() > 3) {
			stream = filtros.parallelStream();
		} else {
			stream = filtros.stream();
		}
		int quantidadeFiltros = filtros.size();
		
		return stream.filter(f -> f.atende(coluna, valor)).count() == quantidadeFiltros;
	}

	static void montarColunas() {
		colunas.add(new Coluna(CNPJ, 4, 14));
		colunas.add(new Coluna(INDICADOR_MATRIZ, 18, 1));
		colunas.add(new Coluna(RAZAO_SOCIAL, 19, 150));
		colunas.add(new Coluna(NOME_FANTAZIA, 169, 55));
		colunas.add(new Coluna(SITUACAO, 224, 2));
		colunas.add(new Coluna(NATUREZA_JURIDICA, 364, 4));
		colunas.add(new Coluna(INICIO_ATIVIDADES, 368, 8));
		colunas.add(new Coluna(CNAE, 376, 7));
		colunas.add(new Coluna(LOGRADOURO, 403, 60));
		colunas.add(new Coluna(NUMERO, 463, 6));
		colunas.add(new Coluna(COMPLEMENTO, 469, 156));
		colunas.add(new Coluna(BAIRRO, 625, 50));
		colunas.add(new Coluna(CEP, 675, 8));
		colunas.add(new Coluna(UF, 683, 2));
		colunas.add(new Coluna(TEL1, 683, 12));
		colunas.add(new Coluna(TEL2, 751, 12));
		colunas.add(new Coluna(FAX, 763, 12));
		colunas.add(new Coluna(EMAIL, 775, 115));
		colunas.add(new Coluna(CAPITAL_SOCIAL, 892, 14).tipoNumero());
		colunas.add(new Coluna(PORTE, 906, 2));
		colunas.add(new Coluna(SIMPLES, 908, 1));
		colunas.add(new Coluna(MEI, 925, 1));
	}

}
