package logback_bundle.core;

import org.slf4j.Marker;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.core.spi.FilterReply;

public interface TurboFilterStrategy {

    public FilterReply decide(Marker marker, Logger logger, Level level, String format, Object[] params, Throwable t);

}
