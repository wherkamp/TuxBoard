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

  public TuxScoreboard(ScoreboardLine title,
      List<ScoreboardLine> content) {
    this.title = title;
    this.content = content;
  }


}
