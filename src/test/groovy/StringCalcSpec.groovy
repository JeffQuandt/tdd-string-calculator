import spock.lang.Specification
import spock.lang.Unroll

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

    def "should allow newlines as number separators"() {

        when:
        def sum = calc.add(numberOne + separator1 + numberTwo + separator2 + numberThree)

        then:
        sum == numberOne + numberTwo + numberThree

        where:
        numberOne | numberTwo | numberThree | separator1 | separator2
        0         | 0         | 0           | ","        | ","
        0         | 1         | 2           | "\n"       | ","
        2         | 4         | 2           | "\n"       | "\n"
        3         | 7         | 87          | ","        | "\n"
    }

    @Unroll
    def "should support custom delimiters"() {
        when:
        def sum = calc.add("//" + customDelim + "\n" + numberOne + separator1 + numberTwo + separator2 + numberThree)

        then:
        sum == numberOne + numberTwo + numberThree

        where:
        numberOne | numberTwo | numberThree | separator1 | separator2 | customDelim
        0         | 0         | 0           | ","        | ","        | ";"
        0         | 0         | 0           | ","        | ";"        | ";"
        0         | 1         | 2           | "\n"       | ";"        | ";"
        0         | 1         | 2           | ";"        | ";"        | ";"
    }

    def "should not allow negative numbers"() {
        when:
        calc.add(numberOne + separator1 + numberTwo + separator2 + numberThree)

        then:
        def exception = thrown(IllegalArgumentException)
        expectedNegativeNumbers.each {

            assert exception.message.contains(it.toString())
        }

        where:
        numberOne | numberTwo | numberThree | separator1 | separator2 || expectedNegativeNumbers
        0         | -1        | 0           | ","        | "," || [-1]
        0         | -1        | -2          | "\n"       | "," || [-1,-2]
        -2        | -4        | -3          | "\n"       | "\n"|| [-2,-4,-3]
    }

    def "should ignore numbers larger than 1000"() {
        when:
        def sum = calc.add("0,1,1050")

        then:
        sum == 1
    }
}
