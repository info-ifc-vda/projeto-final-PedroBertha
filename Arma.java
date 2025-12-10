public abstract class Arma {
    protected String nome;
    protected int danoBase;

    public Arma(String nome, int danoBase) {
        this.nome = nome;
        this.danoBase = danoBase;
    }

    public String getNome() {
        return nome;
    }

    public int getDanoBase() {
        return danoBase;
    }

    public abstract int calcularDanoExtra();

    @Override
    public String toString() {
        return nome + " (+" + danoBase + " dano)";
    }
    
}
