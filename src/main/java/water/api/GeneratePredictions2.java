package water.api;

import hex.ScoreTask;
import water.*;
import water.util.RString;
import water.util.Log;
import water.fvec.Frame;

/**
 * @author cliffc
 */
public class GeneratePredictions2 extends Request2 {
  static final int API_WEAVER = 1; // This file has auto-gen'd doc & json fields
  static public DocGen.FieldDoc[] DOC_FIELDS; // Initialized from Auto-Gen code.

  @API( help="Model", required=true, filter=Default.class )
  Model model;
  
  @API(help = "Data frame", required = true, filter = Default.class)
  Frame data;

  @API( help="Prediction key", required=true, filter=Default.class )
  Key prediction_key;


  public static String link(Key k, String content) {
    RString rs = new RString("<a href='GeneratePredictions2.query?model=%$key'>%content</a>");
    rs.replace("key", k.toString());
    rs.replace("content", content);
    return rs.toString();
  }

  @Override protected Response serve() {
    try {
      return Inspect2.redirect(this, ScoreTask.score(model, data, prediction_key).toString());
    } catch (Throwable t) {
      Log.err(t);
      return Response.error(t.getMessage());
    }
  }
}