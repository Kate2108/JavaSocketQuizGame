package ru.itis.models;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

@Builder
@Getter
@Data
public class Question implements Serializable {
    private int points;
    private String question;
    private String[] answers;
    private int correctAnsId;
}

