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

	// variável auxiliar para definir o tamanho do estoque
	private int tamanho = 0;

	// Método construtor da classe
	public VendaCarro() {
		processar();
	}

	// Método principal do programa
	public void processar() {

		// capturando do usuário o tamanho do estoque
		tamanho = Integer.parseInt(JOptionPane.showInputDialog("Informe o tamanho do estoque:"));

		// definindo o tamanho do estoque de carros
		estoque = new Carro[tamanho];

		// processamento de looping do programa
		while (true) {
			escolhaUsuario();
		}
	}

	// Método para capturar do usuário a opção
	public void escolhaUsuario() {
		String menu = "Informe a opção desejada:\n\n" + "OPÇÃO 1 : Cadastrar Carro\n" + "OPÇÃO 2 : Listar Estoque\n"
				+ "OPÇÃO 3 : Consultar Carro\n" + "OPÇÃO 4 : Pesquisar por Modelo\n"
				+ "OPÇÃO 5 : Pesquisar por Combustível\n" + "OPÇÃO 6 : Listar carro em promoção\n"
				+ "OPÇÃO 7 : Sair do Sistema\n";

		int escolha = Integer.parseInt(JOptionPane.showInputDialog(menu));
		escolhaProcessamento(escolha);
	}

	// Método para seleção das opções do programa
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
			JOptionPane.showMessageDialog(null, "Opção inválida!", "Cadastro de estoque.", 0);// erro
			break;
		}
	}

	// Método para efetuar o cadastro de um novo carro no estoque
	public void cadastrarCarro() {

		// variável auxiliar para verificar se gravou
		boolean gravado = false;

		// laço para verificar espaço em estoque
		for (int i = 0; i < estoque.length; i++) {
			if (estoque[i] == null) {
				estoque[i] = criarCarro();
				gravado = true;
				break;
			}
		}

		// exibindo o resultado para o usuário
		if (gravado) {
			JOptionPane.showMessageDialog(null, "Veículo gravado com sucesso!", "Cadastro de Estoque", 1);
		} else {
			JOptionPane.showMessageDialog(null, "Estoque Cheio!", "Cadastro de Estoque", 2);
		}

	}

	// Método para devolver um carro para cadastro no estoque
	public Carro criarCarro() {
		Marca marca = new Marca();
		Modelo modelo = new Modelo();
		Carro carro = new Carro();

		// recebendo do usuário a marca do carro
		marca.setNome(JOptionPane.showInputDialog("Informe o nome da marca"));

		// recebendo do usuário o modelo do carro
		modelo.setNome(JOptionPane.showInputDialog("Informe o nome do modelo"));

		// atribuindo a marca ao modelo
		modelo.setMarca(marca);

		// recebendo os dados do carro
		carro.setPlaca(JOptionPane.showInputDialog("Informe a placa"));
		carro.setVelocidadeMaxima(Integer.parseInt(JOptionPane.showInputDialog("Informe a velocidade máxima")));
		carro.setCombustivel(JOptionPane.showInputDialog("Informe o combustível"));

		// Carro disponível ou não
		int disponivel = Integer.parseInt(JOptionPane.showInputDialog("1 - Disponível\n" + "2 - Não Disponível"));

		if (disponivel == 1) {
			carro.setDisponivel(true);
		} else {
			carro.setDisponivel(false);
		}

		// Carro em promoção ou não
		int promocao = Integer.parseInt(JOptionPane.showInputDialog("1 - Em promoção\n" + "2 - Não está em promoção"));

		if (promocao == 1) {
			carro.setPromocao(true);
		} else {
			carro.setPromocao(false);
		}

		carro.setValor(Double.parseDouble(JOptionPane.showInputDialog("Informe o valor do carro")));
		carro.setValorPromocao(Double.parseDouble(JOptionPane.showInputDialog("Informe o valor em promoção")));
		carro.setDesconto(Double.parseDouble(JOptionPane.showInputDialog("Informe o valor do desconto")));

		// atribuindo o modelo ao carro
		carro.setModelo(modelo);

		return carro;
	}

	// Método para listar o estoque total de veículos cadastrados
	public void listarEstoque() {

		// variável auxiliar para exibir estoque para o usuário
		String mensagem = "";

		// varrendo o estoque para ver se existe carros cadastrados
		for (int i = 0; i < estoque.length; i++) {
			if (estoque[i] != null) {
				mensagem += (i + 1) + " - " + estoque[i].getModelo().getNome() + "\n";
			}
		}

		// exibindo o estoque para o usuário
		JOptionPane.showMessageDialog(null, mensagem, "Cadastro de Estoque", 1);

	}

	// Método para consultar um carro específico
	public void consultarCarro() {

		// variável auxiliar para mandar mensagem para o usuário
		String mensagem = "Informe o código do veículo a ser consultado:\n\n";

		// varrendo o estoque para verificar os carros cadastrados
		for (int i = 0; i < estoque.length; i++) {
			if (estoque[i] != null) {
				mensagem += "Código " + (i + 1) + " - " + estoque[i].getModelo().getNome() + "\n";
			}
		}

		// capturando o modelo a ser consultado
		int codigo = Integer.parseInt(JOptionPane.showInputDialog(mensagem));

		// inicializando a variável auxiliar para exibir a mensagem
		mensagem = "";
		mensagem += "Modelo: " + estoque[codigo - 1].getModelo().getNome() + "\n";
		mensagem += "Marca.: " + estoque[codigo - 1].getModelo().getMarca().getNome() + "\n";
		mensagem += "Placa: " + estoque[codigo - 1].getPlaca() + "\n";
		mensagem += "Combustível: " + estoque[codigo - 1].getCombustivel() + "\n";
		mensagem += "Vel Máxima: " + estoque[codigo - 1].getVelocidadeMaxima() + "\n";

		// verificar se está disponível
		if (estoque[codigo - 1].isDisponivel()) {
			mensagem += "Disponível.: SIM\n";
		} else {
			mensagem += "Disponível.: NÃO\n";
		}

		// verificando se o carro está em promoção
		if (estoque[codigo - 1].isPromocao()) {
			mensagem += "Promoção...: SIM\n";
			mensagem += "Valor......: " + estoque[codigo - 1].getValorPromocao() + "\n";
		} else {
			mensagem += "Promoção...: NÃO\n	";
			mensagem += "Valor......: " + estoque[codigo - 1].getValor() + "\n";
		}

		// exibindo o resultado da pesquisa para o usuário
		JOptionPane.showMessageDialog(null, mensagem, "Cadastro de Estoque", 1);
	}

	// Método para retornar todos os carros por modelo escolhido
	public void pesquisarPorModelo() {

		// recebendo do usuário qual o modelo a ser pesquisado
		String modelo = JOptionPane.showInputDialog("Informe o modelo para pesquisa");

		// variável auxiliar para exibir a mensagem do resultado pesquisado
		String mensagem = "Pesquisa por modelo:\n\n";

		// variável auxiliar para verificar se o modelo existe no cadastro
		boolean existe = false;

		// varrendo o vetor e verificando o carro cadastrado
		for (int i = 0; i < estoque.length; i++) {
			if (estoque[i] != null && estoque[i].getModelo().getNome().equals(modelo)) {
				mensagem += mensagem(estoque[i]);
				existe = true;
			}
		}

		// exibindo o resultado da pesquisa ao usuário
		if (existe) {
			JOptionPane.showMessageDialog(null, mensagem, "Cadastro de Estoque", 1);
		} else {
			JOptionPane.showMessageDialog(null, "Não existe o modelo cadastrado", "Cadastro de Estoque", 2);
		}

	}

	// Método para retornar todos os carros por combustível escolhido
	public void pesquisarPorCombustivel() {

		// recebendo do usuário qual o modelo a ser pesquisado
		String combustivel = JOptionPane.showInputDialog("Informe o combustivel para pesquisa");

		// variável auxiliar para exibir a mensagem do resultado pesquisado
		String mensagem = "Pesquisa por combustivel:\n\n";

		// variável auxiliar para verificar se o modelo existe no cadastro
		boolean existe = false;

		// varrendo o vetor e verificando o carro cadastrado
		for (int i = 0; i < estoque.length; i++) {
			if (estoque[i] != null && estoque[i].getCombustivel().equals(combustivel)) {
				mensagem += mensagem(estoque[i]);
				existe = true;
			}
		}

		// exibindo o resultado da pesquisa ao usuário
		if (existe) {
			JOptionPane.showMessageDialog(null, mensagem, "Cadastro de Estoque", 1);
		} else {
			JOptionPane.showMessageDialog(null, "Não existe o combustível cadastrado", "Cadastro de Estoque", 2);
		}
	}

	// Método para retornar todos os carros em promoção
	public void listarCarrosEmpromocao() {

		// recebendo do usuário qual o modelo a ser pesquisado
		String promocao = JOptionPane.showInputDialog("Informe a promoção para pesquisa");
		boolean aux = false;
		if (promocao.equals("SIM")) {
			aux = true;
		}

		// variável auxiliar para exibir a mensagem do resultado pesquisado
		String mensagem = "Pesquisa por promoção:\n\n";

		// variável auxiliar para verificar se o modelo existe no cadastro
		boolean existe = false;

		// varrendo o vetor e verificando o carro cadastrado
		for (int i = 0; i < estoque.length; i++) {
			if (estoque[i] != null && estoque[i].isPromocao() == aux) {
				mensagem += mensagem(estoque[i]);
				existe = true;
			}
		}

		// exibindo o resultado da pesquisa ao usuário
		if (existe) {
			JOptionPane.showMessageDialog(null, mensagem, "Cadastro de Estoque", 1);
		} else {
			JOptionPane.showMessageDialog(null, "Não existe promoção cadastrada", "Cadastro de Estoque", 2);
		}
	}

	// Método para montar mensagem para o usuário
	public String mensagem(Carro carro) {

		// variável auxiliar para retornar a mensagem formada
		String msg = "";

		// montando a mensagem de retorno para o usuário
		msg += "\nModelo: " + carro.getModelo().getNome() + "\n";
		msg += "Marca: " + carro.getModelo().getMarca().getNome() + "\n";
		msg += "Placa: " + carro.getPlaca() + "\n";
		msg += "Combustível: " + carro.getCombustivel() + "\n";
		msg += "Vel Máxima: " + carro.getVelocidadeMaxima() + "\n";

		// retornando a variável formatada
		return msg;
	}

	// Método para sair do sistema
	public void sairDoSistema() {
		System.exit(0);
	}
}
