package com.example;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

public class OhHai extends AbstractBehavior<OhHai.HaiMe> {

  public static final class HaiMe {
    public final String whom;
    public final Long whence;

    public HaiMe(String whom, Long whence) {
      this.whom = whom;
      this.whence = whence;
    }
  }

  public static Behavior<HaiMe> create() {
    return Behaviors.setup(OhHai::new);
  }

  private OhHai(ActorContext<HaiMe> context) {
    super(context);
  }

  @Override
  public Receive<HaiMe> createReceive() {
    return newReceiveBuilder().onMessage(HaiMe.class, this::onHaiMe).build();
  }

  private Behavior<HaiMe> onHaiMe(HaiMe command) {
    getContext().getLog().info("OHHAI {} whence {}!", command.whom, command.whence);
    return this;
  }
}