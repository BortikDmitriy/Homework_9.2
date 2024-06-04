import java.util.Arrays;

public class MyArrayList <T> {
    private T[] array;
    private int size;

    public MyArrayList() {
        array = (T[]) new Object[10];
        size = 0;
    }

    public void add(T value) {
        ensureCapacity(size + 1);
        array[size] = value;
        size++;
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        array[size - 1] = null;
        size--;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    public int size() {
        return size;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return array[index];
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > array.length) {
            int newCapacity = array.length * 2;
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            array = Arrays.copyOf(array, newCapacity);

        }
    }

    public static void main(String[] args) {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("First");
        list.add("Second");
        list.add("Third");
        System.out.println("Size: " + list.size());
        System.out.println("Get index 1: " + list.get(1));
        list.remove(1);
        System.out.println("Size after removal: " + list.size());
        System.out.println("Get index 1 after removal: " + list.get(1));
        list.clear();
        System.out.println("Size after clear: " + list.size());
    }
}