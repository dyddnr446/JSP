package Yonguk.Yonguk;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class SortTest {
    Sort sort;
    @BeforeEach
    void before(){
        this.sort = new Sort();
    }

    @Test
    void Sort(){
        //준비(Given)
        //실행(When)
        List<String> list = sort.sortByLength(Arrays.asList("aa", "b"));
        //검증(Then)
        Assertions.assertThat(list).isEqualTo(List.of("b","aa"));
    }

    @Test
    void sort3Items(){
        List<String> list = sort.sortByLength(Arrays.asList("abcd", "abc", "a"));
        Assertions.assertThat(list).isEqualTo(List.of("a","abc","abcd"));
    }

    @Test
    void sortAlreadySorted(){
        List<String> list = sort.sortByLength(Arrays.asList("a", "abc", "abcd" ));
        Assertions.assertThat(list).isEqualTo(List.of("a", "abc", "abcd" ));
    }
}
