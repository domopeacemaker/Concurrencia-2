package alternativa2;

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

                // Añadimos el número de Fibonacci a la cola bloqueante
                queue.put(a);

                // Mostramos el número de elementos en la cola y el elemento añadido
                System.out.println("Producer: added element " + a + " to queue, queue size = " + queue.size());
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

// Mostramos el elemento consumido y el tamaño de la cola
                System.out.println("Consumer: consumed element " + element + " from queue, queue size = " + queue.size());
            } catch (InterruptedException e) {
// Si se produce una excepción InterruptedException, salimos del bucle
                break;
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
// Creamos una LinkedBlockingQueue de tamaño 1 como cola bloqueante
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(1);
        // Creamos las hebras
        Thread producer = new Thread(new FibonacciProducer(queue));
        Thread consumer = new Thread(new FibonacciConsumer(queue));

        // Iniciamos las hebras
        producer.start();
        consumer.start();
    }
}
