package semaforo;

import semaforo.Cuenta;

public class ExtraerDinero extends Thread {
    private Cuenta cuenta;
    private int toExtract;

    public ExtraerDinero(Cuenta cuenta, int toExtract) {
        this.cuenta = cuenta;
        this.toExtract = toExtract;
    }

    @Override
    public void run() {
        cuenta.withdraw(toExtract);
    }
}
