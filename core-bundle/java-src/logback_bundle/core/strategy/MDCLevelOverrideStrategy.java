package logback_bundle.core.strategy;

import org.slf4j.MDC;
import org.slf4j.Marker;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.core.spi.FilterReply;
import logback_bundle.core.TurboFilterStrategy;

public class MDCLevelOverrideStrategy implements TurboFilterStrategy {

    private final String mdcLevelKey;

    public MDCLevelOverrideStrategy(String mdcLevelKey) {
        this.mdcLevelKey = mdcLevelKey;
    }

    @Override
    public FilterReply decide(Marker marker, Logger logger, Level level, String format, Object[] params, Throwable t) {
        final String mdcValue = MDC.get(this.mdcLevelKey);
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
