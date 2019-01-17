package com.integralads.nemo.controllers

class SampleControllerIntegrationTest extends BaseControllerTest {
    def "Basic test to exercise logging"() {
        given: "a report request"
        def reportMessageJSON = """
            {
              "id": 1,
              "reportName": "Report for simple data",
              "createdOn": "2018-07-19 05:33:48",
              "reportPropertiesVersion": "2.0",
              "reportProperties": {
                "prop1": "value1",
                "prop2": "value2"
              },
              "startDate": "2018-11-05",
              "endDate": "2018-11-05"
            }
        """

        when: "request the report from endpoint that will demonstrate the logging"
        def ret = doPost('/echo', reportMessageJSON)

        then: "We have a result"
        ret != null
    }

    def "An Exception logged through the stackTrace Provider is escaped"() {
        when: "Trigger an Exception for the log"
        def ret = doGet('/exception')

        then: "We have a result"
        ret != null
    }
}
