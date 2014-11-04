package functionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.RandomAccess;
import java.util.function.Function;

/**
 * @author TeamworkGuy2
 * @since 2014-11-2
 */
public class FunctionUtil {

	public static final <T, R> List<R> transform(T[] values, Function<T, R> convert) {
		ArrayList<R> res = new ArrayList<R>();

		for(T value : values) {
			res.add(convert.apply(value));
		}

		return res;
	}


	public static final <T, R> List<R> transform(Collection<? extends T> values, Function<T, R> convert) {
		ArrayList<R> res = new ArrayList<R>();

		if(values instanceof List && values instanceof RandomAccess) {
			List<? extends T> valuesList = (List<? extends T>)values;
			for(int i = 0, size = values.size(); i < size; i++) {
				res.add(convert.apply(valuesList.get(i)));
			}
		}
		else {
			for(T value : values) {
				res.add(convert.apply(value));
			}
		}

		return res;
	}

}
