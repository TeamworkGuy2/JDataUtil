package templates.generators;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;

import templates.ArrayInfo;
import templates.ArrayInfo.ArrayType;
import codeTemplate.TemplateUtil;

/**
 * @author TeamworkGuy2
 * @since 2014-12-28
 */
public class GenerateArrayUtils {


	public static final void generateArrayUtil() throws IOException {
		ArrayInfo info = new ArrayInfo();
		info.className = "ArrayUtil";
		info.importStatements = Arrays.asList("java.util.Arrays");
		info.packageName = "arrayUtils";
		info.types = Arrays.asList(
				new ArrayInfo.ArrayType("boolean",	false, null, null, " == ",		null, null, Boolean.TYPE, Boolean.TYPE, false),
				new ArrayInfo.ArrayType("byte",		false, null, "(byte)0", " == ",	"Byte.MIN_VALUE", "Byte.MAX_VALUE", Float.TYPE, Integer.TYPE, true),
				new ArrayInfo.ArrayType("short",	false, null, "(short)0", " == ","Short.MIN_VALUE", "Short.MAX_VALUE", Float.TYPE, Integer.TYPE, true),
				new ArrayInfo.ArrayType("char",		false, null, "(char)0", " == ",	"Character.MIN_VALUE", "Character.MAX_VALUE", Float.TYPE, Integer.TYPE, true),
				new ArrayInfo.ArrayType("int",		false, null, "0", " == ",		"Integer.MIN_VALUE", "Integer.MAX_VALUE", Float.TYPE, Integer.TYPE, true),
				new ArrayInfo.ArrayType("long",		false, null, "0L", " == ",		"Long.MIN_VALUE", "Long.MAX_VALUE", Double.TYPE, Long.TYPE, true),
				new ArrayInfo.ArrayType("float",	false, null, "0f", " == ",		"Float.MIN_VALUE", "Float.MAX_VALUE", Float.TYPE, Float.TYPE, true),
				new ArrayInfo.ArrayType("double",	false, null, "0d", " == ",		"Double.MIN_VALUE", "Double.MAX_VALUE", Double.TYPE, Double.TYPE, true),
				new ArrayInfo.ArrayType("T",		true, "<T>", null, ".equals",	null, null, Object.class, Object.class, false)
				);

		Writer out = new FileWriter(TemplateUtil.getSrcRelativePath(info).toFile());
		TemplateUtil.renderObjectTemplate("src/templates/TArrayUtil.stg", "TArrayUtil", info, out);
		out.close();
	}


	public static void main(String[] args) throws IOException {
		generateArrayUtil();
	}

}
