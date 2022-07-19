package estoque;

/**
 * Classe para armazenar os métodos e os atributos do objeto modelo
 * 
 * @author Victor Baptista
 * @since 17/02/2021
 */
public class Modelo {

	//Método construtor da classe
	public Modelo() {
	}
	
	// declarando os atributos
	private String nome;
	private Marca marca;

	//Métodos getters e setters
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

}
