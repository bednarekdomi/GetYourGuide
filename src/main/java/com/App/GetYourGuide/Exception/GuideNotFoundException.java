package com.App.GetYourGuide.Exception;

public class GuideNotFoundException extends RuntimeException{

    public GuideNotFoundException(){
        super("No guides available for this day");
    }
}
