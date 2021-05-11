package com.teamdev.booby.compiler.unaryoperators;

import com.teamdev.booby.runtime.RuntimeEnvironment;
import com.teamdev.booby.runtime.UnaryOperatorOutputChain;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.State;
import com.teamdev.calculator.compiler.TypeOfExpressionElement;
import com.teamdev.calculator.impl.CompilerFactoryImpl;
import com.teamdev.calculator.runtime.ShuntingYardStack;
import com.teamdev.calculator.runtime.command.Command;
import com.teamdev.calculator.runtime.holder.ValueHolder;
import com.teamdev.calculator.runtime.holder.value.DoubleVisitor;
import org.checkerframework.checker.nullness.Opt;

import java.util.Optional;
import java.util.regex.Pattern;

public class UnaryOperandState extends State<UnaryOperatorOutputChain> {
    private final Pattern pattern = Pattern.compile("[a-zA-Z]");


    @Override
    public boolean tryTransition(InputCharacterStream characterStream, UnaryOperatorOutputChain builder) {
        RuntimeEnvironment environment = RuntimeEnvironment.getInstance();

        if (pattern.matcher(String.valueOf(characterStream.getCurrentSymbol())).matches()) {
            ValueHolder<?> valueHolder = environment.getValue(String.valueOf(characterStream.getCurrentSymbol()));
            Optional<Double> value = new DoubleVisitor().getDoubleValue(valueHolder);
            if(value.isPresent()) {
                builder.setOperand(value.get());
                characterStream.increasePointer();
                return true;
            }
        }
        return false;
    }
}
