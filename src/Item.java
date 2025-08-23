import java.io.Serializable;

public abstract class Item implements Serializable {
    protected String titulo;
    protected String id;

    public Item(String titulo, String id) {
        this.titulo = titulo;
        this.id = id;
    }

    public String getTitulo() { return titulo; }
    public String getId() { return id; }
}