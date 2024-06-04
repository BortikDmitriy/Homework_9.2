public class MyStack<T> {
    // Внутренний класс для представления узлов
    private static class Node<T> {
        T value;
        Node<T> next;

        Node(T value) {
            this.value = value;
        }
    }

    private Node<T> top;
    private int size;

    // Конструктор по умолчанию
    public MyStack() {
        top = null;
        size = 0;
    }

    // Метод для добавления элемента в стек
    public void push(T value) {
        Node<T> newNode = new Node<>(value);
        newNode.next = top;
        top = newNode;
        size++;
    }

    // Метод для удаления элемента по индексу
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        if (index == 0) {
            top = top.next;
        } else {
            Node<T> current = top;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            current.next = current.next.next;
        }
        size--;
    }

    // Метод для очистки стека
    public void clear() {
        top = null;
        size = 0;
    }

    // Метод для получения размера стека
    public int size() {
        return size;
    }

    // Метод для просмотра верхнего элемента стека без его удаления
    public T peek() {
        if (top == null) {
            return null;
        }
        return top.value;
    }

    // Метод для получения и удаления верхнего элемента стека
    public T pop() {
        if (top == null) {
            return null;
        }
        T value = top.value;
        top = top.next;
        size--;
        return value;
    }

    // Тестовый метод main для проверки функциональности
    public static void main(String[] args) {
        MyStack<Integer> stack = new MyStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        System.out.println("Size: " + stack.size()); // Должно напечатать 3

        System.out.println("Peek: " + stack.peek()); // Должно напечатать 3
        System.out.println("Pop: " + stack.pop()); // Должно напечатать 3

        System.out.println("Size after pop: " + stack.size()); // Должно напечатать 2

        stack.remove(0);
        System.out.println("Size after remove: " + stack.size()); // Должно напечатать 1

        stack.clear();
        System.out.println("Size after clear: " + stack.size()); // Должно напечатать 0
    }
}


