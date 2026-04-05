package com.teb.practice.context;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TestContext {

    private int firstNumber;
    private int secondNumber;
    private int numericResult;

    private String inputString;
    private String stringResult;

    private boolean error;
    private boolean booleanValue;
}
