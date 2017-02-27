import spock.lang.Specification

class StringCalcSpec extends Specification {

    StringCalc calc = new StringCalc()

    def "should return 0 for empty string"() {

        when:
        def sum = calc.add("")

        then:
        sum == 0
    }

    def "should return value for single number"() {
        when:
        def sum = calc.add(singleNumber.toString())

        then:
        sum == singleNumber

        where:
        singleNumber << (1..10)
    }

    def "should return sum for two comma-separated numbers "() {

        when:
        def sum = calc.add(numberOne + "," + numberTwo)

        then:
        sum == numberOne + numberTwo

        where:
        numberOne | numberTwo
        0         | 0
        0         | 1
        2         | 4
        3         | 7
    }

    def "should return sum for three comma-separated numbers "() {

        when:
        def sum = calc.add(numberOne + "," + numberTwo + "," + numberThree)

        then:
        sum == numberOne + numberTwo + numberThree

        where:
        numberOne | numberTwo | numberThree
        0         | 0         | 0
        0         | 1         | 2
        2         | 4         | 2
        3         | 7         | 87
    }
}
