package com.ccf.wc.plugin

import com.android.build.api.transform.Context
import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.Transform
import com.android.build.api.transform.TransformException
import com.android.build.api.transform.TransformInput
import com.android.build.api.transform.TransformOutputProvider

class WcTransform extends Transform {

    final static String TAG = "WcTransform"

    public WcTransform() {
        println("${TAG}.init")
    }

    @Override
    String getName() {
        return "${TAG}"
    }

    @Override
    Set<QualifiedContent.ContentType> getInputTypes() {
        QualifiedContent.ContentType type = QualifiedContent.DefaultContentType.CLASSES
        return new HashSet<QualifiedContent.ContentType>(Arrays.asList(type))
    }

    @Override
    Set<QualifiedContent.Scope> getScopes() {
        return new HashSet<QualifiedContent.Scope>(
                Arrays.asList(QualifiedContent.Scope.PROJECT,
                        QualifiedContent.Scope.SUB_PROJECTS,
                        QualifiedContent.Scope.EXTERNAL_LIBRARIES,
                ))
    }

    @Override
    boolean isIncremental() {
        return false
    }

    @Override
    void transform(Context context, Collection<TransformInput> inputs,
                   Collection<TransformInput> referencedInputs,
                   TransformOutputProvider outputProvider,
                   boolean isIncremental) throws IOException, TransformException, InterruptedException {
        println("${TAG}.transform")
        for (TransformInput input : inputs) {
            println("inputs: ${input}")
        }
        for (TransformInput input : referencedInputs) {
            println("referencedInputs: ${input}")
        }
    }
    // :app:transformClassesWithWcTransformForRelease
    // WcTransform.transform
}
