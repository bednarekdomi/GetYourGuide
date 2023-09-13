package com.App.GetYourGuide.exception;

public class GuideNotFoundException extends RuntimeException{

    public GuideNotFoundException(){
        super("No guides available for this day");
    }
}
