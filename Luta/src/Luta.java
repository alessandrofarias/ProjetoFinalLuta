public class Luta {

	public Lutador lutador;
	public int iniciar;
	
	public Luta(Lutador lutador) {
			this.lutador = lutador;
	}
	
	
	public void rodada(int golpe, int defesa) {
		if( golpe != defesa ) {
			lutador.vida -= 1;
		}
	}
	
	public Lutador getLutador() {
		return this.lutador;
	}
		
}
