package org.javacore.code.instruction;

import org.javacore.Identifier;

/**
 */
public interface FMul extends OperandInstruction {

    @Override
    default public Identifier getIdentifier() {
        return Identifier.FMUL;
    }
}
