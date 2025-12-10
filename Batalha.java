import java.util.Scanner;
// import entidades.*; 

public class Batalha {
    private Combatente heroi;
    private Combatente inimigo;
    private int turno;
    private Scanner sc;

    public Batalha(Combatente heroi, Combatente inimigo) {
        this.heroi = heroi;
        this.inimigo = inimigo;
        this.turno = 1;
        this.sc = new Scanner(System.in);
    }

    public void iniciar() {
        System.out.println("\n========================================");
        System.out.println("   COMBATE INICIADO: " + heroi.getNome() + " vs " + inimigo.getNome());
        System.out.println("========================================");

        while (heroi.estaVivo() && inimigo.estaVivo()) {
            executarTurno();
            turno++;
        }
        encerrarBatalha();
    }

    private void executarTurno() {
        System.out.println("\n=== TURNO " + turno + " ===");

        boolean turnoPassou = false;

        while (!turnoPassou) {
            
            System.out.println("┌──────────────────────────────────────────────────────────────────────┐");
            
            System.out.printf("│ %-28s HP: %-4d | ATK: %-3d | DEF: %-3d │\n", 
                "HERÓI: " + heroi.getNome(), heroi.getVida(), heroi.getAtaque(), heroi.getDefesa());
            
            System.out.println("│                                                                      │");
            
            System.out.printf("│ %-28s HP: %-4d | ATK: %-3d | DEF: %-3d │\n", 
                "INIMIGO: " + inimigo.getNome(), inimigo.getVida(), inimigo.getAtaque(), inimigo.getDefesa());
                
            System.out.println("└──────────────────────────────────────────────────────────────────────┘");

            System.out.println("\nAções:");
            System.out.println("1 - ⚔️ Atacar (Encerra o turno)");
            System.out.println("2 - 🏃 Fugir (Encerra a batalha)");
            System.out.println("3 - 🎒 Usar Item (Ação Livre)"); 
            System.out.print("Escolha: ");

            if (!sc.hasNextInt()) {
                System.out.println(">>> Digite apenas números!");
                sc.next();
                continue;
            }

            int acao = sc.nextInt();

            if (acao == 1) {
                System.out.println("\n> Você partiu para o ataque!");
                heroi.atacar(inimigo);
                turnoPassou = true; 

            } else if (acao == 2) {
                // FUGIR
                boolean ehBilbo = heroi.getNome().equalsIgnoreCase("Bilbo");
                // Tenta verificar se é Hobbit (se der erro de classe, remova o instanceof)
                boolean ehHobbit = (heroi.getClass().getSimpleName().equals("Hobbit")); 
                
                // O anel não funciona na presença de Morgoth
                boolean inimigoEhMorgoth = inimigo.getNome().contains("Morgoth");

                if (ehBilbo && ehHobbit && !inimigoEhMorgoth) {
                    System.out.println("\n💍 Bilbo usa o Anel e desaparece! Fuga com sucesso!");
                    System.exit(0);
                } else {
                    if (inimigoEhMorgoth && ehBilbo) {
                        System.out.println("\n👁️ O Anel não esconde você dos olhos de MORGOTH!");
                        System.out.println("Você não pode fugir desta batalha final!");
                        // Não deixa fugir, volta pro menu ou gasta o turno sem fugir
                    } else {
                        System.out.println("\n> " + heroi.getNome() + " tentou fugir, mas falhou!");
                        heroi.defender(9999); // Game Over
                        return;
                    }
                }
                
            } else if (acao == 3) {
                usarItemDoHeroi();
                
            } else {
                System.out.println(">>> Opção inválida!");
            }
        }

        if (!inimigo.estaVivo()) return;

        System.out.println("\n>>> VEZ DO INIMIGO <<<");
        try { Thread.sleep(1000); } catch (Exception e) {}
        System.out.println(inimigo.getNome() + " prepara um golpe...");
        inimigo.atacar(heroi);
    }

    private void usarItemDoHeroi() {
        if (heroi.getItens().isEmpty()) {
            System.out.println("\n⚠ Sua mochila está vazia!");
            return;
        }

        System.out.println("\n🎒 --- MOCHILA ---");
        for (int i = 0; i < heroi.getItens().size(); i++) {
            System.out.println((i + 1) + ". " + heroi.getItens().get(i).getNome());
        }
        System.out.println("0. Cancelar");
        System.out.print("Escolha o item: ");

        if (!sc.hasNextInt()) {
            sc.next(); return;
        }

        int escolha = sc.nextInt();

        if (escolha == 0) {
            System.out.println("Cancelado.");
            return;
        }

        int indiceReal = escolha - 1;

        if (indiceReal >= 0 && indiceReal < heroi.getItens().size()) {
            heroi.usarItem(indiceReal);
        } else {
            System.out.println("❌ Item inválido!");
        }
    }

   private void encerrarBatalha() {
        if (heroi.estaVivo()) {
            System.out.println("\nVITÓRIA! O inimigo caiu!");

            int xpBase = 50;
            if (inimigo.getNome().contains("Sauron")) xpBase = 5000; 
            
            int xpGanho = xpBase * inimigo.getNivel();
            
            heroi.ganharExperiencia(xpGanho);

            System.out.println("\n💪 --- STATUS PÓS-BATALHA ---");
            heroi.mostrarFicha();
            
        } else {
            System.out.println("\nDERROTA... " + heroi.getNome() + " caiu em combate.");
            System.out.println("A Terra Média foi condenada.");
        }
    }
}