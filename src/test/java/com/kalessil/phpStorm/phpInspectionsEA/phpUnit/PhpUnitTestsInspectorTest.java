package com.kalessil.phpStorm.phpInspectionsEA.phpUnit;

import com.kalessil.phpStorm.phpInspectionsEA.PhpCodeInsightFixtureTestCase;
import com.kalessil.phpStorm.phpInspectionsEA.inspectors.phpUnit.PhpUnitTestsInspector;

final public class PhpUnitTestsInspectorTest extends PhpCodeInsightFixtureTestCase {
    public void testIfFindsCoversAnnotationPatterns() {
        PhpUnitTestsInspector inspector = new PhpUnitTestsInspector();
        inspector.WORKAROUND_COVERS_REFERENCES = false;

        myFixture.configureByFile("fixtures/phpUnit/covers-annotation.php");
        myFixture.enableInspections(inspector);
        myFixture.testHighlighting(true, false, true);
    }
    public void testIfFindsCoversAnnotationPatternsResolveRefsByOwn() {
        PhpUnitTestsInspector inspector = new PhpUnitTestsInspector();
        inspector.WORKAROUND_COVERS_REFERENCES = true;

        myFixture.configureByFile("fixtures/phpUnit/covers-annotation.php");
        myFixture.enableInspections(inspector);
        myFixture.testHighlighting(true, false, true);
    }

    public void testIfFindsTestAnnotationPatterns() {
        myFixture.configureByFile("fixtures/phpUnit/test-annotation.php");
        myFixture.enableInspections(PhpUnitTestsInspector.class);
        myFixture.testHighlighting(true, false, true);
    }

    public void testIfFindsAssetNullNotNullPatterns() {
        myFixture.configureByFile("fixtures/phpUnit/assert-null-not-null.php");
        myFixture.enableInspections(PhpUnitTestsInspector.class);
        myFixture.testHighlighting(true, false, true);
    }
    public void testIfFindsAssetTrueNotTruePatterns() {
        myFixture.configureByFile("fixtures/phpUnit/assert-true-not-true.php");
        myFixture.enableInspections(PhpUnitTestsInspector.class);
        myFixture.testHighlighting(true, false, true);
    }
    public void testIfFindsAssetFalseNotFalsePatterns() {
        myFixture.configureByFile("fixtures/phpUnit/assert-false-not-false.php");
        myFixture.enableInspections(PhpUnitTestsInspector.class);
        myFixture.testHighlighting(true, false, true);
    }

    public void testIfFindsAssertCountPatterns() {
        myFixture.configureByFile("fixtures/phpUnit/assert-count-not-count.php");
        myFixture.enableInspections(PhpUnitTestsInspector.class);
        myFixture.testHighlighting(true, false, true);
    }

    public void testIfFindsAssertStringEqualsFilePatterns() {
        myFixture.configureByFile("fixtures/phpUnit/assert-string-equals-file.php");
        myFixture.enableInspections(PhpUnitTestsInspector.class);
        myFixture.testHighlighting(true, false, true);
    }
}
