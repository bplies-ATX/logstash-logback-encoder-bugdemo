package com.integralads.nemo.controllers

import com.integralads.nemo.DemoApp
import groovy.json.JsonSlurper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = DemoApp.class)
@ContextConfiguration
abstract class BaseControllerTest extends Specification {
    @Value('${local.server.port}')
    protected int port;

    @Autowired
    private WebApplicationContext webapp;

    def jsonSlurper = new JsonSlurper()

    protected MockMvc mvc

    void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(webapp)
                .build()
    }

    String getURL(String path) {
        return "http://localhost:${port}/${path}"
    }

    def doGet(String url) {
        def resp = mvc.perform(get(url)).andReturn().response
        if (resp.status != 200)
            throw new HttpClientErrorException(HttpStatus.valueOf(resp.status), resp.errorMessage)
        return jsonSlurper.parseText(resp.getContentAsString())
    }

    def doPost(String url, String body) {
        def resp = mvc.perform(post(url + '/').content(body)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .characterEncoding('UTF-8')
        ).andReturn().response
        if (resp.status != 200)
            throw new HttpClientErrorException(HttpStatus.valueOf(resp.status), resp.errorMessage)
        return jsonSlurper.parseText(resp.getContentAsString())
    }
}
