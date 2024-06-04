public class MyLinkedList<T> {
    // Внутренний класс для представления узлов
    private static class Node<T> {
        T value;
        Node<T> next;
        Node<T> prev;

        Node(T value) {
            this.value = value;
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size;

    // Конструктор по умолчанию
    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    // Метод для добавления элемента в конец списка
    public void add(T value) {
        Node<T> newNode = new Node<>(value);
        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    // Метод для удаления элемента по индексу
    public void remove(int index) {
        checkElementIndex(index);

        Node<T> current = getNode(index);

        if (current.prev != null) {
            current.prev.next = current.next;
        } else {
            head = current.next;
        }

        if (current.next != null) {
            current.next.prev = current.prev;
        } else {
            tail = current.prev;
        }

        size--;
    }

    // Метод для очистки списка
    public void clear() {
        head = tail = null;
        size = 0;
    }

    // Метод для получения размера списка
    public int size() {
        return size;
    }

    // Метод для получения элемента по индексу
    public T get(int index) {
        checkElementIndex(index);
        return getNode(index).value;
    }

    // Метод для проверки корректности индекса
    private void checkElementIndex(int index) {
        if (!isElementIndex(index)) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    // Метод для проверки, является ли индекс допустимым
    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    // Метод для получения узла по индексу
    private Node<T> getNode(int index) {
        Node<T> current;
        if (index < (size >> 1)) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
        }
        return current;
    }

    // Тестовый метод main для проверки функциональности
    public static void main(String[] args) {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(1);
        list.add(99);
        list.add(5);
        list.add(49);

        System.out.println("Size: " + list.size()); // Должно напечатать 4

        System.out.println("Element at index 1: " + list.get(1)); // Должно напечатать 99
        list.remove(1);
        System.out.println("Size after removal: " + list.size()); // Должно напечатать 3

        list.clear();
        System.out.println("Size after clearing: " + list.size()); // Должно напечатать 0
    }
}
