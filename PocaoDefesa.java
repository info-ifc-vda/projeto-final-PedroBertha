

public class PocaoDefesa extends Item {

    private int bonus;

    public PocaoDefesa() {
        super("Poção de Defesa");
        this.bonus = 10;
    }

    @Override
    public void aplicarEfeito(Combatente combatente) {
        combatente.defesa += bonus;
        System.out.println(combatente.getNome() + " ganhou +" + bonus + " de defesa!");
    }
}