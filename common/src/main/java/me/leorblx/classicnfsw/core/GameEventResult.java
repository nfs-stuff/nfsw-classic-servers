package me.leorblx.classicnfsw.core;

public interface GameEventResult
{
    Long getEventSessionId();
    
    Long getPersonaId();
    
    FinishReason getFinishReason();
}
