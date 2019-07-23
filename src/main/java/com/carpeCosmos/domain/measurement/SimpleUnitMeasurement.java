package com.carpeCosmos.domain.measurement;

import lombok.Getter;

import java.util.NoSuchElementException;
import java.util.Objects;

@Getter
public class SimpleUnitMeasurement
{
    private UnitPrefix unitPrefix;
    private UnitMeasureType unitMeasureType;

    public SimpleUnitMeasurement(UnitPrefix unitPrefix, UnitMeasureType unitMeasureType)
    {
        this.unitPrefix = unitPrefix;
        this.unitMeasureType = unitMeasureType;
    }

    public SimpleUnitMeasurement(UnitMeasureType unitMeasureType)
    {
        this.unitPrefix = UnitPrefix.UNO;
        this.unitMeasureType = unitMeasureType;
    }

    public SimpleUnitMeasurement sumUnitPrefixWithPowerOf10(int powerOf10) throws NoSuchElementException
    {
        if(powerOf10==0)
            return this;
        UnitPrefix unitPrefixForSumPowerOf10 = this.getUnitPrefix().addPowerOf10(powerOf10);
        return new SimpleUnitMeasurement(unitPrefixForSumPowerOf10, this.unitMeasureType);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof SimpleUnitMeasurement)) return false;
        SimpleUnitMeasurement that = (SimpleUnitMeasurement) o;
        return getUnitPrefix() == that.getUnitPrefix() &&
                Objects.equals(getUnitMeasureType(), that.getUnitMeasureType());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getUnitPrefix(), getUnitMeasureType());
    }
}
