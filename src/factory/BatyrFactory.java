package batyrgame.factory;

import batyrgame.core.Batyr;

public class BatyrFactory {
    public static Batyr create(String name) {
        return new Batyr(name, 200);
    }
}