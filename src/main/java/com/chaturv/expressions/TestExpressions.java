package com.chaturv.expressions;

public class TestExpressions {
	
	public static void main(String[] args) {
		// 5 / 2 * (3 + 4)
		Expression expr0 = new Expression(Double.valueOf(4), null, null); 
		Expression expr1 = new Expression(Double.valueOf(3), Operand.PLUS, expr0);
		Expression expr2 = new Expression(Double.valueOf(2), Operand.MULTIPLY, expr1);
		Expression expr3 = new Expression(Double.valueOf(5), Operand.DIVIDE, expr2);
		
		ExpressionEvaluator evaluator = new ExpressionEvaluator();
		System.out.println(evaluator.eval(expr3));
		
	}

}
