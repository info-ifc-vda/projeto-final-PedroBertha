public abstract class Item {
    protected String nome;

    public Item(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void aplicarEfeito(Combatente combatente) {
        throw new UnsupportedOperationException("Unimplemented method 'aplicarEfeito'");
    }
}
