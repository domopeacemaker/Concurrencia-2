package semaforo;

import semaforo.Cuenta;

public class IngresarDinero extends Thread {
    private Cuenta cuenta;
    private int toAdd;

    public IngresarDinero(Cuenta cuenta, int toAdd) {
        this.cuenta = cuenta;
        this.toAdd = toAdd;
    }

    @Override
    public void run() {
        cuenta.deposit(toAdd);
    }
}
