package com.sbab.home.assignment.integrationtest.initlizesetup;

import com.sbab.home.assignment.SbabTestProjectApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = SbabTestProjectApplication.class)
@ContextConfiguration(classes = SbabTestProjectApplication.class)
public class SpringContextTest {

    @Test
    public void whenSpringContextIsBootstrapped_thenNoExceptions() {
    }
}
