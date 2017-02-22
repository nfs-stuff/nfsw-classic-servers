package me.leorblx.classicnfsw.core;

public class HttpState
{
    private static Long personaId = -1L;
    private static Long eventId = -1L;

    public static Long getPersonaId()
    {
        return personaId;
    }

    public static void setPersonaId(Long personaId)
    {
        HttpState.personaId = personaId;
    }

    public static Long getEventId()
    {
        return eventId;
    }

    public static void setEventId(Long eventId)
    {
        HttpState.eventId = eventId;
    }
}
