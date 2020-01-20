package com.carpeCosmos.domain.vectors;

import lombok.Builder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@Builder
public class Scalar
{
    private Double magnitude;
    private UnitMeasurementFraction unitMeasurementFraction;

    public Scalar(Double magnitude, List<SimpleUnitMeasurement> inputNumeratorSimpleUnitMeasurementList, List<SimpleUnitMeasurement> inputDenominatorSimpleUnitMeasurementList)
    {
        this.magnitude = magnitude;
        this.unitMeasurementFraction = new UnitMeasurementFraction(inputNumeratorSimpleUnitMeasurementList, inputDenominatorSimpleUnitMeasurementList);
    }


    public Scalar(Double magnitude, SimpleUnitMeasurement simpleUnitMeasurement)
    {
        new Scalar(magnitude, Arrays.asList(simpleUnitMeasurement), Collections.emptyList());
    }

    public Scalar(Double magnitude, UnitMeasurementFraction unitMeasurementFraction)
    {
        new Scalar(magnitude, unitMeasurementFraction.getNumeratorSimpleUnitMeasurementList(), unitMeasurementFraction.getDenominatorSimpleUnitMeasurementList());
    }


    public Scalar squared(){
        return Scalar.builder()
                .magnitude(magnitude.doubleValue()*magnitude.doubleValue())
                .unitMeasurementFraction(UnitMeasurementFraction(unitMeasurementFraction.times(unitMeasurementFraction)))
                .build();
    }



}
