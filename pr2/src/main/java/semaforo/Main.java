package semaforo;

public class Main {
    public static void main(String[] args) {
        // Creamos una instancia de Cuenta2
        Cuenta cuenta = new Cuenta();
        // Creamos tres hebras que extraen dinero de la cuenta con cantidades diferentes (siempre mayores de 9000)
        ExtraerDinero h1 = new ExtraerDinero(cuenta, 9000);
        ExtraerDinero h2 = new ExtraerDinero(cuenta, 10000);
        ExtraerDinero h3 = new ExtraerDinero(cuenta, 11000);
        // Creamos una hebra que ingresa 10000 euros en la cuenta
        IngresarDinero h4 = new IngresarDinero(cuenta, 10000);
        // Iniciamos las hebras
        h1.start();
        h2.start();
        h3.start();
        h4.start();
    }
}
