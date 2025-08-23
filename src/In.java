import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class In {

    private static final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public static int lerInt(String texto) {
       System.out.println(texto);
       return lerInt();
    }

    public static int lerInt() {
        int numero = 0;

        try{
            numero = Integer.parseInt(in.readLine());
        } catch (IOException e) {
            System.err.println("Erro na leitura");
        }
        return numero;
    }

    public static long lerLong(String texto) {
        System.out.println(texto);
        return lerLong();
    }

    public static long lerLong() {
        long numero = 0;

        try{
            numero = Long.parseLong(in.readLine());
        } catch (IOException e) {
            System.err.println("Erro na leitura");
        }
        return numero;
    }

    public static double lerDouble(String texto) {
        System.out.println(texto);
        return lerDouble();
    }

    public static double lerDouble() {
        double numero = 0;

        try{
            numero = Double.parseDouble(in.readLine());
        } catch (IOException e) {
            System.err.println("Erro na leitura");
        }
        return numero;
    }

    public static float lerFloat(String texto) {
        System.out.println(texto);
        return lerFloat();
    }

    public static float lerFloat() {
        float numero = 0;

        try{
            numero = Float.parseFloat(in.readLine());
        } catch (IOException e) {
            System.err.println("Erro na leitura");
        }
        return numero;
    }

    public static String lerString(String texto) {
        System.out.println(texto);
        return lerString();
    }

    public static String lerString() {
       String texto = "";

        try{
            texto = in.readLine();
        } catch (IOException e) {
            System.err.println("Erro na leitura");
        }
        return texto;
    }
    
        public static char lerChar() {
        return In.lerString().charAt(0);
    }

    public static char lerChar(String texto) {
        System.out.println(texto);
        return lerChar();
    }
}
