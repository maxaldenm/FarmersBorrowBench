package com.miw.farmersborrowbench;

import me.xdrop.fuzzywuzzy.FuzzySearch;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FuzzySearchTest {

    @Test
    public void doBla() {
        System.out.println(FuzzySearch.ratio("Jansen","Janse"));
        FuzzySearch.ratio("mysmilarstring","mysimilarstring");
    }

}
