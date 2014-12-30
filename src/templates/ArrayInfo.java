package templates;

import java.util.List;

import codeTemplate.ClassTemplateInfo;

/**
 * @author TeamworkGuy2
 * @since 2014-12-28
 */
public class ArrayInfo extends ClassTemplateInfo {
	public List<ArrayType> types;




	/**
	 * @author TeamworkGuy2
	 * @since 2014-12-28
	 */
	public static class ArrayType extends ClassTemplateInfo {
		public boolean isGeneric;
		public String genericSignature;
		public String checkEquality;


		/**
		 * @param typeName the name of the data type, for example {@code int} or
		 * {@code String}
		 * @param isGeneric true if the type is a generic type
		 * @param defaultValue the default value for this data type
		 * @param genericSignature a generic signature string like {@code <T>} or
		 * {@code <E extends CharSequence>}
		 * @param checkEquality the type of equality check to use when comparing two values
		 * of this type, for example {@code "=="} or {@code ".equals"}
		 * @param minValue
		 * @param maxValue
		 * @param averageType the average data type for this type, for example {@code float} for {@code int}
		 * @param doAggregate true to include {@code sum(), avg(), min(), max()} methods for this type
		 */
		public ArrayType(String typeName, boolean isGeneric, String genericSignature, String defaultValue, String checkEquality,
				String minValue, String maxValue, Class<?> averageType, Class<?> sumType, boolean doAggregate) {
			this.type = typeName;
			this.isGeneric = isGeneric;
			this.genericSignature = genericSignature;
			this.defaultValue = defaultValue;
			this.checkEquality = checkEquality;
			this.minValue = minValue;
			this.maxValue = maxValue;
			this.averageType = averageType.getName();
			this.sumType = sumType.getName();
			this.isAggregatable = doAggregate;
		}


		public String createEqualityCheck(String varName1, String varName2) {
			return varName1 + checkEquality + "(" + varName2 + ")";
		}

	}

}
