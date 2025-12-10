import java.util.Random;

public class Cajado extends Arma {

    public Cajado() {
        super("Cajado Arcano", 5);
    }

    @Override
    public int calcularDanoExtra() {
        Random r = new Random();

        if (r.nextInt(100) < 40) {
            System.out.println("→ Magia Arcana Explode no inimigo!");
            return danoBase + 15;
        }

        return danoBase;
    }
    
}
