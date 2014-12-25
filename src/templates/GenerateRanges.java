package templates;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;

import codeTemplate.TemplateUtil;

/**
 * @author TeamworkGuy2
 * @since 2014-12-24
 */
public class GenerateRanges {


	public static final void generateRangeClasses() throws IOException {
		SearchSetInfo charRange = new SearchSetInfo();
		SearchSetInfo intRange = new SearchSetInfo();
		SearchSetInfo floatRange = new SearchSetInfo();
		SearchSetInfo[] infos = new SearchSetInfo[] { charRange, intRange, floatRange };

		// generates XyzRange interfaces
		charRange.className = "CharRange";
		charRange.implementClassNames = Arrays.asList();
		charRange.objectType = "Character";
		charRange.packageName = "ranges";
		charRange.type = "char";

		intRange.className = "IntRange";
		intRange.implementClassNames = Arrays.asList();
		intRange.objectType = "Integer";
		intRange.packageName = "ranges";
		intRange.type = "int";

		floatRange.className = "FloatRange";
		floatRange.implementClassNames = Arrays.asList();
		floatRange.objectType = "Float";
		floatRange.packageName = "ranges";
		floatRange.type = "float";

		generateRanges(infos);

		// generates XyzSearcher interfaces
		charRange.className = "CharSearcher";
		intRange.className = "IntSearcher";
		floatRange.className = "FloatSearcher";

		generateSearchers(infos);

		// generates XyzRangeSearcher interfaces
		charRange.className = "CharRangeSearcher";
		charRange.implementClassNames = Arrays.asList("CharRange", "CharSearcher");

		intRange.className = "IntRangeSearcher";
		intRange.implementClassNames = Arrays.asList("IntRange", "IntSearcher");

		floatRange.className = "FloatRangeSearcher";
		floatRange.implementClassNames = Arrays.asList("FloatRange", "FloatSearcher");

		generateRangeSearchers(infos);

		// generates XyzRangeSearcherMutable interfaces
		charRange.className = "CharRangeSearcherMutable";
		charRange.implementClassNames = Arrays.asList("CharRangeSearcher");
		charRange.immutableClassName = "CharRangeSearcher";

		intRange.className = "IntRangeSearcherMutable";
		intRange.implementClassNames = Arrays.asList("IntRangeSearcher");
		intRange.immutableClassName = "IntRangeSearcher";

		floatRange.className = "FloatRangeSearcherMutable";
		floatRange.implementClassNames = Arrays.asList("FloatRangeSearcher");
		floatRange.immutableClassName = "FloatRangeSearcher";

		generateRangeSearcherMutables(infos);

		// generates XyzRangeSearcherMutableImpl classes
		charRange.className = "CharRangeSearcherMutableImpl";
		charRange.primitiveListImpl = "primitiveCollections.CharArrayList";
		charRange.primitiveListInterface = "primitiveCollections.CharList";
		charRange.implementClassNames = Arrays.asList("CharRangeSearcherMutable");
		charRange.immutableClassName = "CharRangeSearcher";

		intRange.className = "IntRangeSearcherMutableImpl";
		intRange.primitiveListImpl = "primitiveCollections.IntArrayList";
		intRange.primitiveListInterface = "primitiveCollections.IntList";
		intRange.implementClassNames = Arrays.asList("IntRangeSearcherMutable");
		intRange.immutableClassName = "IntRangeSearcher";

		floatRange.className = "FloatRangeSearcherMutableImpl";
		floatRange.primitiveListImpl = "primitiveCollections.FloatArrayList";
		floatRange.primitiveListInterface = "primitiveCollections.FloatList";
		floatRange.implementClassNames = Arrays.asList("FloatRangeSearcherMutable");
		floatRange.immutableClassName = "FloatRangeSearcher";

		generateRangeSearcherMutableImpls(infos);

		// generates XyzSearchSet classes
		charRange.className = "CharSearchSet";
		charRange.typeUpperCase = "Char";
		charRange.importStatements = Arrays.asList("import primitiveCollections.CharListSorted;");
		charRange.implementClassNames = Arrays.asList("CharSearcher");
		charRange.valuesCollectionClassName = "CharListSorted";
		charRange.rangesCollectionClassName = "CharRangeSearcherMutableImpl";

		intRange.className = "IntSearchSet";
		intRange.typeUpperCase = "Int";
		intRange.importStatements = Arrays.asList("import primitiveCollections.IntListSorted;");
		intRange.implementClassNames = Arrays.asList("IntSearcher");
		intRange.valuesCollectionClassName = "IntListSorted";
		intRange.rangesCollectionClassName = "IntRangeSearcherMutableImpl";

		floatRange.className = "FloatSearchSet";
		floatRange.typeUpperCase = "Float";
		floatRange.importStatements = Arrays.asList("import primitiveCollections.FloatListSorted;");
		floatRange.implementClassNames = Arrays.asList("FloatSearcher");
		floatRange.valuesCollectionClassName = "FloatListSorted";
		floatRange.rangesCollectionClassName = "FloatRangeSearcherMutableImpl";

		generateRangeSearcherSet(infos);

		charRange.className = "CharSearcherMutable";
		charRange.importStatements = Arrays.asList("import primitiveCollections.CharListSorted;");
		charRange.implementClassNames = Arrays.asList("CharSearcher");

		intRange.className = "IntSearcherMutable";
		intRange.importStatements = Arrays.asList("import primitiveCollections.IntListSorted;");
		intRange.implementClassNames = Arrays.asList("IntSearcher");

		floatRange.className = "FloatSearcherMutable";
		floatRange.importStatements = Arrays.asList("import primitiveCollections.FloatListSorted;");
		floatRange.implementClassNames = Arrays.asList("FloatSearcher");

		generateSearcherMutable(infos);
	}


	public static final void generateRanges(RangeInfo... infos) throws IOException {
		for(RangeInfo info : infos) {
			Writer out = new FileWriter(TemplateUtil.getSrcRelativePath(info).toFile());
			TemplateUtil.renderObjectTemplate("src/templates/TRange.stg", "TRange", info, out);
			out.close();
		}
	}


	public static final void generateSearchers(RangeInfo... infos) throws IOException {
		for(RangeInfo info : infos) {
			Writer out = new FileWriter(TemplateUtil.getSrcRelativePath(info).toFile());
			TemplateUtil.renderObjectTemplate("src/templates/TSearcher.stg", "TSearcher", info, out);
			out.close();
		}
	}


	public static final void generateRangeSearchers(RangeInfo... infos) throws IOException {
		for(RangeInfo info : infos) {
			Writer out = new FileWriter(TemplateUtil.getSrcRelativePath(info).toFile());
			TemplateUtil.renderObjectTemplate("src/templates/TRangeSearcher.stg", "TRangeSearcher", info, out);
			out.close();
		}
	}


	public static final void generateRangeSearcherMutables(RangeInfo... infos) throws IOException {
		for(RangeInfo info : infos) {
			Writer out = new FileWriter(TemplateUtil.getSrcRelativePath(info).toFile());
			TemplateUtil.renderObjectTemplate("src/templates/TRangeSearcherMutable.stg", "TRangeSearcherMutable", info, out);
			out.close();
		}
	}


	public static final void generateRangeSearcherMutableImpls(RangeInfo... infos) throws IOException {
		for(RangeInfo info : infos) {
			Writer out = new FileWriter(TemplateUtil.getSrcRelativePath(info).toFile());
			TemplateUtil.renderObjectTemplate("src/templates/TRangeSearcherMutableImpl.stg", "TRangeSearcherMutableImpl", info, out);
			out.close();
		}
	}


	public static final void generateRangeSearcherSet(SearchSetInfo... infos) throws IOException {
		for(RangeInfo info : infos) {
			Writer out = new FileWriter(TemplateUtil.getSrcRelativePath(info).toFile());
			TemplateUtil.renderObjectTemplate("src/templates/TSearchSet.stg", "TSearchSet", info, out);
			out.close();
		}
	}


	public static final void generateSearcherMutable(SearchSetInfo... infos) throws IOException {
		for(RangeInfo info : infos) {
			Writer out = new FileWriter(TemplateUtil.getSrcRelativePath(info).toFile());
			TemplateUtil.renderObjectTemplate("src/templates/TSearcherMutable.stg", "TSearcherMutable", info, out);
			out.close();
		}
	}


	public static void main(String[] args) throws IOException {
		generateRangeClasses();
	}

}
