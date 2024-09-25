package ui.helpers;

import java.awt.*;

public class ComponentSearch {
    public static Component findComponentByName(Container container, String name) {
        for (Component component : container.getComponents()) {
            if (name.equals(component.getName()))
                return component;
            if (component instanceof Container subcontainer) {
                Component subcomponent = findComponentByName(subcontainer, name);
                if (subcomponent != null)
                    return subcomponent;
            }
        }
        return null;
    }
}