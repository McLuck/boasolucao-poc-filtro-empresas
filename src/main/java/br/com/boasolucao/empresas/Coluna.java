package br.com.boasolucao.empresas;

public class Coluna {
	private String descricao;
	private int posicao;
	private int tamanho;
	private TipoDados tipo = TipoDados.STRING;
	
	public static final String DETALHE = "DETALHE";
	public static final String CNPJ = "CNPJ";
	public static final String INDICADOR_MATRIZ = "Indicador Matriz(1)/Filial(2)";
	public static final String RAZAO_SOCIAL = "Razão Social";
	public static final String NOME_FANTAZIA = "Nome Fantasia";
	public static final String SITUACAO = "Situacao";
	public static final String NATUREZA_JURIDICA = "Natureza Juridica";
	public static final String INICIO_ATIVIDADES = "Inicio de Atividade";
	public static final String CNAE = "CNAE";
	public static final String LOGRADOURO = "Logradouro";
	public static final String NUMERO = "Número";
	public static final String COMPLEMENTO = "Complemento (endereço)";
	public static final String BAIRRO = "Bairro";
	public static final String CEP = "CEP";
	public static final String UF = "UF";
	public static final String TEL1 = "Telefone 1";
	public static final String TEL2 = "Telefone 2";
	public static final String FAX = "FAX";
	public static final String EMAIL = "Email";
	public static final String CAPITAL_SOCIAL = "Capital Social";
	public static final String PORTE = "Porte";
	public static final String SIMPLES = "Simples";
	public static final String MEI = "MEI";

	public Coluna(String descricao, int posicao, int tamanho) {
		super();
		this.descricao = descricao;
		this.posicao = posicao - 1;
		this.tamanho = tamanho;
	}
	
	public Coluna tipoNumero() {
		this.tipo = TipoDados.NUMERO;
		return this;
	}

	public String getNomeColuna() {
		return descricao;
	}

	public String extrairValor(String texto) {
		return texto.substring(posicao, tamanho + posicao);
	}
	
	public boolean ehColuna(String nome) {
		return nome.equals(descricao);
	}
	
	public boolean ehNumerico() {
		return TipoDados.NUMERO.equals(tipo);
	}
}
