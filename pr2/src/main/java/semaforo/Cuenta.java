package semaforo;

import java.util.concurrent.Semaphore;

public class Cuenta {
    private int amount;
    private Semaphore semaphore;

    public Cuenta() {
        amount = 9000;
        semaphore = new Semaphore(1);
    }

    public void withdraw(int toExtract) {
        try {
            // Obtenemos un permiso del semáforo
            semaphore.acquire();
            while (toExtract > amount) {
                // Si no hay suficiente dinero en la cuenta, liberamos el permiso y esperamos
                semaphore.release();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // Manejo de excepción
                }
                semaphore.acquire();
            }
            // Realizamos el retiro
            amount -= toExtract;
        } catch (InterruptedException e) {
            // Manejo de excepción
        } finally {
            // Liberamos el permiso del semáforo
            semaphore.release();
        }
    }

    public void deposit(int toAdd) {
        try {
            // Obtenemos un permiso del semáforo
            semaphore.acquire();
            // Realizamos el ingreso
            amount += toAdd;
        } catch (InterruptedException e) {
            // Manejo de excepción
        } finally {
            // Liberamos el permiso del semáforo
            semaphore.release();
        }
    }
}