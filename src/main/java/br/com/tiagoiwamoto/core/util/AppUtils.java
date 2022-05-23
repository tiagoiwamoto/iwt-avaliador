package br.com.tiagoiwamoto.core.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class AppUtils {

    public static void lerArquivo(String caminhoDoArquivo){
        try{
            var lines = Files.readAllLines(Path.of(caminhoDoArquivo));
            lines.forEach(System.out::println);
        }catch (IOException e){
            System.out.println("Não foi possível localizar as informações do arquivo ".concat(caminhoDoArquivo));
        }
    }

    public static void delay(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println("Impossível parar a aplicação por 5 segundos...");
        }
    }

    public static void limpaTela(){
        for(int linha = 0; linha < 50; linha++){
            System.out.println();
        }
    }

    public static boolean validarOpcaoSelecionada(int value){
        return value < 0 || value > 4;
    }

}
