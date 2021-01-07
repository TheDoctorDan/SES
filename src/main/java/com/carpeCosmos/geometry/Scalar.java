package com.carpeCosmos.geometry;

import com.carpeCosmos.unitMeasure.domain.*;
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

    public Scalar matchUnitMeasurementScale(Scalar other){
        if(this,unitMeasurementFraction.isEquivalentMeasurement(other.unitMeasurementFraction))
        Scalar result = Scalar.builder().magnitude(mag).unitMeasurementFraction(umf).build()
    }

    public Scalar squared(){
        return Scalar.builder()
                .magnitude(magnitude.doubleValue()*magnitude.doubleValue())
                .unitMeasurementFraction(UnitMeasurementFraction(unitMeasurementFraction.times(unitMeasurementFraction)))
                .build();
    }



}
