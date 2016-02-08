package logback_bundle.core;

import org.slf4j.MDC;
import org.slf4j.Marker;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.turbo.TurboFilter;
import ch.qos.logback.core.spi.FilterReply;

/**
 * 
 * Log level can be overridden via specified MDC attribute.
 *
 * @see http://logback.qos.ch/manual/filters.html#TurboFilter
 * @see http://stackoverflow.com/a/24052506
 */
public class LevelOverrideFilter extends TurboFilter {

    private String mdcKey = null;

    public String getMdcKey() {
        return mdcKey;
    }

    public void setMdcKey(String mdcKey) {
        this.mdcKey = mdcKey;
    }

    @Override
    public void start() {
        if (mdcKey == null) {
            addError("No level was specified");
        }
        super.start();
    }

    @Override
    public FilterReply decide(Marker marker, Logger logger, Level level, String format, Object[] params, Throwable t) {
        if (!isStarted()) {
            return FilterReply.NEUTRAL;
        }
        final String mdcValue = MDC.get(this.mdcKey);
        if (mdcValue == null) {
            return FilterReply.NEUTRAL;
        }
        final Level overrideLevel = Level.toLevel(mdcValue, /* no default level */ null);
        if (overrideLevel == null) {
            return FilterReply.NEUTRAL;
        }
        if (level.isGreaterOrEqual(overrideLevel)) {
            return FilterReply.ACCEPT;
        }
        return FilterReply.DENY;
    }

}
