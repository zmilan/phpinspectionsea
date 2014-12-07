package com.kalessil.phpStorm.phpInspectionsEA.inspectors.earlyReturns;

import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.jetbrains.php.lang.psi.elements.GroupStatement;
import com.jetbrains.php.lang.psi.elements.If;
import com.jetbrains.php.lang.psi.elements.PhpPsiElement;
import com.kalessil.phpStorm.phpInspectionsEA.openApi.BasePhpElementVisitor;
import com.kalessil.phpStorm.phpInspectionsEA.openApi.BasePhpInspection;
import com.kalessil.phpStorm.phpInspectionsEA.utils.ExpressionSemanticUtil;
import org.jetbrains.annotations.NotNull;

public class NestedPositiveIfStatementsInspector extends BasePhpInspection {
    private static final String strProblemDescription = "If statement can be merged into parent one.";

    @NotNull
    public String getDisplayName() {
        return "API: nested positive ifs";
    }

    @NotNull
    public String getShortName() {
        return "NestedPositiveIfStatementsInspection";
    }

    @NotNull
    @Override
    public PsiElementVisitor buildVisitor(@NotNull final ProblemsHolder holder, boolean isOnTheFly) {
        return new BasePhpElementVisitor() {
            public void visitPhpIf(If ifStatement) {
                /** meet pre-conditions */
                PsiElement objParent = ifStatement.getParent();
                if (!(objParent instanceof GroupStatement)) {
                    return;
                }
                objParent = objParent.getParent();
                if (!(objParent instanceof If)) {
                    return;
                }

                /** ensure parent if has no alternative branches */
                if (ExpressionSemanticUtil.hasAlternativeBranches((If) objParent)) {
                    return;
                }


                /** ensure if has no alternative branches as well */
                if (ExpressionSemanticUtil.hasAlternativeBranches(ifStatement)) {
                    return;
                }


                /** ensure that if is single expression in group */
                int countStatementsInParent = 0;
                for (PsiElement objStatement : ifStatement.getParent().getChildren()) {
                    if (!(objStatement instanceof PhpPsiElement)) {
                        continue;
                    }

                    ++countStatementsInParent;
                }
                if (countStatementsInParent > 1) {
                    return;
                }


                /** point on the issues */
                PhpPsiElement objIfCondition = ifStatement.getCondition();
                if (objIfCondition == null) {
                    return;
                }
                holder.registerProblem(objIfCondition, strProblemDescription, ProblemHighlightType.GENERIC_ERROR_OR_WARNING);
            }
        };
    }
}