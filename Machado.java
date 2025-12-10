import java.util.Random;

public class Machado extends Arma {

    public Machado() {
        super("Machado Anão", 10);
    }

    @Override
    public int calcularDanoExtra() {
        Random r = new Random();
        return danoBase + r.nextInt(15);
    }
    
}
