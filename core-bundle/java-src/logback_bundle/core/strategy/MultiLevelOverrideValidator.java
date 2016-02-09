package logback_bundle.core.strategy;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicLong;

public class MultiLevelOverrideValidator {

    public static final Callable<Boolean> FOREVER_TRUE = new Callable<Boolean>() {
        @Override
        public Boolean call() throws Exception {
            return Boolean.TRUE;
        }
    };

    public static Callable<Boolean> untilDurationMillis(final long durationMillis) {
        return untilTimeMillis(System.currentTimeMillis() + durationMillis);
    }

    public static Callable<Boolean> untilTimeMillis(final long untilTimeMillis) {
        return new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return System.currentTimeMillis() < untilTimeMillis;
            }
        };
    }

    public static Callable<Boolean> untilCount(final long count) {
        final AtomicLong counter = new AtomicLong();
        return new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                if (counter.get() >= count) {  // short-circuit if threshold crossed
                    return false;
                }
                return counter.getAndIncrement() < count;
            }
        };
    }

}
