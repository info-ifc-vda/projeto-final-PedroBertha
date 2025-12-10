

public class PocaoForca extends Item {

    private int bonus;

    public PocaoForca() {
        super("Poção de Força");
        this.bonus = 10;
    }

    @Override
    public void aplicarEfeito(Combatente combatente) {
        combatente.ataque += bonus;
        System.out.println(combatente.getNome() + " ganhou +" + bonus + " de ataque!");
    }
}
