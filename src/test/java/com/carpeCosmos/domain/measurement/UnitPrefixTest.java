package com.carpeCosmos.domain.measurement;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static com.carpeCosmos.domain.measurement.UnitPrefix.*;
import static org.junit.Assert.*;

public class UnitPrefixTest
{

    @Before
    public void setUp() throws Exception
    {
    }

    @Test
    public void findByfactorTestNormalSet()
    {
        UnitPrefix actualUnitPrefix;

        actualUnitPrefix = UnitPrefix.findByfactor(1D);
        assertEquals(UNO, actualUnitPrefix);

        actualUnitPrefix = UnitPrefix.findByfactor(10D);
        assertEquals(DECA, actualUnitPrefix);

        actualUnitPrefix = UnitPrefix.findByfactor(.1D);
        assertEquals(DECI, actualUnitPrefix);

        actualUnitPrefix = UnitPrefix.findByfactor(1000D);
        assertEquals(KILO, actualUnitPrefix);

        actualUnitPrefix = UnitPrefix.findByfactor(.001D);
        assertEquals(MILLI, actualUnitPrefix);


        actualUnitPrefix = UnitPrefix.findByfactor(1.0E+24D);
        assertEquals(YOTTA, actualUnitPrefix);

        actualUnitPrefix = UnitPrefix.findByfactor(1.0E-24D);
        assertEquals(YOCTO, actualUnitPrefix);

    }

    @Test(expected = NoSuchElementException.class)
    public void findByfactorTestFailure()
    {
        UnitPrefix.findByfactor(1110D);
    }


    @Test
    public void findByPowerOf10TestNormalSet()
    {
        UnitPrefix actualUnitPrefix;

        actualUnitPrefix = UnitPrefix.findByPowerOf10(0);
        assertEquals(UNO, actualUnitPrefix);

        actualUnitPrefix = UnitPrefix.findByPowerOf10(1);
        assertEquals(DECA, actualUnitPrefix);

        actualUnitPrefix = UnitPrefix.findByPowerOf10(-1);
        assertEquals(DECI, actualUnitPrefix);

        actualUnitPrefix = UnitPrefix.findByPowerOf10(3);
        assertEquals(KILO, actualUnitPrefix);

        actualUnitPrefix = UnitPrefix.findByPowerOf10(-3);
        assertEquals(MILLI, actualUnitPrefix);


        actualUnitPrefix = UnitPrefix.findByPowerOf10(24);
        assertEquals(YOTTA, actualUnitPrefix);

        actualUnitPrefix = UnitPrefix.findByPowerOf10(-24);
        assertEquals(YOCTO, actualUnitPrefix);

    }


    @Test(expected = NoSuchElementException.class)
    public void findByPowerOf10TestFailure()
    {
        UnitPrefix.findByPowerOf10(4);
    }

    @Test
    public void addPowerOf10TestNormalSet()
    {
        assertEquals(UNO, UNO.addPowerOf10(0));
        assertEquals(DECA, UNO.addPowerOf10(1));
        assertEquals(HECTO, UNO.addPowerOf10(2));
        assertEquals(KILO, UNO.addPowerOf10(3));

        assertEquals(MEGA, KILO.addPowerOf10(3));
        assertEquals(GIGA, KILO.addPowerOf10(6));
        assertEquals(UNO, KILO.addPowerOf10(-3));

        assertEquals(UNO, UNO.addPowerOf10(-0));
        assertEquals(DECI, UNO.addPowerOf10(-1));
        assertEquals(CENTI, UNO.addPowerOf10(-2));
        assertEquals(MILLI, UNO.addPowerOf10(-3));

        assertEquals(MICRO, MILLI.addPowerOf10(-3));
    }

    @Test(expected = NoSuchElementException.class)
    public void addPowerOf10TestNoPower4() { UNO.addPowerOf10(4); }


    @Test(expected = NoSuchElementException.class)
    public void addPowerOf10TestNoPower8() { MEGA.addPowerOf10(2); }

    @Test
    public void sumUnitPrefixesTestNormalSet()
    {
        assertEquals(MEGA, KILO.sumUnitPrefixes(KILO));
        assertEquals(TERA, GIGA.sumUnitPrefixes(KILO));
        assertEquals(KILO, GIGA.sumUnitPrefixes(MICRO));
    }

    @Test(expected = NoSuchElementException.class)
    public void sumUnitPrefixesTestOutOfRange()
    {
        PETA.sumUnitPrefixes(PETA);
    }


    @Test(expected = NoSuchElementException.class)
    public void sumUnitPrefixesTestNotMultipleOf1000()
    {
        DECA.sumUnitPrefixes(KILO);
    }



    @Test
    public void subtractUnitPrefixesTestNormalSet()
    {
        assertEquals(KILO, MEGA.subtractUnitPrefixes(KILO));
        assertEquals(MEGA, GIGA.subtractUnitPrefixes(KILO));
        assertEquals(PETA, GIGA.subtractUnitPrefixes(MICRO));
    }

    @Test(expected = NoSuchElementException.class)
    public void subtractUnitPrefixesTestOutOfRange()
    {
        FEMTO.subtractUnitPrefixes(PETA);
    }


    @Test(expected = NoSuchElementException.class)
    public void subtractUnitPrefixesTestNotMultipleOf1000()
    {
        DECI.subtractUnitPrefixes(KILO);
    }


}