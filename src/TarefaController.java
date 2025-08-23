import java.io.*;
import java.time.LocalTime;
import java.util.*;

public class TarefaController {
    private Map<String, Tarefa> tarefas = new HashMap<>();
    private Set<String> tarefasConcluidas = new HashSet<>();

    public void adicionarTarefa(Tarefa tarefa) {
        tarefas.put(tarefa.getId(), tarefa);
        salvarBackup();
    }

    public void marcarTarefaConcluida(String idTarefa) {
        if (!tarefas.containsKey(idTarefa)) {
            System.out.println("[Erro] Tarefa não encontrada!");
            return;
        }

        Tarefa tarefa = tarefas.get(idTarefa);
        tarefa.marcarComoConcluida();
        tarefasConcluidas.add(idTarefa);
        salvarBackup();
        salvarRelatorioConclusao(tarefa);
    }

    public void exibirTarefasPendentes() {
        System.out.println("\nTarefas Pendentes:\n");
        for (Tarefa tarefa : tarefas.values()) {
            if (!tarefa.isConcluido()) {
                System.out.println("Título: " + tarefa.getTitulo());
                System.out.println("Descrição: " + tarefa.getDescricao());
                System.out.println("Início: " + tarefa.getHorarioInicio());
                System.out.println("Término: " + tarefa.getHorarioTermino());
                System.out.println("Duração: " + tarefa.getDuracaoMinutos() + " minutos");
                System.out.println("ID: " + tarefa.getId());
                System.out.println("------------------------");
            }
        }
    }

    public void exibirTarefasConcluidas() {
        System.out.println("\nTarefas Concluídas:\n");
        for (String id : tarefasConcluidas) {
            Tarefa tarefa = tarefas.get(id);
            System.out.println("Título: " + tarefa.getTitulo() + " (CONCLUÍDA)");
        }
    }

    public void exibirRelatorioCompleto() {
        System.out.println("\nRelatório Completo de Tarefas:\n");

        int pendentes = 0;
        int concluidas = tarefasConcluidas.size();

        for (Tarefa tarefa : tarefas.values()) {
            if (!tarefa.isConcluido()) {
                pendentes++;
            }
        }

        System.out.println("Total de tarefas: " + tarefas.size());
        System.out.println("Tarefas concluídas: " + concluidas);
        System.out.println("Tarefas pendentes: " + pendentes);

        salvarRelatorioCompleto();
    }

    private void salvarRelatorioConclusao(Tarefa tarefa) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("conclusoes.txt", true))) {
            pw.println("Tarefa concluída: " + tarefa.getTitulo() +
                    " | Concluída em: " + LocalTime.now());
        } catch (IOException ex) {
            System.out.println("[Erro] Ao salvar relatório de conclusão.");
        }
    }

    private void salvarBackup() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("tarefas.dat"))) {
            oos.writeObject(tarefas);
        } catch (IOException ex) {
            System.out.println("[Erro] Ao salvar backup.");
        }
    }

    private void salvarRelatorioCompleto() {
        try (PrintWriter pw = new PrintWriter("relatorio_tarefas.txt")) {
            for (Tarefa tarefa : tarefas.values()) {
                String status = tarefa.isConcluido() ? "CONCLUÍDA" : "PENDENTE";
                pw.println("Tarefa: " + tarefa.getTitulo() +
                        " | Status: " + status +
                        " | Início: " + tarefa.getHorarioInicio() +
                        " | Duração: " + tarefa.getDuracaoMinutos() + "min");
            }
        } catch (IOException ex) {
            System.out.println("[Erro] Ao salvar relatório completo.");
        }
    }

    @SuppressWarnings("unchecked")
    public void carregarDados() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("tarefas.dat"))) {
            tarefas = (Map<String, Tarefa>) ois.readObject();
            // Atualizar conjunto de tarefas concluídas
            for (Tarefa tarefa : tarefas.values()) {
                if (tarefa.isConcluido()) {
                    tarefasConcluidas.add(tarefa.getId());
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("[Info] Nenhum backup encontrado. Iniciando com lista vazia.");
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("[Erro] Ao carregar dados.");
        }
    }
}
