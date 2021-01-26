package com.douma.line.array;

/**
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class ArrayList<E> {
    private E[] data;
    private int capacity;
    private int size;

    public ArrayList(int capacity) {
        this.data = (E[])new Object[capacity];
        this.capacity = capacity;
        this.size = 0;
    }

    public ArrayList(E[] arr) {
        this.data = (E[])new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        size = arr.length;
    }

    public ArrayList() {
        this(15);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return capacity;
    }

    /**** 新增操作 ****/
    /**
     * C(Create)-R(Retrieve)-U(Update)-D(Delete)
     * 向指定索引位置添加一个新元素
     * @param index 指定索引
     * @param e 新元素
     */
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("add failed, require index >= 0 && index <= size");
        }
        // 扩容
        if (size == data.length) {
            resize(data.length * 2);
        }
        // 最差时间复杂度，循环代码运行最大的次数
        // size = data.length && index = 0
        // 时间复杂度 O(n)
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;
    }

    private void resize(int newCapacity) {
        // 1. 创建一个容量为 newCapacity 的临时数组
        E [] newData = (E[])new Object[newCapacity];
        // 2. 将原来数组中的元素拷贝到新数组中
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        // 3. 将新数组覆盖老数组
        data = newData;
        // bug 修复：将容量设置位新容量值
        capacity = newCapacity;
    }

    // 时间复杂度 O(n)
    public void addFirst(E e) {
        add(0, e);
    }

    // 时间复杂度 O(1)
    public void addLast(E e) {
        add(size, e);
    }

    /**** 查询操作 ****/
    /**
     * 获取 index 索引位置的元素
     * @param index 指定索引
     * @return  返回指定索引对应的元素值
     */
    // 时间复杂度 O(1)
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("get failed, require index >= 0 && index < size");
        }
        return data[index];
    }

    public E getLast() {
        return get(size - 1);
    }

    public E getFirst() {
        return get(0);
    }

    // 时间复杂度 O(n)
    public boolean contains(E target) {
        for (E num : data) {
            if (target.equals(num)) return true;
        }
        return false;
    }

    /**
     * 查找数组元素 e 所在的索引，如果不存在的元素 e，则返回 -1
     * @param e 指定元素
     * @return  元素 e 所在的索引
     */
    // 时间复杂度 O(n)
    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    /**** 修改操作 ****/
    /**
     *  将 index 索引位置的元素修改为新元素 e
     * @param index 需要修改的索引位置
     * @param e 新设置的元素值
     */
    // 时间复杂度 O(1)
    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("set failed, require index >= 0 && index < size");
        }
        data[index] = e;
    }

    /**** 删除操作 ****/
    /**
     *  删除指定索引位置的元素
     * @param index 指定索引
     * @return  返回删除的元素
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("remove failed, require index >= 0 && index < size");
        }
        E res = data[index];
        // index = 0 && size = data.length
        // 时间复杂度 O(n)
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;

        // GC 清除不用的对象
        data[size] = null;

        // 如果 size 等于总容量的一半的话，则进行缩容
        // // 因为 data.length 有可能不断的减少，所以有可能小于 2 了，所以需要判断下
        if (size == data.length / 2 && data.length / 2 != 0) {
            resize(data.length / 2);
        }
        return res;
    }

    /**
     * 删除第一个元素
     * @return  第一个元素值
     */
    // 时间复杂度 O(n)
    public E removeFirst() {
        return remove(0);
    }

    /**
     * 删除最后一个元素
     * @return  最后一个元素的值
     */
    // 时间复杂度 O(1)
    public E removeLast() {
        return remove(size - 1);
    }

    /**
     *  删除指定元素
     * @param e 需要删除的元素
     */
    // 时间复杂度 O(n)
    public void removeElement(E e) {
        int index = find(e);
        if (index != -1) {
            remove(index);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(
                "Array: size = %d, capacity = %d\n", size, data.length));
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if (i != size - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
