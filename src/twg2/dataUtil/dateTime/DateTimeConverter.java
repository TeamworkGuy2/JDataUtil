package twg2.dataUtil.dateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Locale;

/** Utility methods and fields for date and time formatting<br>
 * <a href="http://en.wikipedia.org/wiki/ISO_8601">http://en.wikipedia.org/wiki/ISO_8601</a><br>
 * <a href="http://tools.ietf.org/html/rfc822#section-5.1">http://tools.ietf.org/html/rfc822#section-5.1</a>
 * @author TeamworkGuy2
 * @since 2014-6-2
 */
public final class DateTimeConverter {
	public static final String ISO_8601_DATE_FORMAT_STRING = "yyyy-MM-dd";
	public static final String ISO_8601_DATE_TIME_FORMAT_STRING = "yyyy-MM-dd'T'HH:mm:ssZ";
	public static final String RFC_822_FORMAT_STRING = "E, W MMM y H:m:s z";
	private static final DateTimeConverter defaultInstance = new DateTimeConverter();

	public final SimpleDateFormat iso8601DateFormatter;
	public final SimpleDateFormat iso8601DateTimeFormatter;
	public final SimpleDateFormat rfc822Formatter;


	public DateTimeConverter() {
		this.iso8601DateFormatter = new SimpleDateFormat(ISO_8601_DATE_FORMAT_STRING);
		this.iso8601DateTimeFormatter = new SimpleDateFormat(ISO_8601_DATE_TIME_FORMAT_STRING);
		this.rfc822Formatter = new SimpleDateFormat(RFC_822_FORMAT_STRING);
	}


	public DateTimeConverter(Locale locale) {
		this.iso8601DateFormatter = new SimpleDateFormat(ISO_8601_DATE_FORMAT_STRING, locale);
		this.iso8601DateTimeFormatter = new SimpleDateFormat(ISO_8601_DATE_TIME_FORMAT_STRING, locale);
		this.rfc822Formatter = new SimpleDateFormat(RFC_822_FORMAT_STRING, locale);
	}


	public SimpleDateFormat getIso8601DateFormatter() {
		return iso8601DateFormatter;
	}


	public SimpleDateFormat getIso8601DateTimeFormatter() {
		return iso8601DateTimeFormatter;
	}


	public SimpleDateFormat getRfc822Formatter() {
		return rfc822Formatter;
	}


	public String formatIso8601Date(Instant time) {
		String str = iso8601DateFormatter.format(Date.from(time));
		return str;
	}


	public String formatIso8601DateTime(Instant time) {
		String str = iso8601DateTimeFormatter.format(Date.from(time));
		return str;
	}


	public String formatRfc822(Instant time) {
		String str = rfc822Formatter.format(Date.from(time));
		return str;
	}


	public String formatIso8601Date(Date date) {
		String str = iso8601DateFormatter.format(date);
		return str;
	}


	public String formatIso8601DateTime(Date date) {
		String str = iso8601DateTimeFormatter.format(date);
		return str;
	}


	public String formatRfc822(Date date) {
		String str = rfc822Formatter.format(date);
		return str;
	}


	public static final DateTimeConverter getDefaultInstance() {
		return defaultInstance;
	}


	public static ZonedDateTime parseIso8601Date(String str, ZoneId zone) {
		return parseFromFormatter(getDefaultInstance().iso8601DateFormatter, str, zone);
	}


	public static ZonedDateTime parseIso8601DateTime(String str, ZoneId zone) {
		return parseFromFormatter(getDefaultInstance().iso8601DateTimeFormatter, str, zone);
	}


	public static ZonedDateTime parseRfc822DateTime(String str, ZoneId zone) {
		return parseFromFormatter(getDefaultInstance().rfc822Formatter, str, zone);
	}


	public static ZonedDateTime parseFromFormatter(SimpleDateFormat formatter, String str, ZoneId zone) {
		try {
			Instant instant = formatter.parse(str).toInstant();
			return ZonedDateTime.ofInstant(instant, zone);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

}
