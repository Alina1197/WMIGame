package com.eleks.game.model.request;

import java.time.Instant;
import java.util.Objects;

public class RoomRequest
{
    private String id = String.format("%d", Instant.now().toEpochMilli());

    public RoomRequest(String id)
    {
        this.id = id;
    }

    public RoomRequest()
    {
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        RoomRequest that = (RoomRequest) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id);
    }

    @Override
    public String toString()
    {
        return "RoomRequest{" +
            "id='" + id + '\'' +
            '}';
    }
}
