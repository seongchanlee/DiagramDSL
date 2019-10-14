package model;

import java.util.ArrayList;
import java.util.List;

public class Class {
    private String name;
    private String type;
    private Class extendedClass;
    private List<Class> implementedClasses;
    private List<Method> methods;

    public Class(String name, String type) {
        this.name = name;
        this.type = type;
        methods = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Class getExtendedClass() {
        return extendedClass;
    }

    public List<Class> getImplementedClasses() {
        return implementedClasses;
    }

    public void classExtends(Class extendedClass) {
        assert this.extendedClass == null;
        this.extendedClass = extendedClass;
    }

    public void classImplements(Class implementedClass) {
        if (implementedClasses == null) {
            implementedClasses = new ArrayList<>();
        }

        implementedClasses.add(implementedClass);
    }

    public void addMethod(Method method) {
        methods.add(method);
    }

    public List<Method> getMethods() {
        return methods;
    }
}
