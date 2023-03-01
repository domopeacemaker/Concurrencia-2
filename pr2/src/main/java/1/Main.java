package ejercicio1b;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

// Clase que representa la hebra productora de números de Fibonacci
class FibonacciProducer implements Runnable {
    // Cola bloqueante donde se añaden los números de Fibonacci
    private BlockingQueue<Integer> queue;

    // Constructor de la clase
    public FibonacciProducer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    // Método que se ejecuta al iniciar la hebra
    public void run() {
        // Inicializamos las variables a y b para calcular los números de Fibonacci
        int a = 0;
        int b = 1;

        // Bucle infinito
        while (true) {
            try {
                // Calculamos el siguiente número de Fibonacci
                int c = a + b;
                a = b;
                b = c;

                // Hacemos una pausa de 2 segundos
                Thread.sleep(2000);

                // Añadimos el número de Fibonacci a la cola bloqueante
                queue.put(a);
            } catch (InterruptedException e) {
                // Si se produce una excepción InterruptedException, salimos del bucle
                break;
            }
        }
    }
}

// Clase que representa la hebra consumidora de números de Fibonacci
class FibonacciConsumer implements Runnable {
    // Cola bloqueante de donde se consumen los números de Fibonacci
    private BlockingQueue<Integer> queue;

    // Constructor de la clase
    public FibonacciConsumer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    // Método que se ejecuta al iniciar la hebra
    public void run() {
        // Bucle infinito
        while (true) {
            try {
                // Tomamos el primer elemento de la cola bloqueante
                int element = queue.take();

                // Mostramos el elemento por pantalla
                System.out.println(element);

                // Hacemos una pausa de 1 segundo
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // Si se produce una excepción InterruptedException, salimos del bucle
                break;
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        // Creamos una cola bloqueante con capacidad ilimitada
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();

        // Creamos las hebras
        Thread producer = new Thread(new FibonacciProducer(queue));
        Thread consumer = new Thread(new FibonacciConsumer(queue));

        // Iniciamos las hebras
        producer.start();
        consumer.start();

        // Esperamos a que terminen las hebras
        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            // Si se produce una excepción InterruptedException, terminamos el programa
            return;
        }
    }
}

