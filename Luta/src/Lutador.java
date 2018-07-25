public class Lutador extends Pessoa{
	
	public String categoria;
	public int vida;
	
	public Lutador(String nome, String pais, String categoria ) {
		super(nome, pais);
		this.categoria = categoria;
		this.vida = 3;
		}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

	@Override
	public String toString() {
		return this.nome + " [país="+ pais +", categoria=" + categoria + ", vida=" + vida + "]";
	}
	
	
	

}
