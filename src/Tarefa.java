import java.time.LocalTime;

public class Tarefa extends Item implements Gerenciavel {
    private String descricao;
    private LocalTime horarioInicio;
    private int duracaoMinutos;
    private boolean concluida;

    public Tarefa(String titulo, String id, String descricao,
                  LocalTime horarioInicio, int duracaoMinutos) {
        super(titulo, id);
        this.descricao = descricao;
        this.horarioInicio = horarioInicio;
        this.duracaoMinutos = duracaoMinutos;
        this.concluida = false;
    }

    public String getDescricao() { return descricao; }
    public LocalTime getHorarioInicio() { return horarioInicio; }
    public int getDuracaoMinutos() { return duracaoMinutos; }
    public boolean isConcluido() { return concluida; }

    public void marcarComoConcluida() {
        this.concluida = true;
    }

    public LocalTime getHorarioTermino() {
        return horarioInicio.plusMinutes(duracaoMinutos);
    }
}