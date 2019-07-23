package com.carpeCosmos.domain.vectors;

import com.carpeCosmos.domain.measurement.SimpleUnitMeasurement;
import com.carpeCosmos.domain.measurement.UnitMeasurementFraction;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.carpeCosmos.domain.measurement.UnitPrefix.*;

public class Scalar
{
    private double amount;
    private UnitMeasurementFraction unitMeasurementFraction;

    public Scalar(double amount, List<SimpleUnitMeasurement> inputNumeratorSimpleUnitMeasurementList, List<SimpleUnitMeasurement> inputDenominatorSimpleUnitMeasurementList)
    {
        this.amount = amount;
        UnitMeasurementFraction tempUnitMeasurementFraction = new UnitMeasurementFraction(inputNumeratorSimpleUnitMeasurementList, inputDenominatorSimpleUnitMeasurementList);

        UnitMeasurementFraction baseTypesUnitMeasurementFraction = UnitMeasurementFraction.reduceUnitTypes(tempUnitMeasurementFraction, UNO);
        UnitMeasurementFraction simplifiedUnitMeasurementFraction = UnitMeasurementFraction.cancelUnitTypes(baseTypesUnitMeasurementFraction);

        this.unitMeasurementFraction = new UnitMeasurementFraction();
        this.amount = amount * UnitMeasurementFraction.reduceDimensionUnitPrefixEach(this.unitMeasurementFraction, simplifiedUnitMeasurementFraction);;
    }


    public Scalar(double amount, SimpleUnitMeasurement simpleUnitMeasurement)
    {
        new Scalar(amount, Arrays.asList(simpleUnitMeasurement), Collections.emptyList());
    }

    public Scalar(double amount, UnitMeasurementFraction unitMeasurementFraction)
    {
        new Scalar(amount, unitMeasurementFraction.getNumeratorSimpleUnitMeasurementList(), unitMeasurementFraction.getDenominatorSimpleUnitMeasurementList());
    }




}
