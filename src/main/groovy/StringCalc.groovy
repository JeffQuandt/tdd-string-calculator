class StringCalc {

    def customDelimPtrn = ~/(?s)\/\/(.+?)\n(.+)/

    Integer add(String numbers) {
        def separator = ",|\n"
        def sum = 0
        def negativeNumbersFound = new StringJoiner(",")
        if (numbers) {
            (numbers, separator) = checkForCustomSeparator(numbers, separator)
            numbers.split(separator).each {

                def value = Integer.valueOf(it)
                if (value < 0) {
                    negativeNumbersFound.add(it)
                } else if(value <= 1000) {
                    sum += value
                }
            }
        }

        throwErrorIfNegativesPresent(negativeNumbersFound)
        sum
    }

    private void throwErrorIfNegativesPresent(StringJoiner negativeNumbersFound) {
        if (negativeNumbersFound.toString()) {
            throw new IllegalArgumentException("Negative numbers are not allowed! Negative numbers: ${negativeNumbersFound.toString()}")
        }
    }

    private List checkForCustomSeparator(String numbers, String separator) {
        def matcher = numbers =~ customDelimPtrn
        if (matcher) {
            separator += "|" + matcher[0][1]
            numbers = matcher[0][2]
        }
        [numbers, separator]
    }
}
