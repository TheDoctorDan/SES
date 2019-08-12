package com.carpeCosmos.measurement.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import static com.carpeCosmos.measurement.domain.UnitPrefix.UNO;

@Builder
@AllArgsConstructor
@Getter
@ToString
public class UnitMeasurement
{
    @Builder.Default
    private UnitPrefix unitPrefix;
    UnitType unitType;


    // constructor, assume uno
    public UnitMeasurement(UnitType unitType)
    {
        this.unitPrefix = UNO;
        this.unitType = unitType;
    }
}
