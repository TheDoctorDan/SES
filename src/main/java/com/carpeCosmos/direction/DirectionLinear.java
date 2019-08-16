package com.carpeCosmos.direction;


public class DirectionLinear implements Direction
{
    private Axis3DLinear cartesianAxis;

    @Override
    public boolean isRotation()
    {
        return false;
    }

    @Override
    public Axis getAxis()
    {
        return cartesianAxis;
    }
}
