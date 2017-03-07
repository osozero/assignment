package com.shopinle.assignment.enums;


public enum Exceptions {
    NOT_REGISTERED_EMAIL("Hatalı giriş yaptınız. Lütfen bilgilerinizi kontrol ederek tekrar deneyiniz."),
    WRONG_PASSWORD("Hatalı giriş yaptınız. Lütfen bilgilerinizi kontrol ederek tekrar deneyiniz.");
    private String type;

    Exceptions(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static Exceptions get(String type) {

        for (Exceptions exceptions : values()) {
            if (exceptions.getType().equalsIgnoreCase(type)) return exceptions;
        }

        throw new RuntimeException("Exception not found!");
    }
}
