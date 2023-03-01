package alternativa1;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

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

                // Mostramos el número de elementos en la cola y el elemento que añadimos
                System.out.println("Producer: queue size = " + queue.size() + ", element = " + a);
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

                // Mostramos el último elemento en la cola y si hemos podido extraer un elemento
                System.out.println("Consumer: last element = " + element + ", success = true");
            } catch (InterruptedException e) {
                // Si se produce una excepción InterruptedException, salimos del bucle
                break;
            } catch (Exception e) {
                // Si se produce cualquier otra excepción, mostramos que no hemos podido extraer un elemento
                System.out.println("Consumer: success = false");
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        // Creamos una SynchronousQueue como cola bloqueante
        BlockingQueue<Integer> queue = new SynchronousQueue<>();

        // Creamos las hebras
        Thread producer = new Thread(new FibonacciProducer(queue));
        Thread consumer = new Thread(new FibonacciConsumer(queue));

        // Iniciamos las hebras
        producer.start();
        consumer.start();
    }
}
