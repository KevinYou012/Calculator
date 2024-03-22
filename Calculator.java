package calculator;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Stack;

/**
 * 计算器实现
 */
public class Calculator {

	private ArrayList<CalculatorCommand> commands = new ArrayList<>();

	/**
	 * 计算
	 */
	public BigDecimal compute() {
		System.out.println("执行一次compute\n");

		//所以操作转为加法操作
		Stack<BigDecimal> caculateStack = new Stack<>();
		caculateStack.push(new BigDecimal(0));
		// 100 + 50 * 20 / 2 - 10 = 590

        for (CalculatorCommand command : commands) {
            switch (command.getOperator()) {
				case '+':
					caculateStack.push(command.getOperand());
					break;
				case '-':
					caculateStack.push(command.getOperand().negate());
					break;
				case '*':
					BigDecimal multiplyRet = caculateStack.pop().multiply(command.getOperand());
					caculateStack.push(multiplyRet);
					break;
				case '/':
					if (command.getOperand().equals(new BigDecimal(0))) {
						System.out.println("系统异常: 除数不能为0");
						System.exit(-1);
					}

                    BigDecimal divideRet = caculateStack.pop().divide(command.getOperand());
                    caculateStack.push(divideRet);
                    break;
            }
        }

		BigDecimal total = new BigDecimal(0);
		while (!caculateStack.isEmpty()) {
			total = total.add(caculateStack.pop());
		}
		StringBuilder log = new StringBuilder();
		log.append("0");
		commands.forEach(it-> log.append(" ").append(it.getOperator()).append(" ").append(it.getOperand()));
		log.append(" = ").append(total);
		System.out.println(log);

		return total;
	}

	/**
	 * 增加操作
	 * @param operator
	 * @param operand
	 */
	public void push(char operator, BigDecimal operand) {
		commands.add(new CalculatorCommand(operator, operand));
	}

	/**
	 * 归0
	 */
	public void init() {
		commands.clear();
	}

	/**
	 * 重做
	 */
	public BigDecimal redo() {
		System.out.println("执行一次redo\n");
		return compute();
	}

	/**
	 * 撤销一次操作
	 */
	public void undo() {
		System.out.println("执行一次undo\n");
		if (commands.isEmpty()) {
			return;
		}
		commands.remove(commands.size() - 1);
	}


	/**
	 * 计算机命令
	 */
	public static class CalculatorCommand  {

		/**
		 * 运算符
		 */
		private char operator;

		/**
		 * 操作数据
		 */
		private BigDecimal operand;

		public CalculatorCommand(char operator, BigDecimal operand) {
			super();
			this.operator = operator;
			this.operand = operand;
		}

		public char getOperator() {
			return operator;
		}

		public void setOperator(char operator) {
			this.operator = operator;
		}

		public BigDecimal getOperand() {
			return operand;
		}

		public void setOperand(BigDecimal operand) {
			this.operand = operand;
		}
	}
}
