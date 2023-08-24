package com.App.GetYourGuide.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MailDetails {
    private String mailTo;
    private String subject;
    private String message;

}
