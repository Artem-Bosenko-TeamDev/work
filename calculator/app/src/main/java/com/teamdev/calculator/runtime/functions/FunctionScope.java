package com.teamdev.calculator.runtime.functions;

import com.teamdev.calculator.runtime.holder.ValueHolder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This is parser for one type of {@link Function functions} and list of arguments for this function.
 * */
@SuppressWarnings("ClassWithTooManyTransitiveDependents")
public class FunctionScope implements Cloneable {
    private Function function;
    private final List<ValueHolder<?>> arguments;

    public FunctionScope(){
        arguments = new ArrayList<>();
    }

    public void addName(String nameFunction){
        this.function = new FunctionFactory().getFunction(nameFunction);
    }

    public void addArgument(ValueHolder<?> argument){
        arguments.add(argument);
    }

    public List<ValueHolder<?>> getArguments() {
        return Collections.unmodifiableList(arguments);
    }
    public Function getFunction(){
        return function;
    }
}
