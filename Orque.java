import java.util.Random;

public class Orque extends Combatente {

    public Orque(String nome, int nivel) {
        super(nome, nivel);
        this.vida += 10;
        this.ataque += 2;
    }

    @Override
    public void atacar(Combatente alvo) {
        Random r = new Random();
        
        int danoArma = (this.arma != null) ? this.arma.getDanoBase() : 2; 
        
        int sorte = r.nextInt(5); 
        
        int danoTotal = this.ataque + danoArma + sorte;
        
        System.out.println(this.nome + " urra e ataca violentamente causando " + danoTotal + " de dano!");
        alvo.defender(danoTotal);
    }
}