package calculator;

import java.math.BigDecimal;

public class Client {

	public static void main(String[] args) {
		//case1: 100 + 50 * 20 / 2 - 10 = 590; 乘法除法优先于加减
		Calculator calculator = new Calculator();
		calculator.push('+',  new BigDecimal(100));
		calculator.push('+',  new BigDecimal(50));
		calculator.push('*',  new BigDecimal(20));
		calculator.push('/',  new BigDecimal(2));
		calculator.push('-',  new BigDecimal(10));
		calculator.compute();

		//case2: 100 + 50 * 20 / 2 - 10 = 590; => //100 + 50 * 20 = 1100
		calculator.undo();
		calculator.undo();
		calculator.redo();

		//case3:
		calculator.init();
		calculator.push('+',  new BigDecimal(100));
		calculator.push('/',  new BigDecimal(0));

	}
}