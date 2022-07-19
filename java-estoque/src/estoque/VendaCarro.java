package estoque;

import javax.swing.JOptionPane;

/**
 * Classe para efetuar o processamento de consulta e estoque
 * 
 * @author Victor Baptista
 * @since 17/02/2021
 */
public class VendaCarro {

	// vetor para armazenar os carros cadastrados
	private Carro estoque[];

	// vari�vel auxiliar para definir o tamanho do estoque
	private int tamanho = 0;

	// M�todo construtor da classe
	public VendaCarro() {
		processar();
	}

	// M�todo principal do programa
	public void processar() {

		// capturando do usu�rio o tamanho do estoque
		tamanho = Integer.parseInt(JOptionPane.showInputDialog("Informe o tamanho do estoque:"));

		// definindo o tamanho do estoque de carros
		estoque = new Carro[tamanho];

		// processamento de looping do programa
		while (true) {
			escolhaUsuario();
		}
	}

	// M�todo para capturar do usu�rio a op��o
	public void escolhaUsuario() {
		String menu = "Informe a op��o desejada:\n\n" + "OP��O 1 : Cadastrar Carro\n" + "OP��O 2 : Listar Estoque\n"
				+ "OP��O 3 : Consultar Carro\n" + "OP��O 4 : Pesquisar por Modelo\n"
				+ "OP��O 5 : Pesquisar por Combust�vel\n" + "OP��O 6 : Listar carro em promo��o\n"
				+ "OP��O 7 : Sair do Sistema\n";

		int escolha = Integer.parseInt(JOptionPane.showInputDialog(menu));
		escolhaProcessamento(escolha);
	}

	// M�todo para sele��o das op��es do programa
	public void escolhaProcessamento(int opcao) {
		switch (opcao) {
		case 1:
			cadastrarCarro();
			break;

		case 2:
			listarEstoque();
			break;

		case 3:
			consultarCarro();
			break;

		case 4:
			pesquisarPorModelo();
			break;

		case 5:
			pesquisarPorCombustivel();
			break;

		case 6:
			listarCarrosEmpromocao();
			break;

		case 7:
			sairDoSistema();
			break;

		default:
			JOptionPane.showMessageDialog(null, "Op��o inv�lida!", "Cadastro de estoque.", 0);// erro
			break;
		}
	}

	// M�todo para efetuar o cadastro de um novo carro no estoque
	public void cadastrarCarro() {

		// vari�vel auxiliar para verificar se gravou
		boolean gravado = false;

		// la�o para verificar espa�o em estoque
		for (int i = 0; i < estoque.length; i++) {
			if (estoque[i] == null) {
				estoque[i] = criarCarro();
				gravado = true;
				break;
			}
		}

		// exibindo o resultado para o usu�rio
		if (gravado) {
			JOptionPane.showMessageDialog(null, "Ve�culo gravado com sucesso!", "Cadastro de Estoque", 1);
		} else {
			JOptionPane.showMessageDialog(null, "Estoque Cheio!", "Cadastro de Estoque", 2);
		}

	}

	// M�todo para devolver um carro para cadastro no estoque
	public Carro criarCarro() {
		Marca marca = new Marca();
		Modelo modelo = new Modelo();
		Carro carro = new Carro();

		// recebendo do usu�rio a marca do carro
		marca.setNome(JOptionPane.showInputDialog("Informe o nome da marca"));

		// recebendo do usu�rio o modelo do carro
		modelo.setNome(JOptionPane.showInputDialog("Informe o nome do modelo"));

		// atribuindo a marca ao modelo
		modelo.setMarca(marca);

		// recebendo os dados do carro
		carro.setPlaca(JOptionPane.showInputDialog("Informe a placa"));
		carro.setVelocidadeMaxima(Integer.parseInt(JOptionPane.showInputDialog("Informe a velocidade m�xima")));
		carro.setCombustivel(JOptionPane.showInputDialog("Informe o combust�vel"));

		// Carro dispon�vel ou n�o
		int disponivel = Integer.parseInt(JOptionPane.showInputDialog("1 - Dispon�vel\n" + "2 - N�o Dispon�vel"));

		if (disponivel == 1) {
			carro.setDisponivel(true);
		} else {
			carro.setDisponivel(false);
		}

		// Carro em promo��o ou n�o
		int promocao = Integer.parseInt(JOptionPane.showInputDialog("1 - Em promo��o\n" + "2 - N�o est� em promo��o"));

		if (promocao == 1) {
			carro.setPromocao(true);
		} else {
			carro.setPromocao(false);
		}

		carro.setValor(Double.parseDouble(JOptionPane.showInputDialog("Informe o valor do carro")));
		carro.setValorPromocao(Double.parseDouble(JOptionPane.showInputDialog("Informe o valor em promo��o")));
		carro.setDesconto(Double.parseDouble(JOptionPane.showInputDialog("Informe o valor do desconto")));

		// atribuindo o modelo ao carro
		carro.setModelo(modelo);

		return carro;
	}

	// M�todo para listar o estoque total de ve�culos cadastrados
	public void listarEstoque() {

		// vari�vel auxiliar para exibir estoque para o usu�rio
		String mensagem = "";

		// varrendo o estoque para ver se existe carros cadastrados
		for (int i = 0; i < estoque.length; i++) {
			if (estoque[i] != null) {
				mensagem += (i + 1) + " - " + estoque[i].getModelo().getNome() + "\n";
			}
		}

		// exibindo o estoque para o usu�rio
		JOptionPane.showMessageDialog(null, mensagem, "Cadastro de Estoque", 1);

	}

	// M�todo para consultar um carro espec�fico
	public void consultarCarro() {

		// vari�vel auxiliar para mandar mensagem para o usu�rio
		String mensagem = "Informe o c�digo do ve�culo a ser consultado:\n\n";

		// varrendo o estoque para verificar os carros cadastrados
		for (int i = 0; i < estoque.length; i++) {
			if (estoque[i] != null) {
				mensagem += "C�digo " + (i + 1) + " - " + estoque[i].getModelo().getNome() + "\n";
			}
		}

		// capturando o modelo a ser consultado
		int codigo = Integer.parseInt(JOptionPane.showInputDialog(mensagem));

		// inicializando a vari�vel auxiliar para exibir a mensagem
		mensagem = "";
		mensagem += "Modelo: " + estoque[codigo - 1].getModelo().getNome() + "\n";
		mensagem += "Marca.: " + estoque[codigo - 1].getModelo().getMarca().getNome() + "\n";
		mensagem += "Placa: " + estoque[codigo - 1].getPlaca() + "\n";
		mensagem += "Combust�vel: " + estoque[codigo - 1].getCombustivel() + "\n";
		mensagem += "Vel M�xima: " + estoque[codigo - 1].getVelocidadeMaxima() + "\n";

		// verificar se est� dispon�vel
		if (estoque[codigo - 1].isDisponivel()) {
			mensagem += "Dispon�vel.: SIM\n";
		} else {
			mensagem += "Dispon�vel.: N�O\n";
		}

		// verificando se o carro est� em promo��o
		if (estoque[codigo - 1].isPromocao()) {
			mensagem += "Promo��o...: SIM\n";
			mensagem += "Valor......: " + estoque[codigo - 1].getValorPromocao() + "\n";
		} else {
			mensagem += "Promo��o...: N�O\n	";
			mensagem += "Valor......: " + estoque[codigo - 1].getValor() + "\n";
		}

		// exibindo o resultado da pesquisa para o usu�rio
		JOptionPane.showMessageDialog(null, mensagem, "Cadastro de Estoque", 1);
	}

	// M�todo para retornar todos os carros por modelo escolhido
	public void pesquisarPorModelo() {

		// recebendo do usu�rio qual o modelo a ser pesquisado
		String modelo = JOptionPane.showInputDialog("Informe o modelo para pesquisa");

		// vari�vel auxiliar para exibir a mensagem do resultado pesquisado
		String mensagem = "Pesquisa por modelo:\n\n";

		// vari�vel auxiliar para verificar se o modelo existe no cadastro
		boolean existe = false;

		// varrendo o vetor e verificando o carro cadastrado
		for (int i = 0; i < estoque.length; i++) {
			if (estoque[i] != null && estoque[i].getModelo().getNome().equals(modelo)) {
				mensagem += mensagem(estoque[i]);
				existe = true;
			}
		}

		// exibindo o resultado da pesquisa ao usu�rio
		if (existe) {
			JOptionPane.showMessageDialog(null, mensagem, "Cadastro de Estoque", 1);
		} else {
			JOptionPane.showMessageDialog(null, "N�o existe o modelo cadastrado", "Cadastro de Estoque", 2);
		}

	}

	// M�todo para retornar todos os carros por combust�vel escolhido
	public void pesquisarPorCombustivel() {

		// recebendo do usu�rio qual o modelo a ser pesquisado
		String combustivel = JOptionPane.showInputDialog("Informe o combustivel para pesquisa");

		// vari�vel auxiliar para exibir a mensagem do resultado pesquisado
		String mensagem = "Pesquisa por combustivel:\n\n";

		// vari�vel auxiliar para verificar se o modelo existe no cadastro
		boolean existe = false;

		// varrendo o vetor e verificando o carro cadastrado
		for (int i = 0; i < estoque.length; i++) {
			if (estoque[i] != null && estoque[i].getCombustivel().equals(combustivel)) {
				mensagem += mensagem(estoque[i]);
				existe = true;
			}
		}

		// exibindo o resultado da pesquisa ao usu�rio
		if (existe) {
			JOptionPane.showMessageDialog(null, mensagem, "Cadastro de Estoque", 1);
		} else {
			JOptionPane.showMessageDialog(null, "N�o existe o combust�vel cadastrado", "Cadastro de Estoque", 2);
		}
	}

	// M�todo para retornar todos os carros em promo��o
	public void listarCarrosEmpromocao() {

		// recebendo do usu�rio qual o modelo a ser pesquisado
		String promocao = JOptionPane.showInputDialog("Informe a promo��o para pesquisa");
		boolean aux = false;
		if (promocao.equals("SIM")) {
			aux = true;
		}

		// vari�vel auxiliar para exibir a mensagem do resultado pesquisado
		String mensagem = "Pesquisa por promo��o:\n\n";

		// vari�vel auxiliar para verificar se o modelo existe no cadastro
		boolean existe = false;

		// varrendo o vetor e verificando o carro cadastrado
		for (int i = 0; i < estoque.length; i++) {
			if (estoque[i] != null && estoque[i].isPromocao() == aux) {
				mensagem += mensagem(estoque[i]);
				existe = true;
			}
		}

		// exibindo o resultado da pesquisa ao usu�rio
		if (existe) {
			JOptionPane.showMessageDialog(null, mensagem, "Cadastro de Estoque", 1);
		} else {
			JOptionPane.showMessageDialog(null, "N�o existe promo��o cadastrada", "Cadastro de Estoque", 2);
		}
	}

	// M�todo para montar mensagem para o usu�rio
	public String mensagem(Carro carro) {

		// vari�vel auxiliar para retornar a mensagem formada
		String msg = "";

		// montando a mensagem de retorno para o usu�rio
		msg += "\nModelo: " + carro.getModelo().getNome() + "\n";
		msg += "Marca: " + carro.getModelo().getMarca().getNome() + "\n";
		msg += "Placa: " + carro.getPlaca() + "\n";
		msg += "Combust�vel: " + carro.getCombustivel() + "\n";
		msg += "Vel M�xima: " + carro.getVelocidadeMaxima() + "\n";

		// retornando a vari�vel formatada
		return msg;
	}

	// M�todo para sair do sistema
	public void sairDoSistema() {
		System.exit(0);
	}
}
