package me.kingtux.tuxboard;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import me.kingtux.tuxboard.api.events.ScoreboardSetEvent;
import me.kingtux.tuxboard.scoreboard.TuxScoreboard;
import org.apache.commons.io.FilenameUtils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class TuxScoreboardManager {

  private List<TuxScoreboard> scoreboardList;
  private TuxBoard tuxBoard;
  private static TuxScoreboardManager instance;

  public static TuxScoreboardManager getInstance() {
    return instance;
  }

  protected TuxScoreboardManager(TuxBoard tuxBoard) {
    scoreboardList = new ArrayList<>();
    this.tuxBoard = tuxBoard;
    instance = this;
  }

  public void loadScoreboards() {
    File scoreBoardFolder = new File(tuxBoard.getDataFolder(), "scoreboards");
    File[] scoreboardFiles = scoreBoardFolder.listFiles();
    if (scoreboardFiles.length == 0) {
      tuxBoard.getLogger().log(Level.WARNING, "No Scoreboard files found!");
      return;
    }
    for (int i = 0; i < scoreboardFiles.length; i++) {
      if (scoreboardFiles[i].isFile()) {
        if (FilenameUtils.getExtension(scoreboardFiles[i].getName()).equalsIgnoreCase(".yml")) {
          tuxBoard.getLogger()
              .log(Level.WARNING, scoreboardFiles[i].getName() + " Is not an YML file.");
          continue;
        }
        loadScoreboard(scoreboardFiles[i]);

      }

    }

  }

  public void registerScoreboard(TuxScoreboard tuxScoreboard) {
    if (scoreboardList.contains(tuxScoreboard)) {
      tuxBoard.getLogger().log(Level.WARNING, "Scoreboard already exists" + tuxScoreboard.getId());
      return;
    }
    if (tuxScoreboard == null) {
      tuxBoard.getLogger().log(Level.WARNING, "Scoreboard that was registered is null!");
      return;
    }
    scoreboardList.add(tuxScoreboard);
  }

  private void loadScoreboard(File file) {
    FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
    TuxScoreboard tuxScoreboard = null;
    registerScoreboard(tuxScoreboard);

  }

  public void scoreboardPlayer(Player player) {
    for (TuxScoreboard tuxScoreboard : scoreboardList) {
      if (player.hasPermission(tuxScoreboard.getPermission())) {
        Scoreboard scoreboard = buildScoreboard(player, tuxScoreboard);
        if (scoreboard == null) {
          return;
        }
        player.setScoreboard(scoreboard);
      }
    }
  }

  public Scoreboard buildScoreboard(final Player player, final TuxScoreboard tuxScoreboard) {
    ScoreboardSetEvent scoreboardSetEvent = new ScoreboardSetEvent(player, tuxScoreboard);
    if (scoreboardSetEvent.isCancelled()) {
      return null;
    }

    Scoreboard board = Bukkit.getServer().getScoreboardManager().getNewScoreboard();
    Objective o = board.registerNewObjective("Scoreboard", "dummy");

    return board;
  }
}
