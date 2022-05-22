package br.com.tiagoiwamoto.core.domain.enums;

import lombok.Getter;

import javax.swing.text.html.Option;
import java.util.Arrays;
import java.util.Optional;

@Getter
public enum Opcao {

    AVALIAR_CANDIDADO(1, ""),
    RESULTADO_PARCIAL(2, ""),
    MODO_ADMIN(3, ""),
    SAIR(4, ""),
    CADASTRAR_CANDIDATO(5, ""),
    ATUALIZAR_CANDIDATO(6, ""),
    EXCLUIR_CANDIDATO(7, "");

    Opcao(Integer numero, String descricao) {
        this.numero = numero;
        this.descricao = descricao;
    }

    private Integer numero;
    private String descricao;

    public static Opcao value(Integer numero){
        Optional<Opcao> opcaoOptional =  Arrays.stream(Opcao.values()).filter(value -> value.numero == numero).findFirst();
        return opcaoOptional.orElseThrow();
    }

}
