public class MyHashMap<K, V> {
    // Внутренний класс для представления узлов
    private static class Node<K, V> {
        final K key;
        V value;
        Node<K, V> next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node<K, V>[] table;
    private int size;
    private static final int DEFAULT_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;

    // Отдельное поле для хранения значения с ключом null
    private V nullKeyValue = null;
    private boolean hasNullKey = false;

    // Конструктор по умолчанию
    @SuppressWarnings("unchecked")
    public MyHashMap() {
        table = (Node<K, V>[]) new Node[DEFAULT_CAPACITY];
        size = 0;
    }

    // Метод для вычисления хеш-кода и индекса
    private int hash(Object key) {
        return (key == null) ? 0 : Math.abs(key.hashCode() % table.length);
    }

    // Метод для добавления пары ключ-значение
    public void put(K key, V value) {
        if (key == null) {
            if (!hasNullKey) {
                size++;
                hasNullKey = true;
            }
            nullKeyValue = value;
            return;
        }

        int index = hash(key);
        Node<K, V> newNode = new Node<>(key, value);

        if (table[index] == null) {
            table[index] = newNode;
        } else {
            Node<K, V> current = table[index];
            Node<K, V> prev = null;
            while (current != null) {
                if (current.key.equals(key)) {
                    current.value = value; // Обновление значения для существующего ключа
                    return;
                }
                prev = current;
                current = current.next;
            }
            prev.next = newNode;
        }
        size++;
        if (size > table.length * LOAD_FACTOR) {
            resize();
        }
    }

    // Метод для удаления пары по ключу
    public void remove(K key) {
        if (key == null) {
            if (hasNullKey) {
                hasNullKey = false;
                nullKeyValue = null;
                size--;
            }
            return;
        }

        int index = hash(key);

        Node<K, V> current = table[index];
        Node<K, V> prev = null;
        while (current != null) {
            if (current.key.equals(key)) {
                if (prev == null) {
                    table[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return;
            }
            prev = current;
            current = current.next;
        }
    }

    // Метод для очистки коллекции
    public void clear() {
        table = (Node<K, V>[]) new Node[DEFAULT_CAPACITY];
        size = 0;
        hasNullKey = false;
        nullKeyValue = null;
    }

    // Метод для получения размера коллекции
    public int size() {
        return size;
    }

    // Метод для получения значения по ключу
    public V get(K key) {
        if (key == null) {
            return hasNullKey ? nullKeyValue : null;
        }

        int index = hash(key);

        Node<K, V> current = table[index];
        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }


    @SuppressWarnings("unchecked")
    private void resize() {
        Node<K, V>[] oldTable = table;
        table = (Node<K, V>[]) new Node[oldTable.length * 2];
        size = 0;

        for (Node<K, V> node : oldTable) {
            Node<K, V> current = node;
            while (current != null) {
                put(current.key, current.value);
                current = current.next;
            }
        }
    }

    // Тестовый метод main для проверки функциональности
    public static void main(String[] args) {
        MyHashMap<Integer, String> map = new MyHashMap<>();
        map.put(1, "One");
        map.put(2, "Two");
        map.put(3, "Three");
        map.put(null, "NullKey");

        System.out.println("Size: " + map.size()); // Должно напечатать 4
        System.out.println("Value for key 2: " + map.get(2)); // Должно напечатать "Two"
        System.out.println("Value for key null: " + map.get(null)); // Должно напечатать "NullKey"

        map.remove(2);
        System.out.println("Size after removal: " + map.size()); // Должно напечатать 3
        System.out.println("Value for key 2: " + map.get(2)); // Должно напечатать null

        map.remove(null);
        System.out.println("Size after removal of null key: " + map.size()); // Должно напечатать 2
        System.out.println("Value for key null: " + map.get(null)); // Должно напечатать null

        map.clear();
        System.out.println("Size after clear: " + map.size()); // Должно напечатать 0
    }
}
