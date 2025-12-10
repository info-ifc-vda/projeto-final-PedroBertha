import java.util.Random;

public class Elfo extends Combatente {

    public Elfo(String nome, int nivel) {
        super(nome, nivel);
        this.ataque += 5; 
    }

    @Override
    public void atacar(Combatente alvo) {
        Random r = new Random();

        int dano = this.ataque + r.nextInt(8);

        if (arma != null) {
            dano += arma.calcularDanoExtra();
        }

        System.out.println(this.nome + " atacou com graça élfica causando " + dano + " de dano!");
        alvo.defender(dano);
    }
    
}
