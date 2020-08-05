package com.kulygina.list;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

class DIYListTest {

    @Test
    @DisplayName("Should create empty array")
    public void create() {
        List<Integer> list = new DIYList<>();

        assertThat(list).isNotNull();
        assertThat(list).hasSize(0);
    }

    @Test
    @DisplayName("Should add element")
    public void add() {
        List<Integer> list = new DIYList<>();

        list.add(1);
        list.add(11);
        list.add(12);
        list.add(13);
        list.add(14);
        list.add(15);
        list.add(16);
        list.add(17);
        list.add(18);
        list.add(19);
        list.add(111);

        assertThat(list).hasSize(11).containsExactly(1, 11, 12, 13, 14, 15, 16, 17, 18, 19, 111);
    }

    @Test
    @DisplayName("Should add all elements")
    public void addAll() {
        List<Integer> list = new DIYList<>();

        Collections.addAll(list, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);

        assertThat(list).hasSize(11).containsExactly(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);
    }

    @Test
    @DisplayName("Should get element")
    public void get() {
        List<Integer> list = new DIYList<>();
        Collections.addAll(list, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);

        assertThat(list.get(3)).isNotNull().isEqualTo(4);
    }

    @Test
    @DisplayName("Should set element")
    public void set() {
        List<Integer> list = new DIYList<>();
        Collections.addAll(list, 1, 2, 3, 4, 5);

        list.set(2, 22);

        assertThat(list).hasSize(5);
        assertThat(list.get(2)).isNotNull().isEqualTo(22);
    }

    @Test
    @DisplayName("Should return size")
    public void size() {
        List<Integer> list = new DIYList<>();
        Collections.addAll(list, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);

        assertThat(list).hasSize(11);
    }

    @Test
    @DisplayName("Should copy to array")
    public void copy() {
        List<Integer> list = new DIYList<>();
        Collections.addAll(list, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);
        List<Integer> forCopy = new DIYList<>();
        forCopy.add(22);
        forCopy.add(23);
        forCopy.add(24);

        Collections.copy(list, forCopy);

        assertThat(list).hasSize(11).containsExactly(22, 23, 24, 4, 5, 6, 7, 8, 9, 10, 11);
    }

    @Test
    @DisplayName("Should sort")
    public void sort() {
        List<Integer> list = new DIYList<>();
        Collections.addAll(list, 11, 22, 3, 4, 5, 6, 7, 8, 9, 10, 11);

        Collections.sort(list, Comparator.naturalOrder());

        assertThat(list).hasSize(11).containsExactly(3, 4, 5, 6, 7, 8, 9, 10, 11, 11, 22);
    }
}