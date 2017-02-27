class StringCalc {

    Integer add(String numbers) {
        def sum = 0
        if (numbers) {
            def index = numbers.indexOf(',')
            while (index != -1) {
                def value = numbers.substring(0, index)
                numbers = numbers.substring(index+1)
                sum += Integer.valueOf(value)
                index = numbers.indexOf(',')
            }
            sum += Integer.valueOf(numbers)
        }
        sum
    }
}
