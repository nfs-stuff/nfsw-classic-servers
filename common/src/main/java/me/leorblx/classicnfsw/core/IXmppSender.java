package me.leorblx.classicnfsw.core;

public interface IXmppSender
{
    void send(String msg, Long to);

    void send(Object object, Long to);

    void createUpdatePersona(Long id, String password);
}
