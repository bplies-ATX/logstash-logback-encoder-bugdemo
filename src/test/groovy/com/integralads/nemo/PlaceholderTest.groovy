package com.integralads.nemo

import spock.lang.Specification

/**
 * Created by bplies on 1/16/19.
 */
class PlaceholderTest extends Specification {
    def "NOOP test just so we have a Unit Test class"() {
        when:
        int x = 1 + 2

        then:
        x == 3
    }
}
