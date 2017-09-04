package com.didekinlib.model.incidencia.dominio;

import org.junit.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.didekinlib.model.incidencia.dominio.IncidenciaExceptionMsg.RESOLUCION_WRONG_INIT;
import static com.didekinlib.model.incidencia.dominio.Resolucion.doResolucionModifiedWithNewAvance;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * User: pedro@didekin
 * Date: 04/09/17
 * Time: 15:05
 */
public class ResolucionTest {

    private static final String USER_ADM = "userAdm";

    @Test
    public void test_DoResolucionModifiedWithNewAvance_1() throws Exception
    {
        // Check resolucion with 1 avance.
        Resolucion resolucion = doResolucionModifiedWithNewAvance(doResolucion(doAvances("new_avance")), USER_ADM);
        assertThat(resolucion.getAvances().size(), is(1));
        // Check resolucion without avance.
        resolucion = doResolucionModifiedWithNewAvance(doResolucion(null), USER_ADM);
        assertThat(resolucion.getAvances().size(), is(0));
        // Check resolucionn with empty avances list.
        resolucion = doResolucionModifiedWithNewAvance(doResolucion(new ArrayList<Avance>(0)), USER_ADM);
        assertThat(resolucion.getAvances().size(), is(0));
        // Check avance without descripcion.
        resolucion = doResolucionModifiedWithNewAvance(doResolucion(doAvances("")), USER_ADM);
        assertThat(resolucion.getAvances().size(), is(0));
    }

    @Test
    public void test_DoResolucionModifiedWithNewAvance_2() throws Exception
    {
        List<Avance> avances = doAvances("avance_1");
        avances.add(new Avance.AvanceBuilder()
                .avanceDesc("avance_2")
                .userName(USER_ADM)
                .build());
        try {
            doResolucionModifiedWithNewAvance(doResolucion(avances), USER_ADM);
            fail();
        } catch (IllegalArgumentException ie) {
            assertThat(ie.getMessage(), is(RESOLUCION_WRONG_INIT.toString()));
        }
    }

    // ======================================== HELPERS ========================================

    @SuppressWarnings("SameParameterValue")
    private List<Avance> doAvances(String avanceDesc)
    {
        List<Avance> avances = new ArrayList<>(1);
        avances.add(new Avance.AvanceBuilder()
                .avanceDesc(avanceDesc)
                .userName(USER_ADM)
                .build());
        return avances;
    }

    private Resolucion doResolucion(List<Avance> avances)
    {
        return new Resolucion.ResolucionBuilder
                (
                        new Incidencia.IncidenciaBuilder()
                                .incidenciaId(222L)
                                .build()
                )
                .descripcion("resolucion_to_be_modified")
                .fechaPrevista(new Timestamp(new Date().getTime()))
                .avances(avances)
                .build();
    }
}