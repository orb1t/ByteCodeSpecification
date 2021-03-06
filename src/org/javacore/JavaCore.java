package org.javacore;

import org.javacore.type.Type;
import org.javacore.type.TypeRepository;
import org.javacore.type.generic.Declaration;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author Obicere
 */
public class JavaCore {

    private static TypeRepository typeRepository;

    static {
        initSystem();
    }

    private static void initSystem() {
        final String customPath = System.getProperty("javacore.class.loader");
        if (customPath != null) {
            final TypeRepository customRepository = createRepository(customPath);
            if (customRepository != null) {
                typeRepository = customRepository;
                return;
            } else {
                System.err.println("Error loading custom type repository. Falling back to system default.");
            }
        }

        // default to the default type loader implementation
        final String defaultPath = "org.obicere.bytecode.core.objects.type.TypeLoader";

        typeRepository = createRepository(defaultPath);
    }

    private static TypeRepository createRepository(final String name) {
        try {
            final Class<?> cls = Class.forName(name);
            final Constructor<?> constructor = cls.getConstructor();

            constructor.setAccessible(true);

            final Object instance = constructor.newInstance();
            return (TypeRepository) instance;
        } catch (final ClassNotFoundException e) {
            System.err.println("Could not find custom class: " + name);
            e.printStackTrace();
        } catch (final InstantiationException e) {
            System.err.println("Error invoking default constructor for class: " + name);
            e.printStackTrace();
        } catch (final IllegalAccessException e) {
            System.err.println("Could not access default constructor for class: " + name);
            e.printStackTrace();
        } catch (final ClassCastException e) {
            System.err.println("The custom class is not a subclass of TypeRepository: " + name);
            e.printStackTrace();
        } catch (final NoSuchMethodException e) {
            System.err.println("No default constructor available for class: " + name);
            e.printStackTrace();
        } catch (final InvocationTargetException e) {
            System.err.println("Could not target constructor for class: " + name);
            e.printStackTrace();
        }
        return null;
    }

    public static JCClass getClass(final String name) {
        return (JCClass) typeRepository.getType(name);
    }

    public static Type getType(final String name) {
        return typeRepository.getType(name);
    }

    public static Type getType(final String name, final Declaration<?> scope) {
        return typeRepository.getType(name, scope);
    }
}
