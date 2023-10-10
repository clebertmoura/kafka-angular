package br.com.ctm.kafkaangular.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public record Message (String text, LocalDateTime sentAt) {

    public static List<Message> messages = new ArrayList<>();

}