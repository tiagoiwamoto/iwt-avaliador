package br.com.tiagoiwamoto.core.converter;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class LocalDateConverter {

    public static LocalDate toLocaldate(Date date){
        return Instant
                .ofEpochMilli(date.getTime())
                .atZone( ZoneId.of("America/Sao_Paulo") )
                .toLocalDate();
    }

}
