package ru.korostelev.Weather.clients.exceptions;

public class AlreadyExistException extends Exception{

    public AlreadyExistException(String message) {
        super(message);
    }
}
