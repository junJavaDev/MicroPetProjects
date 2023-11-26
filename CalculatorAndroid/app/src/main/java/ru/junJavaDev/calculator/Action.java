package ru.junJavaDev.calculator;

public enum Action {
    plus(R.string.plus),           //сложение
    minus(R.string.minus),          //вычитание
    multiply(R.string.multiply),       //умножение
    division(R.string.division)       //деление
    ;
    final int symbol;
     Action(int symbol) {
         this.symbol = symbol;
    }
}
