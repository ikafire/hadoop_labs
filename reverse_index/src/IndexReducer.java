import java.io.IOException;
import java.lang.String;
import java.util.StringTokenizer;
import java.util.Vector;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class IndexReducer 
     extends Reducer<Text,Text,Text,Text> {
  private Text result = new Text();

  public void reduce(Text key, Iterable<Text> values, 
                     Context context
                     ) throws IOException, InterruptedException {
    Vector<String> paths = new Vector<String>();
    for (Text val : values) {
      paths.addElement(val.toString());
    }
    String[] array = paths.toArray(new String[paths.size()]);
    String combined = StringUtils.join(array, new String(","));
    result.set(combined);

    context.write(key, result);
  }
}
