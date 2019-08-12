package com.carpeCosmos.measurement.domain;

import org.junit.Test;

import java.util.NoSuchElementException;

import static com.carpeCosmos.measurement.domain.MeasurementType.*;
import static com.carpeCosmos.measurement.domain.UnitType.*;
import static org.junit.Assert.*;

public class UnitTypeTest
{

    @Test
    public void isBaseUnitDimensionTest()
    {
        assertTrue(METER.isBaseUnitDimension());
        assertTrue(GRAM.isBaseUnitDimension());
        assertTrue(SECOND.isBaseUnitDimension());
        assertTrue(AMPERE.isBaseUnitDimension());
        assertTrue(KELVIN.isBaseUnitDimension());
        assertTrue(MOLE.isBaseUnitDimension());
        assertTrue(CANDELA.isBaseUnitDimension());
        assertTrue(EACH.isBaseUnitDimension());
        assertFalse(NEWTON.isBaseUnitDimension());
    }


    @Test
    public void findBySymbolTestNormalSet()
    {
        assertEquals(METER, findBySymbol("m"));
        assertEquals(GRAM, findBySymbol( "g"));
        assertEquals(SECOND, findBySymbol("s"));
        assertEquals(AMPERE, findBySymbol("A"));
        assertEquals(KELVIN, findBySymbol("K"));
        assertEquals(MOLE, findBySymbol("mole"));
        assertEquals(CANDELA, findBySymbol("cd"));
        assertEquals(EACH, findBySymbol("ea"));
    }

    @Test(expected = NoSuchElementException.class)
    public void findBySymbolTestFailure()
    {
        findBySymbol("z");
    }


    @Test
    public void findByFundamentalMeasurementTypeTestNormalSet()
    {
        assertEquals(METER, findByFundamentalMeasurementType(LENGTH));
        assertEquals(GRAM, findByFundamentalMeasurementType(MASS));
        assertEquals(SECOND, findByFundamentalMeasurementType(TIME));
        assertEquals(AMPERE, findByFundamentalMeasurementType(ELECTRICAL_CURRENT));
        assertEquals(KELVIN, findByFundamentalMeasurementType(TEMPERATURE));
        assertEquals(MOLE, findByFundamentalMeasurementType(AMOUNT_OF_SUBSTANCE));
        assertEquals(CANDELA, findByFundamentalMeasurementType(LUMINOUS_INTENSITY));
        assertEquals(EACH, findByFundamentalMeasurementType(UNIT_LESS_NUMBER));
    }



    @Test(expected = NoSuchElementException.class)
    public void findByFundamentalMeasurementTypeTestFailure()
    {
        findByFundamentalMeasurementType(null);
    }




}