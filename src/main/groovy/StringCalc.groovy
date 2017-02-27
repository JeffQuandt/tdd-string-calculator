class StringCalc {

    Integer add(String numbers) {
        def sum = 0
        if (numbers) {
            sum += Integer.valueOf(numbers)
        }
        sum
    }
}
