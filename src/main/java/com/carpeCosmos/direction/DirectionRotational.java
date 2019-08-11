package com.carpeCosmos.domain.direction;

public class DirectionRotational implements Direction
{
    @Override
    public boolean isRotation()
    {
        return true;
    }

    @Override
    public Axis getAxis()
    {
        return null;
    }
}
