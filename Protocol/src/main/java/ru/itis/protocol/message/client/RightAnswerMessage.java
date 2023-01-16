package ru.itis.protocol.message.client;

import lombok.AllArgsConstructor;
import ru.itis.constants.MessageTypes;
import ru.itis.protocol.message.ContentMessage;


@AllArgsConstructor
public class RightAnswerMessage implements ContentMessage<Integer> {
    private final int senderId;
    private final Integer pointsForAnswer;

    @Override
    public MessageTypes getType() {
        return MessageTypes.RIGHT_ANSWER;
    }

    @Override
    public Integer getContent() {
        return pointsForAnswer;
    }

    @Override
    public int getSenderId() {
        return senderId;
    }
}
