package logback_bundle.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Marker;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.turbo.TurboFilter;
import ch.qos.logback.core.spi.FilterReply;

/**
 * This filter delegates decision making to a specified strategy. This filter must have a unique name configured.
 *
 * The specified strategy may be set via {@link #setStrategy(String, TurboFilterStrategy)}.
 *
 */
public class StrategyTurboFilter extends TurboFilter {

    private static final Map<String, StrategyTurboFilter> NAMED_FILTERS = new ConcurrentHashMap<>();

    public static StrategyTurboFilter getFilter(String filterName) {
        return NAMED_FILTERS.get(filterName);
    }

    public static void removeStrategy(String filterName) {
        setStrategy(filterName, null);
    }

    public static void setStrategy(String filterName, TurboFilterStrategy strategy) {
        final StrategyTurboFilter filter = NAMED_FILTERS.get(filterName);
        if (filter == null) {
            throw new IllegalStateException(String.format("No such turbo filter found: '%s'", filterName));
        }
        filter.strategy = strategy;
    }

    private volatile TurboFilterStrategy strategy = null;

    @Override
    public void start() {
        final String name = getName();
        if (name == null || name.isEmpty()) {
            addError("No name was specified");
        }
        NAMED_FILTERS.put(name, this);
        super.start();
    }

    @Override
    public void stop() {
        NAMED_FILTERS.remove(this.getName());
        super.stop();
    }

    @Override
    public FilterReply decide(Marker marker, Logger logger, Level level, String format, Object[] params, Throwable t) {
        if (!isStarted()) {
            return FilterReply.NEUTRAL;
        }
        if (strategy == null) {
            return FilterReply.NEUTRAL;
        }
        return strategy.decide(marker, logger, level, format, params, t);
    }

}
