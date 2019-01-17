package com.integralads.nemo.controllers;

import ch.qos.logback.classic.Logger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.integralads.nemo.api.request.ReportMessageDTO;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

import static net.logstash.logback.argument.StructuredArguments.kv;
import static net.logstash.logback.argument.StructuredArguments.value;

@RestController
public class SampleController {
    private static Logger logger = (Logger) LoggerFactory.getLogger(SampleController.class);

    @RequestMapping(value = "/echo", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public final ReportMessageDTO echoReportRequest(@RequestBody ReportMessageDTO reportMessageDTO) {
        logger.info("Report {} requested.",
                kv("report-id", reportMessageDTO.getId()),
                value("report-request-body", reportMessageDTO)); // <==  Will serialize to JSON into log

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            logger.info("Forced JSON serialization", value("report-request-body", objectMapper.writeValueAsString(reportMessageDTO)));
        } catch (JsonProcessingException e) {
           logger.error("Exception with forced JSON serialization", e);
        }

        logger.info("Escape test 1 {}",
                kv("escape-this", "Did \"THIS\" get escaped?")); // <==  Will serialize to JSON into log

        // DO STUFF

        logger.info("Report {} completed", kv("report-id", reportMessageDTO.getId()));
        return reportMessageDTO;
    }

    @RequestMapping(value = "/exception", method = RequestMethod.GET, produces = "application/json")
    public final Map triggerException() {

        try{
            throw new RuntimeException("This String \"NEEDS\" \"escaping\"");
        }
        catch (Exception ex) {
            logger.error("Triggered exception", ex);
        }

        return Collections.singletonMap("result", "success");
    }
}

