package me.kingtux.tuxboard.listeners;

import me.kingtux.tuxboard.TuxBoard;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Events implements Listener {

  private TuxBoard tuxBoard;

  public Events(TuxBoard tuxBoard) {
    this.tuxBoard = tuxBoard;
  }

  @EventHandler
  public void onJoin(PlayerJoinEvent e) {
    tuxBoard.getTuxScoreboardManager().scoreboardPlayer(e.getPlayer());
  }

}
