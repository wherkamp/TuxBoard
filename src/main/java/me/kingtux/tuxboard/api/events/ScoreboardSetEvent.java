package me.kingtux.tuxboard.api.events;

import me.kingtux.tuxboard.scoreboard.TuxScoreboard;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public class ScoreboardSetEvent extends PlayerEvent implements Cancellable {

  private static final HandlerList handlers = new HandlerList();
  private TuxScoreboard scoreboard;
  private boolean canceeled;

  public ScoreboardSetEvent(Player who, TuxScoreboard scoreboard) {
    super(who);
    this.scoreboard = scoreboard;
  }

  public TuxScoreboard getScoreboard() {
    return scoreboard;
  }

  @Override
  public HandlerList getHandlers() {
    return handlers;
  }

  @Override
  public boolean isCancelled() {
    return canceeled;
  }

  @Override
  public void setCancelled(boolean b) {
    canceeled = b;
  }
}
