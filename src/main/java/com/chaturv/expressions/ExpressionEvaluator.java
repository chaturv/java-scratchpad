package com.chaturv.expressions;

public class ExpressionEvaluator {
	
	public Number eval(Expression expr) {
		
		Operand op = expr.getOp();
		if (op == null) return expr.getN().doubleValue();
		
		switch (op) {
		case PLUS:
			return expr.getN().doubleValue() + eval(expr.getExpr()).doubleValue();
		case MINUS:
			return expr.getN().doubleValue() - eval(expr.getExpr()).doubleValue();
		case MULTIPLY:
			return expr.getN().doubleValue() * eval(expr.getExpr()).doubleValue();
		case DIVIDE:
			return expr.getN().doubleValue() / eval(expr.getExpr()).doubleValue();
		default:
			throw new RuntimeException();
		}
	}

}
