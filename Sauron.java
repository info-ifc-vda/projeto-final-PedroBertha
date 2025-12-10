import java.util.Random;


public class Sauron extends Combatente {

    public Sauron(String nome, int nivel) {
        super(nome, nivel);
        this.vida += 100;     
        this.ataque += 5;    
        this.defesa += 5;   
    }

    @Override
    public void atacar(Combatente alvo) {
        Random r = new Random();

        if (r.nextInt(100) < 25) {
            int danoEspecial = this.ataque * 3 + r.nextInt(50);
            System.out.println("\n⚫ O OLHO DE SAURON BRILHA!");
            System.out.println(this.nome + " lançou um ataque sombrio devastador causando " + danoEspecial + " de dano!");
            alvo.defender(danoEspecial);
            return;
        }

        // Ataque normal poderoso
        int dano = this.ataque + r.nextInt(20);
        System.out.println(this.nome + " atacou com sua maça negra causando " + dano + " de dano!");
        alvo.defender(dano);
    }
}

