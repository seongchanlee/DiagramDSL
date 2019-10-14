package model;

import java.util.ArrayList;
import java.util.List;

public class Method {
    private String name;
    private String modifier;
    private boolean isAbstract;
    private List<Parameter> parameters;
    private boolean isStatic = false;
    private boolean isFinal = false;

    public Method(String name, boolean isAbstract) {
        this.name = name;
        this.isAbstract = isAbstract;
        parameters = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public boolean isStatic() {
        return isStatic;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public String getModifier() {
        return modifier;
    }

    public void setStatic() {
        isStatic = true;
    }

    public void setFinal() {
        isFinal = true;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }
}
