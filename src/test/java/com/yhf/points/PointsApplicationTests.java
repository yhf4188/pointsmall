package com.yhf.points;

import com.alibaba.fastjson.JSONObject;
import com.yhf.points.controller.UserController;
import com.yhf.points.model.Address;
import com.yhf.points.model.Attribute;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.swing.event.ListDataEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PointsApplication.class})
@AutoConfigureMockMvc
class PointsApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() {mockMvc= MockMvcBuilders.standaloneSetup(new UserController().getClass()).build(); }

    @Test
    public void userControllerTest() throws Exception{
        List<Address> addresses=new ArrayList<>();

        // 构建请求
        Attribute user=new Attribute(0,"yhf","123456","1304039757@qq.com","13961862263",0,addresses);
        Map<String,Object> map=new HashMap<>();
        map.put("user",user);
        String requestJson = JSONObject.toJSONString(map);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/pointsmall/getUser")
                .contentType("application/json")
                .content(requestJson)
                .accept(MediaType.APPLICATION_JSON);

        // 发送请求，获取请求结果
        ResultActions perform = mockMvc.perform(request);

        // 请求结果校验
        perform.andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult mvcResult = perform.andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
    }

}
