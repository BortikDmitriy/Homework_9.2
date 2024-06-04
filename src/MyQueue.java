public class MyQueue<T> {
    // Внутренний класс для представления узлов
    private static class Node<T> {
        T value;
        Node<T> next;

        Node(T value) {
            this.value = value;
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size;

    // Конструктор по умолчанию
    public MyQueue() {
        head = null;
        tail = null;
        size = 0;
    }

    // Метод для добавления элемента в конец очереди
    public void add(T value) {
        Node<T> newNode = new Node<>(value);
        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    // Метод для очистки очереди
    public void clear() {
        head = tail = null;
        size = 0;
    }

    // Метод для получения размера очереди
    public int size() {
        return size;
    }

    // Метод для просмотра первого элемента в очереди без его удаления
    public T peek() {
        if (head == null) {
            return null;
        }
        return head.value;
    }

    // Метод для получения и удаления первого элемента в очереди
    public T poll() {
        if (head == null) {
            return null;
        }
        T value = head.value;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        size--;
        return value;
    }

    // Тестовый метод main для проверки функциональности
    public static void main(String[] args) {
        MyQueue<Integer> queue = new MyQueue<>();
        queue.add(1);
        queue.add(2);
        queue.add(3);

        System.out.println("Size: " + queue.size()); // Должно напечатать 3

        System.out.println("Peek: " + queue.peek()); // Должно напечатать 1
        System.out.println("Poll: " + queue.poll()); // Должно напечатать 1

        System.out.println("Size after poll: " + queue.size()); // Должно напечатать 2

        queue.clear();
        System.out.println("Size after clear: " + queue.size()); // Должно напечатать 0
    }
}

