package org.javacore.code.instruction;

import org.javacore.Identifier;

/**
 */
public interface FLoad_2 extends LoadInstruction {

    @Override
    default public Identifier getIdentifier() {
        return Identifier.FLOAD_2;
    }
}
