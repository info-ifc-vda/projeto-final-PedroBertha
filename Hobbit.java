import java.util.Random;


public class Hobbit extends Combatente {

    public Hobbit(String nome, int nivel) {
        super(nome, nivel);
        this.vida += 20; 
    }

    @Override
    public void atacar(Combatente alvo) {
        Random r = new Random();

        int dano = this.ataque + r.nextInt(4);

        if (arma != null) {
            dano += arma.calcularDanoExtra();
        }

        System.out.println(this.nome + " atacou timidamente causando " + dano + " de dano.");
        alvo.defender(dano);
    }
    
}
