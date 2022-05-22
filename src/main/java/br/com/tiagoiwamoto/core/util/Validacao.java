package br.com.tiagoiwamoto.core.util;

public class Validacao {

    public static boolean validarOpcaoSelecionada(int value){
        return value < 0 || value > 4;
    }

}
