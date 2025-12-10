import java.util.Random;
// package entidades;

public class Humano extends Combatente {

    public Humano(String nome, int nivel) {
        super(nome, nivel);
    }

    @Override
    public void atacar(Combatente alvo) {
        // --- LÓGICA: ISILDUR VS SAURON (APENAS O ORIGINAL) ---
        
        boolean temEspada = (this.arma instanceof Espada);
        
        // CORREÇÃO: Verifica se é o Sauron ORIGINAL pelo nome
        // Se for o Morgoth, isso dará 'false' e o ataque será normal
        boolean alvoEhSauron = (alvo instanceof Sauron) && alvo.getNome().contains("Sauron");

        if (temEspada && alvoEhSauron) {
            Random r = new Random();
            int sorte = r.nextInt(100);
            
            boolean ehIsildur = this.nome.equalsIgnoreCase("Isildur");

            if (ehIsildur || sorte < 10) {
                System.out.println("\n⚔️ !!! MOMENTO ÉPICO !!! ⚔️");
                if (ehIsildur) System.out.println("Você é ISILDUR! O destino se cumpre novamente!");
                else System.out.println("A lâmina brilha com a luz de Narsil!");
                
                System.out.println(this.nome + " corta o dedo de Sauron e separa o Anel!");
                alvo.defender(99999); 
                return; 
            }
        }

        // --- ATAQUE NORMAL (MORGOTH CAI AQUI) ---
        int danoArma = 0;
        if (this.arma != null) danoArma = this.arma.calcularDanoExtra();

        int danoTotal = this.ataque + danoArma + rolarD20("Ataque");
        System.out.println(this.nome + " atacou com espada causando " + danoTotal + " de dano.");
        alvo.defender(danoTotal);
    }
}