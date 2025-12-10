
import java.util.Random;


public class Anao extends Combatente {

    public Anao(String nome, int nivel) {
        super(nome, nivel);
        this.defesa += 5;
    }

    @Override
    public void atacar(Combatente alvo) {
        
        Random r = new Random();

        int dano = this.ataque + r.nextInt(10);

        if (arma != null) {
            dano += arma.calcularDanoExtra();
        }

        System.out.println(this.nome + " golpeou com brutalidade anã causando " + dano + "!");
        alvo.defender(dano);
    }
}
