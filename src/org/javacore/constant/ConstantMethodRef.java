package org.javacore.constant;

import org.javacore.Identifier;

/**
 */
public interface ConstantMethodRef extends ConstantRef {

    @Override
    default public Identifier getIdentifier() {
        return Identifier.CONSTANT_METHOD_REF;
    }

}