package customLayouts;

import org.apache.log4j.PatternLayout;
import org.apache.log4j.spi.LoggingEvent;

/**
 * Created by Ирина on 14.02.2017.
 */
public class CustomLogLayout extends PatternLayout {
    @Override
    public String format(LoggingEvent event) {

        return (String) event.getMessage();
    }
}
