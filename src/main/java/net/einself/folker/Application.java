package net.einself.folker;

import io.micronaut.runtime.Micronaut;
import org.springframework.modulith.Modulith;

@Modulith
public class Application {

    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }
}