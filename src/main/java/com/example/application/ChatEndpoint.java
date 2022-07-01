package com.example.application;

import java.time.ZonedDateTime;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import dev.hilla.Endpoint;
import dev.hilla.Nonnull;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.core.publisher.Sinks.EmitResult;
import reactor.core.publisher.Sinks.Many;

@Endpoint
@AnonymousAllowed
public class ChatEndpoint {

  public static class Message {
    public @Nonnull String text;
    public ZonedDateTime time;
    public @Nonnull String userName;
  }

  private Many<Message> chatSink;
  private Flux<Message> chat;

  ChatEndpoint() {
    chatSink = Sinks.many().multicast().directBestEffort();
    chat = chatSink.asFlux().replay(10).autoConnect();
  }

  public @Nonnull Flux<@Nonnull Message> join() {
    return chat;
  }

  public void send(Message message) {
    message.time = ZonedDateTime.now();
    chatSink.emitNext(message,
        (signalType, emitResult) -> emitResult == EmitResult.FAIL_NON_SERIALIZED);
  }


}
