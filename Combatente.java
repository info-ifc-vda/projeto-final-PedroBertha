import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Combatente {
    protected String nome;
    protected int vida;
    protected int vidaMaxima; 
    protected int ataque;
    protected int defesa;
    protected int nivel;
    protected Arma arma;
    
    protected int xpAtual = 0;
    protected int xpParaProximoNivel;
    protected List<Item> itens = new ArrayList<>();

    public abstract void atacar(Combatente alvo);

    public Combatente(String nome, int nivel) {
        this.nome = nome;
        this.nivel = nivel;
        this.xpParaProximoNivel = nivel * 100;

        System.out.println("\n--- 🎲 ROLAGEM DE ATRIBUTOS PARA " + nome.toUpperCase() + " ---");

        int dadoVida = rolarD20("Vida");
        // <--- ALTERADO: Define a Vida Máxima baseada no dado e enche a vida atual
        this.vidaMaxima = 100 + (dadoVida * nivel);
        this.vida = this.vidaMaxima; 

        int dadoAtaque = rolarD20("Força");
        this.ataque = 10 + dadoAtaque + nivel;

        int dadoDefesa = rolarD20("Defesa");
        this.defesa = 10 + dadoDefesa + nivel;

        System.out.println("-------------------------------------------------");
    }

    protected int rolarD20(String atributo) {
        Random r = new Random();
        System.out.print("Rolando D20 para " + atributo + "... ");
        
        try { Thread.sleep(500); } catch (Exception e) {}

        int resultado = r.nextInt(20) + 1; 
        
        if (resultado == 20) System.out.println("🔥 CRÍTICO! (20)");
        else if (resultado == 1) System.out.println("💀 FALHA CRÍTICA! (1)");
        else System.out.println("🎲 " + resultado);
        
        return resultado;
    }

    public void defender(int danoRecebido) {
        int danoReal = danoRecebido - this.defesa;
        if (danoReal < 0) danoReal = 0; 
        this.vida -= danoReal;
        // <--- Ajustei o print para mostrar barra de vida
        System.out.println(this.nome + " tomou " + danoReal + " de dano. (Vida: " + this.vida + "/" + this.vidaMaxima + ")");
    }
    
    public void equiparArma(Arma arma) {
        this.arma = arma;
        System.out.println(this.nome + " equipou " + arma.getNome());
    }

    public void ganharExperiencia(int xpGanha) {
        this.xpAtual += xpGanha;
        System.out.println("✨ " + this.nome + " ganhou " + xpGanha + " XP! (" + xpAtual + "/" + xpParaProximoNivel + ")");

        while (this.xpAtual >= this.xpParaProximoNivel) {
            subirDeNivel();
        }
    }

    private void subirDeNivel() {
        this.xpAtual -= this.xpParaProximoNivel;
        this.nivel++;
        this.xpParaProximoNivel = (int) (this.xpParaProximoNivel * 1.5);

        System.out.println("\n🎉===========================================🎉");
        System.out.println("   LEVEL UP! " + this.nome + " subiu para o Nível " + this.nivel + "!");
        System.out.println("🎉===========================================🎉");
        
        int aumentoVida = 20 + (nivel * 2);
        int aumentoAtaque = 2;
        int aumentoDefesa = 2;

        // <--- ALTERADO: Aumenta o balde (Max) e enche o balde (Atual)
        this.vidaMaxima += aumentoVida;
        this.vida = this.vidaMaxima;

        this.ataque += aumentoAtaque;
        this.defesa += aumentoDefesa;
        
        System.out.println("❤️ Vida Máxima: " + this.vidaMaxima + " (Totalmente Recuperada!)");
        System.out.println("⚔️ Ataque: +" + aumentoAtaque + " -> " + this.ataque);
        System.out.println("🛡️ Defesa: +" + aumentoDefesa + " -> " + this.defesa);
    }

    public void adicionarItem(Item item) {
        if (itens.size() < 10) {
            itens.add(item);
            System.out.println(this.nome + " guardou " + item.getNome());
        } else {
            System.out.println("❌ Mochila cheia!");
        }
    }

    public void usarItem(int index) {
        if (index < 0 || index >= itens.size()) return;
        Item item = itens.remove(index);
        item.aplicarEfeito(this); 
    }

    public void curar(int qtd) {
        this.vida += qtd;
        
        // <--- ALTERADO: Impede que a vida ultrapasse o máximo
        if (this.vida > this.vidaMaxima) {
            this.vida = this.vidaMaxima;
        }

        System.out.println("✨ Recuperou " + qtd + " de vida! (" + this.vida + "/" + this.vidaMaxima + ")");
    }

    public void aumentarAtaque(int qtd) {
        this.ataque += qtd;
        System.out.println("💪 Ataque aumentou em " + qtd + "!");
    }
    
    public void aumentarDefesa(int qtd) {
        this.defesa += qtd;
        System.out.println("🛡️ Defesa aumentou em " + qtd + "!");
    }

    public void mostrarFicha() {
        System.out.println("\n📜 FICHA: " + this.nome + " (Nv " + this.nivel + ")");
        System.out.println("🌟 XP: " + this.xpAtual + "/" + this.xpParaProximoNivel);
        // <--- ALTERADO: Mostra Vida Atual / Vida Máxima
        System.out.println("❤️ Vida:   " + this.vida + "/" + this.vidaMaxima);
        System.out.println("⚔️ Ataque: " + this.ataque);
        System.out.println("🛡️ Defesa: " + this.defesa);
        if (arma != null) System.out.println("🗡️ Arma:   " + arma.getNome());
    }

    public boolean estaVivo() { return this.vida > 0; }
    
    // Getters
    public String getNome() { return nome; }
    public int getVida() { return vida; }
    public int getVidaMaxima() { return vidaMaxima; } // Getter novo se precisar
    public int getAtaque() { return ataque; }
    public int getDefesa() { return defesa; }
    public int getNivel() { return nivel; }
    public List<Item> getItens() { return itens; }
}