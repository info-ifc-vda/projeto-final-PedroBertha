

public class PocaoVida extends Item {

    private int cura;

    public PocaoVida() {
        super("Poção de Vida");
        this.cura = 30;
    }

    @Override
    public void aplicarEfeito(Combatente combatente) {
        combatente.vida += cura;
        System.out.println(combatente.getNome() + " recuperou " + cura + " de vida!");
    }
}
