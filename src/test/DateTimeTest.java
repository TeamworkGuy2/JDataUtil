package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import dataUtility.DateTimeConverter;

/**
 * @author TeamworkGuy2
 * @since 2014-12-2
 */
public class DateTimeTest {


	public static void formatParseDateTimeTest() throws ParseException {
		Date[] dates = new Date[] { Date.from(Instant.now()), new Date((long)(Math.random() * 1_000_000_000_000L)) };
		DateTimeConverter dateFormatter = DateTimeConverter.getDefaultInstance();
		SimpleDateFormat[] formatters = new SimpleDateFormat[] {
				dateFormatter.getIso8601DateFormatter(),
				dateFormatter.getIso8601DateTimeFormatter(),
				dateFormatter.getRfc822Formatter()
		};
		String[] formatterNames = new String[] { "ISO 8601 Date", "ISO 8601 Date/Time", "RFC 822" };

		for(Date date : dates) {
			System.out.println("date [" + date + "]");
			int j = 0;
			for(SimpleDateFormat formatter : formatters) {
				String str = formatter.format(date);
				Date d = formatter.parse(str);
				System.out.println(formatterNames[j] + " format [" + str + "], parse [" + d + "]");
				j++;
			}
			System.out.println();
		}
	}

}
