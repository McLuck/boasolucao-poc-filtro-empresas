package br.com.boasolucao.empresas;

public class Filtro {
	private String nome;
	private Comparador comparador = Comparador.IGUAL;
	private boolean aceitaVazio;
	private String valor;
	private boolean trimmed = true;
	
	public Filtro aceitarVazio() {
		aceitaVazio = true;
		return this;
	}

	public static Filtro filtrar(String coluna, String valor) {
		return filtrar(coluna, Comparador.IGUAL, valor);
	}

	public static Filtro filtrar(String coluna, Comparador comparador, String valor) {
		if (coluna == null) {
			throw new RuntimeException("Nome da coluna nao pode ser nula no filtro");
		}
		if (valor == null) {
			throw new RuntimeException("Valor nao pode ser nulo no filtro");
		}
		Filtro filtro = new Filtro();
		filtro.nome = coluna;
		filtro.valor = valor;
		filtro.comparador = comparador;
		return filtro;
	}

	public Filtro considerarEspacoesNoInicioEFim() {
		trimmed = false;
		valor = valor.trim();
		return this;
	}

	public boolean atende(Coluna coluna, String texto) {
		if (!coluna.ehColuna(nome)) {
			return true;
		}
		if (aceitaVazio && (texto == null || texto.trim().isEmpty())) {
			return true;
		}
		if (texto != null) {
			if (trimmed) {
				texto = texto.trim();
			}
			switch (comparador) {
			case IGUAL:
				return texto.equals(valor);
			case DIFERENTE:
				return !texto.equals(valor);
			case MAIOR:
				return valor.compareTo(texto) > 0;
			case MENOR:
				return valor.compareTo(texto) < 0;
			}
		}
		return false;
	}
}
