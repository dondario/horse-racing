package com.intenthq.horseracing.exception;


public class BallTossInvalidException extends Exception implements HorseRacingInputException {
    private String code;

    public BallTossInvalidException(String code, String message) {
        super(message);
        this.code = code;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
