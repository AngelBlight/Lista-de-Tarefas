import java.time.LocalTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TarefaController gerenciador = new TarefaController();
        Scanner sc = new Scanner(System.in);

        // Carregar dados existentes
        gerenciador.carregarDados();

        while (true) {
            int op = In.lerInt("\n=== SISTEMA DE GERENCIAMENTO DE TAREFAS === \n" +
                    "1. Adicionar nova tarefa\n" +
                    "2. Marcar tarefa como concluída\n" +
                    "3. Exibir tarefas pendentes\n" +
                    "4. Exibir tarefas concluídas\n" +
                    "5. Exibir relatório completo\n" +
                    "6. Sair\n" +
                    "Escolha: " );

            if (op == 1) {
                String titulo = In.lerString("Título da tarefa: ");
                String id = In.lerString("ID único: ");
                String descricao = In.lerString("Descrição: ");
                System.out.print("Horário de início (HH:MM): ");
                String[] tempo = sc.nextLine().split(":");
                LocalTime horarioInicio = LocalTime.of(
                        Integer.parseInt(tempo[0]),
                        Integer.parseInt(tempo[1])
                );
                int duracao = In.lerInt("Duração em minutos: ");

                Tarefa novaTarefa = new Tarefa(titulo, id, descricao, horarioInicio, duracao);
                gerenciador.adicionarTarefa(novaTarefa);
                System.out.println("Tarefa adicionada com sucesso!");

            } else if (op == 2) {
                String id = In.lerString("ID da tarefa a marcar como concluída: ");
                gerenciador.marcarTarefaConcluida(id);
                System.out.println("Tarefa " + id + " foi marcada como concluída!");

            } else if (op == 3) {
                gerenciador.exibirTarefasPendentes();

            } else if (op == 4) {
                gerenciador.exibirTarefasConcluidas();

            } else if (op == 5) {
                gerenciador.exibirRelatorioCompleto();

            } else if (op == 6) {
                System.out.println("Saindo do sistema...");
                break;

            } else {
                System.out.println("[Erro] Opção inválida.");
            }
        }
    }
}