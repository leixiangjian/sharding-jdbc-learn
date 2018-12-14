package com.sharding.demo.cotroller.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath*:application.xml","classpath*:spring/spring-mvc.xml" })
public class ShardingDemoControllerTest {
	private static Logger logger = LogManager.getLogger(ShardingDemoControllerTest.class);
	
	@Autowired
	private WebApplicationContext context;
	
	MockMvc mockMvc;
	
    @Before
    public void before() {
        //可以对所有的controller来进行测试
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

        //仅仅对单个Controller来进行测试
       //mockMvc = MockMvcBuilders.standaloneSetup(new ShardingDemoController()).build();
    }
    
    @Test
    public void testDemo(){
    	try {
			this.mockMvc.perform(post("/demo/test")).andExpect(status().isOk());
			logger.info("=====");
		} catch (Exception e) {
			logger.error(e);
		}
    }
}
