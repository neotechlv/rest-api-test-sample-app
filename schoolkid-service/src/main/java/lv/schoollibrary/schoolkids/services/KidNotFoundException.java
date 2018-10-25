package lv.schoollibrary.schoolkids.services;

import static java.lang.String.format;

public class KidNotFoundException extends RuntimeException {

    public KidNotFoundException(String name, String surname) {
        super(format("Kid %s %s not found", name, surname));
    }
}
