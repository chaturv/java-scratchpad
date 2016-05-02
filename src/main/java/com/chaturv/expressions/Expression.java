package com.chaturv.expressions;

public class Expression {
	
	Number n;
	Operand op;
	Expression expr;
	
	public Expression(Number n, Operand op, Expression expr) {
		this.n = n;
		this.op = op;
		this.expr = expr;
		
	}

	public Number getN() {
		return n;
	}

	public Operand getOp() {
		return op;
	}

	public Expression getExpr() {
		return expr;
	}
	
	
}
