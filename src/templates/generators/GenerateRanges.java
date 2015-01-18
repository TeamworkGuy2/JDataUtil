package templates.generators;

import java.io.IOException;
import java.util.Arrays;

import org.stringtemplate.v4.ST;

import templates.RangeInfo;
import templates.SearchSetInfo;
import codeTemplate.PrimitiveTemplates;
import codeTemplate.TemplateUtil;

/**
 * @author TeamworkGuy2
 * @since 2014-12-24
 */
public class GenerateRanges {


	public static final void generateRangeClasses() throws IOException {
		SearchSetInfo charRange = PrimitiveTemplates.newCharTemplate(new SearchSetInfo(), "", "ranges");
		SearchSetInfo intRange = PrimitiveTemplates.newIntTemplate(new SearchSetInfo(), "", "ranges");
		SearchSetInfo floatRange = PrimitiveTemplates.newFloatTemplate(new SearchSetInfo(), "", "ranges");
		SearchSetInfo[] infos = new SearchSetInfo[] { charRange, intRange, floatRange };

		// generates XyzRange interfaces
		charRange.className = "CharRange";
		intRange.className = "IntRange";
		floatRange.className = "FloatRange";

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
		charRange.importStatements = Arrays.asList("import primitiveCollections.CharListSorted;");
		charRange.implementClassNames = Arrays.asList("CharSearcher");
		charRange.valuesCollectionClassName = "CharListSorted";
		charRange.rangesCollectionClassName = "CharRangeSearcherMutableImpl";

		intRange.className = "IntSearchSet";
		intRange.importStatements = Arrays.asList("import primitiveCollections.IntListSorted;");
		intRange.implementClassNames = Arrays.asList("IntSearcher");
		intRange.valuesCollectionClassName = "IntListSorted";
		intRange.rangesCollectionClassName = "IntRangeSearcherMutableImpl";

		floatRange.className = "FloatSearchSet";
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
		ST tmpl = TemplateUtil.createTemplate("src/templates/TRange.stg", "TRange");
		for(RangeInfo info : infos) {
			TemplateUtil.renderTemplate(tmpl, info);
		}
	}


	public static final void generateSearchers(RangeInfo... infos) throws IOException {
		ST tmpl = TemplateUtil.createTemplate("src/templates/TSearcher.stg", "TSearcher");
		for(RangeInfo info : infos) {
			TemplateUtil.renderTemplate(tmpl, info);
		}
	}


	public static final void generateRangeSearchers(RangeInfo... infos) throws IOException {
		ST tmpl = TemplateUtil.createTemplate("src/templates/TRangeSearcher.stg", "TRangeSearcher");
		for(RangeInfo info : infos) {
			TemplateUtil.renderTemplate(tmpl, info);
		}
	}


	public static final void generateRangeSearcherMutables(RangeInfo... infos) throws IOException {
		ST tmpl = TemplateUtil.createTemplate("src/templates/TRangeSearcherMutable.stg", "TRangeSearcherMutable");
		for(RangeInfo info : infos) {
			TemplateUtil.renderTemplate(tmpl, info);
		}
	}


	public static final void generateRangeSearcherMutableImpls(RangeInfo... infos) throws IOException {
		ST tmpl = TemplateUtil.createTemplate("src/templates/TRangeSearcherMutableImpl.stg", "TRangeSearcherMutableImpl");
		for(RangeInfo info : infos) {
			TemplateUtil.renderTemplate(tmpl, info);
		}
	}


	public static final void generateRangeSearcherSet(SearchSetInfo... infos) throws IOException {
		ST tmpl = TemplateUtil.createTemplate("src/templates/TSearchSet.stg", "TSearchSet");
		for(RangeInfo info : infos) {
			TemplateUtil.renderTemplate(tmpl, info);
		}
	}


	public static final void generateSearcherMutable(SearchSetInfo... infos) throws IOException {
		ST tmpl = TemplateUtil.createTemplate("src/templates/TSearcherMutable.stg", "TSearcherMutable");
		for(RangeInfo info : infos) {
			TemplateUtil.renderTemplate(tmpl, info);
		}
	}


	public static void main(String[] args) throws IOException {
		generateRangeClasses();
	}

}
