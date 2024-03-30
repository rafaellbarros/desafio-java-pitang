package br.com.rafaellbarros.domain.core.model.mapper;


import br.com.rafaellbarros.domain.core.exception.InfraException;
import org.apache.commons.io.IOUtils;
import org.mapstruct.IterableMapping;
import org.mapstruct.Named;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface BaseGenericObjectMapper<T> {

    @IterableMapping(qualifiedByName = {"toMap"})
    List<T> toList(final List<Object> value);

    @Named("toMap")
    T toMap(final Object value);

    default String toString(final Object valor, final int pos) {
        final Object retorno = this.toObject(valor, pos);
        if (retorno instanceof String) {
            return (String)retorno;
        } else {
            return retorno == null ? null : retorno.toString();
        }
    }

    default String fromClobToString(final Object valor, final int pos) {
        try {
            final Object[] value = (Object[]) valor;
            final Clob lob = (Clob)value[pos];
            return IOUtils.toString(lob.getCharacterStream());
        } catch (IOException | SQLException var5) {
            throw new InfraException(var5);
        }
    }

    default Boolean toBooleanFromSimNao(final Object valor, final int pos) {
        return this.toBooleanFromString(valor, pos, "S");
    }

    default Boolean toBooleanFromString(final Object valor, final int pos, final String verdadeiro) {
        final String retorno = this.toString(valor, pos);
        return retorno == null ? null : retorno.equals(verdadeiro);
    }

    default Long toLong(final Object valor, final int pos) {
        final Number retorno = (Number)this.toObject(valor, pos);
        return retorno == null ? null : retorno.longValue();
    }

    default Integer toInteger(final Object valor, final int pos) {
        final Number retorno = (Number)this.toObject(valor, pos);
        return retorno == null ? null : retorno.intValue();
    }

    default BigDecimal toBigDecimal(final Object valor, final int pos) {
        return (BigDecimal)this.toObject(valor, pos);
    }

    default LocalDate toLocalDate(final Object valor, final int pos) {
        final Object obj = this.toObject(valor, pos);
        return obj instanceof Timestamp ? ((Timestamp)valor).toLocalDateTime().toLocalDate() : (LocalDate)obj;
    }

    default LocalDateTime toLocalDateTime(final Object valor, final int pos) {
        final Object obj = this.toObject(valor, pos);
        return obj instanceof Timestamp ? ((Timestamp)valor).toLocalDateTime() : (LocalDateTime)obj;
    }

    default Date toDate(final Object valor, final int pos) {
        return (Date)this.toObject(valor, pos);
    }

    default Object toObject(final Object valor, final int pos) {
        return valor == null ? null : ((Object[])((Object[])valor))[pos];
    }
}
