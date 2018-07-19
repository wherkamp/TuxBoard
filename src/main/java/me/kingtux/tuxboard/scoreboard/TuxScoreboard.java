package me.kingtux.tuxboard.scoreboard;

import java.util.List;

/**
 * An Easier representation of a scoreboard
 *
 * @author KingTux
 */
public class TuxScoreboard {

  private ScoreboardLine title;
  private List<ScoreboardLine> content;
  private String scoreboardPermission;
  private String id;

  public TuxScoreboard(ScoreboardLine title,
      List<ScoreboardLine> content, String scoreboardPermission, String id) {
    this.title = title;
    this.content = content;
    this.scoreboardPermission = scoreboardPermission;
    this.id = id;
  }

  public ScoreboardLine getTitle() {
    return title;
  }

  public String getId() {
    return id;
  }

  public String getPermission() {
    return scoreboardPermission;
  }
}
