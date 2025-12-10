public class Espada extends Arma {

    public Espada() {
        super("Espada", 8);
    }

    @Override
    public int calcularDanoExtra() {
        return danoBase; 
    }
    
}
