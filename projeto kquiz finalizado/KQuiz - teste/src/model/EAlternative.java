/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author morga
 */
public enum EAlternative {
    A(1), B(2), C(3), D(4), E(5);

    public int value;

    EAlternative(int value) {
        this.value = value;
    }

    public boolean equals(EAlternative obj) {
        return (obj.getValue() == this.value);
    }

    public int getValue() {
        return value;
    }

    public static EAlternative getValue(int value) {
        switch (value) {
            case 1:
                return A;
            case 2:
                return B;
            case 3:
                return C;
            case 4:
                return D;
            case 5:
                return E;
            default:
                return null;
        }
    }
}
