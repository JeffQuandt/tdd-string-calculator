import spock.lang.Specification

class StringCalcSpec extends Specification{

    StringCalc calc = new StringCalc()

    def "should return 0 for empty string"() {

        when:
        def sum = calc.add("")

        then:
        sum == 0
    }

}
