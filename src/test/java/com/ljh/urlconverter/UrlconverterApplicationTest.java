package com.ljh.urlconverter;

import com.ljh.urlconverter.domain.UrlInfo;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UrlconverterApplicationTest {

    private MockMvc mockMvc;
    private static UrlInfo urlInfo;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @BeforeClass
    public static void setData() {
        urlInfo = new UrlInfo();
        urlInfo.setOriginalUrl("https://en.wikipedia.org/wiki/URL_shortening");
    }

    @Test
    public void urlConvertTest() throws Exception {
        MvcResult mvcResult_1 = mockMvc.perform(post("/url/convert").flashAttr("urlInfo", urlInfo))
                .andExpect(model().attributeExists("urlInfo"))
                .andExpect(status().isOk())
                .andReturn();
        MvcResult mvcResult_2 = mockMvc.perform(post("/url/convert").flashAttr("urlInfo", urlInfo))
                .andExpect(model().attributeExists("urlInfo"))
                .andExpect(status().isOk())
                .andReturn();

        UrlInfo resultUrlInfo_1 = (UrlInfo) mvcResult_1.getModelAndView().getModel().get("urlInfo");
        UrlInfo resultUrlInfo_2 = (UrlInfo) mvcResult_2.getModelAndView().getModel().get("urlInfo");
        assertEquals(resultUrlInfo_1.getShortUrl(), resultUrlInfo_2.getShortUrl());
    }

    @Test
    public void urlRedirectTest() throws Exception {
        MvcResult mvcResult_1 = mockMvc.perform(post("/url/convert").flashAttr("urlInfo", urlInfo))
                .andExpect(model().attributeExists("urlInfo"))
                .andExpect(status().isOk())
                .andReturn();
        UrlInfo resultUrlInfo_1 = (UrlInfo) mvcResult_1.getModelAndView().getModel().get("urlInfo");
        MvcResult mvcResult_2 = mockMvc.perform(post("/url/connect").flashAttr("urlInfo", resultUrlInfo_1))
                .andExpect(status().isFound())
                .andReturn();
        MvcResult mvcResult_3 = mockMvc.perform(get(resultUrlInfo_1.getShortUrl()))
                .andExpect(status().isFound())
                .andReturn();

        assertEquals(mvcResult_2.getResponse().getRedirectedUrl(), urlInfo.getOriginalUrl());
        assertEquals(mvcResult_3.getResponse().getRedirectedUrl(), urlInfo.getOriginalUrl());
    }

}