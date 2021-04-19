package com.teamdev.calculator.runtime.operators.impl;

import com.teamdev.calculator.runtime.operators.BinaryOperator;

public class Devide implements BinaryOperator {
    @Override
    public int getPriority() {
        return 1;
    }

    @Override
    public double apply(double leftArgument, double rightArgument) {
        return leftArgument/rightArgument;
    }

    @Override
    public int compareTo(BinaryOperator o) {

        return Integer.compare(this.getPriority(), o.getPriority());
    }
}
