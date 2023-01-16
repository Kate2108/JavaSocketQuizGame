package ru.itis.protocol.message.server;

import lombok.AllArgsConstructor;
import ru.itis.constants.MessageTypes;
import ru.itis.models.Question;
import ru.itis.protocol.message.ContentMessage;

@AllArgsConstructor
public class NextQuestionMessage implements ContentMessage<Question> {
    private final Question content;
    private final int senderId;

    @Override
    public MessageTypes getType() {
        return MessageTypes.NEXT_QUESTION;
    }

    @Override
    public Question getContent() {
        return content;
    }

    @Override
    public int getSenderId() {
        return senderId;
    }

}
