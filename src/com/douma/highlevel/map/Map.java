package com.douma.highlevel.map;

public interface Map<K, V> {
    /**
     * 获取映射中存储的键值对的数量大小
     * @return
     */
    int size();

    /**
     * 判断 Map 是否为空
     * @return
     */
    boolean isEmpty();

    /**
     * 向 Map 中添加一对键值对
     * @param key
     * @param value
     */
    void add(K key, V value);

    /**
     * 删除指定键的键值对，并返回删除的键值对的值
     * @param key
     * @return
     */
    V remove(K key);

    /**
     * 更新指定的键的值为新指定的值
     * @param key
     * @param newValue
     */
    void set(K key, V newValue);

    /**
     * 根据指定的键查询对应的值
     * @param key
     * @return
     */
    V get(K key);

    /**
     * 判断 Map 中是否包含指定的键
     * @param key
     * @return
     */
    boolean containsKey(K key);
}
