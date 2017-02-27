class StringCalc {

    Integer add(String numbers) {
        def sum = 0
        if (numbers) {
            def index = numbers.indexOf(',')
            String num1 = numbers
            if (index != -1) {
                num1 = numbers.substring(0, index)
                if (index < numbers.length()) {
                    def num2 = numbers.substring(index + 1, numbers.length())
                    sum += Integer.valueOf(num2)
                }
            }
            sum += Integer.valueOf(num1)
        }
        sum
    }
}
