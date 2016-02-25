package twg2.dataUtil.dataUtils;

import java.util.concurrent.TimeUnit;

/**
 * @author TeamworkGuy2
 * @since 2015-7-4
 */
public class TimeUnitUtil {
	static double C1 = 1;
	static double C2 = C1 * 1000;
	static double C3 = C2 * 1000;
	static double C4 = C3 * 1000;
	static double C5 = C4 * 60;
	static double C6 = C5 * 60;
	static double C7 = C6 * 24;

	static double[] nsPer = { C1, C2, C3, C4, C5, C6, C7 };

	static double[] microPer = { C1/C2, C2/C2, C3/C2, C4/C2, C5/C2, C6/C2, C7/C2 };

	static double[] milliPer = { C1/C3, C2/C3, C3/C3, C4/C3, C5/C3, C6/C3, C7/C3 };

	static double[] secPer = { C1/C4, C2/C4, C3/C4, C4/C4, C5/C4, C6/C4, C7/C4 };

	static double[] minPer = { C1/C5, C2/C5, C3/C5, C4/C5, C5/C5, C6/C5, C7/C5 };

	static double[] hrPer = { C1/C6, C2/C6, C3/C6, C4/C6, C5/C6, C6/C6, C7/C6 };

	static double[] dayPer = { C1/C7, C2/C7, C3/C7, C4/C7, C5/C7, C6/C7, C7/C7 };


	static double[][] APerB = {
		nsPer,
		microPer,
		milliPer,
		secPer,
		minPer,
		hrPer,
		dayPer
	};


	private TimeUnitUtil() { throw new AssertionError("cannot instantiate static class TimeUnitUtil"); }


	public static final double srcToDstMultiple(TimeUnit srcUnit, TimeUnit dstUnit) {
		return APerB[dstUnit.ordinal()][srcUnit.ordinal()];
	}


	public static final String abbreviation(TimeUnit unit, boolean shortest, boolean plural) {
		switch(unit) {
		case DAYS:
			return (plural ? "days" : "day");
		case HOURS:
			return (plural ? "hrs" : "hr");
		case MICROSECONDS:
			return (plural ? "micros" : "micro");
		case MILLISECONDS:
			return (shortest ? "ms" : (plural ? "millis" : "milli"));
		case MINUTES:
			return (plural ? "mins" : "min");
		case NANOSECONDS:
			return (shortest ? "ns" : (plural ? "nanos" : "nano"));
		case SECONDS:
			return (plural ? "secs" : "sec");
		default:
			throw throwUnknownUnit(unit, null);
		}
	}


	public static final double convert(TimeUnit srcUnit, double srcDuration, TimeUnit dstUnit) {
		return srcToDstMultiple(srcUnit, dstUnit) * srcDuration;
		/*
		//long nsPerPs = 1L;
		long nsPerMicro = 1_000L;
		long nsPerMilli = 1_000_000L;
		long nsPerSec = 1_000_000_000L;
		long nsPerMin = 60_000_000_000L;
		long nsPerHr = 3_600_000_000_000L;
		long nsPerDay = 86_400_000_000_000L;

		switch(srcUnit) {
		case DAYS:
			switch(dstUnit) {
			case DAYS:
				return srcDuration;
			case HOURS:
				return srcDuration * 24L;
			case MICROSECONDS:
				return srcDuration * (24 * 3_600_000_000L);
			case MILLISECONDS:
				return srcDuration * (24 * 3_600_000L);
			case MINUTES:
				return srcDuration * (24 * 60L);
			case NANOSECONDS:
				return srcDuration * nsPerDay;
			case SECONDS:
				return srcDuration * (24 * 3600L);
			default:
				throw throwUnknownUnit(dstUnit, "destination");
			}

		case HOURS:
			switch(dstUnit) {
			case DAYS:
				return srcDuration / 24L;
			case HOURS:
				return srcDuration;
			case MICROSECONDS:
				return srcDuration * 3_600_000_000L;
			case MILLISECONDS:
				return srcDuration * 3_600_000L;
			case MINUTES:
				return srcDuration * 60L;
			case NANOSECONDS:
				return srcDuration * nsPerHr;
			case SECONDS:
				return srcDuration * 3600L;
			default:
				throw throwUnknownUnit(dstUnit, "destination");
			}

		case MICROSECONDS:
			switch(dstUnit) {
			case DAYS:
				return srcDuration / (24 * 3_600_000_000L);
			case HOURS:
				return srcDuration / (3_600_000_000L);
			case MICROSECONDS:
				return srcDuration;
			case MILLISECONDS:
				return srcDuration / 1000L;
			case MINUTES:
				return srcDuration / 60_000_000L;
			case NANOSECONDS:
				return srcDuration * nsPerMicro;
			case SECONDS:
				return srcDuration / 1_000_000L;
			default:
				throw throwUnknownUnit(dstUnit, "destination");
			}

		case MILLISECONDS:
			switch(dstUnit) {
			case DAYS:
				return srcDuration / (24 * 3_600_000L);
			case HOURS:
				return srcDuration / 3_600_000L;
			case MICROSECONDS:
				return srcDuration * 1000L;
			case MILLISECONDS:
				return srcDuration;
			case MINUTES:
				return srcDuration / 60_000L;
			case NANOSECONDS:
				return srcDuration * nsPerMilli;
			case SECONDS:
				return srcDuration / 1000L;
			default:
				throw throwUnknownUnit(dstUnit, "destination");
			}

		case MINUTES:
			switch(dstUnit) {
			case DAYS:
				return srcDuration / (24 * 60L);
			case HOURS:
				return srcDuration / 60L;
			case MICROSECONDS:
				return srcDuration * 60_000_000L;
			case MILLISECONDS:
				return srcDuration * 60_000L;
			case MINUTES:
				return srcDuration;
			case NANOSECONDS:
				return srcDuration * nsPerMin;
			case SECONDS:
				return srcDuration * 60L;
			default:
				throw throwUnknownUnit(dstUnit, "destination");
			}

		case NANOSECONDS:
			switch(dstUnit) {
			case DAYS:
				return srcDuration / nsPerDay;
			case HOURS:
				return srcDuration / nsPerHr;
			case MICROSECONDS:
				return srcDuration / nsPerMicro;
			case MILLISECONDS:
				return srcDuration / nsPerMilli;
			case MINUTES:
				return srcDuration / nsPerMin;
			case NANOSECONDS:
				return srcDuration;
			case SECONDS:
				return srcDuration / nsPerSec;
			default:
				throw throwUnknownUnit(dstUnit, "destination");
			}

		case SECONDS:
			switch(dstUnit) {
			case DAYS:
				return srcDuration / (24 * 3600L);
			case HOURS:
				return srcDuration / 3600L;
			case MICROSECONDS:
				return srcDuration * 1_000_000L;
			case MILLISECONDS:
				return srcDuration * 1000L;
			case MINUTES:
				return srcDuration / 60L;
			case NANOSECONDS:
				return srcDuration * nsPerSec;
			case SECONDS:
				return srcDuration;
			default:
				throw throwUnknownUnit(dstUnit, "destination");
			}

		default:
			throw throwUnknownUnit(srcUnit, "source");
		}
		*/
	}


	static RuntimeException throwUnknownUnit(TimeUnit unit, String name) {
		return new IllegalArgumentException("unknown " + (name != null && name.length() > 0 ? name + " " : "") + "time unit '" + unit + "'");
	}
}
