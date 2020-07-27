package bml;

import bml.entity.BmlCategoryDto;
import bml.service.BmlCategoryPlusService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class HelloApplicationTests {

    @Resource
    BmlCategoryPlusService plusService;

    @Test
    void contextLoads() {
        BmlCategoryDto dto = new BmlCategoryDto();
        dto.setId(123L);
    }

    @Test
    void listAll() {
        List<BmlCategoryDto> list = plusService.getCategoryTree();
        list.forEach(System.out::println);
    }



}
