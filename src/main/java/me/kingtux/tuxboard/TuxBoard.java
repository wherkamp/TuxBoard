package me.kingtux.tuxboard;

import java.util.List;
import me.kingtux.tuxboard.listeners.Events;
import me.kingtux.tuxboard.scoreboard.TuxScoreboard;
import org.bukkit.plugin.java.JavaPlugin;

public class TuxBoard extends JavaPlugin {

  private TuxScoreboardManager tuxScoreboardManager;

  @Override
  public void onEnable() {
    loadEvents();
    tuxScoreboardManager = new TuxScoreboardManager(this);
  }

  @Override
  public void onDisable() {

  }

  private void loadEvents() {
    getServer().getPluginManager().registerEvents(new Events(this), this);
  }

  public TuxScoreboardManager getTuxScoreboardManager() {
    return tuxScoreboardManager;
  }
}
