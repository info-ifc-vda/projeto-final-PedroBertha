public class Mago extends Combatente {

    public Mago(String nome, int nivel) {
        super(nome, nivel);
    }

    @Override
    public void atacar(Combatente alvo) {
        
        boolean ehGandalf = this.nome.equalsIgnoreCase("Gandalf");
        boolean inimigoEhMorgoth = alvo.getNome().contains("Morgoth");

        if (ehGandalf && !inimigoEhMorgoth) {
            System.out.println("\n🔥 Gandalf invoca seu antigo inimigo domesticado...");
            System.out.println("O BALROG surge das sombras e incinera " + alvo.getNome() + "!");
            alvo.defender(99999);
            return;
        }

        if (ehGandalf && inimigoEhMorgoth) {
            System.out.println("\n⚠ Gandalf tenta invocar o Balrog, mas a criatura foge de medo de Morgoth!");
            System.out.println("Gandalf terá que lutar usando apenas sua magia!");
        }

        int danoArma = (this.arma != null) ? this.arma.getDanoBase() : 0;
        int sorte = rolarD20("Magia");
        int danoTotal = this.ataque + danoArma + sorte;
        
        System.out.println(this.nome + " lança magia causando " + danoTotal + " de dano!");
        alvo.defender(danoTotal);
    }
}