package br.com.fernandomachado.galaxy;


import br.com.fernandomachado.galaxy.dao.singleton.MaterialSingletonDAOTest;
import br.com.fernandomachado.galaxy.dao.singleton.TranslationSingletonDAOTest;
import br.com.fernandomachado.galaxy.model.numeral.roman.RomanNumeralFactoryTest;
import br.com.fernandomachado.galaxy.model.numeral.roman.RomanNumeralHelperTest;
import br.com.fernandomachado.galaxy.parser.regex.RegexParserUtilsTest;
import br.com.fernandomachado.galaxy.parser.regex.processor.VariableContainerTest;
import br.com.fernandomachado.galaxy.parser.regex.simple.SimpleRegexParserTest;
import br.com.fernandomachado.galaxy.parser.regex.simple.pattern.SimpleCommandTest;
import br.com.fernandomachado.galaxy.parser.regex.simple.pattern.SimpleTokenTest;
import br.com.fernandomachado.galaxy.parser.regex.simple.processor.SimpleMaterialEntryProcessorTest;
import br.com.fernandomachado.galaxy.parser.regex.simple.processor.SimpleMaterialQueryProcessorTest;
import br.com.fernandomachado.galaxy.parser.regex.simple.processor.SimpleTranslationEntryProcessorTest;
import br.com.fernandomachado.galaxy.parser.regex.simple.processor.SimpleTranslationQueryProcessorTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
        RomanNumeralFactoryTest.class,
        RomanNumeralHelperTest.class,

        MaterialSingletonDAOTest.class,
        TranslationSingletonDAOTest.class,

        VariableContainerTest.class,
        RegexParserUtilsTest.class,

        SimpleCommandTest.class,
        SimpleTokenTest.class,

        SimpleTranslationEntryProcessorTest.class,
        SimpleTranslationQueryProcessorTest.class,
        SimpleMaterialEntryProcessorTest.class,
        SimpleMaterialQueryProcessorTest.class,

        SimpleRegexParserTest.class
})
public class TestSuite {

}
