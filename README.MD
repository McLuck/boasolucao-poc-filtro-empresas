# Aplicação para filtrar dados públicos de CNPJ
Os dados públicos de CNPJ estão disponíveis para download no [site da receita federal](http://receita.economia.gov.br/orientacao/tributaria/cadastros/cadastro-nacional-de-pessoas-juridicas-cnpj/dados-publicos-cnpj).
Esta aplicação é uma POC para ajudar a filtrar de forma fácil os dados deste arquivo sem utilização de frameworks externos.

# O que é necessário?
### Ambiente
 - [JDK 8](https://www.azul.com/downloads/zulu/) ou superior
 - [Maven](https://maven.apache.org)
 - IDE de sua preferência. Particularmente, prefiro o [eclipse](https://www.eclipse.org/downloads/packages/release/2019-06/r/eclipse-ide-enterprise-java-developers)

### Fonte de dados
Os arquivos da receita federal estão disponíveis para download na URL [http://receita.economia.gov.br/orientacao/tributaria/cadastros/cadastro-nacional-de-pessoas-juridicas-cnpj/dados-publicos-cnpj](http://receita.economia.gov.br/orientacao/tributaria/cadastros/cadastro-nacional-de-pessoas-juridicas-cnpj/dados-publicos-cnpj)

Além disto, você precisará conhecer o template do arquivo, para conseguir interpretar o conteúdo do arquivo.
O documento que define o layout do arquivo está disponível nesta url: http://200.152.38.155/CNPJ/LAYOUT_DADOS_ABERTOS_CNPJ.pdf

## Filtrando em arquivos menores somente o que você precisa

 - Importe o projeto no seu IDE. [Aqui tem um tópico mostrando como fazer no eclipse](https://pt.stackoverflow.com/a/125575)
 - Navegue até a classe ***br.com.boasolucao.empresas.Aplicacao***
 - Informe o local que está o arquivo que foi baixado no passo anterior (fonte de dados da receita federal) na varável **_lerArquivo_** e o local exato que o arquivo de saída será gerado com os filtros aplicados.
Exemplo:
```java
	lerArquivo = "/User/lucasisrael/pocs/resource/K3241.K03200DV.D90607.L00001";
	escreverArquivo = "/User/lucasisrael/pocs/resource/K3241.K03200DV.D90607.L00001-filtrado.csv";
```

 - Na varável **_converterEmCSV_** você pode configurar se o arquivo de saída será um [CSV](https://pt.wikipedia.org/wiki/Comma-separated_values) ou não. Caso não converta em [CSV](https://pt.wikipedia.org/wiki/Comma-separated_values), o arquivo de saída irá manter o mesmo [layout do original definido no documento da receita federal](http://200.152.38.155/CNPJ/LAYOUT_DADOS_ABERTOS_CNPJ.pdf).
 - Após realizar as configurações descritas acima, é só executar a classe ***br.com.boasolucao.empresas.Aplicacao***

### Adicionando filtros
Para adicionar os filtros, basta adicionar um novo objeto informando a coluna e o valor que deseja filtrar na lista de filtros.
Exemplo:
```java
	filtros.add(Filtro.filtrar(CNAE, IGUAL, "5611203"));
	filtros.add(Filtro.filtrar(UF, DIFERENTE, "SP"));
```


#### Bons estudos!!!
