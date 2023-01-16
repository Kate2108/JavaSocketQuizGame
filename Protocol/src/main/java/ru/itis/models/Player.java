package ru.itis.models;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

@Builder
@Getter
@Data
public class Player implements Serializable{
    private int id;
    private String username;
    private int points;
    private int roomId;
}
